package com.runner.demo.spark_infinispan

import java.util.Properties

import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.{SparkContext, SparkConf}

import org.infinispan.client.hotrod.RemoteCache
import org.infinispan.client.hotrod.RemoteCacheManager
import org.infinispan.client.hotrod.configuration.Configuration
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder
import org.infinispan.spark.rdd.InfinispanRDD

/**
 * Created by Runner on 2015. 12. 11..
 */
object SimpleSparkJob {

  val infinispanAddress = "192.168.215.239"

  def main(args: Array[String]): Unit = {
    // Adjust log levels
    Logger.getLogger("org").setLevel(Level.WARN)

    // Create the remote cache manager
    val build = new ConfigurationBuilder().addServer().host(infinispanAddress).build()
    val remoteCacheManager = new RemoteCacheManager(build)

    // Obtain the remote cache
    val cache :RemoteCache[Integer, Integer] = remoteCacheManager.getCache("my-cache")

    // Add some data
    cache.put(1, 1)
    cache.put(2, 2)
    cache.put(3, 3)
    cache.put(4, 4)

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)


    val config = new Properties
    config.put("infinispan.rdd.cacheName","my-cache")
    config.put("infinispan.client.hotrod.server_list","192.168.215.239:11222")
    val infinispanRDD = new InfinispanRDD[Integer, Integer](sc, configuration = config)


    // Convert RDD to RDD of doubles
    val result = infinispanRDD.values.reduce(_+_)

    println(result)
  }
}

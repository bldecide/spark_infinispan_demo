package com.runner.demo.spark_infinispan.simple

import java.util.Properties

import org.apache.log4j.{Level, Logger}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.infinispan.spark.stream._

/**
 * Created by Runner on 2015. 12. 11..
 */
object CreatingDStream {

  def main(args: Array[String]): Unit = {
    // Adjust log levels
    Logger.getLogger("org").setLevel(Level.WARN)

    val conf = new SparkConf().setAppName("CreatingDStream").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val config = new Properties
    config.put("infinispan.rdd.cacheName","my-cache")
    config.put("infinispan.client.hotrod.server_list","192.168.215.239:11222")

    val ssc = new StreamingContext(sc, Seconds(1))
    val inputDStream = new InfinispanInputDStream(ssc, StorageLevel.MEMORY_ONLY, config)

    inputDStream.foreachRDD((v1, v2) => {
      println (v1.count())
    });

    ssc.start()
    ssc.awaitTermination()

  }
}

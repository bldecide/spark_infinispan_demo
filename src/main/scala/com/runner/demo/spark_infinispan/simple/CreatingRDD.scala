package com.runner.demo.spark_infinispan.simple

import java.util.Properties

import org.apache.spark.{SparkConf, SparkContext}
import org.infinispan.spark.rdd._

/**
 * Created by Runner on 2015. 12. 11..
 */
object CreatingRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val config = new Properties
    config.put("infinispan.rdd.cacheName","default")
    config.put("infinispan.client.hotrod.server_list","172.29.238.69:11222")

    val infinispanRDD = new InfinispanRDD[Integer, Integer](sc, configuration = config)

    println(infinispanRDD.values.count())

  }
}

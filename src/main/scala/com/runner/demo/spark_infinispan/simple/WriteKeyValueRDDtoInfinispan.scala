package com.runner.demo.spark_infinispan.simple

import java.util.Properties

import org.apache.spark.{SparkConf, SparkContext}
import org.infinispan.spark._


/**
 * Created by Runner on 2015. 12. 11..
 */
object WriteKeyValueRDDtoInfinispan {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val config = new Properties
    config.put("infinispan.rdd.cacheName","default")
    config.put("infinispan.client.hotrod.server_list","192.168.215.239:11222")

    val simpleRdd = sc.parallelize((1 to 1000)).zipWithIndex()
    simpleRdd.writeToInfinispan(config)
  }
}

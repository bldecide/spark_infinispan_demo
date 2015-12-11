package com.runner.demo.spark_infinispan

import java.util.Properties

import org.apache.spark.{SparkConf, SparkContext}
import org.infinispan.spark._

/**
  * Created by Runner on 2015. 12. 11..
  */
object WordCountInfinispan {
   def main(args: Array[String]): Unit = {
     val conf = new SparkConf().setAppName("WordCountInfinispan").setMaster("local[*]")
     val spark = new SparkContext(conf)
 
     val textFile = spark.textFile("/Users/Runner/demo/datasets/text/50465-0.txt")
     val counts = textFile.flatMap(line => line.split(" "))
       .map(word => (word, 1))
       .reduceByKey(_ + _)

//     counts.saveAsTextFile("/Users/Runner/demo/datasets/Activityrecognitionexp/result.csv")

     val config = new Properties
     config.put("infinispan.rdd.cacheName","default")
     config.put("infinispan.client.hotrod.server_list","192.168.215.239:11222")

     counts.writeToInfinispan(config)
   }
 }

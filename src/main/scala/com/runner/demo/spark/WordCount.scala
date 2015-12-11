package com.runner.demo.spark

import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by Runner on 2015. 12. 11..
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    val spark = new SparkContext(conf)

    val textFile = spark.textFile("/Users/Runner/demo/datasets/text/50465-0.txt")
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.saveAsTextFile("/Users/Runner/demo/datasets/text/result")
  }
}

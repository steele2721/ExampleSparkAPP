package com.steeleEnterprise.robert.spark.utils

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

trait SparkSuite {

  def appName: String

  val config = new SparkConf()
    .setMaster("local")
    .setAppName("DataAnalyticStack")

//  val spark: SparkSession = SparkSession.builder()
//    .master("local[*]")
//    .getOrCreate()

}

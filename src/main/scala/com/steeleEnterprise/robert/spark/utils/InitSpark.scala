package com.steeleEnterprise.robert.spark.utils

import org.apache.log4j.{Level, LogManager, Logger}
import org.apache.spark.sql.SparkSession

//trait for extending spark jobs

trait InitSpark {

  val spark: SparkSession = SparkSession.builder()
    .master("local[*]")
    .appName("spark-app")
    .getOrCreate()

  val sc = spark.sparkContext

  val sql = spark.sqlContext

  private def init(): Unit = {
    sc.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)
    LogManager.getRootLogger.setLevel(Level.ERROR)
  }

  def close(): Unit = {
    spark.close()
  }

  init()
}

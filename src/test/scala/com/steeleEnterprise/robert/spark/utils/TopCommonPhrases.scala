package com.steeleEnterprise.robert.spark.utils

import org.apache.spark.rdd.RDD

object TopCommonPhrases extends InitSpark {

  def findTopPhrases(inputText: RDD[String]): RDD[(String, Int)] = {
    cleanWords(inputText)
      .filter(isWord(_))
      .map { word =>
        (word, 1)
      }.reduceByKey((a, b) => a + b)
  }

  private def cleanWords(inputText: RDD[String]): RDD[String] = {
    val cleanedText = inputText.map { word =>
      word.trim
    }
    cleanedText
  }

  def isWord(word: String): Boolean = {
    val regex = "[a-z]+"
    word.matches(regex)
  }

}

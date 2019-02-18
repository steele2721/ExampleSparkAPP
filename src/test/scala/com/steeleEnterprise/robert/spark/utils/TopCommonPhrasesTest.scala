package com.steeleEnterprise.robert.spark.utils

import org.apache.spark.SparkContext
import org.junit.{Assert, Test}

class TopCommonPhrasesTest extends SparkSuite {

  override def appName: String = "top-words-in-file"

  val sparkContext: SparkContext = SparkContext.getOrCreate(config)

  @Test
  def logicFindsTopWords_emptyRDD(): Unit = {
    val textUnderTest = sparkContext.emptyRDD[String]
    val topCommonPhrases = TopCommonPhrases.findTopPhrases(textUnderTest).collect().toList
    Assert.assertEquals(List.empty, topCommonPhrases)
  }

  @Test
  def findTopPhrasesMethod_RddOfEmptyString(): Unit = {
    val textUnderTest = sparkContext.parallelize(Seq(""))
    val topCommonPhrases = TopCommonPhrases.findTopPhrases(textUnderTest).collect().toList
    Assert.assertEquals(List.empty, topCommonPhrases)
  }

  @Test
  def findTopPhrasesMethod_ReadSingleWord(): Unit = {
    val textUnderTest = sparkContext.parallelize(Seq("text"))
    val topCommonPhrases = TopCommonPhrases.findTopPhrases(textUnderTest).collect().toList
    Assert.assertEquals(List(("text",1)), topCommonPhrases)
  }

  @Test
  def findTopPhrasesMethod_MalFormed_UntrimmedWord(): Unit = {
    val textUnderTest = sparkContext.parallelize(Seq("  text"))
    val topCommonPhrases = TopCommonPhrases.findTopPhrases(textUnderTest).collect().toList
    Assert.assertEquals(List(("text",1)), topCommonPhrases)
  }

  @Test
  def findTopPhrasesMethod__Malformed_NonAlpha(): Unit = {
    val textUnderTest = sparkContext.parallelize(Seq("tex$t"))
    val topCommonPhrases = TopCommonPhrases.findTopPhrases(textUnderTest).collect().toList
    Assert.assertEquals(List.empty[String], topCommonPhrases)
  }

  @Test
  def findTopPhrasesMethod__MultipleWords_ProperlyFormed(): Unit = {
    val textUnderTest = sparkContext.parallelize(Seq("text", "assemble"))
    val topCommonPhrases = TopCommonPhrases.findTopPhrases(textUnderTest).collect().toList
    Assert.assertEquals(List(("text",1), ("assemble",1)), topCommonPhrases)
  }

  @Test
  def findTopPhrasesMethod_MultipleWords_Malformed_And_ProperlyFormed(): Unit = {
    val textUnderTest = sparkContext.parallelize(Seq("text", "as354semble"))
    val topCommonPhrases = TopCommonPhrases.findTopPhrases(textUnderTest).collect().toList
    Assert.assertEquals(List(("text", 1)), topCommonPhrases)
  }

  @Test
  def findTopPhrasesMethod_
}

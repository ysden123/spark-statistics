/*
 * Copyright (c) 2020. StulSoft
 */

package com.stulsoft.basic.statistics.examples

import com.stulsoft.basic.statistics.Utils
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.stat.{MultivariateStatisticalSummary, Statistics}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author Yuriy Stul
 */
object SummaryStatisticsExample2 extends App {
  val conf = new SparkConf().setAppName("SummaryStatisticsExample")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)

  def test1(): Unit = {
    println("==>test1")
    val observations = sc.parallelize(
      Seq(
        Vectors.dense(1.0, 10.0, 100.0),
        Vectors.dense(2.0, 20.0, 200.0),
        Vectors.dense(3.0, 30.0, 300.0)
      )
    )
    calculateStatistics(observations)
  }

  def test2(): Unit = {
    println("==>test2")
    val observations = sc.parallelize(
      Seq(
        Vectors.dense(1.0),
        Vectors.dense(2.0),
        Vectors.dense(3.0)
      )
    )
    calculateStatistics(observations)
  }

  def test3(): Unit = {
    println("==>test3")
    val observations = sc.parallelize(Utils.toVectors(Seq(1.0, 2.0, 3.0)))
    calculateStatistics(observations)
  }

  def calculateStatistics(data: RDD[Vector]): Unit = {
    // Compute column summary statistics.
    val summary: MultivariateStatisticalSummary = Statistics.colStats(data)
    println(s"summary.mean: ${summary.mean}") // a dense vector containing the mean value for each column
    println(s"summary.variance: ${summary.variance}") // column-wise variance
    println(s"summary.numNonzeros: ${summary.numNonzeros}") // number of nonzeros in each column
    println(s"summary.max: ${summary.max}") // max for each column
    println(s"summary.min: ${summary.min}") // max for each column
  }

  test1()
  test2()
  test3()
  sc.stop()
}

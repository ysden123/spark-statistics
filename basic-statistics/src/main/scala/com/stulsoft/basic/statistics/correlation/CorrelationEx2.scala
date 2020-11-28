/*
 * Copyright (c) 2020. StulSoft
 */

package com.stulsoft.basic.statistics.correlation

import com.typesafe.scalalogging.StrictLogging
import org.apache.spark.ml.linalg.{Matrix, Vector, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.{Row, SparkSession}

/**
 * @author Yuriy Stul
 */
object CorrelationEx2 extends App with StrictLogging  {
  logger.info("Building spark session ...")
  val sparkSession = SparkSession
    .builder()
    .appName("Correlation exercise 2")
    .master("local[*]")
    .getOrCreate()

  import sparkSession.implicits._

  def calculateCorrelationMatrix(data:Seq[Vector]): Unit ={
    println(s"data:")
    data.foreach(v => println(v.toArray.mkString(", ")))
    println()
    val df = data.map(Tuple1.apply).toDF("features")

    val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
    println(s"Pearson correlation matrix:\n $coeff1")
    println()

    val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
    println(s"Spearman correlation matrix:\n $coeff2")
  }

  def test1(): Unit ={
    println("==>test1")
    val data = Seq(
      Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
      Vectors.dense(4.0, 5.0, 0.0, 3.0),
      Vectors.dense(6.0, 7.0, 0.0, 8.0),
      Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
    )
    calculateCorrelationMatrix(data)
  }

  def test2(): Unit ={
    println("==>test2")
    val data = Seq(
      Vectors.dense(1.0, 2.0),
      Vectors.dense(2.0, 4.0),
      Vectors.dense(3.0, 6.0),
    )
    calculateCorrelationMatrix(data)
  }

  def test3(): Unit ={
    println("==>test3")
    val data = Seq(
      Vectors.dense(1.0, 2.0),
      Vectors.dense(2.0, -2.0),
      Vectors.dense(3.0, 0.0),
    )
    calculateCorrelationMatrix(data)
  }

  def test4(): Unit ={
    println("==>test4")
    val data = Seq(
      Vectors.dense(1.0, 3.0),
      Vectors.dense(2.0, 2.0),
      Vectors.dense(3.0, 1.0),
    )
    calculateCorrelationMatrix(data)
  }

  test1()
  test2()
  test3()
  test4()

  sparkSession.stop()
}

/*
 * Copyright (c) 2020. StulSoft
 */

package com.stulsoft.basic.statistics.correlation

import com.typesafe.scalalogging.StrictLogging
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.{Row, SparkSession}

/**
 * @see <a href="https://spark.apache.org/docs/latest/ml-statistics.html#basic-statistics">Basic statistics</a>
 * @author Yuriy Stul
 */
object CorrelationEx1 extends App with StrictLogging {
  logger.info("Building spark session ...")
  val sparkSession = SparkSession
    .builder()
    .appName("Correlation exercise 1")
    .master("local[*]")
    .getOrCreate()

  import sparkSession.implicits._

  logger.info("Creating data...")
  val data = Seq(
    Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
    Vectors.dense(4.0, 5.0, 0.0, 3.0),
    Vectors.dense(6.0, 7.0, 0.0, 8.0),
    Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
  )
  println(s"data:")
  data.foreach(v => println(v.toArray.mkString(", ")))

  val df = data.map(Tuple1.apply).toDF("features")

  logger.info("Calculating correlation by default (pearson) method ...")
  val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
  logger.info("Completed calculating correlation by default (pearson) method ...")
  println(s"Pearson correlation matrix:\n $coeff1")

  logger.info("Calculating correlation by spearman method ...")
  val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
  logger.info("Completed calculating correlation by spearman method ...")
  println(s"Spearman correlation matrix:\n $coeff2")

  sparkSession.stop()
}

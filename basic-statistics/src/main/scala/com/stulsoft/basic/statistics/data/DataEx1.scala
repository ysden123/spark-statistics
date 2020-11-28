/*
 * Copyright (c) 2020. StulSoft
 */

package com.stulsoft.basic.statistics.data

import org.apache.spark.ml.linalg.Vectors

/**
 * @author Yuriy Stul
 */
object DataEx1 extends App {
  val data = Seq(
    Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
    Vectors.dense(4.0, 5.0, 0.0, 3.0),
    Vectors.dense(6.0, 7.0, 0.0, 8.0),
    Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
  )
  println("data:")
  data.foreach(v => println(v.toArray.mkString(", ")))
}

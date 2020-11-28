/*
 * Copyright (c) 2020. StulSoft
 */

package com.stulsoft.basic.statistics.elements

import org.apache.spark.ml.linalg.Vectors

/**
 * @author Yuriy Stul
 */
object Sparse extends App {
  val sparse1 = Vectors.sparse(4, Seq((0, 1.0), (3, -2.0)))
  println(s"sparse1: $sparse1")
  println(s"sparse1.toArray: ${sparse1.toArray.mkString("Array(", ", ", ")")}")

  println()
  val sparse2 = Vectors.sparse(5, Seq((0, 1.0), (4, -2.0)))
  println(s"sparse2: $sparse2")
  println(s"sparse2.toArray: ${sparse2.toArray.mkString("Array(", ", ", ")")}")
}

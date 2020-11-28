/*
 * Copyright (c) 2020. StulSoft
 */

package com.stulsoft.basic.statistics

import org.apache.spark.mllib.linalg.{Vector, Vectors}

/**
 * @author Yuriy Stul
 */
object Utils {
  def toVectors(data: Seq[Double]): Seq[Vector] = data.map(v => Vectors.dense(v))
}

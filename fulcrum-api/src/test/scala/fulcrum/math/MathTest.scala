/*
 * MathTest.scala
 * 
 * Copyright (c) 2013 Lonnie Pryor III
 * 
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fulcrum.math

import org.junit.Assert._
import org.junit.Test

/**
 * Test case for {@link fulcrum.math}.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
class MathTest {

  val testData = Array[Double](0.0, 1.0, 2.0, 3.0)

  /**
   * The declared constants provide the accurate values of various common
   * mathematical constants.
   */
  @Test
  def providesValuesForCommonMathematicalConstants() {
    assertEquals(scala.math.E.toFloat, E, 0.001f)
    assertEquals(scala.math.Pi.toFloat, Pi, 0.001f)
    assertEquals((scala.math.Pi / 2.0).toFloat, HalfPi, 0.001f)
    assertEquals((scala.math.Pi / 4.0).toFloat, QuarterPi, 0.001f)
    assertEquals((scala.math.Pi * 2.0).toFloat, TwoPi, 0.001f)
  }

  /** Many float-specific common mathematical operations are provided. */
  @Test
  def providesCommonMathematicalOperations() {
    val max = testData(testData.length - 1)
    for (data <- testData) {
      assertEquals(scala.math.sqrt(data).toFloat, data.toFloat.sqrt, 0.001f)
      assertEquals(scala.math.pow(data, 2.4).toFloat, data.toFloat.pow(2.4f), 0.001f)
      assertEquals(scala.math.sin(data).toFloat, data.toFloat.sin, 0.001f)
      assertEquals(scala.math.cos(data).toFloat, data.toFloat.cos, 0.001f)
      assertEquals(scala.math.acos(data / max).toFloat, (data / max).toFloat.acos, 0.001f)
    }
  }

  /** Supports a number of utility functions for working with floats. */
  @Test
  def providesCommonUtilityFunctions() {
    val min = 1.0
    val max = 2.0
    for (data <- testData)
      assertEquals(scala.math.max(min, scala.math.min(data, max)).toFloat,
        data.toFloat.clamp(min.toFloat, max.toFloat), 0.00001f)
  }

}
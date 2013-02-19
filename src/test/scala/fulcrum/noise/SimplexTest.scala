/*
 * SimplexTest.scala
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
package fulcrum.noise

import org.junit.Assert._
import org.junit.Test

/**
 * Test case for {@link Simplex}.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
class SimplexTest {

  /** The simplex class generates smooth continuous noise over unit vectors. */
  @Test
  def generatesSmoothContinuousNoise() {
    val simplex = Simplex(0x01234567)
    val step = 1f / 100f
    val bound = 1f / 8f
    var previous = simplex(0f, 0f)
    var i = step
    while (i < 10) {
      val current = simplex(i, i)
      assertTrue(Math.abs(current - previous) <= bound)
      previous = current
      i += step
    }
    previous = simplex(0f, 0f, 0f)
    i = step
    while (i < 10) {
      val current = simplex(i, i, i)
      assertTrue(Math.abs(current - previous) <= bound)
      previous = current
      i += step
    }
    previous = simplex(0f, 0f, 0f, 0f)
    i = step
    while (i < 10) {
      val current = simplex(i, i, i, i)
      assertTrue(Math.abs(current - previous) <= bound)
      previous = current
      i += step
    }
  }

}
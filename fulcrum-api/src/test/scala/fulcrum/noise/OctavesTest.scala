/*
 * OctavesTest.scala
 * 
 * Copyright (c) 2013 Lonnie Pryor III
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
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

import org.junit.Test
import org.junit.Assert._

/**
 * Test case for {@link Octaves}.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
class OctavesTest {

  /**
   * Octaves generates noise by combining the output of multiple other noise
   * generators.
   */
  @Test
  def combinesMultipleNoiseSources() {
    val low = new MockNoise(1f / 4)
    val high = new MockNoise(3f / 4f)
    assertEquals(0.4166f, Octaves(1f / 2f, low.asInstanceOf[Noise2], high)(0f, 0f), 0.001f)
    assertEquals(0.4166f, Octaves(1f / 2f, low.asInstanceOf[Noise3], high)(0f, 0f, 0f), 0.001f)
    assertEquals(0.4166f, Octaves(1f / 2f, low.asInstanceOf[Noise4], high)(0f, 0f, 0f, 0f), 0.001f)
  }

  /**
   * A noise implementation that returns a single value.
   *
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private class MockNoise(value: Float) extends Noise2 with Noise3 with Noise4 {

    /* @see Noise2#apply(float, float) */
    override def apply(x: Float, y: Float) = value

    /* @see Noise3#apply(float, float, float) */
    override def apply(x: Float, y: Float, z: Float) = value

    /* @see Noise4#apply(float, float, float, float) */
    override def apply(x: Float, y: Float, z: Float, w: Float) = value

  }

}
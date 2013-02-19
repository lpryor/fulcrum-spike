/*
 * OctavesTest.java
 * 
 * Copyright (c) 2012 Lonnie Pryor III
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
package fulcrum.noise;

import junit.framework.TestCase;

/**
 * Test case for {@link OctavesOld}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class OctavesOldTest extends TestCase {

  /**
   * Octaves generates noise by combining the output of multiple other noise
   * generators.
   */
  public void testCombinesMultipleNoiseSources() {
    MockNoise low = new MockNoise(1f / 4), high = new MockNoise(3f / 4f);
    OctavesOld._2D o2 = OctavesOld.create(1f / 2f, (NoiseOld._2D) low, high);
    assertEquals(0.4166f, o2.sample(0f, 0f), 0.001f);
    OctavesOld._3D o3 = OctavesOld.create(1f / 2f, (NoiseOld._3D) low, high);
    assertEquals(0.4166f, o3.sample(0f, 0f, 0f), 0.001f);
    OctavesOld._4D o4 = OctavesOld.create(1f / 2f, (NoiseOld._4D) low, high);
    assertEquals(0.4166f, o4.sample(0f, 0f, 0f, 0f), 0.001f);
  }

  /**
   * A noise implementation that returns a single value.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private static class MockNoise implements NoiseOld._2D, NoiseOld._3D, NoiseOld._4D {

    /** The value to return. */
    final float value;

    /** Creates a new mock noise. */
    MockNoise(float value) {
      this.value = value;
    }

    /* @see Noise._2D#sample(float, float) */
    @Override
    public float sample(float x, float y) {
      return value;
    }

    /* @see Noise._3D#sample(float, float, float) */
    @Override
    public float sample(float x, float y, float z) {
      return value;
    }

    /* @see Noise._4D#sample(float, float, float, float) */
    @Override
    public float sample(float x, float y, float z, float w) {
      return value;
    }

  }

}

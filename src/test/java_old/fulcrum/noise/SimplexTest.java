/*
 * SimplexTest.java
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
 * Test case for {@link Simplex}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class SimplexTest extends TestCase {

  /** The simplex class generates smooth continuous noise over unit vectors. */
  public void testGeneratesSmoothContinuousNoise() {
    Simplex simplex = new Simplex(0x01234567);
    float step = 1f / 100f, bound = 1f / 8f, previous = simplex.sample(0f, 0f);
    for (float i = step; i < 10; i += step) {
      float current = simplex.sample(i, i);
      assertTrue(Math.abs(current - previous) <= bound);
      previous = current;
    }
    previous = simplex.sample(0f, 0f, 0f);
    for (float i = step; i < 10; i += step) {
      float current = simplex.sample(i, i, i);
      assertTrue(Math.abs(current - previous) <= bound);
      previous = current;
    }
    previous = simplex.sample(0f, 0f, 0f, 0f);
    for (float i = step; i < 10; i += step) {
      float current = simplex.sample(i, i, i, i);
      assertTrue(Math.abs(current - previous) <= bound);
      previous = current;
    }
  }

}

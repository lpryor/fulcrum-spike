/*
 * NumbersTest.java
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
package fulcrum.math;

import junit.framework.TestCase;

/**
 * Test case for {@link Numbers}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class NumbersTest extends TestCase {

  private static final double[] TEST_DATA = { 0.0, 1.0, 2.0, 3.0 };

  /**
   * The declared constants provide the accurate values of various common
   * mathematical constants.
   */
  public void testProvidesValuesForCommonMathematicalConstants() {
    assertEquals((float) Math.E, Numbers.E, 0.001f);
    assertEquals((float) Math.PI, Numbers.PI, 0.001f);
    assertEquals((float) (Math.PI / 2.0), Numbers.HALF_PI, 0.001f);
    assertEquals((float) (Math.PI / 4.0), Numbers.QUARTER_PI, 0.001f);
    assertEquals((float) (Math.PI * 2.0), Numbers.TWO_PI, 0.001f);
    assertEquals((float) ((1.0 + Math.sqrt(5.0)) / 2.0), Numbers.GOLDEN_RATIO, 0.001f);
  }

  /** Many float-specific common mathematical operations are provided. */
  public void testProvidesCommonMathematicalOperations() {
    double max = TEST_DATA[TEST_DATA.length - 1];
    for (double data : TEST_DATA) {
      assertEquals((float) Math.sqrt(data), Numbers.sqrt((float) data), 0.001f);
      assertEquals((float) Math.pow(data, 2.4), Numbers.pow((float) data, 2.4f), 0.001f);
      assertEquals((float) Math.sin(data), Numbers.sin((float) data), 0.001f);
      assertEquals((float) Math.cos(data), Numbers.cos((float) data), 0.001f);
      assertEquals((float) Math.acos(data / max), Numbers.acos((float) (data / max)), 0.001f);
    }
  }

  /** Enables the conversion of radians to degrees and back using floats. */
  public void testProvidesGeometricConversionFunctions() {
    for (double data : TEST_DATA) {
      double dDegrees = Math.toDegrees(data);
      float fDegrees = Numbers.toDegrees((float) data);
      assertEquals((float) dDegrees, fDegrees, 0.001f);
      assertEquals((float) Math.toRadians(dDegrees), Numbers.toRadians(fDegrees), 0.001f);
    }
  }

  /** Supports a number of utility functions for working with floats. */
  public void testProvidesCommonUtilityFunctions() {
    double min = 1.0, max = 2.0;
    for (double data : TEST_DATA) {
      assertEquals(data, Numbers.floor((float) data + 0.3f), 0.00001f);
      assertEquals(data, Numbers.ceil((float) data - 0.3f), 0.00001f);
      assertEquals((float) Math.max(min, Math.min(data, max)), Numbers.clamp((float) data, (float) min, (float) max),
          0.00001f);
    }
  }

}

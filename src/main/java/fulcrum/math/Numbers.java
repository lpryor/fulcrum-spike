/*
 * Numbers.java
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

/**
 * Utilities for working with 32-bit numbers.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class Numbers {

  /** @see Math#E. */
  public static final float E = (float) Math.E;
  /** @see Math#PI. */
  public static final float PI = (float) Math.PI;
  /** @see Math#PI. */
  public static final float HALF_PI = PI / 2f;
  /** @see Math#PI. */
  public static final float QUARTER_PI = PI / 4f;
  /** @see Math#PI. */
  public static final float TWO_PI = PI * 2f;
  /** The golden ratio. */
  public static final float GOLDEN_RATIO = (1f + sqrt(5f)) / 2f;

  /** Square root function. */
  public static float sqrt(float f) {
    return (float) Math.sqrt(f);
  }

  /** Power function. */
  public static float pow(float f, float e) {
    return (float) Math.pow(f, e);
  }

  /** Sine function. */
  public static float sin(float f) {
    return (float) Math.sin(f);
  }

  /** Cosine function. */
  public static float cos(float f) {
    return (float) Math.cos(f);
  }

  /** Arccosine function. */
  public static float acos(float f) {
    return (float) Math.acos(f);
  }

  /** Converts an angle in radians to degrees. */
  public static float toDegrees(float radians) {
    return (float) Math.toDegrees(radians);
  }

  /** Converts an angle in degrees to radians. */
  public static float toRadians(float degrees) {
    return (float) Math.toRadians(degrees);
  }

  /** Clamp function. */
  public static float clamp(float f, float min, float max) {
    return f < min ? min : f > max ? max : f;
  }

}

/*
 * GLPrecision.java
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
package fulcrum.opengl;

/**
 * Represents the range and precision for different shader numeric formats.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class GLPrecision {

  /** The minimum range value of the format. */
  private final int rangeMin;
  /** The maximum range value of the format. */
  private final int rangeMax;
  /** The precision of the format. */
  private final int precision;

  /** Creates a new shader precision format. */
  public GLPrecision(int rangeMin, int rangeMax, int precision) {
    this.rangeMin = rangeMin;
    this.rangeMax = rangeMax;
    this.precision = precision;
  }

  /** Returns the minimum range value of the format. */
  public int rangeMin() {
    return rangeMin;
  }

  /** Returns the maximum range value of the format. */
  public int rangeMax() {
    return rangeMax;
  }

  /** Returns the precision of the format. */
  public int precision() {
    return precision;
  }

}

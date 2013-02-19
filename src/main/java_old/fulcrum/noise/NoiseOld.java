/*
 * Noise.java
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

/**
 * Marker interface for object that can generate continuous, multi-dimensional
 * noise.
 * 
 * @author Lonnie Pryor III
 */
public interface NoiseOld {

  /**
   * A two-dimensional noise generator.
   * 
   * @author Lonnie Pryor III
   */
  interface _2D extends NoiseOld {

    /** Returns a sample in the range [-1, 1] for the specified coordinates. */
    float sample(float x, float y);

  }

  /**
   * A three-dimensional noise generator.
   * 
   * @author Lonnie Pryor III
   */
  interface _3D extends NoiseOld {

    /** Returns a sample in the range [-1, 1] for the specified coordinates. */
    float sample(float x, float y, float z);

  }

  /**
   * A four-dimensional noise generator.
   * 
   * @author Lonnie Pryor III
   */
  interface _4D extends NoiseOld {

    /** Returns a sample in the range [-1, 1] for the specified coordinates. */
    float sample(float x, float y, float z, float w);

  }

}

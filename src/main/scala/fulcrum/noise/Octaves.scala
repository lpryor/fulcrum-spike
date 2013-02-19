/*
 * Octaves.scala
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

import fulcrum.math._

/**
 * A factory for noise generators that merge the samples from other generators.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
object Octaves {

  /** Creates a new two-dimensional multi-octave noise generator. */
  def apply(persistence: Float, octaves: Noise2*): Noise2 = {
    require(octaves.nonEmpty, "octaves must not be empty")
    new Octaves2(persistence, octaves.toList)
  }

  /** Creates a new three-dimensional multi-octave noise generator. */
  def apply(persistence: Float, octaves: Noise3*): Noise3 = {
    require(octaves.nonEmpty, "octaves must not be empty")
    new Octaves3(persistence, octaves.toList)
  }

  /** Creates a new four-dimensional multi-octave noise generator. */
  def apply(persistence: Float, octaves: Noise4*): Noise4 = {
    require(octaves.nonEmpty, "octaves must not be empty")
    new Octaves4(persistence, octaves.toList)
  }

}

/**
 * Definition of an object that can generate continuous two-dimensional
 * noise by merging the samples from other generators.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
private final class Octaves2(persistence: Float, octaves: List[Noise2]) extends Noise2 {

  /* @see Noise2#apply(float, float) */
  override def apply(x: Float, y: Float) = {
    var amplitude = 1f
    var total = 0f
    var deniminator = 0f
    for (octave <- octaves) {
      deniminator += amplitude
      total += amplitude * octave(x, y)
      amplitude *= persistence
    }
    (total / deniminator).clamp(-1f, 1f)
  }

}

/**
 * Definition of an object that can generate continuous three-dimensional
 * noise by merging the samples from other generators.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
private final class Octaves3(persistence: Float, octaves: List[Noise3]) extends Noise3 {

  /* @see Noise3#apply(float, float, float) */
  override def apply(x: Float, y: Float, z: Float) = {
    var amplitude = 1f
    var total = 0f
    var deniminator = 0f
    for (octave <- octaves) {
      deniminator += amplitude
      total += amplitude * octave(x, y, z)
      amplitude *= persistence
    }
    (total / deniminator).clamp(-1f, 1f)
  }

}

/**
 * Definition of an object that can generate continuous four-dimensional
 * noise by merging the samples from other generators.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
private final class Octaves4(persistence: Float, octaves: List[Noise4]) extends Noise4 {

  /* @see Noise4#apply(float, float, float, float) */
  override def apply(x: Float, y: Float, z: Float, w: Float) = {
    var amplitude = 1f
    var total = 0f
    var deniminator = 0f
    for (octave <- octaves) {
      deniminator += amplitude
      total += amplitude * octave(x, y, z, w)
      amplitude *= persistence
    }
    (total / deniminator).clamp(-1f, 1f)
  }

}
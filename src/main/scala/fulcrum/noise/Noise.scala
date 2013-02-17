/*
 * Noise.scala
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

/**
 * Marker interface for objects that can generate continuous multi-dimensional
 * noise.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
sealed trait Noise

/**
 * Declaration of an object that can generate continuous two-dimensional
 * noise.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
trait Noise2 extends Noise {

  /** Returns a sample in the range [-1, 1] for the specified coordinates. */
  def apply(x: Float, y: Float): Float

}

/**
 * Declaration of an object that can generate continuous three-dimensional
 * noise.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
trait Noise3 extends Noise {

  /** Returns a sample in the range [-1, 1] for the specified coordinates. */
  def apply(x: Float, y: Float, z: Float): Float

}

/**
 * Declaration of an object that can generate continuous four-dimensional
 * noise.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
trait Noise4 extends Noise {

  /** Returns a sample in the range [-1, 1] for the specified coordinates. */
  def apply(x: Float, y: Float, z: Float, w: Float): Float

}
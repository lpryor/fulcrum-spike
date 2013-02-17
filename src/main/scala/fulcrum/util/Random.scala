/*
 * Random.scala
 * 
 * Copyright (c) 2013 Lonnie Pryor III
 * 
 * Portions of this file are licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in 
 * compliance with the License. You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * The code in this file is based on version 20 of the MersenneTwisterFast 
 * implementation by Sean Luke and Michael Lecuyer available at:
 * http://www.cs.gmu.edu/~sean/research/mersenne/MersenneTwisterFast.java
 * The following copyright notice and licensing information is reproduced 
 * from the original file:
 *
 * Copyright (c) 2003 by Sean Luke.
 * Portions copyright (c) 1993 by Michael Lecuyer.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  - Redistributions of source code must retain the above copyright notice, 
 * this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution.
 *  - Neither the name of the copyright owners, their employers, nor the 
 * names of its contributors may be used to endorse or promote products 
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package fulcrum.util

/**
 * A random number generator based on the mersenne twister.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class Random private (seed: Int) {

  import Random._

  /** The array for the state vector. */
  private val stateVector = new Array[Int](N)
  /** Index into the state vector. */
  private var stateIndex = 1

  stateVector(0) = seed
  while (stateIndex < N) {
    val tmp = stateVector(stateIndex - 1)
    stateVector(stateIndex) = (1812433253 * (tmp ^ (tmp >>> 30)) + stateIndex)
    stateIndex += 1
  }

  /** Returns the next boolean value. */
  def nextBoolean(): Boolean = (next() >>> 31) != 0

  /** Returns the next byte value. */
  def nextByte(): Byte = (next() >>> 24) toByte

  /** Returns the next integer value. */
  def nextInteger(): Int = next()

  /** Returns the next value in {@code [0, toValue)}. */
  def nextInteger(toValue: Int): Int = {
    require(toValue > 0, "toValue must be greater than zero")
    if ((toValue & -toValue) == toValue)
      (toValue * (next() >>> 1 toLong)) >> 31 toInt
    else {
      var bits, result = 0
      do {
        bits = next() >>> 1;
        result = bits % toValue;
      } while (bits - result + (toValue - 1) < 0)
      result
    }
  }

  /** Returns the next value in {@code [0, 1)}. */
  def nextFloat(): Float = (next() >>> 8) / (1 << 24 toFloat)

  /** Produces the next pseudo-random 32-bit number. */
  private def next() = {
    var y = 0
    if (stateIndex >= N) {
      var kk = 0
      val magic = MAGIC
      val stateVector = this.stateVector
      while (kk < N - M) {
        y = (stateVector(kk) & UPPER_MASK) | (stateVector(kk + 1) & LOWER_MASK)
        stateVector(kk) = stateVector(kk + M) ^ (y >>> 1) ^ magic(y & 0x1)
        kk += 1
      }
      while (kk < N - 1) {
        y = (stateVector(kk) & UPPER_MASK) | (stateVector(kk + 1) & LOWER_MASK)
        stateVector(kk) = stateVector(kk + (M - N)) ^ (y >>> 1) ^ magic(y & 0x1)
        kk += 1
      }
      y = (stateVector(N - 1) & UPPER_MASK) | (stateVector(0) & LOWER_MASK)
      stateVector(N - 1) = stateVector(M - 1) ^ (y >>> 1) ^ magic(y & 0x1)
      stateIndex = 0
    }
    y = stateVector(stateIndex)
    stateIndex += 1
    y ^= y >>> 11
    y ^= (y << 7) & TEMPERING_MASK_B
    y ^= (y << 15) & TEMPERING_MASK_C
    y ^= (y >>> 18)
    y
  }

}

/**
 * Factory for random number generators.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
object Random {

  /** Period parameter. */
  private val N = 624
  /** Period parameter. */
  private val M = 397
  /** Most significant w-r bits. */
  private val UPPER_MASK = 0x80000000
  /** Least significant r bits. */
  private val LOWER_MASK = 0x7fffffff
  /** Tempering parameter. */
  private val TEMPERING_MASK_B = 0x9d2c5680
  /** Tempering parameter. */
  private val TEMPERING_MASK_C = 0xefc60000
  /** Magic numbers. */
  private val MAGIC = Array(0x0, 0x9908b0df)

  /** Creates a RNG from a time-based seed. */
  def apply(): Random = {
    val s = System.currentTimeMillis ^ System.nanoTime
    apply(s & 0x00000000FFFFFFFFL ^ s >>> 32 toInt)
  }

  /** Creates a RNG from the specified seed. */
  def apply(seed: Int): Random = new Random(seed)

}
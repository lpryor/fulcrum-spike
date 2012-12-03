/*
 * Random.java
 * 
 * Copyright (c) 2012 Lonnie Pryor III and others.
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
 * Copyright (c) 2003 by Sean Luke. <br>
 * Portions copyright (c) 1993 by Michael Lecuyer. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice, 
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the 
 * names of its contributors may be used to endorse or promote products 
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
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
package fulcrum.util;

/**
 * A random number generator based on the mersenne twister.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class Random implements Cloneable {

  /** Period parameter. */
  private static final int N = 624;
  /** Period parameter. */
  private static final int M = 397;
  /** Most significant w-r bits. */
  private static final int UPPER_MASK = 0x80000000;
  /** Least significant r bits. */
  private static final int LOWER_MASK = 0x7fffffff;
  /** Tempering parameter. */
  private static final int TEMPERING_MASK_B = 0x9d2c5680;
  /** Tempering parameter. */
  private static final int TEMPERING_MASK_C = 0xefc60000;
  /** Magic numbers. */
  private static final int[] MAGIC = { 0x0, 0x9908b0df };

  /** The array for the state vector. */
  private final int[] stateVector = new int[N];
  /**
   * Index into the state vector. If {@code stateIndex == N + 1} then
   * {@code stateVector[N]} is not initialized.
   */
  private int stateIndex = 0;

  /** Clones a random number generator. */
  private Random() {}

  /** Creates a new random number generator. */
  public Random(int seed) {
    stateVector[0] = seed;
    for (stateIndex = 1; stateIndex < N; ++stateIndex) {
      int tmp = stateVector[stateIndex - 1];
      stateVector[stateIndex] = (1812433253 * (tmp ^ (tmp >>> 30)) + stateIndex);
    }
  }

  /** Returns the next boolean value. */
  public boolean nextBoolean() {
    return (next() >>> 31) != 0;
  }

  /** Returns the next byte value. */
  public byte nextByte() {
    return (byte) (next() >>> 24);
  }

  /** Returns the next integer value. */
  public int nextInteger() {
    return next();
  }

  /** Returns the next value in {@code [0, toValue)}. */
  public int nextInteger(int toValue) {
    if (toValue <= 0)
      throw new IllegalArgumentException(String.valueOf(toValue));
    if ((toValue & -toValue) == toValue)
      return (int) ((toValue * (long) (next() >>> 1)) >> 31);
    int bits, val;
    do {
      bits = next() >>> 1;
      val = bits % toValue;
    } while (bits - val + (toValue - 1) < 0);
    return val;
  }

  /** Returns the next value in {@code [0, 1)}. */
  public float nextFloat() {
    return (next() >>> 8) / (float) (1 << 24);
  }

  /** Produces the next pseudo-random 32-bit number. */
  private int next() {
    int y;
    if (stateIndex >= N) {
      int kk;
      final int[] magic = MAGIC;
      final int[] stateVector = this.stateVector;
      for (kk = 0; kk < N - M; ++kk) {
        y = (stateVector[kk] & UPPER_MASK) | (stateVector[kk + 1] & LOWER_MASK);
        stateVector[kk] = stateVector[kk + M] ^ (y >>> 1) ^ magic[y & 0x1];
      }
      for (; kk < N - 1; ++kk) {
        y = (stateVector[kk] & UPPER_MASK) | (stateVector[kk + 1] & LOWER_MASK);
        stateVector[kk] = stateVector[kk + (M - N)] ^ (y >>> 1) ^ magic[y & 0x1];
      }
      y = (stateVector[N - 1] & UPPER_MASK) | (stateVector[0] & LOWER_MASK);
      stateVector[N - 1] = stateVector[M - 1] ^ (y >>> 1) ^ magic[y & 0x1];
      stateIndex = 0;
    }
    y = stateVector[stateIndex++];
    y ^= y >>> 11;
    y ^= (y << 7) & TEMPERING_MASK_B;
    y ^= (y << 15) & TEMPERING_MASK_C;
    y ^= (y >>> 18);
    return y;
  }

  /* @see Object#clone() */
  @Override
  public Random clone() {
    Random that = new Random();
    System.arraycopy(this.stateVector, 0, that.stateVector, 0, this.stateVector.length);
    that.stateIndex = this.stateIndex;
    return that;
  }

}

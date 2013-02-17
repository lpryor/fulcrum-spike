/*
 * RandomTest.scala
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
package fulcrum.util

import org.junit.Test
import org.junit.Assert._

/**
 * Test case for {@link Random}.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
class RandomTest {

  /** Random number generators can generate random true or false values. */
  @Test
  def generatesRandomBooleans() {
    val r1 = Random(0x44444444)
    val r2 = Random(0x44444444)
    assertEquals(r1.nextBoolean(), r2.nextBoolean())
    val r3 = Random(0x80000000)
    val r4 = Random(0x80000001)
    assertTrue(r3.nextBoolean() != r4.nextBoolean())
    val r5 = Random(0xCCCCCCCC)
    assertEquals(r5.nextBoolean(), false)
  }

  /** Random number generators can generate random byte values. */
  @Test
  def generatesRandomBytes() {
    val r1 = Random(0x44444444)
    val r2 = Random(0x44444444)
    assertEquals(r1.nextByte(), r2.nextByte())
    val r3 = Random(0x80000000)
    val r4 = Random(0x80000001)
    assertTrue(r3.nextByte() != r4.nextByte())
    val r5 = Random(0xCCCCCCCC)
    assertEquals(r5.nextByte(), 15)
  }

  /** Random number generators can generate random integer values. */
  @Test
  def generatesRandomIntegers() {
    val r1 = Random(0x44444444)
    val r2 = Random(0x44444444)
    assertEquals(r1.nextInteger(), r2.nextInteger())
    val r3 = Random(0x80000000)
    val r4 = Random(0x80000001)
    assertTrue(r3.nextInteger() != r4.nextInteger())
    val r5 = Random(0xCCCCCCCC)
    assertEquals(r5.nextInteger(), 263024714)
  }

  /** Random number generators can generate random bounded integer values. */
  @Test
  def generatesRandomBoundedIntegers() {
    val r1 = Random(0x44444444)
    val r2 = Random(0x44444444)
    assertEquals(r1.nextInteger(147), r2.nextInteger(147))
    val r3 = Random(0x80000000)
    val r4 = Random(0x80000001)
    assertTrue(r3.nextInteger(147) != r4.nextInteger(147))
    val r5 = Random(0xCCCCCCCC)
    assertEquals(r5.nextInteger(147), 130)
  }

  /** Random number generators can generate random float values. */
  @Test
  def generatesRandomFloats() {
    val r1 = Random(0x44444444)
    val r2 = Random(0x44444444)
    assertEquals(r1.nextFloat(), r2.nextFloat(), 0f)
    val r3 = Random(0x80000000)
    val r4 = Random(0x80000001)
    assertTrue(r3.nextFloat() != r4.nextFloat())
    val r5 = Random(0xCCCCCCCC)
    assertEquals(r5.nextFloat(), java.lang.Float.intBitsToFloat(1031460608), 0f)
  }

}

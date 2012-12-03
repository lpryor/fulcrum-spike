/*
 * RandomTest.java
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
package fulcrum.util;

import junit.framework.TestCase;

/**
 * Test case for {@link Random}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class RandomTest extends TestCase {

  /** Random number generators can generate random true or false values. */
  public void testGeneratesRandomBooleans() {
    Random r1 = new Random(0x44444444), r2 = new Random(0x44444444);
    assertTrue(r1.nextBoolean() == r2.nextBoolean());
    Random r3 = new Random(0x80000000), r4 = new Random(0x80000001);
    assertTrue(r3.nextBoolean() != r4.nextBoolean());
    Random r5 = new Random(0xCCCCCCCC);
    assertTrue(r5.nextBoolean() == false);
  }

  /** Random number generators can generate random byte values. */
  public void testGeneratesRandomBytes() {
    Random r1 = new Random(0x44444444), r2 = new Random(0x44444444);
    assertTrue(r1.nextByte() == r2.nextByte());
    Random r3 = new Random(0x80000000), r4 = new Random(0x80000001);
    assertTrue(r3.nextByte() != r4.nextByte());
    Random r5 = new Random(0xCCCCCCCC);
    assertTrue(r5.nextByte() == 15);
  }

  /** Random number generators can generate random integer values. */
  public void testGeneratesRandomIntegers() {
    Random r1 = new Random(0x44444444), r2 = new Random(0x44444444);
    assertTrue(r1.nextInteger() == r2.nextInteger());
    Random r3 = new Random(0x80000000), r4 = new Random(0x80000001);
    assertTrue(r3.nextInteger() != r4.nextInteger());
    Random r5 = new Random(0xCCCCCCCC);
    assertTrue(r5.nextInteger() == 263024714);
  }

  /** Random number generators can generate random bounded integer values. */
  public void testGeneratesRandomBoundedIntegers() {
    Random r1 = new Random(0x44444444), r2 = new Random(0x44444444);
    assertTrue(r1.nextInteger(147) == r2.nextInteger(147));
    Random r3 = new Random(0x80000000), r4 = new Random(0x80000001);
    assertTrue(r3.nextInteger(147) != r4.nextInteger(147));
    Random r5 = new Random(0xCCCCCCCC);
    assertTrue(r5.nextInteger(147) == 130);
  }

  /** Random number generators can generate random float values. */
  public void testGeneratesRandomFloats() {
    Random r1 = new Random(0x44444444), r2 = new Random(0x44444444);
    assertTrue(r1.nextFloat() == r2.nextFloat());
    Random r3 = new Random(0x80000000), r4 = new Random(0x80000001);
    assertTrue(r3.nextFloat() != r4.nextFloat());
    Random r5 = new Random(0xCCCCCCCC);
    assertTrue(r5.nextFloat() == Float.intBitsToFloat(1031460608));
  }

  /** Random number generators current state can be cloned and used separately. */
  public void testGeneratorStateCanBeCloned() {
    Random r1 = new Random(0x44444444), r2 = r1.clone();
    assertTrue(r1 != r2);
    assertTrue(r1.nextBoolean() == r2.nextBoolean());
    assertTrue(r1.nextByte() == r2.nextByte());
    assertTrue(r1.nextInteger() == r2.nextInteger());
    assertTrue(r1.nextInteger(147) == r2.nextInteger(147));
    assertTrue(r1.nextFloat() == r2.nextFloat());
    r2 = r1.clone();
    assertTrue(r1 != r2);
    assertTrue(r1.nextBoolean() == r2.nextBoolean());
    assertTrue(r1.nextByte() == r2.nextByte());
    assertTrue(r1.nextInteger() == r2.nextInteger());
    assertTrue(r1.nextInteger(147) == r2.nextInteger(147));
    assertTrue(r1.nextFloat() == r2.nextFloat());
  }

}

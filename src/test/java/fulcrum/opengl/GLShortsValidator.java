/*
 * GLShortsValidator.java
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

import java.util.Arrays;

import fulcrum.Function;

import junit.framework.Assert;

/**
 * Test cases for {@link GLShorts}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class GLShortsValidator {

  /** Verifies that implementations provide for fixed-size data storage. */
  public static void testProvidesMutableDataBufferCapabilites(Function<Integer, GLShorts> factory) {
    validateImplementation(factory.apply(4), false);
    validateImplementation(factory.apply(16), true);
  }

  /** Verifies that implementations provide for fixed-size data storage. */
  static void validateImplementation(GLShorts implementation, boolean validateConversions) {
    GLShorts buffer = implementation;
    if (buffer.size() != 4)
      buffer = buffer.subData(0, 4);
    Assert.assertEquals(4, buffer.size());
    Assert.assertEquals(0, buffer.get(0));
    Assert.assertEquals(0, buffer.get(1));
    buffer.set(0, (short) 2);
    buffer.set(1, (short) 4);
    Assert.assertEquals(2, buffer.get(0));
    Assert.assertEquals(4, buffer.get(1));
    short[] array = new short[4];
    buffer.copyTo(array);
    Assert.assertTrue(Arrays.equals(new short[] { 2, 4, 0, 0 }, array));
    buffer.copyFrom(new short[] { 2, 4, 8, 0 });
    buffer.copyTo(array);
    Assert.assertTrue(Arrays.equals(new short[] { 2, 4, 8, 0 }, array));
    for (int i = 0; i < implementation.size(); ++i)
      implementation.set(i, (short) 0);
    if (validateConversions)
      GLBytesValidator.validateImplementation(implementation.asBytes(), false);
  }

}

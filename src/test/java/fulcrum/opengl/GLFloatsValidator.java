/*
 * GLFloatsValidator.java
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
 * Test cases for {@link GLFloats}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class GLFloatsValidator {

  /** Verifies that implementations provide for fixed-size data storage. */
  public static void testProvidesMutableDataBufferCapabilites(Function<Integer, GLFloats> factory) {
    validateImplementation(factory.apply(4), false);
    validateImplementation(factory.apply(16), true);
  }

  /** Verifies that implementations provide for fixed-size data storage. */
  static void validateImplementation(GLFloats implementation, boolean validateConversions) {
    GLFloats buffer = implementation;
    if (buffer.size() != 4)
      buffer = buffer.subData(0, 4);
    Assert.assertEquals(4, buffer.size());
    Assert.assertEquals(0f, buffer.get(0));
    Assert.assertEquals(0f, buffer.get(1));
    buffer.set(0, 2f);
    buffer.set(1, 4f);
    Assert.assertEquals(2f, buffer.get(0));
    Assert.assertEquals(4f, buffer.get(1));
    float[] array = new float[4];
    buffer.copyTo(array);
    Assert.assertTrue(Arrays.equals(new float[] { 2, 4, 0, 0 }, array));
    buffer.copyFrom(new float[] { 2, 4, 8, 0 });
    buffer.copyTo(array);
    Assert.assertTrue(Arrays.equals(new float[] { 2, 4, 8, 0 }, array));
    for (int i = 0; i < implementation.size(); ++i)
      implementation.set(i, 0f);
    if (validateConversions)
      GLBytesValidator.validateImplementation(implementation.asBytes(), false);
  }

}

/*
 * DesktopGLIntegersTest.java
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
package fulcrum.desktop.opengl;

import junit.framework.TestCase;
import fulcrum.Function;
import fulcrum.opengl.GLIntegers;
import fulcrum.opengl.GLIntegersValidator;

/**
 * Test case for {@link DesktopGLIntegers}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class DesktopGLIntegersTest extends TestCase {

  /** Verifies that implementations provide for fixed-size data storage. */
  public void testProvidesMutableDataBufferCapabilites() {
    GLIntegersValidator.testProvidesMutableDataBufferCapabilites(new Function<Integer, GLIntegers>() {
      @Override
      public GLIntegers apply(Integer input) {
        return new DesktopGLIntegers(input);
      }
    });
  }

}

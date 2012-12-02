/*
 * StringsTest.java
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
package fulcrum.text;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * Test case for {@link Strings}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class StringsTest extends TestCase {

  /**
   * The various {@code format(...)} methods provide simple, cross-platform
   * message and object formatting.
   */
  public void testFormatMethodsPerformSimpleFormatting() {
    verifyMessageFormatting("msg", "msg");
    verifyMessageFormatting("%-msg", "%%-msg");
    verifyMessageFormatting("%-msg-7", "%%-msg-%s", 7);
    verifyMessageFormatting("%-msg-7-hi", "%%-msg-%s-%s", 7, "hi");
    verifyMessageFormatting("%-msg-7-hi-%", "%%-msg-%s-%s-%%", 7, "hi");
    try {
      Strings.format("%");
      fail("Expected an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {}
    try {
      Strings.format("%s");
      fail("Expected an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {}
    try {
      Strings.format("%d", 7);
      fail("Expected an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {}
    try {
      Strings.format("msg", 7);
      fail("Expected an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {}
    verifyObjectFormatting("StringsTest", getClass());
    verifyObjectFormatting("StringsTest(7)", getClass(), 7);
    verifyObjectFormatting("StringsTest(7, hi)", getClass(), 7, "hi");
    verifyObjectFormatting("StringsTest_Mock", Mock.class);
    verifyObjectFormatting("StringsTest_Mock(7)", Mock.class, 7);
    verifyObjectFormatting("StringsTest_Mock(7, hi)", Mock.class, 7, "hi");
  }

  /** Verifies the message formatting routines. */
  private void verifyMessageFormatting(String expected, String message, Object... arguments) {
    assertEquals(expected, Strings.format(message, arguments));
    assertEquals(expected, Strings.format(message, Arrays.asList(arguments)));
  }

  /** Verifies the object formatting routines. */
  private void verifyObjectFormatting(String expected, Class<?> type, Object... members) {
    assertTrue(Strings.format(type, members).endsWith(expected));
    assertTrue(Strings.format(type, Arrays.asList(members)).endsWith(expected));
  }

  /**
   * A mock nested class for testing the object formatting routines.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private static final class Mock {}

}

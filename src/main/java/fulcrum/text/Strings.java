/*
 * Strings.java
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
import java.util.Iterator;

/**
 * Utility methods for working with strings.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class Strings {

  /** Provides support for simple message formatting. */
  public static String format(String message, Object... arguments) {
    assert message != null;
    assert arguments != null;
    return format(message, Arrays.asList(arguments));
  }

  /** Provides support for simple message formatting. */
  public static String format(String message, Iterable<Object> arguments) {
    assert message != null;
    assert arguments != null;
    Iterator<Object> iter = arguments.iterator();
    int offset = 0;
    StringBuilder builder = new StringBuilder(message.length() + 32);
    for (int index = message.indexOf("%"), count = 0; index > -1; index = message.indexOf("%", offset)) {
      if (offset < index)
        builder.append(message, offset, index);
      if (index == message.length() - 1)
        throw new IllegalArgumentException("Invalid tailing conversion prefix.");
      else if (message.charAt(index + 1) == '%')
        builder.append('%');
      else if (message.charAt(index + 1) != 's')
        throw new IllegalArgumentException("Conversion \"" + message.charAt(index + 1) + "\" is invalid.");
      else if (!iter.hasNext())
        throw new IllegalArgumentException("Argument " + count + " not provided.");
      else
        builder.append(iter.next());
      ++count;
      offset = index + 2;
    }
    if (offset < message.length())
      builder.append(message, offset, message.length());
    int unbound = 0;
    while (iter.hasNext()) {
      iter.next();
      ++unbound;
    }
    if (unbound > 0)
      throw new IllegalArgumentException("Found " + unbound + " unbound arguments.");
    return builder.toString();
  }

  /** Formats object information for display. */
  public static String format(Class<?> type, Object... members) {
    return format(type, Arrays.asList(members));
  }

  /** Formats object information for display. */
  public static String format(Class<?> type, Iterable<Object> members) {
    String name = type.getName();
    StringBuilder builder = new StringBuilder();
    builder.append(name.substring(name.lastIndexOf('.') + 1).replace('$', '_'));
    Iterator<Object> iter = members.iterator();
    if (iter.hasNext()) {
      builder.append('(');
      while (iter.hasNext())
        builder.append(iter.next()).append(", ");
      builder.setLength(builder.length() - 2);
      builder.append(')');
    }
    return builder.toString();
  }

}

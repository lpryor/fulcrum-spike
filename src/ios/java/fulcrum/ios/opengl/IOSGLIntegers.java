/*
 * IOSGLIntegers.java
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
package fulcrum.ios.opengl;

import fulcrum.opengl.GLBytes;
import fulcrum.opengl.GLIntegers;

/**
 * And implementation of the {@link GLIntegers} type for use with the iOS OpenGL ES 2.0 API.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class IOSGLIntegers extends GLIntegers {

  /** The buffer used to store the data. */
  private final IOSGLBuffer buffer;
  /** The index in the buffer where this data set begins (inclusive). */
  private final int beginIndex;
  /** The index in the buffer where this data set ends (exclusive). */
  private final int endIndex;

  /** Creates a new iOS-specific implementation of {@link GLIntegers}. */
  IOSGLIntegers(int size) {
    buffer = new IOSGLBuffer(size * 4);
    beginIndex = 0;
    endIndex = size;
  }

  /** Creates a new iOS-specific implementation of {@link GLIntegers}. */
  IOSGLIntegers(IOSGLBuffer buffer, int beginIndex, int endIndex) {
    this.buffer = buffer;
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }

  /** Acquires the appropriate range in the underlying buffer. */
  int pointer() {
    return buffer.integerPointer(beginIndex);
  }

  /* @see GLIntegers#size() */
  @Override
  public int size() {
    return endIndex - beginIndex;
  }

  /* @see GLIntegers#get(int) */
  @Override
  public int get(int index) {
    assert index >= 0;
    assert index < size();
    return buffer.integerValue(beginIndex + index);
  }

  /* @see GLIntegers#set(int, int) */
  @Override
  public void set(int index, int value) {
    assert index >= 0;
    assert index < size();
    buffer.setIntegerValue(beginIndex + index, value);
  }

  /* @see GLIntegers#copyTo(int[], int) */
  @Override
  public void copyTo(int[] array, int offset) {
    assert array != null;
    assert offset >= 0;
    assert offset <= array.length;
    assert array.length - offset >= size();
    for (int i = 0, size = size(); i < size; ++i)
      array[offset + i] = buffer.integerValue(beginIndex + i);
  }

  /* @see GLIntegers#copyFrom(int[], int) */
  @Override
  public void copyFrom(int[] array, int offset) {
    assert array != null;
    assert offset >= 0;
    assert offset <= array.length;
    assert array.length - offset >= size();
    for (int i = 0, size = size(); i < size; ++i)
      buffer.setIntegerValue(beginIndex + i, array[offset + i]);
  }

  /* @see GLIntegers#subData(int, int) */
  @Override
  public GLIntegers subData(int beginIndex, int endIndex) {
    assert beginIndex >= 0;
    assert beginIndex <= endIndex;
    assert endIndex <= size();
    return new IOSGLIntegers(buffer, this.beginIndex + beginIndex, this.beginIndex + endIndex);
  }

  /* @see GLIntegers#asBytes() */
  @Override
  public GLBytes asBytes() {
    return new IOSGLBytes(buffer, beginIndex * 4, endIndex * 4);
  }

}

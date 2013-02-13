/*
 * IOSGLBytes.java
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
import fulcrum.opengl.GLFloats;
import fulcrum.opengl.GLIntegers;
import fulcrum.opengl.GLShorts;

/**
 * And implementation of the {@link GLBytes} type for use with the iOS OpenGL ES 2.0 API.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class IOSGLBytes extends GLBytes {

  /** The buffer used to store the data. */
  private final IOSGLBuffer buffer;
  /** The index in the buffer where this data set begins (inclusive). */
  private final int beginIndex;
  /** The index in the buffer where this data set ends (exclusive). */
  private final int endIndex;

  /** Creates a new iOS-specific implementation of {@link GLBytes}. */
  IOSGLBytes(int size) {
    buffer = new IOSGLBuffer(size);
    beginIndex = 0;
    endIndex = size;
  }

  /** Creates a new iOS-specific implementation of {@link GLBytes}. */
  IOSGLBytes(IOSGLBuffer buffer, int beginIndex, int endIndex) {
    this.buffer = buffer;
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }

  /** Acquires the appropriate range in the underlying buffer. */
  long pointer() {
    return buffer.bytePointer(beginIndex);
  }

  /* @see GLBytes#size() */
  @Override
  public int size() {
    return endIndex - beginIndex;
  }

  /* @see GLBytes#get(int) */
  @Override
  public byte get(int index) {
    assert index >= 0;
    assert index < size();
    return buffer.byteValue(beginIndex + index);
  }

  /* @see GLBytes#set(int, byte) */
  @Override
  public void set(int index, byte value) {
    assert index >= 0;
    assert index < size();
    buffer.setByteValue(beginIndex + index, value);
  }

  /* @see GLBytes#copyTo(byte[], int) */
  @Override
  public void copyTo(byte[] array, int offset) {
    assert array != null;
    assert offset >= 0;
    assert offset <= array.length;
    assert array.length - offset >= size();
    buffer.byteValues(beginIndex, size(), array, offset);
  }

  /* @see GLBytes#copyFrom(byte[], int) */
  @Override
  public void copyFrom(byte[] array, int offset) {
    assert array != null;
    assert offset >= 0;
    assert offset <= array.length;
    assert array.length - offset >= size();
    buffer.setByteValues(beginIndex, size(), array, offset);
  }

  /* @see GLBytes#subData(int, int) */
  @Override
  public GLBytes subData(int beginIndex, int endIndex) {
    assert beginIndex >= 0;
    assert beginIndex <= endIndex;
    assert endIndex <= size();
    return new IOSGLBytes(buffer, this.beginIndex + beginIndex, this.beginIndex + endIndex);
  }

  /* @see GLBytes#asShorts() */
  @Override
  public GLShorts asShorts() {
    return new IOSGLShorts(buffer, beginIndex / 2, endIndex / 2);
  }

  /* @see GLBytes#asIntegers() */
  @Override
  public GLIntegers asIntegers() {
    return new IOSGLIntegers(buffer, beginIndex / 4, endIndex / 4);
  }

  /* @see GLBytes#asFloats() */
  @Override
  public GLFloats asFloats() {
    return new IOSGLFloats(buffer, beginIndex / 4, endIndex / 4);
  }

}

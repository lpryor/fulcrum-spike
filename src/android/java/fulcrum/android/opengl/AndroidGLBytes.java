/*
 * AndroidGLBytes.java
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
package fulcrum.android.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import fulcrum.opengl.GLBytes;
import fulcrum.opengl.GLFloats;
import fulcrum.opengl.GLIntegers;
import fulcrum.opengl.GLShorts;

/**
 * And implementation of the {@link GLBytes} type for use with the Android OpenGL ES 2.0 API.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class AndroidGLBytes extends GLBytes {

  /** The buffer used to store the data. */
  private final ByteBuffer buffer;
  /** The index in the buffer where this data set begins (inclusive). */
  private final int beginIndex;
  /** The index in the buffer where this data set ends (exclusive). */
  private final int endIndex;

  /** Creates a new Android-specific implementation of {@link GLBytes}. */
  AndroidGLBytes(int size) {
    buffer = ByteBuffer.allocateDirect(size);
    buffer.order(ByteOrder.nativeOrder());
    beginIndex = 0;
    endIndex = size;
  }

  /** Creates a new Android-specific implementation of {@link GLBytes}. */
  AndroidGLBytes(ByteBuffer buffer, int beginIndex, int endIndex) {
    this.buffer = buffer;
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }

  /** Acquires the appropriate range in the underlying buffer. */
  ByteBuffer acquireBuffer() {
    buffer.position(beginIndex);
    buffer.limit(endIndex);
    return buffer;
  }

  /** Releases the underlying buffer. */
  void releaseBuffer() {
    buffer.clear();
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
    return buffer.get(beginIndex + index);
  }

  /* @see GLBytes#set(int, byte) */
  @Override
  public void set(int index, byte value) {
    assert index >= 0;
    assert index < size();
    buffer.put(beginIndex + index, value);
  }

  /* @see GLBytes#copyTo(byte[], int) */
  @Override
  public void copyTo(byte[] array, int offset) {
    assert array != null;
    assert offset >= 0;
    assert offset <= array.length;
    assert array.length - offset >= size();
    buffer.position(beginIndex);
    try {
      buffer.get(array, offset, size());
    } finally {
      buffer.clear();
    }
  }

  /* @see GLBytes#copyFrom(byte[], int) */
  @Override
  public void copyFrom(byte[] array, int offset) {
    assert array != null;
    assert offset >= 0;
    assert offset <= array.length;
    assert array.length - offset >= size();
    buffer.position(beginIndex);
    try {
      buffer.put(array, offset, size());
    } finally {
      buffer.clear();
    }
  }

  /* @see GLBytes#subData(int, int) */
  @Override
  public GLBytes subData(int beginIndex, int endIndex) {
    assert beginIndex >= 0;
    assert beginIndex <= endIndex;
    assert endIndex <= size();
    return new AndroidGLBytes(buffer, this.beginIndex + beginIndex, this.beginIndex + endIndex);
  }

  /* @see GLBytes#asShorts() */
  @Override
  public GLShorts asShorts() {
    return new AndroidGLShorts(buffer, buffer.asShortBuffer(), beginIndex / 2, endIndex / 2);
  }

  /* @see GLBytes#asIntegers() */
  @Override
  public GLIntegers asIntegers() {
    return new AndroidGLIntegers(buffer, buffer.asIntBuffer(), beginIndex / 4, endIndex / 4);
  }

  /* @see GLBytes#asFloats() */
  @Override
  public GLFloats asFloats() {
    return new AndroidGLFloats(buffer, buffer.asFloatBuffer(), beginIndex / 4, endIndex / 4);
  }

}

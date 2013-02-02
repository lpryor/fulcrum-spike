/*
 * AndroidGLFloats.java
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
import java.nio.FloatBuffer;

import fulcrum.opengl.GLBytes;
import fulcrum.opengl.GLFloats;

/**
 * And implementation of the {@link GLFloats} type for use with the Android OpenGL ES 2.0 API.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class AndroidGLFloats extends GLFloats {

  /** The raw byte buffer that backs this data. */
  private final ByteBuffer raw;
  /** The typed buffer used to store the data. */
  private final FloatBuffer buffer;
  /** The index in the buffer where this data set begins (inclusive). */
  private final int beginIndex;
  /** The index in the buffer where this data set ends (exclusive). */
  private final int endIndex;

  /** Creates a new Android-specific implementation of {@link GLFloats}. */
  AndroidGLFloats(int size) {
    raw = ByteBuffer.allocateDirect(size * 4);
    raw.order(ByteOrder.nativeOrder());
    buffer = raw.asFloatBuffer();
    beginIndex = 0;
    endIndex = size;
  }

  /** Creates a new Android-specific implementation of {@link GLFloats}. */
  AndroidGLFloats(ByteBuffer raw, FloatBuffer buffer, int beginIndex, int endIndex) {
    this.raw = raw;
    this.buffer = buffer;
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }

  /** Acquires the appropriate range in the underlying buffer. */
  FloatBuffer acquireBuffer() {
    buffer.position(beginIndex);
    buffer.limit(endIndex);
    return buffer;
  }

  /** Releases the underlying buffer. */
  void releaseBuffer() {
    buffer.clear();
  }

  /* @see GLFloats#size() */
  @Override
  public int size() {
    return endIndex - beginIndex;
  }

  /* @see GLFloats#get(int) */
  @Override
  public float get(int index) {
    assert index >= 0;
    assert index < size();
    return buffer.get(beginIndex + index);
  }

  /* @see GLFloats#set(int, float) */
  @Override
  public void set(int index, float value) {
    assert index >= 0;
    assert index < size();
    buffer.put(beginIndex + index, value);
  }

  /* @see GLFloats#copyTo(float[], int) */
  @Override
  public void copyTo(float[] array, int offset) {
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

  /* @see GLFloats#copyFrom(float[], int) */
  @Override
  public void copyFrom(float[] array, int offset) {
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

  /* @see GLFloats#subData(int, int) */
  @Override
  public GLFloats subData(int beginIndex, int endIndex) {
    assert beginIndex >= 0;
    assert beginIndex <= endIndex;
    assert endIndex <= size();
    return new AndroidGLFloats(raw, buffer, this.beginIndex + beginIndex, this.beginIndex + endIndex);
  }

  /* @see GLFloats#asBytes() */
  @Override
  public GLBytes asBytes() {
    return new AndroidGLBytes(raw, beginIndex * 4, endIndex * 4);
  }

}

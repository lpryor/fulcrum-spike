/*
 * DesktopGLShorts.java
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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

import fulcrum.opengl.GLBytes;
import fulcrum.opengl.GLShorts;

/**
 * And implementation of the {@link GLShorts} type for use with the Desktop OpenGL ES 2.0 API.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class DesktopGLShorts extends GLShorts {

  /** The raw byte buffer that backs this data. */
  private final ByteBuffer raw;
  /** The typed buffer used to store the data. */
  private final ShortBuffer buffer;
  /** The index in the buffer where this data set begins (inclusive). */
  private final int beginIndex;
  /** The index in the buffer where this data set ends (exclusive). */
  private final int endIndex;

  /** Creates a new Desktop-specific implementation of {@link GLShorts}. */
  DesktopGLShorts(int size) {
    raw = ByteBuffer.allocateDirect(size * 2);
    raw.order(ByteOrder.nativeOrder());
    buffer = raw.asShortBuffer();
    beginIndex = 0;
    endIndex = size;
  }

  /** Creates a new Desktop-specific implementation of {@link GLShorts}. */
  DesktopGLShorts(ByteBuffer raw, ShortBuffer buffer, int beginIndex, int endIndex) {
    this.raw = raw;
    this.buffer = buffer;
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }

  /** Acquires the appropriate range in the underlying buffer. */
   ShortBuffer acquireBuffer() {
    buffer.position(beginIndex);
    buffer.limit(endIndex);
    return buffer;
  }

  /** Releases the underlying buffer. */
   void releaseBuffer() {
    buffer.clear();
  }

  /* @see GLShorts#size() */
  @Override
  public int size() {
    return endIndex - beginIndex;
  }

  /* @see GLShorts#get(int) */
  @Override
  public short get(int index) {
    assert index >= 0;
    assert index < size();
    return buffer.get(beginIndex + index);
  }

  /* @see GLShorts#set(int, short) */
  @Override
  public void set(int index, short value) {
    assert index >= 0;
    assert index < size();
    buffer.put(beginIndex + index, value);
  }

  /* @see GLShorts#copyTo(short[], int) */
  @Override
  public void copyTo(short[] array, int offset) {
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

  /* @see GLShorts#copyFrom(short[], int) */
  @Override
  public void copyFrom(short[] array, int offset) {
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

  /* @see GLShorts#subData(int, int) */
  @Override
  public GLShorts subData(int beginIndex, int endIndex) {
    assert beginIndex >= 0;
    assert beginIndex <= endIndex;
    assert endIndex <= size();
    return new DesktopGLShorts(raw, buffer, this.beginIndex + beginIndex, this.beginIndex + endIndex);
  }

  /* @see GLShorts#asBytes() */
  @Override
  public GLBytes asBytes() {
    return new DesktopGLBytes(raw, beginIndex * 2, endIndex * 2);
  }

}

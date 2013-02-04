/*
 * IOSGLFloats.java
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

/**
 * And implementation of the {@link GLFloats} type for use with the iOS OpenGL ES 2.0 API.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class IOSGLFloats extends GLFloats {

  /** The buffer used to store the data. */
  private final IOSGLBuffer buffer;
  /** The index in the buffer where this data set begins (inclusive). */
  private final int beginIndex;
  /** The index in the buffer where this data set ends (exclusive). */
  private final int endIndex;

  /** Creates a new iOS-specific implementation of {@link GLFloats}. */
  IOSGLFloats(int size) {
    buffer = new IOSGLBuffer(size * 4);
    beginIndex = 0;
    endIndex = size;
  }

  /** Creates a new iOS-specific implementation of {@link GLFloats}. */
  IOSGLFloats(IOSGLBuffer buffer, int beginIndex, int endIndex) {
    this.buffer = buffer;
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }

  /** Acquires the appropriate range in the underlying buffer. */
  int pointer() {
    return buffer.floatPointer(beginIndex);
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
    return buffer.floatValue(beginIndex + index);
  }

  /* @see GLFloats#set(int, float) */
  @Override
  public void set(int index, float value) {
    assert index >= 0;
    assert index < size();
    buffer.setFloatValue(beginIndex + index, value);
  }

  /* @see GLFloats#copyTo(float[], int) */
  @Override
  public void copyTo(float[] array, int offset) {
    assert array != null;
    assert offset >= 0;
    assert offset <= array.length;
    assert array.length - offset >= size();
    for (int i = 0, size = size(); i < size; ++i)
      array[offset + i] = buffer.floatValue(beginIndex + i);
  }

  /* @see GLFloats#copyFrom(float[], int) */
  @Override
  public void copyFrom(float[] array, int offset) {
    assert array != null;
    assert offset >= 0;
    assert offset <= array.length;
    assert array.length - offset >= size();
    for (int i = 0, size = size(); i < size; ++i)
      buffer.setFloatValue(beginIndex + i, array[offset + i]);
  }

  /* @see GLFloats#subData(int, int) */
  @Override
  public GLFloats subData(int beginIndex, int endIndex) {
    assert beginIndex >= 0;
    assert beginIndex <= endIndex;
    assert endIndex <= size();
    return new IOSGLFloats(buffer, this.beginIndex + beginIndex, this.beginIndex + endIndex);
  }

  /* @see GLFloats#asBytes() */
  @Override
  public GLBytes asBytes() {
    return new IOSGLBytes(buffer, beginIndex * 4, endIndex * 4);
  }

}

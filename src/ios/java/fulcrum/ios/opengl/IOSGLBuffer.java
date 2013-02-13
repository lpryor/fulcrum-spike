/*
 * IOSGLBuffer.java
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

import fulcrum.text.Strings;

/**
 * A wrapper around an iOS memory buffer.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class IOSGLBuffer {

  /** Allocates memory for use in the OpenGL layer. */
  private static native long calloc(int size)
  /*-[
    return (long long int)(calloc(size, sizeof(char)));
  ]-*/;

  /** Deallocates memory used in the OpenGL layer. */
  private static native void free(long pointer)
  /*-[
      free((void*)pointer);
  ]-*/;

  /** The pointer to the buffer. */
  private final long pointer;

  /** Creates a new IOSGLPointer. */
  IOSGLBuffer(int size) {
    pointer = calloc(size);
    if (pointer == 0)
      throw new IllegalStateException(Strings.format("Failed to allocate %s bytes.", size));
  }

  /** Returns a pointer to a byte in the buffer. */
  native long bytePointer(int offset)
  /*-[
  return (long long int)(((char*)self.pointer) + offset);
  ]-*/;

  /** Returns a pointer to a short in the buffer. */
  native long shortPointer(int offset)
  /*-[
  return (long long int)(((short*)self.pointer) + offset);
  ]-*/;

  /** Returns a pointer to an integer in the buffer. */
  native long integerPointer(int offset)
  /*-[
  return (long long int)(((int*)self.pointer) + offset);
  ]-*/;

  /** Returns a pointer to a float in the buffer. */
  native long floatPointer(int offset)
  /*-[
  return (long long int)(((float*)self.pointer) + offset);
  ]-*/;

  /** Returns the value of a byte in the buffer. */
  native byte byteValue(int offset)
  /*-[
  return *(((char*)self.pointer) + offset);
  ]-*/;

  /** Returns the value of a short in the buffer. */
  native short shortValue(int offset)
  /*-[
  return *(((short*)self.pointer) + offset);
  ]-*/;

  /** Returns the value of an integer in the buffer. */
  native int integerValue(int offset)
  /*-[
  return *(((int*)self.pointer) + offset);
  ]-*/;

  /** Returns the value of a float in the buffer. */
  native float floatValue(int offset)
  /*-[
  return *(((float*)self.pointer) + offset);
  ]-*/;

  /** Returns the value of bytes in the buffer. */
  native void byteValues(int offset, int count, byte[] values, int index)
  /*-[
  [values replaceBytes:(((char*)self.pointer) + offset) length:count offset:index];
  ]-*/;

  /** Returns the value of shorts in the buffer. */
  void shortValues(int offset, int count, short[] values, int index) {
    for (int i = 0; i < count; ++i)
      values[index + i] = shortValue(offset + i);
  }

  /** Returns the value of integers in the buffer. */
  void integerValues(int offset, int count, int[] values, int index) {
    for (int i = 0; i < count; ++i)
      values[index + i] = integerValue(offset + i);
  }

  /** Returns the value of floats in the buffer. */
  void floatValues(int offset, int count, float[] values, int index) {
    for (int i = 0; i < count; ++i)
      values[index + i] = floatValue(offset + i);
  }

  /** Sets the value of a byte in the buffer. */
  native void setByteValue(int offset, byte value)
  /*-[
  *(((char*)self.pointer) + offset) = value;
  ]-*/;

  /** Sets the value of a short in the buffer. */
  native void setShortValue(int offset, short value)
  /*-[
  *(((short*)self.pointer) + offset) = value;
  ]-*/;

  /** Sets the value of an integer in the buffer. */
  native void setIntegerValue(int offset, int value)
  /*-[
  *(((int*)self.pointer) + offset) = value;
  ]-*/;

  /** Sets the value of a float in the buffer. */
  native void setFloatValue(int offset, float value)
  /*-[
  *(((float*)self.pointer) + offset) = value;
  ]-*/;

  /** Sets the values of bytes in the buffer. */
  native void setByteValues(int offset, int count, byte[] values, int index)
  /*-[
  [values getBytes:(((char*)self.pointer) + offset) offset:index length:count];
  ]-*/;

  /** Sets the values of shorts in the buffer. */
  void setShortValues(int offset, int count, short[] values, int index) {
    for (int i = 0, size = count; i < size; ++i)
      setShortValue(offset + i, values[index + i]);
  }

  /** Sets the values of integers in the buffer. */
  void setIntegerValues(int offset, int count, int[] values, int index) {
    for (int i = 0, size = count; i < size; ++i)
      setIntegerValue(offset + i, values[index + i]);
  }

  /** Sets the values of floats in the buffer. */
  void setFloatValues(int offset, int count, float[] values, int index) {
    for (int i = 0, size = count; i < size; ++i)
      setFloatValue(offset + i, values[index + i]);
  }

  /* @see Object#finalize() */
  @Override
  protected void finalize() throws Throwable {
    long pointer = this.pointer;
    super.finalize();
    free(pointer);
  }

}

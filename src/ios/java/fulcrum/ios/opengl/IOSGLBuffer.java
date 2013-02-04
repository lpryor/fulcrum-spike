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

/*-[
 #include "ES2/gl.h"
 ]-*/

/**
 * A wrapper around an iOS memory buffer.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class IOSGLBuffer {

  /** Allocates memory for use in the OpenGL layer. */
  private static native int calloc(int size)
  /*-[
    return (int) (calloc(size, sizeof(GLbyte)));
  ]-*/;

  /** Deallocates memory used in the OpenGL layer. */
  private static native void free(int pointer)
  /*-[
      free((void*)pointer);
  ]-*/;

  /** The pointer to the buffer. */
  private final int pointer;

  /** Creates a new IOSGLPointer. */
  IOSGLBuffer(int size) {
    pointer = calloc(size);
    if (pointer == 0)
      throw new IllegalStateException(Strings.format("Failed to allocate %s bytes.", size));
  }

  /** Returns a pointer to a byte in the buffer. */
  native int bytePointer(int offset)
  /*-[
  return (int)(((GLbyte*)self.pointer) + offset);
  ]-*/;

  /** Returns a pointer to a short in the buffer. */
  native int shortPointer(int offset)
  /*-[
  return (int)(((GLshort*)self.pointer) + offset);
  ]-*/;

  /** Returns a pointer to an integer in the buffer. */
  native int integerPointer(int offset)
  /*-[
  return (int)(((GLint*)self.pointer) + offset);
  ]-*/;

  /** Returns a pointer to a float in the buffer. */
  native int floatPointer(int offset)
  /*-[
  return (int)(((GLfloat*)self.pointer) + offset);
  ]-*/;

  /** Returns the value of a byte in the buffer. */
  native byte byteValue(int offset)
  /*-[
  return ((GLbyte*)self.pointer)[offset];
  ]-*/;

  /** Returns the value of a short in the buffer. */
  native short shortValue(int offset)
  /*-[
  return ((GLshort*)self.pointer)[offset];
  ]-*/;

  /** Returns the value of an integer in the buffer. */
  native int integerValue(int offset)
  /*-[
  return ((GLint*)self.pointer)[offset];
  ]-*/;

  /** Returns the value of a float in the buffer. */
  native float floatValue(int offset)
  /*-[
  return ((GLfloat*)self.pointer)[offset];
  ]-*/;

  /** Returns the value of bytes in the buffer. */
  native void byteValues(int offset, int count, byte[] values, int index)
  /*-[
  [values getBytes:(((char*)self.pointer) + offset) offset:index length:count];
  ]-*/;

  /** Sets the value of a byte in the buffer. */
  native void setByteValue(int offset, byte value)
  /*-[
  ((GLbyte*)self.pointer)[offset] = value;
  ]-*/;

  /** Sets the value of a short in the buffer. */
  native void setShortValue(int offset, short value)
  /*-[
  ((GLshort*)self.pointer)[offset] = value;
  ]-*/;

  /** Sets the value of an integer in the buffer. */
  native void setIntegerValue(int offset, int value)
  /*-[
  ((GLint*)self.pointer)[offset] = value;
  ]-*/;

  /** Sets the value of a float in the buffer. */
  native void setFloatValue(int offset, float value)
  /*-[
  ((GLfloat*)self.pointer)[offset] = value;
  ]-*/;

  /** Sets the values of bytes in the buffer. */
  native void setByteValues(int offset, int count, byte[] values, int index)
  /*-[
  [values replaceBytes:(((char*)self.pointer) + offset) length:count offset:index];
  ]-*/;

  /* @see Object#finalize() */
  @Override
  protected void finalize() throws Throwable {
    int pointer = this.pointer;
    super.finalize();
    free(pointer);
  }

}

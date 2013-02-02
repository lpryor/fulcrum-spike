/*
 * GLBytes.java
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
package fulcrum.opengl;

/**
 * Definition of an OpenGL {@code byte} data set.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public abstract class GLBytes {

  /** Returns the number of items in this data set. */
  public abstract int size();

  /** Returns the value in this data set at the specified index. */
  public abstract byte get(int index);

  /** Sets the value in this data set at the specified index. */
  public abstract void set(int index, byte value);

  /** Assigns values from this data set to the supplied array. */
  public final void copyTo(byte[] array) {
    copyTo(array, 0);
  }

  /** Assigns values from this data set to the supplied array at the specified offset. */
  public abstract void copyTo(byte[] array, int offset);

  /** Assigns values from the supplied array to this data set. */
  public final void copyFrom(byte[] array) {
    copyFrom(array, 0);
  }

  /** Assigns values from the supplied array at the specified offset to this data set. */
  public abstract void copyFrom(byte[] array, int offset);

  /** Returns a view of this data set restricted to the sub-range {@code [0, endIndex)}. */
  public final GLBytes headData(int endIndex) {
    return subData(0, endIndex);
  }

  /** Returns a view of this data set restricted to the sub-range {@code [beginIndex, size())}. */
  public final GLBytes tailData(int beginIndex) {
    return subData(beginIndex, size());
  }

  /** Returns a view of this data set restricted to the sub-range {@code [beginIndex, endIndex)}. */
  public abstract GLBytes subData(int beginIndex, int endIndex);

  /** Returns a {@link GLShorts} view of this data set. */
  public abstract GLShorts asShorts();

  /** Returns a {@link GLIntegers} view of this data set. */
  public abstract GLIntegers asIntegers();

  /** Returns a {@link GLFloats} view of this data set. */
  public abstract GLFloats asFloats();

}

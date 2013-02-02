/*
 * GLShorts.java
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
 * Definition of an OpenGL {@code short} data set.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public abstract class GLShorts {

  /** Returns the number of items in this data set. */
  public abstract int size();

  /** Returns the value in this data set at the specified index. */
  public abstract short get(int index);

  /** Sets the value in this data set at the specified index. */
  public abstract void set(int index, short value);

  /** Assigns values from this data set to the supplied array. */
  public final void copyTo(short[] array) {
    copyTo(array, 0);
  }

  /** Assigns values from this data set to the supplied array at the specified offset. */
  public abstract void copyTo(short[] array, int offset);

  /** Assigns values from the supplied array to this data set. */
  public final void copyFrom(short[] array) {
    copyFrom(array, 0);
  }

  /** Assigns values from the supplied array at the specified offset to this data set. */
  public abstract void copyFrom(short[] array, int offset);

  /** Returns a view of this data set restricted to the sub-range {@code [0, endIndex)}. */
  public final GLShorts headData(int endIndex) {
    return subData(0, endIndex);
  }

  /** Returns a view of this data set restricted to the sub-range {@code [beginIndex, size())}. */
  public final GLShorts tailData(int beginIndex) {
    return subData(beginIndex, size());
  }

  /** Returns a view of this data set restricted to the sub-range {@code [beginIndex, endIndex)}. */
  public abstract GLShorts subData(int beginIndex, int endIndex);

  /** Returns a {@link GLBytes} view of this data set. */
  public abstract GLBytes asBytes();

}

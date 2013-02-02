/*
 * GLVariable.java
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
 * Information about a shader program variable.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class GLVariable {

  /** The name of this variable. */
  private final String name;
  /** The size of this variable. */
  private final int size;
  /** The type of this variable. */
  private final int type;

  /** Creates a new OpenGL variable. */
  public GLVariable(String name, int size, int type) {
    this.name = name;
    this.size = size;
    this.type = type;
  }

  /** Returns the name of this variable. */
  public String name() {
    return name;
  }

  /** Returns the size of this variable. */
  public int size() {
    return size;
  }

  /** Returns the type of this variable. */
  public int type() {
    return type;
  }

}

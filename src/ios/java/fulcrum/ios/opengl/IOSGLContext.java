/*
 * IOSGLContext.java
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
import fulcrum.opengl.GLContext;
import fulcrum.opengl.GLFloats;
import fulcrum.opengl.GLIntegers;
import fulcrum.opengl.GLPrecision;
import fulcrum.opengl.GLShorts;
import fulcrum.opengl.GLVariable;

/**
 * An iOS-specific implementation of {@link GLContext}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class IOSGLContext extends GLContext {

  //
  // Accessors for the OpenGL constants.
  //

  /* @see fulcrum.opengl.GLContext#ACTIVE_ATTRIBUTE_MAX_LENGTH() */
  @Override
  public int ACTIVE_ATTRIBUTE_MAX_LENGTH() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ACTIVE_ATTRIBUTES() */
  @Override
  public int ACTIVE_ATTRIBUTES() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ACTIVE_TEXTURE() */
  @Override
  public int ACTIVE_TEXTURE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ACTIVE_UNIFORM_MAX_LENGTH() */
  @Override
  public int ACTIVE_UNIFORM_MAX_LENGTH() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ACTIVE_UNIFORMS() */
  @Override
  public int ACTIVE_UNIFORMS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ALIASED_LINE_WIDTH_RANGE() */
  @Override
  public int ALIASED_LINE_WIDTH_RANGE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ALIASED_POINT_SIZE_RANGE() */
  @Override
  public int ALIASED_POINT_SIZE_RANGE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ALPHA() */
  @Override
  public int ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ALPHA_BITS() */
  @Override
  public int ALPHA_BITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ALWAYS() */
  @Override
  public int ALWAYS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ARRAY_BUFFER() */
  @Override
  public int ARRAY_BUFFER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ARRAY_BUFFER_BINDING() */
  @Override
  public int ARRAY_BUFFER_BINDING() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ATTACHED_SHADERS() */
  @Override
  public int ATTACHED_SHADERS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BACK() */
  @Override
  public int BACK() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BLEND() */
  @Override
  public int BLEND() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BLEND_COLOR() */
  @Override
  public int BLEND_COLOR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BLEND_DST_ALPHA() */
  @Override
  public int BLEND_DST_ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BLEND_DST_RGB() */
  @Override
  public int BLEND_DST_RGB() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BLEND_EQUATION() */
  @Override
  public int BLEND_EQUATION() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BLEND_EQUATION_ALPHA() */
  @Override
  public int BLEND_EQUATION_ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BLEND_EQUATION_RGB() */
  @Override
  public int BLEND_EQUATION_RGB() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BLEND_SRC_ALPHA() */
  @Override
  public int BLEND_SRC_ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BLEND_SRC_RGB() */
  @Override
  public int BLEND_SRC_RGB() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BLUE_BITS() */
  @Override
  public int BLUE_BITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BOOL() */
  @Override
  public int BOOL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BOOL_VEC2() */
  @Override
  public int BOOL_VEC2() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BOOL_VEC3() */
  @Override
  public int BOOL_VEC3() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BOOL_VEC4() */
  @Override
  public int BOOL_VEC4() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BUFFER_SIZE() */
  @Override
  public int BUFFER_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BUFFER_USAGE() */
  @Override
  public int BUFFER_USAGE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#BYTE() */
  @Override
  public int BYTE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#CCW() */
  @Override
  public int CCW() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#CLAMP_TO_EDGE() */
  @Override
  public int CLAMP_TO_EDGE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#COLOR_ATTACHMENT0() */
  @Override
  public int COLOR_ATTACHMENT0() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#COLOR_BUFFER_BIT() */
  @Override
  public int COLOR_BUFFER_BIT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#COLOR_CLEAR_VALUE() */
  @Override
  public int COLOR_CLEAR_VALUE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#COLOR_WRITEMASK() */
  @Override
  public int COLOR_WRITEMASK() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#COMPILE_STATUS() */
  @Override
  public int COMPILE_STATUS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#COMPRESSED_TEXTURE_FORMATS() */
  @Override
  public int COMPRESSED_TEXTURE_FORMATS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#CONSTANT_ALPHA() */
  @Override
  public int CONSTANT_ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#CONSTANT_COLOR() */
  @Override
  public int CONSTANT_COLOR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#CULL_FACE() */
  @Override
  public int CULL_FACE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#CULL_FACE_MODE() */
  @Override
  public int CULL_FACE_MODE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#CURRENT_PROGRAM() */
  @Override
  public int CURRENT_PROGRAM() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#CURRENT_VERTEX_ATTRIB() */
  @Override
  public int CURRENT_VERTEX_ATTRIB() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#CW() */
  @Override
  public int CW() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DECR() */
  @Override
  public int DECR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DECR_WRAP() */
  @Override
  public int DECR_WRAP() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DELETE_STATUS() */
  @Override
  public int DELETE_STATUS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DEPTH_ATTACHMENT() */
  @Override
  public int DEPTH_ATTACHMENT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DEPTH_BITS() */
  @Override
  public int DEPTH_BITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DEPTH_BUFFER_BIT() */
  @Override
  public int DEPTH_BUFFER_BIT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DEPTH_CLEAR_VALUE() */
  @Override
  public int DEPTH_CLEAR_VALUE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DEPTH_COMPONENT() */
  @Override
  public int DEPTH_COMPONENT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DEPTH_COMPONENT16() */
  @Override
  public int DEPTH_COMPONENT16() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DEPTH_FUNC() */
  @Override
  public int DEPTH_FUNC() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DEPTH_RANGE() */
  @Override
  public int DEPTH_RANGE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DEPTH_TEST() */
  @Override
  public int DEPTH_TEST() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DEPTH_WRITEMASK() */
  @Override
  public int DEPTH_WRITEMASK() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DITHER() */
  @Override
  public int DITHER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DONT_CARE() */
  @Override
  public int DONT_CARE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DST_ALPHA() */
  @Override
  public int DST_ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DST_COLOR() */
  @Override
  public int DST_COLOR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#DYNAMIC_DRAW() */
  @Override
  public int DYNAMIC_DRAW() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ELEMENT_ARRAY_BUFFER() */
  @Override
  public int ELEMENT_ARRAY_BUFFER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ELEMENT_ARRAY_BUFFER_BINDING() */
  @Override
  public int ELEMENT_ARRAY_BUFFER_BINDING() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#EQUAL() */
  @Override
  public int EQUAL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#EXTENSIONS() */
  @Override
  public int EXTENSIONS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FALSE() */
  @Override
  public int FALSE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FASTEST() */
  @Override
  public int FASTEST() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FIXED() */
  @Override
  public int FIXED() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FLOAT() */
  @Override
  public int FLOAT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FLOAT_MAT2() */
  @Override
  public int FLOAT_MAT2() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FLOAT_MAT3() */
  @Override
  public int FLOAT_MAT3() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FLOAT_MAT4() */
  @Override
  public int FLOAT_MAT4() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FLOAT_VEC2() */
  @Override
  public int FLOAT_VEC2() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FLOAT_VEC3() */
  @Override
  public int FLOAT_VEC3() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FLOAT_VEC4() */
  @Override
  public int FLOAT_VEC4() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAGMENT_SHADER() */
  @Override
  public int FRAGMENT_SHADER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER() */
  @Override
  public int FRAMEBUFFER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER_ATTACHMENT_OBJECT_NAME() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_OBJECT_NAME() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER_BINDING() */
  @Override
  public int FRAMEBUFFER_BINDING() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER_COMPLETE() */
  @Override
  public int FRAMEBUFFER_COMPLETE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER_INCOMPLETE_ATTACHMENT() */
  @Override
  public int FRAMEBUFFER_INCOMPLETE_ATTACHMENT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER_INCOMPLETE_DIMENSIONS() */
  @Override
  public int FRAMEBUFFER_INCOMPLETE_DIMENSIONS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT() */
  @Override
  public int FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRAMEBUFFER_UNSUPPORTED() */
  @Override
  public int FRAMEBUFFER_UNSUPPORTED() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRONT() */
  @Override
  public int FRONT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRONT_AND_BACK() */
  @Override
  public int FRONT_AND_BACK() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FRONT_FACE() */
  @Override
  public int FRONT_FACE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FUNC_ADD() */
  @Override
  public int FUNC_ADD() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FUNC_REVERSE_SUBTRACT() */
  @Override
  public int FUNC_REVERSE_SUBTRACT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#FUNC_SUBTRACT() */
  @Override
  public int FUNC_SUBTRACT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#GENERATE_MIPMAP_HINT() */
  @Override
  public int GENERATE_MIPMAP_HINT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#GEQUAL() */
  @Override
  public int GEQUAL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#GREATER() */
  @Override
  public int GREATER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#GREEN_BITS() */
  @Override
  public int GREEN_BITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#HIGH_FLOAT() */
  @Override
  public int HIGH_FLOAT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#HIGH_INT() */
  @Override
  public int HIGH_INT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#IMPLEMENTATION_COLOR_READ_FORMAT() */
  @Override
  public int IMPLEMENTATION_COLOR_READ_FORMAT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#IMPLEMENTATION_COLOR_READ_TYPE() */
  @Override
  public int IMPLEMENTATION_COLOR_READ_TYPE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INCR() */
  @Override
  public int INCR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INCR_WRAP() */
  @Override
  public int INCR_WRAP() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INFO_LOG_LENGTH() */
  @Override
  public int INFO_LOG_LENGTH() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INT() */
  @Override
  public int INT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INT_VEC2() */
  @Override
  public int INT_VEC2() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INT_VEC3() */
  @Override
  public int INT_VEC3() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INT_VEC4() */
  @Override
  public int INT_VEC4() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INVALID_ENUM() */
  @Override
  public int INVALID_ENUM() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INVALID_FRAMEBUFFER_OPERATION() */
  @Override
  public int INVALID_FRAMEBUFFER_OPERATION() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INVALID_OPERATION() */
  @Override
  public int INVALID_OPERATION() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INVALID_VALUE() */
  @Override
  public int INVALID_VALUE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#INVERT() */
  @Override
  public int INVERT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#KEEP() */
  @Override
  public int KEEP() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LEQUAL() */
  @Override
  public int LEQUAL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LESS() */
  @Override
  public int LESS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LINE_LOOP() */
  @Override
  public int LINE_LOOP() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LINE_STRIP() */
  @Override
  public int LINE_STRIP() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LINE_WIDTH() */
  @Override
  public int LINE_WIDTH() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LINEAR() */
  @Override
  public int LINEAR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LINEAR_MIPMAP_LINEAR() */
  @Override
  public int LINEAR_MIPMAP_LINEAR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LINEAR_MIPMAP_NEAREST() */
  @Override
  public int LINEAR_MIPMAP_NEAREST() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LINES() */
  @Override
  public int LINES() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LINK_STATUS() */
  @Override
  public int LINK_STATUS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LOW_FLOAT() */
  @Override
  public int LOW_FLOAT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LOW_INT() */
  @Override
  public int LOW_INT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LUMINANCE() */
  @Override
  public int LUMINANCE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#LUMINANCE_ALPHA() */
  @Override
  public int LUMINANCE_ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_COMBINED_TEXTURE_IMAGE_UNITS() */
  @Override
  public int MAX_COMBINED_TEXTURE_IMAGE_UNITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_CUBE_MAP_TEXTURE_SIZE() */
  @Override
  public int MAX_CUBE_MAP_TEXTURE_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_FRAGMENT_UNIFORM_VECTORS() */
  @Override
  public int MAX_FRAGMENT_UNIFORM_VECTORS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_RENDERBUFFER_SIZE() */
  @Override
  public int MAX_RENDERBUFFER_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_TEXTURE_IMAGE_UNITS() */
  @Override
  public int MAX_TEXTURE_IMAGE_UNITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_TEXTURE_SIZE() */
  @Override
  public int MAX_TEXTURE_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_VARYING_VECTORS() */
  @Override
  public int MAX_VARYING_VECTORS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_VERTEX_ATTRIBS() */
  @Override
  public int MAX_VERTEX_ATTRIBS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_VERTEX_TEXTURE_IMAGE_UNITS() */
  @Override
  public int MAX_VERTEX_TEXTURE_IMAGE_UNITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_VERTEX_UNIFORM_VECTORS() */
  @Override
  public int MAX_VERTEX_UNIFORM_VECTORS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MAX_VIEWPORT_DIMS() */
  @Override
  public int MAX_VIEWPORT_DIMS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MEDIUM_FLOAT() */
  @Override
  public int MEDIUM_FLOAT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MEDIUM_INT() */
  @Override
  public int MEDIUM_INT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#MIRRORED_REPEAT() */
  @Override
  public int MIRRORED_REPEAT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#NEAREST() */
  @Override
  public int NEAREST() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#NEAREST_MIPMAP_LINEAR() */
  @Override
  public int NEAREST_MIPMAP_LINEAR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#NEAREST_MIPMAP_NEAREST() */
  @Override
  public int NEAREST_MIPMAP_NEAREST() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#NEVER() */
  @Override
  public int NEVER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#NICEST() */
  @Override
  public int NICEST() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#NO_ERROR() */
  @Override
  public int NO_ERROR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#NONE() */
  @Override
  public int NONE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#NOTEQUAL() */
  @Override
  public int NOTEQUAL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#NUM_COMPRESSED_TEXTURE_FORMATS() */
  @Override
  public int NUM_COMPRESSED_TEXTURE_FORMATS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#NUM_SHADER_BINARY_FORMATS() */
  @Override
  public int NUM_SHADER_BINARY_FORMATS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ONE() */
  @Override
  public int ONE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ONE_MINUS_CONSTANT_ALPHA() */
  @Override
  public int ONE_MINUS_CONSTANT_ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ONE_MINUS_CONSTANT_COLOR() */
  @Override
  public int ONE_MINUS_CONSTANT_COLOR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ONE_MINUS_DST_ALPHA() */
  @Override
  public int ONE_MINUS_DST_ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ONE_MINUS_DST_COLOR() */
  @Override
  public int ONE_MINUS_DST_COLOR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ONE_MINUS_SRC_ALPHA() */
  @Override
  public int ONE_MINUS_SRC_ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ONE_MINUS_SRC_COLOR() */
  @Override
  public int ONE_MINUS_SRC_COLOR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#OUT_OF_MEMORY() */
  @Override
  public int OUT_OF_MEMORY() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#PACK_ALIGNMENT() */
  @Override
  public int PACK_ALIGNMENT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#POINTS() */
  @Override
  public int POINTS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#POLYGON_OFFSET_FACTOR() */
  @Override
  public int POLYGON_OFFSET_FACTOR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#POLYGON_OFFSET_FILL() */
  @Override
  public int POLYGON_OFFSET_FILL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#POLYGON_OFFSET_UNITS() */
  @Override
  public int POLYGON_OFFSET_UNITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RED_BITS() */
  @Override
  public int RED_BITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER() */
  @Override
  public int RENDERBUFFER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER_ALPHA_SIZE() */
  @Override
  public int RENDERBUFFER_ALPHA_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER_BINDING() */
  @Override
  public int RENDERBUFFER_BINDING() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER_BLUE_SIZE() */
  @Override
  public int RENDERBUFFER_BLUE_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER_DEPTH_SIZE() */
  @Override
  public int RENDERBUFFER_DEPTH_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER_GREEN_SIZE() */
  @Override
  public int RENDERBUFFER_GREEN_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER_HEIGHT() */
  @Override
  public int RENDERBUFFER_HEIGHT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER_INTERNAL_FORMAT() */
  @Override
  public int RENDERBUFFER_INTERNAL_FORMAT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER_RED_SIZE() */
  @Override
  public int RENDERBUFFER_RED_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER_STENCIL_SIZE() */
  @Override
  public int RENDERBUFFER_STENCIL_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERBUFFER_WIDTH() */
  @Override
  public int RENDERBUFFER_WIDTH() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RENDERER() */
  @Override
  public int RENDERER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#REPEAT() */
  @Override
  public int REPEAT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#REPLACE() */
  @Override
  public int REPLACE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RGB() */
  @Override
  public int RGB() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RGB5_A1() */
  @Override
  public int RGB5_A1() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RGB565() */
  @Override
  public int RGB565() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RGBA() */
  @Override
  public int RGBA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#RGBA4() */
  @Override
  public int RGBA4() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SAMPLE_ALPHA_TO_COVERAGE() */
  @Override
  public int SAMPLE_ALPHA_TO_COVERAGE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SAMPLE_BUFFERS() */
  @Override
  public int SAMPLE_BUFFERS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SAMPLE_COVERAGE() */
  @Override
  public int SAMPLE_COVERAGE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SAMPLE_COVERAGE_INVERT() */
  @Override
  public int SAMPLE_COVERAGE_INVERT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SAMPLE_COVERAGE_VALUE() */
  @Override
  public int SAMPLE_COVERAGE_VALUE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SAMPLER_2D() */
  @Override
  public int SAMPLER_2D() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SAMPLER_CUBE() */
  @Override
  public int SAMPLER_CUBE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SAMPLES() */
  @Override
  public int SAMPLES() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SCISSOR_BOX() */
  @Override
  public int SCISSOR_BOX() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SCISSOR_TEST() */
  @Override
  public int SCISSOR_TEST() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SHADER_BINARY_FORMATS() */
  @Override
  public int SHADER_BINARY_FORMATS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SHADER_COMPILER() */
  @Override
  public int SHADER_COMPILER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SHADER_SOURCE_LENGTH() */
  @Override
  public int SHADER_SOURCE_LENGTH() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SHADER_TYPE() */
  @Override
  public int SHADER_TYPE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SHADING_LANGUAGE_VERSION() */
  @Override
  public int SHADING_LANGUAGE_VERSION() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SHORT() */
  @Override
  public int SHORT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SRC_ALPHA() */
  @Override
  public int SRC_ALPHA() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SRC_ALPHA_SATURATE() */
  @Override
  public int SRC_ALPHA_SATURATE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SRC_COLOR() */
  @Override
  public int SRC_COLOR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STATIC_DRAW() */
  @Override
  public int STATIC_DRAW() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_ATTACHMENT() */
  @Override
  public int STENCIL_ATTACHMENT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_BACK_FAIL() */
  @Override
  public int STENCIL_BACK_FAIL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_BACK_FUNC() */
  @Override
  public int STENCIL_BACK_FUNC() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_BACK_PASS_DEPTH_FAIL() */
  @Override
  public int STENCIL_BACK_PASS_DEPTH_FAIL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_BACK_PASS_DEPTH_PASS() */
  @Override
  public int STENCIL_BACK_PASS_DEPTH_PASS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_BACK_REF() */
  @Override
  public int STENCIL_BACK_REF() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_BACK_VALUE_MASK() */
  @Override
  public int STENCIL_BACK_VALUE_MASK() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_BACK_WRITEMASK() */
  @Override
  public int STENCIL_BACK_WRITEMASK() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_BITS() */
  @Override
  public int STENCIL_BITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_BUFFER_BIT() */
  @Override
  public int STENCIL_BUFFER_BIT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_CLEAR_VALUE() */
  @Override
  public int STENCIL_CLEAR_VALUE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_FAIL() */
  @Override
  public int STENCIL_FAIL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_FUNC() */
  @Override
  public int STENCIL_FUNC() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_INDEX() */
  @Override
  public int STENCIL_INDEX() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_INDEX8() */
  @Override
  public int STENCIL_INDEX8() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_PASS_DEPTH_FAIL() */
  @Override
  public int STENCIL_PASS_DEPTH_FAIL() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_PASS_DEPTH_PASS() */
  @Override
  public int STENCIL_PASS_DEPTH_PASS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_REF() */
  @Override
  public int STENCIL_REF() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_TEST() */
  @Override
  public int STENCIL_TEST() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_VALUE_MASK() */
  @Override
  public int STENCIL_VALUE_MASK() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STENCIL_WRITEMASK() */
  @Override
  public int STENCIL_WRITEMASK() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#STREAM_DRAW() */
  @Override
  public int STREAM_DRAW() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#SUBPIXEL_BITS() */
  @Override
  public int SUBPIXEL_BITS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE() */
  @Override
  public int TEXTURE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_2D() */
  @Override
  public int TEXTURE_2D() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_BINDING_2D() */
  @Override
  public int TEXTURE_BINDING_2D() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_BINDING_CUBE_MAP() */
  @Override
  public int TEXTURE_BINDING_CUBE_MAP() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_CUBE_MAP() */
  @Override
  public int TEXTURE_CUBE_MAP() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_CUBE_MAP_NEGATIVE_X() */
  @Override
  public int TEXTURE_CUBE_MAP_NEGATIVE_X() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_CUBE_MAP_NEGATIVE_Y() */
  @Override
  public int TEXTURE_CUBE_MAP_NEGATIVE_Y() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_CUBE_MAP_NEGATIVE_Z() */
  @Override
  public int TEXTURE_CUBE_MAP_NEGATIVE_Z() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_CUBE_MAP_POSITIVE_X() */
  @Override
  public int TEXTURE_CUBE_MAP_POSITIVE_X() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_CUBE_MAP_POSITIVE_Y() */
  @Override
  public int TEXTURE_CUBE_MAP_POSITIVE_Y() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_CUBE_MAP_POSITIVE_Z() */
  @Override
  public int TEXTURE_CUBE_MAP_POSITIVE_Z() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_MAG_FILTER() */
  @Override
  public int TEXTURE_MAG_FILTER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_MIN_FILTER() */
  @Override
  public int TEXTURE_MIN_FILTER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_WRAP_S() */
  @Override
  public int TEXTURE_WRAP_S() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE_WRAP_T() */
  @Override
  public int TEXTURE_WRAP_T() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE0() */
  @Override
  public int TEXTURE0() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE1() */
  @Override
  public int TEXTURE1() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE2() */
  @Override
  public int TEXTURE2() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE3() */
  @Override
  public int TEXTURE3() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE4() */
  @Override
  public int TEXTURE4() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE5() */
  @Override
  public int TEXTURE5() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE6() */
  @Override
  public int TEXTURE6() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE7() */
  @Override
  public int TEXTURE7() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE8() */
  @Override
  public int TEXTURE8() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE9() */
  @Override
  public int TEXTURE9() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE10() */
  @Override
  public int TEXTURE10() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE11() */
  @Override
  public int TEXTURE11() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE12() */
  @Override
  public int TEXTURE12() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE13() */
  @Override
  public int TEXTURE13() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE14() */
  @Override
  public int TEXTURE14() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE15() */
  @Override
  public int TEXTURE15() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE16() */
  @Override
  public int TEXTURE16() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE17() */
  @Override
  public int TEXTURE17() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE18() */
  @Override
  public int TEXTURE18() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE19() */
  @Override
  public int TEXTURE19() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE20() */
  @Override
  public int TEXTURE20() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE21() */
  @Override
  public int TEXTURE21() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE22() */
  @Override
  public int TEXTURE22() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE23() */
  @Override
  public int TEXTURE23() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE24() */
  @Override
  public int TEXTURE24() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE25() */
  @Override
  public int TEXTURE25() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE26() */
  @Override
  public int TEXTURE26() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE27() */
  @Override
  public int TEXTURE27() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE28() */
  @Override
  public int TEXTURE28() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE29() */
  @Override
  public int TEXTURE29() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE30() */
  @Override
  public int TEXTURE30() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TEXTURE31() */
  @Override
  public int TEXTURE31() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TRIANGLE_FAN() */
  @Override
  public int TRIANGLE_FAN() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TRIANGLE_STRIP() */
  @Override
  public int TRIANGLE_STRIP() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TRIANGLES() */
  @Override
  public int TRIANGLES() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#TRUE() */
  @Override
  public int TRUE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#UNPACK_ALIGNMENT() */
  @Override
  public int UNPACK_ALIGNMENT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#UNSIGNED_BYTE() */
  @Override
  public int UNSIGNED_BYTE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#UNSIGNED_INT() */
  @Override
  public int UNSIGNED_INT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#UNSIGNED_SHORT() */
  @Override
  public int UNSIGNED_SHORT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#UNSIGNED_SHORT_4_4_4_4() */
  @Override
  public int UNSIGNED_SHORT_4_4_4_4() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#UNSIGNED_SHORT_5_5_5_1() */
  @Override
  public int UNSIGNED_SHORT_5_5_5_1() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#UNSIGNED_SHORT_5_6_5() */
  @Override
  public int UNSIGNED_SHORT_5_6_5() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VALIDATE_STATUS() */
  @Override
  public int VALIDATE_STATUS() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VENDOR() */
  @Override
  public int VENDOR() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VERSION() */
  @Override
  public int VERSION() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VERTEX_ATTRIB_ARRAY_BUFFER_BINDING() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_BUFFER_BINDING() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VERTEX_ATTRIB_ARRAY_ENABLED() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_ENABLED() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VERTEX_ATTRIB_ARRAY_NORMALIZED() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_NORMALIZED() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VERTEX_ATTRIB_ARRAY_POINTER() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_POINTER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VERTEX_ATTRIB_ARRAY_SIZE() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_SIZE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VERTEX_ATTRIB_ARRAY_STRIDE() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_STRIDE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VERTEX_ATTRIB_ARRAY_TYPE() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_TYPE() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VERTEX_SHADER() */
  @Override
  public int VERTEX_SHADER() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#VIEWPORT() */
  @Override
  public int VIEWPORT() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#ZERO() */
  @Override
  public int ZERO() {
    // TODO Auto-generated method stub
    return 0;
  }

  //
  // Implementations of the OpenGL functions.
  //

  /* @see fulcrum.opengl.GLContext#activeTexture(int) */
  @Override
  public void activeTexture(int texture) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#attachShader(int, int) */
  @Override
  public void attachShader(int program, int shader) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#bindAttribLocation(int, int, java.lang.String) */
  @Override
  public void bindAttribLocation(int program, int index, String name) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#bindBuffer(int, int) */
  @Override
  public void bindBuffer(int target, int buffer) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#bindFramebuffer(int, int) */
  @Override
  public void bindFramebuffer(int target, int framebuffer) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#bindRenderbuffer(int, int) */
  @Override
  public void bindRenderbuffer(int target, int renderbuffer) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#bindTexture(int, int) */
  @Override
  public void bindTexture(int target, int texture) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#blendColor(float, float, float, float) */
  @Override
  public void blendColor(float red, float green, float blue, float alpha) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#blendEquation(int) */
  @Override
  public void blendEquation(int mode) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#blendEquationSeparate(int, int) */
  @Override
  public void blendEquationSeparate(int modeRGB, int modeAlpha) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#blendFunc(int, int) */
  @Override
  public void blendFunc(int sfactor, int dfactor) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#blendFuncSeparate(int, int, int, int) */
  @Override
  public void blendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#bufferData(int, int, int) */
  @Override
  public void bufferData(int target, int size, int usage) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#bufferData(int, fulcrum.opengl.GLBytes, int) */
  @Override
  public void bufferData(int target, GLBytes data, int usage) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#bufferSubData(int, int, fulcrum.opengl.GLBytes) */
  @Override
  public void bufferSubData(int target, int offset, GLBytes data) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#checkFramebufferStatus(int) */
  @Override
  public int checkFramebufferStatus(int target) {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#clear(int) */
  @Override
  public void clear(int mask) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#clearColor(float, float, float, float) */
  @Override
  public void clearColor(float red, float green, float blue, float alpha) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#clearDepthf(float) */
  @Override
  public void clearDepthf(float depth) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#clearStencil(int) */
  @Override
  public void clearStencil(int s) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#colorMask(boolean, boolean, boolean, boolean) */
  @Override
  public void colorMask(boolean red, boolean green, boolean blue, boolean alpha) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#compileShader(int) */
  @Override
  public void compileShader(int shader) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#compressedTexImage2D(int, int, int, int, int, int, fulcrum.opengl.GLBytes) */
  @Override
  public void compressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
      GLBytes data) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#compressedTexSubImage2D(int, int, int, int, int, int, int, fulcrum.opengl.GLBytes) */
  @Override
  public void compressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
      int format, GLBytes data) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#copyTexImage2D(int, int, int, int, int, int, int, int) */
  @Override
  public void copyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#copyTexSubImage2D(int, int, int, int, int, int, int, int) */
  @Override
  public void copyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#createProgram() */
  @Override
  public int createProgram() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#createShader(int) */
  @Override
  public int createShader(int shaderType) {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#cullFace(int) */
  @Override
  public void cullFace(int mode) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#deleteBuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteBuffers(GLIntegers buffers) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#deleteFramebuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteFramebuffers(GLIntegers framebuffers) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#deleteProgram(int) */
  @Override
  public void deleteProgram(int program) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#deleteRenderbuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteRenderbuffers(GLIntegers renderbuffers) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#deleteShader(int) */
  @Override
  public void deleteShader(int shader) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#deleteTextures(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteTextures(GLIntegers textures) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#depthFunc(int) */
  @Override
  public void depthFunc(int func) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#depthMask(boolean) */
  @Override
  public void depthMask(boolean flag) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#depthRangef(float, float) */
  @Override
  public void depthRangef(float nearVal, float farVal) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#detachShader(int, int) */
  @Override
  public void detachShader(int program, int shader) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#disable(int) */
  @Override
  public void disable(int cap) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#disableVertexAttribArray(int) */
  @Override
  public void disableVertexAttribArray(int index) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#drawArrays(int, int, int) */
  @Override
  public void drawArrays(int mode, int first, int count) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#drawElements(int, int, int, int) */
  @Override
  public void drawElements(int mode, int count, int type, int offset) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#drawElements(int, fulcrum.opengl.GLBytes) */
  @Override
  public void drawElements(int mode, GLBytes indices) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#drawElements(int, fulcrum.opengl.GLShorts) */
  @Override
  public void drawElements(int mode, GLShorts indices) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#enable(int) */
  @Override
  public void enable(int cap) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#enableVertexAttribArray(int) */
  @Override
  public void enableVertexAttribArray(int index) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#finish() */
  @Override
  public void finish() {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#flush() */
  @Override
  public void flush() {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#framebufferRenderbuffer(int, int, int, int) */
  @Override
  public void framebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#framebufferTexture2D(int, int, int, int, int) */
  @Override
  public void framebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#frontFace(int) */
  @Override
  public void frontFace(int mode) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#genBuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void genBuffers(GLIntegers buffers) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#genFramebuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void genFramebuffers(GLIntegers framebuffers) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#genRenderbuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void genRenderbuffers(GLIntegers renderbuffers) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#genTextures(fulcrum.opengl.GLIntegers) */
  @Override
  public void genTextures(GLIntegers textures) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#generateMipmap(int) */
  @Override
  public void generateMipmap(int target) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#getBooleanv(int) */
  @Override
  public boolean[] getBooleanv(int pname) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getFloatv(int) */
  @Override
  public float[] getFloatv(int pname) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getIntegerv(int) */
  @Override
  public int[] getIntegerv(int pname) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getActiveAttrib(int, int) */
  @Override
  public GLVariable getActiveAttrib(int program, int index) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getActiveUniform(int, int) */
  @Override
  public GLVariable getActiveUniform(int program, int index) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getAttachedShaders(int) */
  @Override
  public int[] getAttachedShaders(int program) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getAttribLocation(int, java.lang.String) */
  @Override
  public int getAttribLocation(int program, String name) {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#getBufferParameteriv(int, int) */
  @Override
  public int[] getBufferParameteriv(int target, int value) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getError() */
  @Override
  public int getError() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#getFramebufferAttachmentParameteriv(int, int, int) */
  @Override
  public int[] getFramebufferAttachmentParameteriv(int target, int attachment, int pname) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getProgramInfoLog(int) */
  @Override
  public String getProgramInfoLog(int program) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getProgramiv(int, int) */
  @Override
  public int[] getProgramiv(int program, int pname) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getRenderbufferParameteriv(int, int) */
  @Override
  public int[] getRenderbufferParameteriv(int target, int pname) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getShaderInfoLog(int) */
  @Override
  public String getShaderInfoLog(int shader) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getShaderPrecisionFormat(int, int) */
  @Override
  public GLPrecision getShaderPrecisionFormat(int shaderType, int precisionType) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getShaderSource(int) */
  @Override
  public String getShaderSource(int shader) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getShaderiv(int, int) */
  @Override
  public int[] getShaderiv(int shader, int pname) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getString(int) */
  @Override
  public String getString(int name) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getTexParameterfv(int, int) */
  @Override
  public float[] getTexParameterfv(int target, int pname) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getTexParameteriv(int, int) */
  @Override
  public int[] getTexParameteriv(int target, int pname) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @see fulcrum.opengl.GLContext#getUniformfv(int, int, fulcrum.opengl.GLFloats) */
  @Override
  public void getUniformfv(int program, int location, GLFloats params) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#getUniformiv(int, int, fulcrum.opengl.GLIntegers) */
  @Override
  public void getUniformiv(int program, int location, GLIntegers params) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#getUniformLocation(int, java.lang.String) */
  @Override
  public int getUniformLocation(int program, String name) {
    // TODO Auto-generated method stub
    return 0;
  }

  /* @see fulcrum.opengl.GLContext#getVertexAttribfv(int, int, fulcrum.opengl.GLFloats) */
  @Override
  public void getVertexAttribfv(int index, int pname, GLFloats params) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#getVertexAttribiv(int, int, fulcrum.opengl.GLIntegers) */
  @Override
  public void getVertexAttribiv(int index, int pname, GLIntegers params) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#hint(int, int) */
  @Override
  public void hint(int target, int mode) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#isBuffer(int) */
  @Override
  public boolean isBuffer(int buffer) {
    // TODO Auto-generated method stub
    return false;
  }

  /* @see fulcrum.opengl.GLContext#isEnabled(int) */
  @Override
  public boolean isEnabled(int cap) {
    // TODO Auto-generated method stub
    return false;
  }

  /* @see fulcrum.opengl.GLContext#isFramebuffer(int) */
  @Override
  public boolean isFramebuffer(int framebuffer) {
    // TODO Auto-generated method stub
    return false;
  }

  /* @see fulcrum.opengl.GLContext#isProgram(int) */
  @Override
  public boolean isProgram(int program) {
    // TODO Auto-generated method stub
    return false;
  }

  /* @see fulcrum.opengl.GLContext#isRenderbuffer(int) */
  @Override
  public boolean isRenderbuffer(int renderbuffer) {
    // TODO Auto-generated method stub
    return false;
  }

  /* @see fulcrum.opengl.GLContext#isShader(int) */
  @Override
  public boolean isShader(int shader) {
    // TODO Auto-generated method stub
    return false;
  }

  /* @see fulcrum.opengl.GLContext#isTexture(int) */
  @Override
  public boolean isTexture(int texture) {
    // TODO Auto-generated method stub
    return false;
  }

  /* @see fulcrum.opengl.GLContext#lineWidth(float) */
  @Override
  public void lineWidth(float width) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#linkProgram(int) */
  @Override
  public void linkProgram(int program) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#pixelStorei(int, int) */
  @Override
  public void pixelStorei(int pname, int param) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#polygonOffset(float, float) */
  @Override
  public void polygonOffset(float factor, float units) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#readPixels(int, int, int, int, int, fulcrum.opengl.GLBytes) */
  @Override
  public void readPixels(int x, int y, int width, int height, int format, GLBytes data) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#readPixels(int, int, int, int, int, int, fulcrum.opengl.GLShorts) */
  @Override
  public void readPixels(int x, int y, int width, int height, int format, int type, GLShorts data) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#releaseShaderCompiler() */
  @Override
  public void releaseShaderCompiler() {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#renderbufferStorage(int, int, int, int) */
  @Override
  public void renderbufferStorage(int target, int internalformat, int width, int height) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#sampleCoverage(float, boolean) */
  @Override
  public void sampleCoverage(float value, boolean invert) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#scissor(int, int, int, int) */
  @Override
  public void scissor(int x, int y, int width, int height) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#shaderBinary(fulcrum.opengl.GLIntegers, int, fulcrum.opengl.GLBytes) */
  @Override
  public void shaderBinary(GLIntegers shaders, int binaryformat, GLBytes binary) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#shaderSource(int, java.lang.String) */
  @Override
  public void shaderSource(int shader, String string) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#stencilFunc(int, int, int) */
  @Override
  public void stencilFunc(int func, int ref, int mask) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#stencilFuncSeparate(int, int, int, int) */
  @Override
  public void stencilFuncSeparate(int face, int func, int ref, int mask) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#stencilMask(int) */
  @Override
  public void stencilMask(int mask) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#stencilMaskSeparate(int, int) */
  @Override
  public void stencilMaskSeparate(int face, int mask) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#stencilOp(int, int, int) */
  @Override
  public void stencilOp(int sfail, int dpfail, int dppass) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#stencilOpSeparate(int, int, int, int) */
  @Override
  public void stencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#texImage2D(int, int, int, int, int, int, int, fulcrum.opengl.GLBytes) */
  @Override
  public void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
      GLBytes data) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#texImage2D(int, int, int, int, int, int, int, int, fulcrum.opengl.GLShorts) */
  @Override
  public void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
      int type, GLShorts data) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#texParameterf(int, int, float) */
  @Override
  public void texParameterf(int target, int pname, float param) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#texParameteri(int, int, int) */
  @Override
  public void texParameteri(int target, int pname, int param) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#texParameterfv(int, int, fulcrum.opengl.GLFloats) */
  @Override
  public void texParameterfv(int target, int pname, GLFloats params) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#texParameteriv(int, int, fulcrum.opengl.GLIntegers) */
  @Override
  public void texParameteriv(int target, int pname, GLIntegers params) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#texSubImage2D(int, int, int, int, int, int, int, fulcrum.opengl.GLBytes) */
  @Override
  public void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
      GLBytes data) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#texSubImage2D(int, int, int, int, int, int, int, int, fulcrum.opengl.GLShorts) */
  @Override
  public void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
      int type, GLShorts data) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform1f(int, float) */
  @Override
  public void uniform1f(int location, float v0) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform2f(int, float, float) */
  @Override
  public void uniform2f(int location, float v0, float v1) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform3f(int, float, float, float) */
  @Override
  public void uniform3f(int location, float v0, float v1, float v2) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform4f(int, float, float, float, float) */
  @Override
  public void uniform4f(int location, float v0, float v1, float v2, float v3) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform1i(int, int) */
  @Override
  public void uniform1i(int location, int v0) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform2i(int, int, int) */
  @Override
  public void uniform2i(int location, int v0, int v1) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform3i(int, int, int, int) */
  @Override
  public void uniform3i(int location, int v0, int v1, int v2) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform4i(int, int, int, int, int) */
  @Override
  public void uniform4i(int location, int v0, int v1, int v2, int v3) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform1fv(int, fulcrum.opengl.GLFloats) */
  @Override
  public void uniform1fv(int location, GLFloats value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform2fv(int, fulcrum.opengl.GLFloats) */
  @Override
  public void uniform2fv(int location, GLFloats value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform3fv(int, fulcrum.opengl.GLFloats) */
  @Override
  public void uniform3fv(int location, GLFloats value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform4fv(int, fulcrum.opengl.GLFloats) */
  @Override
  public void uniform4fv(int location, GLFloats value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform1iv(int, fulcrum.opengl.GLIntegers) */
  @Override
  public void uniform1iv(int location, GLIntegers value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform2iv(int, fulcrum.opengl.GLIntegers) */
  @Override
  public void uniform2iv(int location, GLIntegers value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform3iv(int, fulcrum.opengl.GLIntegers) */
  @Override
  public void uniform3iv(int location, GLIntegers value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniform4iv(int, fulcrum.opengl.GLIntegers) */
  @Override
  public void uniform4iv(int location, GLIntegers value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniformMatrix2fv(int, boolean, fulcrum.opengl.GLFloats) */
  @Override
  public void uniformMatrix2fv(int location, boolean transpose, GLFloats value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniformMatrix3fv(int, boolean, fulcrum.opengl.GLFloats) */
  @Override
  public void uniformMatrix3fv(int location, boolean transpose, GLFloats value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#uniformMatrix4fv(int, boolean, fulcrum.opengl.GLFloats) */
  @Override
  public void uniformMatrix4fv(int location, boolean transpose, GLFloats value) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#useProgram(int) */
  @Override
  public void useProgram(int program) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#validateProgram(int) */
  @Override
  public void validateProgram(int program) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttrib1f(int, float) */
  @Override
  public void vertexAttrib1f(int index, float v0) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttrib2f(int, float, float) */
  @Override
  public void vertexAttrib2f(int index, float v0, float v1) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttrib3f(int, float, float, float) */
  @Override
  public void vertexAttrib3f(int index, float v0, float v1, float v2) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttrib4f(int, float, float, float, float) */
  @Override
  public void vertexAttrib4f(int index, float v0, float v1, float v2, float v3) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttrib1fv(int, fulcrum.opengl.GLFloats) */
  @Override
  public void vertexAttrib1fv(int index, GLFloats v) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttrib2fv(int, fulcrum.opengl.GLFloats) */
  @Override
  public void vertexAttrib2fv(int index, GLFloats v) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttrib3fv(int, fulcrum.opengl.GLFloats) */
  @Override
  public void vertexAttrib3fv(int index, GLFloats v) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttrib4fv(int, fulcrum.opengl.GLFloats) */
  @Override
  public void vertexAttrib4fv(int index, GLFloats v) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttribPointer(int, int, int, boolean, int, int) */
  @Override
  public void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttribPointer(int, int, boolean, boolean, int, fulcrum.opengl.GLBytes) */
  @Override
  public void vertexAttribPointer(int index, int size, boolean isUnsigned, boolean normalized, int stride, GLBytes pointer) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttribPointer(int, int, boolean, boolean, int, fulcrum.opengl.GLShorts) */
  @Override
  public void vertexAttribPointer(int index, int size, boolean isUnsigned, boolean normalized, int stride,
      GLShorts pointer) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttribPointer(int, int, boolean, boolean, int, fulcrum.opengl.GLIntegers) */
  @Override
  public void vertexAttribPointer(int index, int size, boolean isUnsigned, boolean normalized, int stride,
      GLIntegers pointer) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#vertexAttribPointer(int, int, boolean, int, fulcrum.opengl.GLFloats) */
  @Override
  public void vertexAttribPointer(int index, int size, boolean normalized, int stride, GLFloats pointer) {
    // TODO Auto-generated method stub

  }

  /* @see fulcrum.opengl.GLContext#viewport(int, int, int, int) */
  @Override
  public void viewport(int x, int y, int width, int height) {
    // TODO Auto-generated method stub

  }

  //
  // Factory methods for the OpenGL data sets.
  //

  /* @see GLContext#allocateBytes(int) */
  @Override
  public GLBytes allocateBytes(int size) {
    assert size >= 0;
    return new IOSGLBytes(size);
  }

  /* @see GLContext#allocateShorts(int) */
  @Override
  public GLShorts allocateShorts(int size) {
    assert size >= 0;
    return new IOSGLShorts(size);
  }

  /* @see GLContext#allocateIntegers(int) */
  @Override
  public GLIntegers allocateIntegers(int size) {
    assert size >= 0;
    return new IOSGLIntegers(size);
  }

  /* @see GLContext#allocateFloats(int) */
  @Override
  public GLFloats allocateFloats(int size) {
    assert size >= 0;
    return new IOSGLFloats(size);
  }

}

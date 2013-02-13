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

/*-[
 #include "OpenGLES/ES2/gl.h"
 ]-*/

/**
 * An iOS-specific implementation of {@link GLContext}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class IOSGLContext extends GLContext {

  /** A byte buffer to use inside method implementations. */
  private final IOSGLBytes byteBuffer = new IOSGLBytes(4 * 4);
  /** An integer buffer to use inside method implementations. */
  private final IOSGLIntegers intBufferA = new IOSGLIntegers(2);
  /** Another integer buffer to use inside method implementations. */
  private final IOSGLIntegers intBufferB = new IOSGLIntegers(1);

  //
  // Accessors for the OpenGL constants.
  //

  /* @see GLContext#ACTIVE_ATTRIBUTE_MAX_LENGTH() */
  @Override
  public native int ACTIVE_ATTRIBUTE_MAX_LENGTH()
  /*-[
    return GL_ACTIVE_ATTRIBUTE_MAX_LENGTH;
  ]-*/;

  /* @see GLContext#ACTIVE_ATTRIBUTES() */
  @Override
  public native int ACTIVE_ATTRIBUTES()
  /*-[
    return GL_ACTIVE_ATTRIBUTES;
  ]-*/;

  /* @see GLContext#ACTIVE_TEXTURE() */
  @Override
  public native int ACTIVE_TEXTURE()
  /*-[
    return GL_ACTIVE_TEXTURE;
  ]-*/;

  /* @see GLContext#ACTIVE_UNIFORM_MAX_LENGTH() */
  @Override
  public native int ACTIVE_UNIFORM_MAX_LENGTH()
  /*-[
    return GL_ACTIVE_UNIFORM_MAX_LENGTH;
  ]-*/;

  /* @see GLContext#ACTIVE_UNIFORMS() */
  @Override
  public native int ACTIVE_UNIFORMS()
  /*-[
    return GL_ACTIVE_UNIFORMS;
  ]-*/;

  /* @see GLContext#ALIASED_LINE_WIDTH_RANGE() */
  @Override
  public native int ALIASED_LINE_WIDTH_RANGE()
  /*-[
    return GL_ALIASED_LINE_WIDTH_RANGE;
  ]-*/;

  /* @see GLContext#ALIASED_POINT_SIZE_RANGE() */
  @Override
  public native int ALIASED_POINT_SIZE_RANGE()
  /*-[
    return GL_ALIASED_POINT_SIZE_RANGE;
  ]-*/;

  /* @see GLContext#ALPHA() */
  @Override
  public native int ALPHA()
  /*-[
    return GL_ALPHA;
  ]-*/;

  /* @see GLContext#ALPHA_BITS() */
  @Override
  public native int ALPHA_BITS()
  /*-[
    return GL_ALPHA_BITS;
  ]-*/;

  /* @see GLContext#ALWAYS() */
  @Override
  public native int ALWAYS()
  /*-[
    return GL_ALWAYS;
  ]-*/;

  /* @see GLContext#ARRAY_BUFFER() */
  @Override
  public native int ARRAY_BUFFER()
  /*-[
    return GL_ARRAY_BUFFER;
  ]-*/;

  /* @see GLContext#ARRAY_BUFFER_BINDING() */
  @Override
  public native int ARRAY_BUFFER_BINDING()
  /*-[
    return GL_ARRAY_BUFFER_BINDING;
  ]-*/;

  /* @see GLContext#ATTACHED_SHADERS() */
  @Override
  public native int ATTACHED_SHADERS()
  /*-[
    return GL_ATTACHED_SHADERS;
  ]-*/;

  /* @see GLContext#BACK() */
  @Override
  public native int BACK()
  /*-[
    return GL_BACK;
  ]-*/;

  /* @see GLContext#BLEND() */
  @Override
  public native int BLEND()
  /*-[
    return GL_BLEND;
  ]-*/;

  /* @see GLContext#BLEND_COLOR() */
  @Override
  public native int BLEND_COLOR()
  /*-[
    return GL_BLEND_COLOR;
  ]-*/;

  /* @see GLContext#BLEND_DST_ALPHA() */
  @Override
  public native int BLEND_DST_ALPHA()
  /*-[
    return GL_BLEND_DST_ALPHA;
  ]-*/;

  /* @see GLContext#BLEND_DST_RGB() */
  @Override
  public native int BLEND_DST_RGB()
  /*-[
    return GL_BLEND_DST_RGB;
  ]-*/;

  /* @see GLContext#BLEND_EQUATION() */
  @Override
  public native int BLEND_EQUATION()
  /*-[
    return GL_BLEND_EQUATION;
  ]-*/;

  /* @see GLContext#BLEND_EQUATION_ALPHA() */
  @Override
  public native int BLEND_EQUATION_ALPHA()
  /*-[
    return GL_BLEND_EQUATION_ALPHA;
  ]-*/;

  /* @see GLContext#BLEND_EQUATION_RGB() */
  @Override
  public native int BLEND_EQUATION_RGB()
  /*-[
    return GL_BLEND_EQUATION_RGB;
  ]-*/;

  /* @see GLContext#BLEND_SRC_ALPHA() */
  @Override
  public native int BLEND_SRC_ALPHA()
  /*-[
    return GL_BLEND_SRC_ALPHA;
  ]-*/;

  /* @see GLContext#BLEND_SRC_RGB() */
  @Override
  public native int BLEND_SRC_RGB()
  /*-[
    return GL_BLEND_SRC_RGB;
  ]-*/;

  /* @see GLContext#BLUE_BITS() */
  @Override
  public native int BLUE_BITS()
  /*-[
    return GL_BLUE_BITS;
  ]-*/;

  /* @see GLContext#BOOL() */
  @Override
  public native int BOOL()
  /*-[
    return GL_BOOL;
  ]-*/;

  /* @see GLContext#BOOL_VEC2() */
  @Override
  public native int BOOL_VEC2()
  /*-[
    return GL_BOOL_VEC2;
  ]-*/;

  /* @see GLContext#BOOL_VEC3() */
  @Override
  public native int BOOL_VEC3()
  /*-[
    return GL_BOOL_VEC3;
  ]-*/;

  /* @see GLContext#BOOL_VEC4() */
  @Override
  public native int BOOL_VEC4()
  /*-[
    return GL_BOOL_VEC4;
  ]-*/;

  /* @see GLContext#BUFFER_SIZE() */
  @Override
  public native int BUFFER_SIZE()
  /*-[
    return GL_BUFFER_SIZE;
  ]-*/;

  /* @see GLContext#BUFFER_USAGE() */
  @Override
  public native int BUFFER_USAGE()
  /*-[
    return GL_BUFFER_USAGE;
  ]-*/;

  /* @see GLContext#BYTE() */
  @Override
  public native int BYTE()
  /*-[
    return GL_BYTE;
  ]-*/;

  /* @see GLContext#CCW() */
  @Override
  public native int CCW()
  /*-[
    return GL_CCW;
  ]-*/;

  /* @see GLContext#CLAMP_TO_EDGE() */
  @Override
  public native int CLAMP_TO_EDGE()
  /*-[
    return GL_CLAMP_TO_EDGE;
  ]-*/;

  /* @see GLContext#COLOR_ATTACHMENT0() */
  @Override
  public native int COLOR_ATTACHMENT0()
  /*-[
    return GL_COLOR_ATTACHMENT0;
  ]-*/;

  /* @see GLContext#COLOR_BUFFER_BIT() */
  @Override
  public native int COLOR_BUFFER_BIT()
  /*-[
    return GL_COLOR_BUFFER_BIT;
  ]-*/;

  /* @see GLContext#COLOR_CLEAR_VALUE() */
  @Override
  public native int COLOR_CLEAR_VALUE()
  /*-[
    return GL_COLOR_CLEAR_VALUE;
  ]-*/;

  /* @see GLContext#COLOR_WRITEMASK() */
  @Override
  public native int COLOR_WRITEMASK()
  /*-[
    return GL_COLOR_WRITEMASK;
  ]-*/;

  /* @see GLContext#COMPILE_STATUS() */
  @Override
  public native int COMPILE_STATUS()
  /*-[
    return GL_COMPILE_STATUS;
  ]-*/;

  /* @see GLContext#COMPRESSED_TEXTURE_FORMATS() */
  @Override
  public native int COMPRESSED_TEXTURE_FORMATS()
  /*-[
    return GL_COMPRESSED_TEXTURE_FORMATS;
  ]-*/;

  /* @see GLContext#CONSTANT_ALPHA() */
  @Override
  public native int CONSTANT_ALPHA()
  /*-[
    return GL_CONSTANT_ALPHA;
  ]-*/;

  /* @see GLContext#CONSTANT_COLOR() */
  @Override
  public native int CONSTANT_COLOR()
  /*-[
    return GL_CONSTANT_COLOR;
  ]-*/;

  /* @see GLContext#CULL_FACE() */
  @Override
  public native int CULL_FACE()
  /*-[
    return GL_CULL_FACE;
  ]-*/;

  /* @see GLContext#CULL_FACE_MODE() */
  @Override
  public native int CULL_FACE_MODE()
  /*-[
    return GL_CULL_FACE_MODE;
  ]-*/;

  /* @see GLContext#CURRENT_PROGRAM() */
  @Override
  public native int CURRENT_PROGRAM()
  /*-[
    return GL_CURRENT_PROGRAM;
  ]-*/;

  /* @see GLContext#CURRENT_VERTEX_ATTRIB() */
  @Override
  public native int CURRENT_VERTEX_ATTRIB()
  /*-[
    return GL_CURRENT_VERTEX_ATTRIB;
  ]-*/;

  /* @see GLContext#CW() */
  @Override
  public native int CW()
  /*-[
    return GL_CW;
  ]-*/;

  /* @see GLContext#DECR() */
  @Override
  public native int DECR()
  /*-[
    return GL_DECR;
  ]-*/;

  /* @see GLContext#DECR_WRAP() */
  @Override
  public native int DECR_WRAP()
  /*-[
    return GL_DECR_WRAP;
  ]-*/;

  /* @see GLContext#DELETE_STATUS() */
  @Override
  public native int DELETE_STATUS()
  /*-[
    return GL_DELETE_STATUS;
  ]-*/;

  /* @see GLContext#DEPTH_ATTACHMENT() */
  @Override
  public native int DEPTH_ATTACHMENT()
  /*-[
    return GL_DEPTH_ATTACHMENT;
  ]-*/;

  /* @see GLContext#DEPTH_BITS() */
  @Override
  public native int DEPTH_BITS()
  /*-[
    return GL_DEPTH_BITS;
  ]-*/;

  /* @see GLContext#DEPTH_BUFFER_BIT() */
  @Override
  public native int DEPTH_BUFFER_BIT()
  /*-[
    return GL_DEPTH_BUFFER_BIT;
  ]-*/;

  /* @see GLContext#DEPTH_CLEAR_VALUE() */
  @Override
  public native int DEPTH_CLEAR_VALUE()
  /*-[
    return GL_DEPTH_CLEAR_VALUE;
  ]-*/;

  /* @see GLContext#DEPTH_COMPONENT() */
  @Override
  public native int DEPTH_COMPONENT()
  /*-[
    return GL_DEPTH_COMPONENT;
  ]-*/;

  /* @see GLContext#DEPTH_COMPONENT16() */
  @Override
  public native int DEPTH_COMPONENT16()
  /*-[
    return GL_DEPTH_COMPONENT16;
  ]-*/;

  /* @see GLContext#DEPTH_FUNC() */
  @Override
  public native int DEPTH_FUNC()
  /*-[
    return GL_DEPTH_FUNC;
  ]-*/;

  /* @see GLContext#DEPTH_RANGE() */
  @Override
  public native int DEPTH_RANGE()
  /*-[
    return GL_DEPTH_RANGE;
  ]-*/;

  /* @see GLContext#DEPTH_TEST() */
  @Override
  public native int DEPTH_TEST()
  /*-[
    return GL_DEPTH_TEST;
  ]-*/;

  /* @see GLContext#DEPTH_WRITEMASK() */
  @Override
  public native int DEPTH_WRITEMASK()
  /*-[
    return GL_DEPTH_WRITEMASK;
  ]-*/;

  /* @see GLContext#DITHER() */
  @Override
  public native int DITHER()
  /*-[
    return GL_DITHER;
  ]-*/;

  /* @see GLContext#DONT_CARE() */
  @Override
  public native int DONT_CARE()
  /*-[
    return GL_DONT_CARE;
  ]-*/;

  /* @see GLContext#DST_ALPHA() */
  @Override
  public native int DST_ALPHA()
  /*-[
    return GL_DST_ALPHA;
  ]-*/;

  /* @see GLContext#DST_COLOR() */
  @Override
  public native int DST_COLOR()
  /*-[
    return GL_DST_COLOR;
  ]-*/;

  /* @see GLContext#DYNAMIC_DRAW() */
  @Override
  public native int DYNAMIC_DRAW()
  /*-[
    return GL_DYNAMIC_DRAW;
  ]-*/;

  /* @see GLContext#ELEMENT_ARRAY_BUFFER() */
  @Override
  public native int ELEMENT_ARRAY_BUFFER()
  /*-[
    return GL_ELEMENT_ARRAY_BUFFER;
  ]-*/;

  /* @see GLContext#ELEMENT_ARRAY_BUFFER_BINDING() */
  @Override
  public native int ELEMENT_ARRAY_BUFFER_BINDING()
  /*-[
    return GL_ELEMENT_ARRAY_BUFFER_BINDING;
  ]-*/;

  /* @see GLContext#EQUAL() */
  @Override
  public native int EQUAL()
  /*-[
    return GL_EQUAL;
  ]-*/;

  /* @see GLContext#EXTENSIONS() */
  @Override
  public native int EXTENSIONS()
  /*-[
    return GL_EXTENSIONS;
  ]-*/;

  /* @see GLContext#FALSE() */
  @Override
  public native int FALSE()
  /*-[
    return GL_FALSE;
  ]-*/;

  /* @see GLContext#FASTEST() */
  @Override
  public native int FASTEST()
  /*-[
    return GL_FASTEST;
  ]-*/;

  /* @see GLContext#FIXED() */
  @Override
  public native int FIXED()
  /*-[
    return GL_FIXED;
  ]-*/;

  /* @see GLContext#FLOAT() */
  @Override
  public native int FLOAT()
  /*-[
    return GL_FLOAT;
  ]-*/;

  /* @see GLContext#FLOAT_MAT2() */
  @Override
  public native int FLOAT_MAT2()
  /*-[
    return GL_FLOAT_MAT2;
  ]-*/;

  /* @see GLContext#FLOAT_MAT3() */
  @Override
  public native int FLOAT_MAT3()
  /*-[
    return GL_FLOAT_MAT3;
  ]-*/;

  /* @see GLContext#FLOAT_MAT4() */
  @Override
  public native int FLOAT_MAT4()
  /*-[
    return GL_FLOAT_MAT4;
  ]-*/;

  /* @see GLContext#FLOAT_VEC2() */
  @Override
  public native int FLOAT_VEC2()
  /*-[
    return GL_FLOAT_VEC2;
  ]-*/;

  /* @see GLContext#FLOAT_VEC3() */
  @Override
  public native int FLOAT_VEC3()
  /*-[
    return GL_FLOAT_VEC3;
  ]-*/;

  /* @see GLContext#FLOAT_VEC4() */
  @Override
  public native int FLOAT_VEC4()
  /*-[
    return GL_FLOAT_VEC4;
  ]-*/;

  /* @see GLContext#FRAGMENT_SHADER() */
  @Override
  public native int FRAGMENT_SHADER()
  /*-[
    return GL_FRAGMENT_SHADER;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER() */
  @Override
  public native int FRAMEBUFFER()
  /*-[
    return GL_FRAMEBUFFER;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_OBJECT_NAME() */
  @Override
  public native int FRAMEBUFFER_ATTACHMENT_OBJECT_NAME()
  /*-[
    return GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE() */
  @Override
  public native int FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE()
  /*-[
    return GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE() */
  @Override
  public native int FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE()
  /*-[
    return GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL() */
  @Override
  public native int FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL()
  /*-[
    return GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER_BINDING() */
  @Override
  public native int FRAMEBUFFER_BINDING()
  /*-[
    return GL_FRAMEBUFFER_BINDING;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER_COMPLETE() */
  @Override
  public native int FRAMEBUFFER_COMPLETE()
  /*-[
    return GL_FRAMEBUFFER_COMPLETE;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER_INCOMPLETE_ATTACHMENT() */
  @Override
  public native int FRAMEBUFFER_INCOMPLETE_ATTACHMENT()
  /*-[
    return GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER_INCOMPLETE_DIMENSIONS() */
  @Override
  public native int FRAMEBUFFER_INCOMPLETE_DIMENSIONS()
  /*-[
    return GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT() */
  @Override
  public native int FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT()
  /*-[
    return GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT;
  ]-*/;

  /* @see GLContext#FRAMEBUFFER_UNSUPPORTED() */
  @Override
  public native int FRAMEBUFFER_UNSUPPORTED()
  /*-[
    return GL_FRAMEBUFFER_UNSUPPORTED;
  ]-*/;

  /* @see GLContext#FRONT() */
  @Override
  public native int FRONT()
  /*-[
    return GL_FRONT;
  ]-*/;

  /* @see GLContext#FRONT_AND_BACK() */
  @Override
  public native int FRONT_AND_BACK()
  /*-[
    return GL_FRONT_AND_BACK;
  ]-*/;

  /* @see GLContext#FRONT_FACE() */
  @Override
  public native int FRONT_FACE()
  /*-[
    return GL_FRONT_FACE;
  ]-*/;

  /* @see GLContext#FUNC_ADD() */
  @Override
  public native int FUNC_ADD()
  /*-[
    return GL_FUNC_ADD;
  ]-*/;

  /* @see GLContext#FUNC_REVERSE_SUBTRACT() */
  @Override
  public native int FUNC_REVERSE_SUBTRACT()
  /*-[
    return GL_FUNC_REVERSE_SUBTRACT;
  ]-*/;

  /* @see GLContext#FUNC_SUBTRACT() */
  @Override
  public native int FUNC_SUBTRACT()
  /*-[
    return GL_FUNC_SUBTRACT;
  ]-*/;

  /* @see GLContext#GENERATE_MIPMAP_HINT() */
  @Override
  public native int GENERATE_MIPMAP_HINT()
  /*-[
    return GL_GENERATE_MIPMAP_HINT;
  ]-*/;

  /* @see GLContext#GEQUAL() */
  @Override
  public native int GEQUAL()
  /*-[
    return GL_GEQUAL;
  ]-*/;

  /* @see GLContext#GREATER() */
  @Override
  public native int GREATER()
  /*-[
    return GL_GREATER;
  ]-*/;

  /* @see GLContext#GREEN_BITS() */
  @Override
  public native int GREEN_BITS()
  /*-[
    return GL_GREEN_BITS;
  ]-*/;

  /* @see GLContext#HIGH_FLOAT() */
  @Override
  public native int HIGH_FLOAT()
  /*-[
    return GL_HIGH_FLOAT;
  ]-*/;

  /* @see GLContext#HIGH_INT() */
  @Override
  public native int HIGH_INT()
  /*-[
    return GL_HIGH_INT;
  ]-*/;

  /* @see GLContext#IMPLEMENTATION_COLOR_READ_FORMAT() */
  @Override
  public native int IMPLEMENTATION_COLOR_READ_FORMAT()
  /*-[
    return GL_IMPLEMENTATION_COLOR_READ_FORMAT;
  ]-*/;

  /* @see GLContext#IMPLEMENTATION_COLOR_READ_TYPE() */
  @Override
  public native int IMPLEMENTATION_COLOR_READ_TYPE()
  /*-[
    return GL_IMPLEMENTATION_COLOR_READ_TYPE;
  ]-*/;

  /* @see GLContext#INCR() */
  @Override
  public native int INCR()
  /*-[
    return GL_INCR;
  ]-*/;

  /* @see GLContext#INCR_WRAP() */
  @Override
  public native int INCR_WRAP()
  /*-[
    return GL_INCR_WRAP;
  ]-*/;

  /* @see GLContext#INFO_LOG_LENGTH() */
  @Override
  public native int INFO_LOG_LENGTH()
  /*-[
    return GL_INFO_LOG_LENGTH;
  ]-*/;

  /* @see GLContext#INT() */
  @Override
  public native int INT()
  /*-[
    return GL_INT;
  ]-*/;

  /* @see GLContext#INT_VEC2() */
  @Override
  public native int INT_VEC2()
  /*-[
    return GL_INT_VEC2;
  ]-*/;

  /* @see GLContext#INT_VEC3() */
  @Override
  public native int INT_VEC3()
  /*-[
    return GL_INT_VEC3;
  ]-*/;

  /* @see GLContext#INT_VEC4() */
  @Override
  public native int INT_VEC4()
  /*-[
    return GL_INT_VEC4;
  ]-*/;

  /* @see GLContext#INVALID_ENUM() */
  @Override
  public native int INVALID_ENUM()
  /*-[
    return GL_INVALID_ENUM;
  ]-*/;

  /* @see GLContext#INVALID_FRAMEBUFFER_OPERATION() */
  @Override
  public native int INVALID_FRAMEBUFFER_OPERATION()
  /*-[
    return GL_INVALID_FRAMEBUFFER_OPERATION;
  ]-*/;

  /* @see GLContext#INVALID_OPERATION() */
  @Override
  public native int INVALID_OPERATION()
  /*-[
    return GL_INVALID_OPERATION;
  ]-*/;

  /* @see GLContext#INVALID_VALUE() */
  @Override
  public native int INVALID_VALUE()
  /*-[
    return GL_INVALID_VALUE;
  ]-*/;

  /* @see GLContext#INVERT() */
  @Override
  public native int INVERT()
  /*-[
    return GL_INVERT;
  ]-*/;

  /* @see GLContext#KEEP() */
  @Override
  public native int KEEP()
  /*-[
    return GL_KEEP;
  ]-*/;

  /* @see GLContext#LEQUAL() */
  @Override
  public native int LEQUAL()
  /*-[
    return GL_LEQUAL;
  ]-*/;

  /* @see GLContext#LESS() */
  @Override
  public native int LESS()
  /*-[
    return GL_LESS;
  ]-*/;

  /* @see GLContext#LINE_LOOP() */
  @Override
  public native int LINE_LOOP()
  /*-[
    return GL_LINE_LOOP;
  ]-*/;

  /* @see GLContext#LINE_STRIP() */
  @Override
  public native int LINE_STRIP()
  /*-[
    return GL_LINE_STRIP;
  ]-*/;

  /* @see GLContext#LINE_WIDTH() */
  @Override
  public native int LINE_WIDTH()
  /*-[
    return GL_LINE_WIDTH;
  ]-*/;

  /* @see GLContext#LINEAR() */
  @Override
  public native int LINEAR()
  /*-[
    return GL_LINEAR;
  ]-*/;

  /* @see GLContext#LINEAR_MIPMAP_LINEAR() */
  @Override
  public native int LINEAR_MIPMAP_LINEAR()
  /*-[
    return GL_LINEAR_MIPMAP_LINEAR;
  ]-*/;

  /* @see GLContext#LINEAR_MIPMAP_NEAREST() */
  @Override
  public native int LINEAR_MIPMAP_NEAREST()
  /*-[
    return GL_LINEAR_MIPMAP_NEAREST;
  ]-*/;

  /* @see GLContext#LINES() */
  @Override
  public native int LINES()
  /*-[
    return GL_LINES;
  ]-*/;

  /* @see GLContext#LINK_STATUS() */
  @Override
  public native int LINK_STATUS()
  /*-[
    return GL_LINK_STATUS;
  ]-*/;

  /* @see GLContext#LOW_FLOAT() */
  @Override
  public native int LOW_FLOAT()
  /*-[
    return GL_LOW_FLOAT;
  ]-*/;

  /* @see GLContext#LOW_INT() */
  @Override
  public native int LOW_INT()
  /*-[
    return GL_LOW_INT;
  ]-*/;

  /* @see GLContext#LUMINANCE() */
  @Override
  public native int LUMINANCE()
  /*-[
    return GL_LUMINANCE;
  ]-*/;

  /* @see GLContext#LUMINANCE_ALPHA() */
  @Override
  public native int LUMINANCE_ALPHA()
  /*-[
    return GL_LUMINANCE_ALPHA;
  ]-*/;

  /* @see GLContext#MAX_COMBINED_TEXTURE_IMAGE_UNITS() */
  @Override
  public native int MAX_COMBINED_TEXTURE_IMAGE_UNITS()
  /*-[
    return GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS;
  ]-*/;

  /* @see GLContext#MAX_CUBE_MAP_TEXTURE_SIZE() */
  @Override
  public native int MAX_CUBE_MAP_TEXTURE_SIZE()
  /*-[
    return GL_MAX_CUBE_MAP_TEXTURE_SIZE;
  ]-*/;

  /* @see GLContext#MAX_FRAGMENT_UNIFORM_VECTORS() */
  @Override
  public native int MAX_FRAGMENT_UNIFORM_VECTORS()
  /*-[
    return GL_MAX_FRAGMENT_UNIFORM_VECTORS;
  ]-*/;

  /* @see GLContext#MAX_RENDERBUFFER_SIZE() */
  @Override
  public native int MAX_RENDERBUFFER_SIZE()
  /*-[
    return GL_MAX_RENDERBUFFER_SIZE;
  ]-*/;

  /* @see GLContext#MAX_TEXTURE_IMAGE_UNITS() */
  @Override
  public native int MAX_TEXTURE_IMAGE_UNITS()
  /*-[
    return GL_MAX_TEXTURE_IMAGE_UNITS;
  ]-*/;

  /* @see GLContext#MAX_TEXTURE_SIZE() */
  @Override
  public native int MAX_TEXTURE_SIZE()
  /*-[
    return GL_MAX_TEXTURE_SIZE;
  ]-*/;

  /* @see GLContext#MAX_VARYING_VECTORS() */
  @Override
  public native int MAX_VARYING_VECTORS()
  /*-[
    return GL_MAX_VARYING_VECTORS;
  ]-*/;

  /* @see GLContext#MAX_VERTEX_ATTRIBS() */
  @Override
  public native int MAX_VERTEX_ATTRIBS()
  /*-[
    return GL_MAX_VERTEX_ATTRIBS;
  ]-*/;

  /* @see GLContext#MAX_VERTEX_TEXTURE_IMAGE_UNITS() */
  @Override
  public native int MAX_VERTEX_TEXTURE_IMAGE_UNITS()
  /*-[
    return GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS;
  ]-*/;

  /* @see GLContext#MAX_VERTEX_UNIFORM_VECTORS() */
  @Override
  public native int MAX_VERTEX_UNIFORM_VECTORS()
  /*-[
    return GL_MAX_VERTEX_UNIFORM_VECTORS;
  ]-*/;

  /* @see GLContext#MAX_VIEWPORT_DIMS() */
  @Override
  public native int MAX_VIEWPORT_DIMS()
  /*-[
    return GL_MAX_VIEWPORT_DIMS;
  ]-*/;

  /* @see GLContext#MEDIUM_FLOAT() */
  @Override
  public native int MEDIUM_FLOAT()
  /*-[
    return GL_MEDIUM_FLOAT;
  ]-*/;

  /* @see GLContext#MEDIUM_INT() */
  @Override
  public native int MEDIUM_INT()
  /*-[
    return GL_MEDIUM_INT;
  ]-*/;

  /* @see GLContext#MIRRORED_REPEAT() */
  @Override
  public native int MIRRORED_REPEAT()
  /*-[
    return GL_MIRRORED_REPEAT;
  ]-*/;

  /* @see GLContext#NEAREST() */
  @Override
  public native int NEAREST()
  /*-[
    return GL_NEAREST;
  ]-*/;

  /* @see GLContext#NEAREST_MIPMAP_LINEAR() */
  @Override
  public native int NEAREST_MIPMAP_LINEAR()
  /*-[
    return GL_NEAREST_MIPMAP_LINEAR;
  ]-*/;

  /* @see GLContext#NEAREST_MIPMAP_NEAREST() */
  @Override
  public native int NEAREST_MIPMAP_NEAREST()
  /*-[
    return GL_NEAREST_MIPMAP_NEAREST;
  ]-*/;

  /* @see GLContext#NEVER() */
  @Override
  public native int NEVER()
  /*-[
    return GL_NEVER;
  ]-*/;

  /* @see GLContext#NICEST() */
  @Override
  public native int NICEST()
  /*-[
    return GL_NICEST;
  ]-*/;

  /* @see GLContext#NO_ERROR() */
  @Override
  public native int NO_ERROR()
  /*-[
    return GL_NO_ERROR;
  ]-*/;

  /* @see GLContext#NONE() */
  @Override
  public native int NONE()
  /*-[
    return GL_NONE;
  ]-*/;

  /* @see GLContext#NOTEQUAL() */
  @Override
  public native int NOTEQUAL()
  /*-[
    return GL_NOTEQUAL;
  ]-*/;

  /* @see GLContext#NUM_COMPRESSED_TEXTURE_FORMATS() */
  @Override
  public native int NUM_COMPRESSED_TEXTURE_FORMATS()
  /*-[
    return GL_NUM_COMPRESSED_TEXTURE_FORMATS;
  ]-*/;

  /* @see GLContext#NUM_SHADER_BINARY_FORMATS() */
  @Override
  public native int NUM_SHADER_BINARY_FORMATS()
  /*-[
    return GL_NUM_SHADER_BINARY_FORMATS;
  ]-*/;

  /* @see GLContext#ONE() */
  @Override
  public native int ONE()
  /*-[
    return GL_ONE;
  ]-*/;

  /* @see GLContext#ONE_MINUS_CONSTANT_ALPHA() */
  @Override
  public native int ONE_MINUS_CONSTANT_ALPHA()
  /*-[
    return GL_ONE_MINUS_CONSTANT_ALPHA;
  ]-*/;

  /* @see GLContext#ONE_MINUS_CONSTANT_COLOR() */
  @Override
  public native int ONE_MINUS_CONSTANT_COLOR()
  /*-[
    return GL_ONE_MINUS_CONSTANT_COLOR;
  ]-*/;

  /* @see GLContext#ONE_MINUS_DST_ALPHA() */
  @Override
  public native int ONE_MINUS_DST_ALPHA()
  /*-[
    return GL_ONE_MINUS_DST_ALPHA;
  ]-*/;

  /* @see GLContext#ONE_MINUS_DST_COLOR() */
  @Override
  public native int ONE_MINUS_DST_COLOR()
  /*-[
    return GL_ONE_MINUS_DST_COLOR;
  ]-*/;

  /* @see GLContext#ONE_MINUS_SRC_ALPHA() */
  @Override
  public native int ONE_MINUS_SRC_ALPHA()
  /*-[
    return GL_ONE_MINUS_SRC_ALPHA;
  ]-*/;

  /* @see GLContext#ONE_MINUS_SRC_COLOR() */
  @Override
  public native int ONE_MINUS_SRC_COLOR()
  /*-[
    return GL_ONE_MINUS_SRC_COLOR;
  ]-*/;

  /* @see GLContext#OUT_OF_MEMORY() */
  @Override
  public native int OUT_OF_MEMORY()
  /*-[
    return GL_OUT_OF_MEMORY;
  ]-*/;

  /* @see GLContext#PACK_ALIGNMENT() */
  @Override
  public native int PACK_ALIGNMENT()
  /*-[
    return GL_PACK_ALIGNMENT;
  ]-*/;

  /* @see GLContext#POINTS() */
  @Override
  public native int POINTS()
  /*-[
    return GL_POINTS;
  ]-*/;

  /* @see GLContext#POLYGON_OFFSET_FACTOR() */
  @Override
  public native int POLYGON_OFFSET_FACTOR()
  /*-[
    return GL_POLYGON_OFFSET_FACTOR;
  ]-*/;

  /* @see GLContext#POLYGON_OFFSET_FILL() */
  @Override
  public native int POLYGON_OFFSET_FILL()
  /*-[
    return GL_POLYGON_OFFSET_FILL;
  ]-*/;

  /* @see GLContext#POLYGON_OFFSET_UNITS() */
  @Override
  public native int POLYGON_OFFSET_UNITS()
  /*-[
    return GL_POLYGON_OFFSET_UNITS;
  ]-*/;

  /* @see GLContext#RED_BITS() */
  @Override
  public native int RED_BITS()
  /*-[
    return GL_RED_BITS;
  ]-*/;

  /* @see GLContext#RENDERBUFFER() */
  @Override
  public native int RENDERBUFFER()
  /*-[
    return GL_RENDERBUFFER;
  ]-*/;

  /* @see GLContext#RENDERBUFFER_ALPHA_SIZE() */
  @Override
  public native int RENDERBUFFER_ALPHA_SIZE()
  /*-[
    return GL_RENDERBUFFER_ALPHA_SIZE;
  ]-*/;

  /* @see GLContext#RENDERBUFFER_BINDING() */
  @Override
  public native int RENDERBUFFER_BINDING()
  /*-[
    return GL_RENDERBUFFER_BINDING;
  ]-*/;

  /* @see GLContext#RENDERBUFFER_BLUE_SIZE() */
  @Override
  public native int RENDERBUFFER_BLUE_SIZE()
  /*-[
    return GL_RENDERBUFFER_BLUE_SIZE;
  ]-*/;

  /* @see GLContext#RENDERBUFFER_DEPTH_SIZE() */
  @Override
  public native int RENDERBUFFER_DEPTH_SIZE()
  /*-[
    return GL_RENDERBUFFER_DEPTH_SIZE;
  ]-*/;

  /* @see GLContext#RENDERBUFFER_GREEN_SIZE() */
  @Override
  public native int RENDERBUFFER_GREEN_SIZE()
  /*-[
    return GL_RENDERBUFFER_GREEN_SIZE;
  ]-*/;

  /* @see GLContext#RENDERBUFFER_HEIGHT() */
  @Override
  public native int RENDERBUFFER_HEIGHT()
  /*-[
    return GL_RENDERBUFFER_HEIGHT;
  ]-*/;

  /* @see GLContext#RENDERBUFFER_INTERNAL_FORMAT() */
  @Override
  public native int RENDERBUFFER_INTERNAL_FORMAT()
  /*-[
    return GL_RENDERBUFFER_INTERNAL_FORMAT;
  ]-*/;

  /* @see GLContext#RENDERBUFFER_RED_SIZE() */
  @Override
  public native int RENDERBUFFER_RED_SIZE()
  /*-[
    return GL_RENDERBUFFER_RED_SIZE;
  ]-*/;

  /* @see GLContext#RENDERBUFFER_STENCIL_SIZE() */
  @Override
  public native int RENDERBUFFER_STENCIL_SIZE()
  /*-[
    return GL_RENDERBUFFER_STENCIL_SIZE;
  ]-*/;

  /* @see GLContext#RENDERBUFFER_WIDTH() */
  @Override
  public native int RENDERBUFFER_WIDTH()
  /*-[
    return GL_RENDERBUFFER_WIDTH;
  ]-*/;

  /* @see GLContext#RENDERER() */
  @Override
  public native int RENDERER()
  /*-[
    return GL_RENDERER;
  ]-*/;

  /* @see GLContext#REPEAT() */
  @Override
  public native int REPEAT()
  /*-[
    return GL_REPEAT;
  ]-*/;

  /* @see GLContext#REPLACE() */
  @Override
  public native int REPLACE()
  /*-[
    return GL_REPLACE;
  ]-*/;

  /* @see GLContext#RGB() */
  @Override
  public native int RGB()
  /*-[
    return GL_RGB;
  ]-*/;

  /* @see GLContext#RGB5_A1() */
  @Override
  public native int RGB5_A1()
  /*-[
    return GL_RGB5_A1;
  ]-*/;

  /* @see GLContext#RGB565() */
  @Override
  public native int RGB565()
  /*-[
    return GL_RGB565;
  ]-*/;

  /* @see GLContext#RGBA() */
  @Override
  public native int RGBA()
  /*-[
    return GL_RGBA;
  ]-*/;

  /* @see GLContext#RGBA4() */
  @Override
  public native int RGBA4()
  /*-[
    return GL_RGBA4;
  ]-*/;

  /* @see GLContext#SAMPLE_ALPHA_TO_COVERAGE() */
  @Override
  public native int SAMPLE_ALPHA_TO_COVERAGE()
  /*-[
    return GL_SAMPLE_ALPHA_TO_COVERAGE;
  ]-*/;

  /* @see GLContext#SAMPLE_BUFFERS() */
  @Override
  public native int SAMPLE_BUFFERS()
  /*-[
    return GL_SAMPLE_BUFFERS;
  ]-*/;

  /* @see GLContext#SAMPLE_COVERAGE() */
  @Override
  public native int SAMPLE_COVERAGE()
  /*-[
    return GL_SAMPLE_COVERAGE;
  ]-*/;

  /* @see GLContext#SAMPLE_COVERAGE_INVERT() */
  @Override
  public native int SAMPLE_COVERAGE_INVERT()
  /*-[
    return GL_SAMPLE_COVERAGE_INVERT;
  ]-*/;

  /* @see GLContext#SAMPLE_COVERAGE_VALUE() */
  @Override
  public native int SAMPLE_COVERAGE_VALUE()
  /*-[
    return GL_SAMPLE_COVERAGE_VALUE;
  ]-*/;

  /* @see GLContext#SAMPLER_2D() */
  @Override
  public native int SAMPLER_2D()
  /*-[
    return GL_SAMPLER_2D;
  ]-*/;

  /* @see GLContext#SAMPLER_CUBE() */
  @Override
  public native int SAMPLER_CUBE()
  /*-[
    return GL_SAMPLER_CUBE;
  ]-*/;

  /* @see GLContext#SAMPLES() */
  @Override
  public native int SAMPLES()
  /*-[
    return GL_SAMPLES;
  ]-*/;

  /* @see GLContext#SCISSOR_BOX() */
  @Override
  public native int SCISSOR_BOX()
  /*-[
    return GL_SCISSOR_BOX;
  ]-*/;

  /* @see GLContext#SCISSOR_TEST() */
  @Override
  public native int SCISSOR_TEST()
  /*-[
    return GL_SCISSOR_TEST;
  ]-*/;

  /* @see GLContext#SHADER_BINARY_FORMATS() */
  @Override
  public native int SHADER_BINARY_FORMATS()
  /*-[
    return GL_SHADER_BINARY_FORMATS;
  ]-*/;

  /* @see GLContext#SHADER_COMPILER() */
  @Override
  public native int SHADER_COMPILER()
  /*-[
    return GL_SHADER_COMPILER;
  ]-*/;

  /* @see GLContext#SHADER_SOURCE_LENGTH() */
  @Override
  public native int SHADER_SOURCE_LENGTH()
  /*-[
    return GL_SHADER_SOURCE_LENGTH;
  ]-*/;

  /* @see GLContext#SHADER_TYPE() */
  @Override
  public native int SHADER_TYPE()
  /*-[
    return GL_SHADER_TYPE;
  ]-*/;

  /* @see GLContext#SHADING_LANGUAGE_VERSION() */
  @Override
  public native int SHADING_LANGUAGE_VERSION()
  /*-[
    return GL_SHADING_LANGUAGE_VERSION;
  ]-*/;

  /* @see GLContext#SHORT() */
  @Override
  public native int SHORT()
  /*-[
    return GL_SHORT;
  ]-*/;

  /* @see GLContext#SRC_ALPHA() */
  @Override
  public native int SRC_ALPHA()
  /*-[
    return GL_SRC_ALPHA;
  ]-*/;

  /* @see GLContext#SRC_ALPHA_SATURATE() */
  @Override
  public native int SRC_ALPHA_SATURATE()
  /*-[
    return GL_SRC_ALPHA_SATURATE;
  ]-*/;

  /* @see GLContext#SRC_COLOR() */
  @Override
  public native int SRC_COLOR()
  /*-[
    return GL_SRC_COLOR;
  ]-*/;

  /* @see GLContext#STATIC_DRAW() */
  @Override
  public native int STATIC_DRAW()
  /*-[
    return GL_STATIC_DRAW;
  ]-*/;

  /* @see GLContext#STENCIL_ATTACHMENT() */
  @Override
  public native int STENCIL_ATTACHMENT()
  /*-[
    return GL_STENCIL_ATTACHMENT;
  ]-*/;

  /* @see GLContext#STENCIL_BACK_FAIL() */
  @Override
  public native int STENCIL_BACK_FAIL()
  /*-[
    return GL_STENCIL_BACK_FAIL;
  ]-*/;

  /* @see GLContext#STENCIL_BACK_FUNC() */
  @Override
  public native int STENCIL_BACK_FUNC()
  /*-[
    return GL_STENCIL_BACK_FUNC;
  ]-*/;

  /* @see GLContext#STENCIL_BACK_PASS_DEPTH_FAIL() */
  @Override
  public native int STENCIL_BACK_PASS_DEPTH_FAIL()
  /*-[
    return GL_STENCIL_BACK_PASS_DEPTH_FAIL;
  ]-*/;

  /* @see GLContext#STENCIL_BACK_PASS_DEPTH_PASS() */
  @Override
  public native int STENCIL_BACK_PASS_DEPTH_PASS()
  /*-[
    return GL_STENCIL_BACK_PASS_DEPTH_PASS;
  ]-*/;

  /* @see GLContext#STENCIL_BACK_REF() */
  @Override
  public native int STENCIL_BACK_REF()
  /*-[
    return GL_STENCIL_BACK_REF;
  ]-*/;

  /* @see GLContext#STENCIL_BACK_VALUE_MASK() */
  @Override
  public native int STENCIL_BACK_VALUE_MASK()
  /*-[
    return GL_STENCIL_BACK_VALUE_MASK;
  ]-*/;

  /* @see GLContext#STENCIL_BACK_WRITEMASK() */
  @Override
  public native int STENCIL_BACK_WRITEMASK()
  /*-[
    return GL_STENCIL_BACK_WRITEMASK;
  ]-*/;

  /* @see GLContext#STENCIL_BITS() */
  @Override
  public native int STENCIL_BITS()
  /*-[
    return GL_STENCIL_BITS;
  ]-*/;

  /* @see GLContext#STENCIL_BUFFER_BIT() */
  @Override
  public native int STENCIL_BUFFER_BIT()
  /*-[
    return GL_STENCIL_BUFFER_BIT;
  ]-*/;

  /* @see GLContext#STENCIL_CLEAR_VALUE() */
  @Override
  public native int STENCIL_CLEAR_VALUE()
  /*-[
    return GL_STENCIL_CLEAR_VALUE;
  ]-*/;

  /* @see GLContext#STENCIL_FAIL() */
  @Override
  public native int STENCIL_FAIL()
  /*-[
    return GL_STENCIL_FAIL;
  ]-*/;

  /* @see GLContext#STENCIL_FUNC() */
  @Override
  public native int STENCIL_FUNC()
  /*-[
    return GL_STENCIL_FUNC;
  ]-*/;

  /* @see GLContext#STENCIL_INDEX() */
  @Override
  public native int STENCIL_INDEX()
  /*-[
    return GL_STENCIL_INDEX;
  ]-*/;

  /* @see GLContext#STENCIL_INDEX8() */
  @Override
  public native int STENCIL_INDEX8()
  /*-[
    return GL_STENCIL_INDEX8;
  ]-*/;

  /* @see GLContext#STENCIL_PASS_DEPTH_FAIL() */
  @Override
  public native int STENCIL_PASS_DEPTH_FAIL()
  /*-[
    return GL_STENCIL_PASS_DEPTH_FAIL;
  ]-*/;

  /* @see GLContext#STENCIL_PASS_DEPTH_PASS() */
  @Override
  public native int STENCIL_PASS_DEPTH_PASS()
  /*-[
    return GL_STENCIL_PASS_DEPTH_PASS;
  ]-*/;

  /* @see GLContext#STENCIL_REF() */
  @Override
  public native int STENCIL_REF()
  /*-[
    return GL_STENCIL_REF;
  ]-*/;

  /* @see GLContext#STENCIL_TEST() */
  @Override
  public native int STENCIL_TEST()
  /*-[
    return GL_STENCIL_TEST;
  ]-*/;

  /* @see GLContext#STENCIL_VALUE_MASK() */
  @Override
  public native int STENCIL_VALUE_MASK()
  /*-[
    return GL_STENCIL_VALUE_MASK;
  ]-*/;

  /* @see GLContext#STENCIL_WRITEMASK() */
  @Override
  public native int STENCIL_WRITEMASK()
  /*-[
    return GL_STENCIL_WRITEMASK;
  ]-*/;

  /* @see GLContext#STREAM_DRAW() */
  @Override
  public native int STREAM_DRAW()
  /*-[
    return GL_STREAM_DRAW;
  ]-*/;

  /* @see GLContext#SUBPIXEL_BITS() */
  @Override
  public native int SUBPIXEL_BITS()
  /*-[
    return GL_SUBPIXEL_BITS;
  ]-*/;

  /* @see GLContext#TEXTURE() */
  @Override
  public native int TEXTURE()
  /*-[
    return GL_TEXTURE;
  ]-*/;

  /* @see GLContext#TEXTURE_2D() */
  @Override
  public native int TEXTURE_2D()
  /*-[
    return GL_TEXTURE_2D;
  ]-*/;

  /* @see GLContext#TEXTURE_BINDING_2D() */
  @Override
  public native int TEXTURE_BINDING_2D()
  /*-[
    return GL_TEXTURE_BINDING_2D;
  ]-*/;

  /* @see GLContext#TEXTURE_BINDING_CUBE_MAP() */
  @Override
  public native int TEXTURE_BINDING_CUBE_MAP()
  /*-[
    return GL_TEXTURE_BINDING_CUBE_MAP;
  ]-*/;

  /* @see GLContext#TEXTURE_CUBE_MAP() */
  @Override
  public native int TEXTURE_CUBE_MAP()
  /*-[
    return GL_TEXTURE_CUBE_MAP;
  ]-*/;

  /* @see GLContext#TEXTURE_CUBE_MAP_NEGATIVE_X() */
  @Override
  public native int TEXTURE_CUBE_MAP_NEGATIVE_X()
  /*-[
    return GL_TEXTURE_CUBE_MAP_NEGATIVE_X;
  ]-*/;

  /* @see GLContext#TEXTURE_CUBE_MAP_NEGATIVE_Y() */
  @Override
  public native int TEXTURE_CUBE_MAP_NEGATIVE_Y()
  /*-[
    return GL_TEXTURE_CUBE_MAP_NEGATIVE_Y;
  ]-*/;

  /* @see GLContext#TEXTURE_CUBE_MAP_NEGATIVE_Z() */
  @Override
  public native int TEXTURE_CUBE_MAP_NEGATIVE_Z()
  /*-[
    return GL_TEXTURE_CUBE_MAP_NEGATIVE_Z;
  ]-*/;

  /* @see GLContext#TEXTURE_CUBE_MAP_POSITIVE_X() */
  @Override
  public native int TEXTURE_CUBE_MAP_POSITIVE_X()
  /*-[
    return GL_TEXTURE_CUBE_MAP_POSITIVE_X;
  ]-*/;

  /* @see GLContext#TEXTURE_CUBE_MAP_POSITIVE_Y() */
  @Override
  public native int TEXTURE_CUBE_MAP_POSITIVE_Y()
  /*-[
    return GL_TEXTURE_CUBE_MAP_POSITIVE_Y;
  ]-*/;

  /* @see GLContext#TEXTURE_CUBE_MAP_POSITIVE_Z() */
  @Override
  public native int TEXTURE_CUBE_MAP_POSITIVE_Z()
  /*-[
    return GL_TEXTURE_CUBE_MAP_POSITIVE_Z;
  ]-*/;

  /* @see GLContext#TEXTURE_MAG_FILTER() */
  @Override
  public native int TEXTURE_MAG_FILTER()
  /*-[
    return GL_TEXTURE_MAG_FILTER;
  ]-*/;

  /* @see GLContext#TEXTURE_MIN_FILTER() */
  @Override
  public native int TEXTURE_MIN_FILTER()
  /*-[
    return GL_TEXTURE_MIN_FILTER;
  ]-*/;

  /* @see GLContext#TEXTURE_WRAP_S() */
  @Override
  public native int TEXTURE_WRAP_S()
  /*-[
    return GL_TEXTURE_WRAP_S;
  ]-*/;

  /* @see GLContext#TEXTURE_WRAP_T() */
  @Override
  public native int TEXTURE_WRAP_T()
  /*-[
    return GL_TEXTURE_WRAP_T;
  ]-*/;

  /* @see GLContext#TEXTURE0() */
  @Override
  public native int TEXTURE0()
  /*-[
    return GL_TEXTURE0;
  ]-*/;

  /* @see GLContext#TEXTURE1() */
  @Override
  public native int TEXTURE1()
  /*-[
    return GL_TEXTURE1;
  ]-*/;

  /* @see GLContext#TEXTURE2() */
  @Override
  public native int TEXTURE2()
  /*-[
    return GL_TEXTURE2;
  ]-*/;

  /* @see GLContext#TEXTURE3() */
  @Override
  public native int TEXTURE3()
  /*-[
    return GL_TEXTURE3;
  ]-*/;

  /* @see GLContext#TEXTURE4() */
  @Override
  public native int TEXTURE4()
  /*-[
    return GL_TEXTURE4;
  ]-*/;

  /* @see GLContext#TEXTURE5() */
  @Override
  public native int TEXTURE5()
  /*-[
    return GL_TEXTURE5;
  ]-*/;

  /* @see GLContext#TEXTURE6() */
  @Override
  public native int TEXTURE6()
  /*-[
    return GL_TEXTURE6;
  ]-*/;

  /* @see GLContext#TEXTURE7() */
  @Override
  public native int TEXTURE7()
  /*-[
    return GL_TEXTURE7;
  ]-*/;

  /* @see GLContext#TEXTURE8() */
  @Override
  public native int TEXTURE8()
  /*-[
    return GL_TEXTURE8;
  ]-*/;

  /* @see GLContext#TEXTURE9() */
  @Override
  public native int TEXTURE9()
  /*-[
    return GL_TEXTURE9;
  ]-*/;

  /* @see GLContext#TEXTURE10() */
  @Override
  public native int TEXTURE10()
  /*-[
    return GL_TEXTURE10;
  ]-*/;

  /* @see GLContext#TEXTURE11() */
  @Override
  public native int TEXTURE11()
  /*-[
    return GL_TEXTURE11;
  ]-*/;

  /* @see GLContext#TEXTURE12() */
  @Override
  public native int TEXTURE12()
  /*-[
    return GL_TEXTURE12;
  ]-*/;

  /* @see GLContext#TEXTURE13() */
  @Override
  public native int TEXTURE13()
  /*-[
    return GL_TEXTURE13;
  ]-*/;

  /* @see GLContext#TEXTURE14() */
  @Override
  public native int TEXTURE14()
  /*-[
    return GL_TEXTURE14;
  ]-*/;

  /* @see GLContext#TEXTURE15() */
  @Override
  public native int TEXTURE15()
  /*-[
    return GL_TEXTURE15;
  ]-*/;

  /* @see GLContext#TEXTURE16() */
  @Override
  public native int TEXTURE16()
  /*-[
    return GL_TEXTURE16;
  ]-*/;

  /* @see GLContext#TEXTURE17() */
  @Override
  public native int TEXTURE17()
  /*-[
    return GL_TEXTURE17;
  ]-*/;

  /* @see GLContext#TEXTURE18() */
  @Override
  public native int TEXTURE18()
  /*-[
    return GL_TEXTURE18;
  ]-*/;

  /* @see GLContext#TEXTURE19() */
  @Override
  public native int TEXTURE19()
  /*-[
    return GL_TEXTURE19;
  ]-*/;

  /* @see GLContext#TEXTURE20() */
  @Override
  public native int TEXTURE20()
  /*-[
    return GL_TEXTURE20;
  ]-*/;

  /* @see GLContext#TEXTURE21() */
  @Override
  public native int TEXTURE21()
  /*-[
    return GL_TEXTURE21;
  ]-*/;

  /* @see GLContext#TEXTURE22() */
  @Override
  public native int TEXTURE22()
  /*-[
    return GL_TEXTURE22;
  ]-*/;

  /* @see GLContext#TEXTURE23() */
  @Override
  public native int TEXTURE23()
  /*-[
    return GL_TEXTURE23;
  ]-*/;

  /* @see GLContext#TEXTURE24() */
  @Override
  public native int TEXTURE24()
  /*-[
    return GL_TEXTURE24;
  ]-*/;

  /* @see GLContext#TEXTURE25() */
  @Override
  public native int TEXTURE25()
  /*-[
    return GL_TEXTURE25;
  ]-*/;

  /* @see GLContext#TEXTURE26() */
  @Override
  public native int TEXTURE26()
  /*-[
    return GL_TEXTURE26;
  ]-*/;

  /* @see GLContext#TEXTURE27() */
  @Override
  public native int TEXTURE27()
  /*-[
    return GL_TEXTURE27;
  ]-*/;

  /* @see GLContext#TEXTURE28() */
  @Override
  public native int TEXTURE28()
  /*-[
    return GL_TEXTURE28;
  ]-*/;

  /* @see GLContext#TEXTURE29() */
  @Override
  public native int TEXTURE29()
  /*-[
    return GL_TEXTURE29;
  ]-*/;

  /* @see GLContext#TEXTURE30() */
  @Override
  public native int TEXTURE30()
  /*-[
    return GL_TEXTURE30;
  ]-*/;

  /* @see GLContext#TEXTURE31() */
  @Override
  public native int TEXTURE31()
  /*-[
    return GL_TEXTURE31;
  ]-*/;

  /* @see GLContext#TRIANGLE_FAN() */
  @Override
  public native int TRIANGLE_FAN()
  /*-[
    return GL_TRIANGLE_FAN;
  ]-*/;

  /* @see GLContext#TRIANGLE_STRIP() */
  @Override
  public native int TRIANGLE_STRIP()
  /*-[
    return GL_TRIANGLE_STRIP;
  ]-*/;

  /* @see GLContext#TRIANGLES() */
  @Override
  public native int TRIANGLES()
  /*-[
    return GL_TRIANGLES;
  ]-*/;

  /* @see GLContext#TRUE() */
  @Override
  public native int TRUE()
  /*-[
    return GL_TRUE;
  ]-*/;

  /* @see GLContext#UNPACK_ALIGNMENT() */
  @Override
  public native int UNPACK_ALIGNMENT()
  /*-[
    return GL_UNPACK_ALIGNMENT;
  ]-*/;

  /* @see GLContext#UNSIGNED_BYTE() */
  @Override
  public native int UNSIGNED_BYTE()
  /*-[
    return GL_UNSIGNED_BYTE;
  ]-*/;

  /* @see GLContext#UNSIGNED_INT() */
  @Override
  public native int UNSIGNED_INT()
  /*-[
    return GL_UNSIGNED_INT;
  ]-*/;

  /* @see GLContext#UNSIGNED_SHORT() */
  @Override
  public native int UNSIGNED_SHORT()
  /*-[
    return GL_UNSIGNED_SHORT;
  ]-*/;

  /* @see GLContext#UNSIGNED_SHORT_4_4_4_4() */
  @Override
  public native int UNSIGNED_SHORT_4_4_4_4()
  /*-[
    return GL_UNSIGNED_SHORT_4_4_4_4;
  ]-*/;

  /* @see GLContext#UNSIGNED_SHORT_5_5_5_1() */
  @Override
  public native int UNSIGNED_SHORT_5_5_5_1()
  /*-[
    return GL_UNSIGNED_SHORT_5_5_5_1;
  ]-*/;

  /* @see GLContext#UNSIGNED_SHORT_5_6_5() */
  @Override
  public native int UNSIGNED_SHORT_5_6_5()
  /*-[
    return GL_UNSIGNED_SHORT_5_6_5;
  ]-*/;

  /* @see GLContext#VALIDATE_STATUS() */
  @Override
  public native int VALIDATE_STATUS()
  /*-[
    return GL_VALIDATE_STATUS;
  ]-*/;

  /* @see GLContext#VENDOR() */
  @Override
  public native int VENDOR()
  /*-[
    return GL_VENDOR;
  ]-*/;

  /* @see GLContext#VERSION() */
  @Override
  public native int VERSION()
  /*-[
    return GL_VERSION;
  ]-*/;

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_BUFFER_BINDING() */
  @Override
  public native int VERTEX_ATTRIB_ARRAY_BUFFER_BINDING()
  /*-[
    return GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING;
  ]-*/;

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_ENABLED() */
  @Override
  public native int VERTEX_ATTRIB_ARRAY_ENABLED()
  /*-[
    return GL_VERTEX_ATTRIB_ARRAY_ENABLED;
  ]-*/;

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_NORMALIZED() */
  @Override
  public native int VERTEX_ATTRIB_ARRAY_NORMALIZED()
  /*-[
    return GL_VERTEX_ATTRIB_ARRAY_NORMALIZED;
  ]-*/;

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_POINTER() */
  @Override
  public native int VERTEX_ATTRIB_ARRAY_POINTER()
  /*-[
    return GL_VERTEX_ATTRIB_ARRAY_POINTER;
  ]-*/;

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_SIZE() */
  @Override
  public native int VERTEX_ATTRIB_ARRAY_SIZE()
  /*-[
    return GL_VERTEX_ATTRIB_ARRAY_SIZE;
  ]-*/;

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_STRIDE() */
  @Override
  public native int VERTEX_ATTRIB_ARRAY_STRIDE()
  /*-[
    return GL_VERTEX_ATTRIB_ARRAY_STRIDE;
  ]-*/;

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_TYPE() */
  @Override
  public native int VERTEX_ATTRIB_ARRAY_TYPE()
  /*-[
    return GL_VERTEX_ATTRIB_ARRAY_TYPE;
  ]-*/;

  /* @see GLContext#VERTEX_SHADER() */
  @Override
  public native int VERTEX_SHADER()
  /*-[
    return GL_VERTEX_SHADER;
  ]-*/;

  /* @see GLContext#VIEWPORT() */
  @Override
  public native int VIEWPORT()
  /*-[
    return GL_VIEWPORT;
  ]-*/;

  /* @see GLContext#ZERO() */
  @Override
  public native int ZERO()
  /*-[
    return GL_ZERO;
  ]-*/;

  //
  // Implementations of the OpenGL functions.
  //

  /* @see GLContext#activeTexture(int) */
  @Override
  public native void activeTexture(int texture)
  /*-[
    glActiveTexture(texture);
  ]-*/;

  /* @see GLContext#attachShader(int, int) */
  @Override
  public native void attachShader(int program, int shader)
  /*-[
    glAttachShader(program, shader);
  ]-*/;

  /* @see GLContext#bindAttribLocation(int, int, String) */
  @Override
  public native void bindAttribLocation(int program, int index, String name)
  /*-[
    glBindAttribLocation(program, index, [name cStringUsingEncoding:NSUTF8StringEncoding]);
  ]-*/;

  /* @see GLContext#bindBuffer(int, int) */
  @Override
  public native void bindBuffer(int target, int buffer)
  /*-[
    glBindBuffer(target, buffer);
  ]-*/;

  /* @see GLContext#bindFramebuffer(int, int) */
  @Override
  public native void bindFramebuffer(int target, int framebuffer)
  /*-[
    glBindFramebuffer(target, framebuffer);
  ]-*/;

  /* @see GLContext#bindRenderbuffer(int, int) */
  @Override
  public native void bindRenderbuffer(int target, int renderbuffer)
  /*-[
    glBindRenderbuffer(target, renderbuffer);
  ]-*/;

  /* @see GLContext#bindTexture(int, int) */
  @Override
  public native void bindTexture(int target, int texture)
  /*-[
    glBindTexture(target, texture);
  ]-*/;

  /* @see GLContext#blendColor(float, float, float, float) */
  @Override
  public native void blendColor(float red, float green, float blue, float alpha)
  /*-[
    glBlendColor(red, green, blue, alpha);
  ]-*/;

  /* @see GLContext#blendEquation(int) */
  @Override
  public native void blendEquation(int mode)
  /*-[
    glBlendEquation(mode);
  ]-*/;

  /* @see GLContext#blendEquationSeparate(int, int) */
  @Override
  public native void blendEquationSeparate(int modeRGB, int modeAlpha)
  /*-[
    glBlendEquationSeparate(modeRGB, modeAlpha);
  ]-*/;

  /* @see GLContext#blendFunc(int, int) */
  @Override
  public native void blendFunc(int sfactor, int dfactor)
  /*-[
    glBlendFunc(sfactor, dfactor);
  ]-*/;

  /* @see GLContext#blendFuncSeparate(int, int, int, int) */
  @Override
  public native void blendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha)
  /*-[
    glBlendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
  ]-*/;

  /* @see GLContext#bufferData(int, int, int) */
  @Override
  public native void bufferData(int target, int size, int usage)
  /*-[
    glBufferData(target, size, 0, usage);
  ]-*/;

  /* @see GLContext#bufferData(int, GLBytes, int) */
  @Override
  public void bufferData(int target, GLBytes data, int usage) {
    assert data instanceof IOSGLBytes;
    native_bufferData(target, data.size(), ((IOSGLBytes) data).pointer(), usage);
  }

  /** Delegate for the native {@code glBufferData} function. */
  private native void native_bufferData(int target, int size, long data, int usage)
  /*-[
    glBufferData(target, size, data, usage);
  ]-*/;

  /* @see GLContext#bufferSubData(int, int, GLBytes) */
  @Override
  public void bufferSubData(int target, int offset, GLBytes data) {
    assert data instanceof IOSGLBytes;
    native_bufferSubData(target, offset, data.size(), ((IOSGLBytes) data).pointer());
  }

  /** Delegate for the native {@code glBufferSubData} function. */
  private native void native_bufferSubData(int target, int offset, int size, long data)
  /*-[
    glBufferSubData(target, offset, size, data);
  ]-*/;

  /* @see GLContext#checkFramebufferStatus(int) */
  @Override
  public native int checkFramebufferStatus(int target)
  /*-[
    return glCheckFramebufferStatus(target);
  ]-*/;

  /* @see GLContext#clear(int) */
  @Override
  public native void clear(int mask)
  /*-[
    glClear(mask);
  ]-*/;

  /* @see GLContext#clearColor(float, float, float, float) */
  @Override
  public native void clearColor(float red, float green, float blue, float alpha)
  /*-[
    glClearColor(red, green, blue, alpha);
  ]-*/;

  /* @see GLContext#clearDepthf(float) */
  @Override
  public native void clearDepthf(float depth)
  /*-[
    glClearDepthf(depth);
  ]-*/;

  /* @see GLContext#clearStencil(int) */
  @Override
  public native void clearStencil(int s)
  /*-[
    glClearStencil(s);
  ]-*/;

  /* @see GLContext#colorMask(boolean, boolean, boolean, boolean) */
  @Override
  public native void colorMask(boolean red, boolean green, boolean blue, boolean alpha)
  /*-[
    glColorMask(red, green, blue, alpha);
  ]-*/;

  /* @see GLContext#compileShader(int) */
  @Override
  public native void compileShader(int shader)
  /*-[
    glCompileShader(shader);
  ]-*/;

  /* @see GLContext#compressedTexImage2D(int, int, int, int, int, int, GLBytes) */
  @Override
  public void compressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
      GLBytes data) {
    assert data instanceof IOSGLBytes;
    native_compressedTexImage2D(target, level, internalformat, width, height, border, data.size(),
        ((IOSGLBytes) data).pointer());
  }

  /** Delegate for the native {@code glCompressedTexImage2D} function. */
  private native void native_compressedTexImage2D(int target, int level, int internalformat, int width, int height,
      int border, int size, long data)
  /*-[
    glCompressedTexImage2D(target, level, internalformat, width, height, border, size, data);
  ]-*/;

  /* @see GLContext#compressedTexSubImage2D(int, int, int, int, int, int, int, GLBytes) */
  @Override
  public void compressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
      int format, GLBytes data) {
    assert data instanceof IOSGLBytes;
    native_compressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, data.size(),
        ((IOSGLBytes) data).pointer());
  }

  /** Delegate for the native {@code glCompressedTexSubImage2D} function. */
  private native void native_compressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width,
      int height, int format, int size, long data)
  /*-[
    glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, size, data);
  ]-*/;

  /* @see GLContext#copyTexImage2D(int, int, int, int, int, int, int, int) */
  @Override
  public native void copyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height,
      int border)
  /*-[
    glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
  ]-*/;

  /* @see GLContext#copyTexSubImage2D(int, int, int, int, int, int, int, int) */
  @Override
  public native void copyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width,
      int height)
  /*-[
    glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
  ]-*/;

  /* @see GLContext#createProgram() */
  @Override
  public native int createProgram()
  /*-[
    return glCreateProgram();
  ]-*/;

  /* @see GLContext#createShader(int) */
  @Override
  public native int createShader(int shaderType)
  /*-[
    return glCreateShader(shaderType);
  ]-*/;

  /* @see GLContext#cullFace(int) */
  @Override
  public native void cullFace(int mode)
  /*-[
    glCullFace(int mode);
  ]-*/;

  /* @see GLContext#deleteBuffers(GLIntegers) */
  @Override
  public void deleteBuffers(GLIntegers buffers) {
    assert buffers instanceof IOSGLIntegers;
    native_deleteBuffers(buffers.size(), ((IOSGLIntegers) buffers).pointer());
  }

  /** Delegate for the native {@code glDeleteBuffers} function. */
  private native void native_deleteBuffers(int size, long buffers)
  /*-[
    glDeleteBuffers(size, buffers);
  ]-*/;

  /* @see GLContext#deleteFramebuffers(GLIntegers) */
  @Override
  public void deleteFramebuffers(GLIntegers framebuffers) {
    assert framebuffers instanceof IOSGLIntegers;
    native_deleteFramebuffers(framebuffers.size(), ((IOSGLIntegers) framebuffers).pointer());
  }

  /** Delegate for the native {@code glDeleteFramebuffers} function. */
  private native void native_deleteFramebuffers(int size, long framebuffers)
  /*-[
    glDeleteFramebuffers(size, framebuffers);
  ]-*/;

  /* @see GLContext#deleteProgram(int) */
  @Override
  public native void deleteProgram(int program)
  /*-[
    glDeleteProgram(program);
  ]-*/;

  /* @see GLContext#deleteRenderbuffers(GLIntegers) */
  @Override
  public void deleteRenderbuffers(GLIntegers renderbuffers) {
    assert renderbuffers instanceof IOSGLIntegers;
    native_deleteRenderbuffers(renderbuffers.size(), ((IOSGLIntegers) renderbuffers).pointer());
  }

  /** Delegate for the native {@code glDleteRenderbuffers} function. */
  private native void native_deleteRenderbuffers(int size, long renderbuffers)
  /*-[
    glDleteRenderbuffers(size, renderbuffers);
  ]-*/;

  /* @see GLContext#deleteShader(int) */
  @Override
  public native void deleteShader(int shader)
  /*-[
    glDeleteShader(shader);
  ]-*/;

  /* @see GLContext#deleteTextures(GLIntegers) */
  @Override
  public void deleteTextures(GLIntegers textures) {
    assert textures instanceof IOSGLIntegers;
    native_deleteTextures(textures.size(), ((IOSGLIntegers) textures).pointer());
  }

  /** Delegate for the native {@code glDeleteTextures} function. */
  private native void native_deleteTextures(int size, long textures)
  /*-[
    glDeleteTextures(size, textures);
  ]-*/;

  /* @see GLContext#depthFunc(int) */
  @Override
  public native void depthFunc(int func)
  /*-[
    glDepthFunc(func);
  ]-*/;

  /* @see GLContext#depthMask(boolean) */
  @Override
  public native void depthMask(boolean flag)
  /*-[
    glDepthMask(flag);
  ]-*/;

  /* @see GLContext#depthRangef(float, float) */
  @Override
  public native void depthRangef(float nearVal, float farVal)
  /*-[
    glDepthRangef(nearVal, farVal);
  ]-*/;

  /* @see GLContext#detachShader(int, int) */
  @Override
  public native void detachShader(int program, int shader)
  /*-[
    glDetachShader(program, shader);
  ]-*/;

  /* @see GLContext#disable(int) */
  @Override
  public native void disable(int cap)
  /*-[
    glDisable(cap);
  ]-*/;

  /* @see GLContext#disableVertexAttribArray(int) */
  @Override
  public native void disableVertexAttribArray(int index)
  /*-[
    glDisableVertexAttribArray(index);
  ]-*/;

  /* @see GLContext#drawArrays(int, int, int) */
  @Override
  public native void drawArrays(int mode, int first, int count)
  /*-[
    glDrawArrays(mode, first, count);
  ]-*/;

  /* @see GLContext#drawElements(int, int, int, int) */
  @Override
  public native void drawElements(int mode, int count, int type, int offset)
  /*-[
    glDrawElements(mode, count, type, offset);
  ]-*/;

  /* @see GLContext#drawElements(int, GLBytes) */
  @Override
  public void drawElements(int mode, GLBytes indices) {
    assert indices instanceof IOSGLBytes;
    // FIXME drawElements(mode, indices.size(), BYTE(), ((IOSGLBytes) indices).pointer());
  }

  /* @see GLContext#drawElements(int, GLShorts) */
  @Override
  public void drawElements(int mode, GLShorts indices) {
    assert indices instanceof IOSGLShorts;
    // FIXME drawElements(mode, indices.size(), UNSIGNED_SHORT(), ((IOSGLShorts) indices).pointer());
  }

  /* @see GLContext#enable(int) */
  @Override
  public native void enable(int cap)
  /*-[
    glEnable(cap);
  ]-*/;

  /* @see GLContext#enableVertexAttribArray(int) */
  @Override
  public native void enableVertexAttribArray(int index)
  /*-[
    glEnableVertexAttribArray(index);
  ]-*/;

  /* @see GLContext#finish() */
  @Override
  public native void finish()
  /*-[
    glFinish();
  ]-*/;

  /* @see GLContext#flush() */
  @Override
  public native void flush()
  /*-[
    glFlush();
  ]-*/;

  /* @see GLContext#framebufferRenderbuffer(int, int, int, int) */
  @Override
  public native void framebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer)
  /*-[
    glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer);
  ]-*/;

  /* @see GLContext#framebufferTexture2D(int, int, int, int, int) */
  @Override
  public native void framebufferTexture2D(int target, int attachment, int textarget, int texture, int level)
  /*-[
    glFramebufferTexture2D(target, attachment, textarget, texture, level);
  ]-*/;

  /* @see GLContext#frontFace(int) */
  @Override
  public native void frontFace(int mode)
  /*-[
    glFrontFace(mode);
  ]-*/;

  /* @see GLContext#genBuffers(GLIntegers) */
  @Override
  public void genBuffers(GLIntegers buffers) {
    assert buffers instanceof IOSGLIntegers;
    native_genBuffers(buffers.size(), ((IOSGLIntegers) buffers).pointer());
  }

  /** Delegate for the native {@code glGenBuffers} function. */
  private native void native_genBuffers(int size, long buffers)
  /*-[
    glGenBuffers(size, buffers);
  ]-*/;

  /* @see GLContext#genFramebuffers(GLIntegers) */
  @Override
  public void genFramebuffers(GLIntegers framebuffers) {
    assert framebuffers instanceof IOSGLIntegers;
    native_genFramebuffers(framebuffers.size(), ((IOSGLIntegers) framebuffers).pointer());
  }

  /** Delegate for the native {@code glGenFramebuffers} function. */
  private native void native_genFramebuffers(int size, long framebuffers)
  /*-[
    glGenFramebuffers(size, framebuffers);
  ]-*/;

  /* @see GLContext#genRenderbuffers(GLIntegers) */
  @Override
  public void genRenderbuffers(GLIntegers renderbuffers) {
    assert renderbuffers instanceof IOSGLIntegers;
    native_genRenderbuffers(renderbuffers.size(), ((IOSGLIntegers) renderbuffers).pointer());
  }

  /** Delegate for the native {@code glGenRenderbuffers} function. */
  private native void native_genRenderbuffers(int size, long renderbuffers)
  /*-[
    glGenRenderbuffers(size, renderbuffers);
  ]-*/;

  /* @see GLContext#genTextures(GLIntegers) */
  @Override
  public void genTextures(GLIntegers textures) {
    assert textures instanceof IOSGLIntegers;
    native_genTextures(textures.size(), ((IOSGLIntegers) textures).pointer());
  }

  /** Delegate for the native {@code glGenTextures} function. */
  private native void native_genTextures(int size, long textures)
  /*-[
    glGenTextures(size, textures);
  ]-*/;

  /* @see GLContext#generateMipmap(int) */
  @Override
  public native void generateMipmap(int target)
  /*-[
    glGenerateMipmap(target);
  ]-*/;

  /* @see GLContext#getBooleanv(int) */
  @Override
  public boolean[] getBooleanv(int pname) {
    boolean[] array = new boolean[parameterSize(pname)];
    native_getBooleanv(pname, byteBuffer.pointer());
    for (int i = 0; i < array.length; ++i)
      if (byteBuffer.get(i) != 0)
        array[i] = true;
    return array;
  }

  /** Delegate for the native {@code glGetBooleanv} function. */
  private native void native_getBooleanv(int pname, long params)
  /*-[
    glGetBooleanv(pname, params);
  ]-*/;

  /* @see GLContext#getFloatv(int) */
  @Override
  public float[] getFloatv(int pname) {
    float[] array = new float[parameterSize(pname)];
    IOSGLFloats buffer = (IOSGLFloats) byteBuffer.asFloats();
    native_getFloatv(pname, buffer.pointer());
    buffer.headData(array.length).copyTo(array);
    return array;
  }

  /** Delegate for the native {@code glGetFloatv} function. */
  private native void native_getFloatv(int pname, long params)
  /*-[
    return glGetFloatv(pname, params);
  ]-*/;

  /* @see GLContext#getIntegerv(int) */
  @Override
  public int[] getIntegerv(int pname) {
    int[] array = new int[parameterSize(pname)];
    IOSGLIntegers buffer = (IOSGLIntegers) byteBuffer.asIntegers();
    native_getIntegerv(pname, buffer.pointer());
    buffer.headData(array.length).copyTo(array);
    return array;
  }

  /** Delegate for the native {@code glGetIntegerv} function. */
  private native int[] native_getIntegerv(int pname, long params)
  /*-[
    return glGetIntegerv(pname, params);
  ]-*/;

  /** Returns the number of data elements for the specified parameter. */
  private native int parameterSize(int pname)
  /*-[
    switch (pname) {
    case GL_ALIASED_LINE_WIDTH_RANGE:
      return 2;
    case GL_ALIASED_POINT_SIZE_RANGE:
      return 2;
    case GL_BLEND_COLOR:
      return 4;
    case GL_COLOR_CLEAR_VALUE:
      return 4;
    case GL_COLOR_WRITEMASK:
      return 4;
    case GL_COMPRESSED_TEXTURE_FORMATS:
      return [[self getIntegervWithInt: GL_NUM_COMPRESSED_TEXTURE_FORMATS] intAtIndex: 0];
    case GL_DEPTH_RANGE:
      return 2;
    case GL_MAX_VIEWPORT_DIMS:
      return 2;
    case GL_SCISSOR_BOX:
      return 4;
    case GL_SHADER_BINARY_FORMATS:
      return [[self getIntegervWithInt: GL_NUM_SHADER_BINARY_FORMATS] intAtIndex: 0];
    case GL_VIEWPORT:
      return 4;
    default:
      return 1;
   }
  ]-*/;

  /* @see GLContext#getActiveAttrib(int, int) */
  @Override
  public native GLVariable getActiveAttrib(int program, int index)
  /*-[
    return gl_getActiveAttrib(int program, int index);
  ]-*/;

  /* @see GLContext#getActiveUniform(int, int) */
  @Override
  public native GLVariable getActiveUniform(int program, int index)
  /*-[
    return gl_getActiveUniform(int program, int index);
  ]-*/;

  /* @see GLContext#getAttachedShaders(int) */
  @Override
  public native int[] getAttachedShaders(int program)
  /*-[
    return gl_getAttachedShaders(int program);
  ]-*/;

  /* @see GLContext#getAttribLocation(int, String) */
  @Override
  public native int getAttribLocation(int program, String name)
  /*-[
    return gl_getAttribLocation(int program, String name);
  ]-*/;

  /* @see GLContext#getBufferParameteriv(int, int) */
  @Override
  public native int[] getBufferParameteriv(int target, int value)
  /*-[
    return gl_getBufferParameteriv(int target, int value);
  ]-*/;

  /* @see GLContext#getError() */
  @Override
  public native int getError()
  /*-[
    return gl_getError();
  ]-*/;

  /* @see GLContext#getFramebufferAttachmentParameteriv(int, int, int) */
  @Override
  public native int[] getFramebufferAttachmentParameteriv(int target, int attachment, int pname)
  /*-[
    return gl_getFramebufferAttachmentParameteriv(int target, int attachment, int pname);
  ]-*/;

  /* @see GLContext#getProgramInfoLog(int) */
  @Override
  public native String getProgramInfoLog(int program)
  /*-[
    return gl_getProgramInfoLog(int program);
  ]-*/;

  /* @see GLContext#getProgramiv(int, int) */
  @Override
  public native int[] getProgramiv(int program, int pname)
  /*-[
    return gl_getProgramiv(int program, int pname);
  ]-*/;

  /* @see GLContext#getRenderbufferParameteriv(int, int) */
  @Override
  public native int[] getRenderbufferParameteriv(int target, int pname)
  /*-[
    return gl_getRenderbufferParameteriv(int target, int pname);
  ]-*/;

  /* @see GLContext#getShaderInfoLog(int) */
  @Override
  public native String getShaderInfoLog(int shader)
  /*-[
    return gl_getShaderInfoLog(int shader);
  ]-*/;

  /* @see GLContext#getShaderPrecisionFormat(int, int) */
  @Override
  public native GLPrecision getShaderPrecisionFormat(int shaderType, int precisionType)
  /*-[
    return gl_getShaderPrecisionFormat(int shaderType, int precisionType);
  ]-*/;

  /* @see GLContext#getShaderSource(int) */
  @Override
  public native String getShaderSource(int shader)
  /*-[
    return gl_getShaderSource(int shader);
  ]-*/;

  /* @see GLContext#getShaderiv(int, int) */
  @Override
  public native int[] getShaderiv(int shader, int pname)
  /*-[
    return gl_getShaderiv(int shader, int pname);
  ]-*/;

  /* @see GLContext#getString(int) */
  @Override
  public native String getString(int name)
  /*-[
    return gl_getString(int name);
  ]-*/;

  /* @see GLContext#getTexParameterfv(int, int) */
  @Override
  public native float[] getTexParameterfv(int target, int pname)
  /*-[
    return gl_getTexParameterfv(int target, int pname);
  ]-*/;

  /* @see GLContext#getTexParameteriv(int, int) */
  @Override
  public native int[] getTexParameteriv(int target, int pname)
  /*-[
    return gl_getTexParameteriv(int target, int pname);
  ]-*/;

  /* @see GLContext#getUniformfv(int, int, GLFloats) */
  @Override
  public native void getUniformfv(int program, int location, GLFloats params)
  /*-[
    gl_getUniformfv(int program, int location, GLFloats params);
  ]-*/;

  /* @see GLContext#getUniformiv(int, int, GLIntegers) */
  @Override
  public native void getUniformiv(int program, int location, GLIntegers params)
  /*-[
    gl_getUniformiv(int program, int location, GLIntegers params);
  ]-*/;

  /* @see GLContext#getUniformLocation(int, String) */
  @Override
  public native int getUniformLocation(int program, String name)
  /*-[
    return gl_getUniformLocation(int program, String name);
  ]-*/;

  /* @see GLContext#getVertexAttribfv(int, int, GLFloats) */
  @Override
  public native void getVertexAttribfv(int index, int pname, GLFloats params)
  /*-[
    gl_getVertexAttribfv(int index, int pname, GLFloats params);
  ]-*/;

  /* @see GLContext#getVertexAttribiv(int, int, GLIntegers) */
  @Override
  public native void getVertexAttribiv(int index, int pname, GLIntegers params)
  /*-[
    gl_getVertexAttribiv(int index, int pname, GLIntegers params);
  ]-*/;

  /* @see GLContext#hint(int, int) */
  @Override
  public native void hint(int target, int mode)
  /*-[
    gl_hint(int target, int mode);
  ]-*/;

  /* @see GLContext#isBuffer(int) */
  @Override
  public native boolean isBuffer(int buffer)
  /*-[
    return gl_isBuffer(int buffer);
  ]-*/;

  /* @see GLContext#isEnabled(int) */
  @Override
  public native boolean isEnabled(int cap)
  /*-[
    return gl_isEnabled(int cap);
  ]-*/;

  /* @see GLContext#isFramebuffer(int) */
  @Override
  public native boolean isFramebuffer(int framebuffer)
  /*-[
    return gl_isFramebuffer(int framebuffer);
  ]-*/;

  /* @see GLContext#isProgram(int) */
  @Override
  public native boolean isProgram(int program)
  /*-[
    return gl_isProgram(int program);
  ]-*/;

  /* @see GLContext#isRenderbuffer(int) */
  @Override
  public native boolean isRenderbuffer(int renderbuffer)
  /*-[
    return gl_isRenderbuffer(int renderbuffer);
  ]-*/;

  /* @see GLContext#isShader(int) */
  @Override
  public native boolean isShader(int shader)
  /*-[
    return gl_isShader(int shader);
  ]-*/;

  /* @see GLContext#isTexture(int) */
  @Override
  public native boolean isTexture(int texture)
  /*-[
    return gl_isTexture(int texture);
  ]-*/;

  /* @see GLContext#lineWidth(float) */
  @Override
  public native void lineWidth(float width)
  /*-[
    gl_lineWidth(float width);
  ]-*/;

  /* @see GLContext#linkProgram(int) */
  @Override
  public native void linkProgram(int program)
  /*-[
    gl_linkProgram(int program);
  ]-*/;

  /* @see GLContext#pixelStorei(int, int) */
  @Override
  public native void pixelStorei(int pname, int param)
  /*-[
    gl_pixelStorei(int pname, int param);
  ]-*/;

  /* @see GLContext#polygonOffset(float, float) */
  @Override
  public native void polygonOffset(float factor, float units)
  /*-[
    gl_polygonOffset(float factor, float units);
  ]-*/;

  /* @see GLContext#readPixels(int, int, int, int, int, GLBytes) */
  @Override
  public native void readPixels(int x, int y, int width, int height, int format, GLBytes data)
  /*-[
    gl_readPixels(int x, int y, int width, int height, int format, GLBytes data);
  ]-*/;

  /* @see GLContext#readPixels(int, int, int, int, int, int, GLShorts) */
  @Override
  public native void readPixels(int x, int y, int width, int height, int format, int type, GLShorts data)
  /*-[
    gl_readPixels(int x, int y, int width, int height, int format, int type, GLShorts data);
  ]-*/;

  /* @see GLContext#releaseShaderCompiler() */
  @Override
  public native void releaseShaderCompiler()
  /*-[
    gl_releaseShaderCompiler();
  ]-*/;

  /* @see GLContext#renderbufferStorage(int, int, int, int) */
  @Override
  public native void renderbufferStorage(int target, int internalformat, int width, int height)
  /*-[
    gl_renderbufferStorage(int target, int internalformat, int width, int height);
  ]-*/;

  /* @see GLContext#sampleCoverage(float, boolean) */
  @Override
  public native void sampleCoverage(float value, boolean invert)
  /*-[
    gl_sampleCoverage(float value, boolean invert);
  ]-*/;

  /* @see GLContext#scissor(int, int, int, int) */
  @Override
  public native void scissor(int x, int y, int width, int height)
  /*-[
    gl_scissor(int x, int y, int width, int height);
  ]-*/;

  /* @see GLContext#shaderBinary(GLIntegers, int, GLBytes) */
  @Override
  public native void shaderBinary(GLIntegers shaders, int binaryformat, GLBytes binary)
  /*-[
    gl_shaderBinary(GLIntegers shaders, int binaryformat, GLBytes binary);
  ]-*/;

  /* @see GLContext#shaderSource(int, String) */
  @Override
  public native void shaderSource(int shader, String string)
  /*-[
    gl_shaderSource(int shader, String string);
  ]-*/;

  /* @see GLContext#stencilFunc(int, int, int) */
  @Override
  public native void stencilFunc(int func, int ref, int mask)
  /*-[
    gl_stencilFunc(int func, int ref, int mask);
  ]-*/;

  /* @see GLContext#stencilFuncSeparate(int, int, int, int) */
  @Override
  public native void stencilFuncSeparate(int face, int func, int ref, int mask)
  /*-[
    gl_stencilFuncSeparate(int face, int func, int ref, int mask);
  ]-*/;

  /* @see GLContext#stencilMask(int) */
  @Override
  public native void stencilMask(int mask)
  /*-[
    gl_stencilMask(int mask);
  ]-*/;

  /* @see GLContext#stencilMaskSeparate(int, int) */
  @Override
  public native void stencilMaskSeparate(int face, int mask)
  /*-[
    gl_stencilMaskSeparate(int face, int mask);
  ]-*/;

  /* @see GLContext#stencilOp(int, int, int) */
  @Override
  public native void stencilOp(int sfail, int dpfail, int dppass)
  /*-[
    gl_stencilOp(int sfail, int dpfail, int dppass);
  ]-*/;

  /* @see GLContext#stencilOpSeparate(int, int, int, int) */
  @Override
  public native void stencilOpSeparate(int face, int sfail, int dpfail, int dppass)
  /*-[
    gl_stencilOpSeparate(int face, int sfail, int dpfail, int dppass);
  ]-*/;

  /* @see GLContext#texImage2D(int, int, int, int, int, int, int, GLBytes) */
  @Override
  public native void texImage2D(int target, int level, int internalformat, int width, int height, int border,
      int format, GLBytes data)
  /*-[
    gl_texImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
      GLBytes data);
  ]-*/;

  /* @see GLContext#texImage2D(int, int, int, int, int, int, int, int, GLShorts) */
  @Override
  public native void texImage2D(int target, int level, int internalformat, int width, int height, int border,
      int format, int type, GLShorts data)
  /*-[
    gl_texImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
      int type, GLShorts data);
  ]-*/;

  /* @see GLContext#texParameterf(int, int, float) */
  @Override
  public native void texParameterf(int target, int pname, float param)
  /*-[
    gl_texParameterf(int target, int pname, float param);
  ]-*/;

  /* @see GLContext#texParameteri(int, int, int) */
  @Override
  public native void texParameteri(int target, int pname, int param)
  /*-[
    gl_texParameteri(int target, int pname, int param);
  ]-*/;

  /* @see GLContext#texParameterfv(int, int, GLFloats) */
  @Override
  public native void texParameterfv(int target, int pname, GLFloats params)
  /*-[
    gl_texParameterfv(int target, int pname, GLFloats params);
  ]-*/;

  /* @see GLContext#texParameteriv(int, int, GLIntegers) */
  @Override
  public native void texParameteriv(int target, int pname, GLIntegers params)
  /*-[
    gl_texParameteriv(int target, int pname, GLIntegers params);
  ]-*/;

  /* @see GLContext#texSubImage2D(int, int, int, int, int, int, int, GLBytes) */
  @Override
  public native void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
      GLBytes data)
  /*-[
    gl_texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
      GLBytes data);
  ]-*/;

  /* @see GLContext#texSubImage2D(int, int, int, int, int, int, int, int, GLShorts) */
  @Override
  public native void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
      int type, GLShorts data)
  /*-[
    gl_texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
      int type, GLShorts data);
  ]-*/;

  /* @see GLContext#uniform1f(int, float) */
  @Override
  public native void uniform1f(int location, float v0)
  /*-[
    gl_uniform1f(int location, float v0);
  ]-*/;

  /* @see GLContext#uniform2f(int, float, float) */
  @Override
  public native void uniform2f(int location, float v0, float v1)
  /*-[
    gl_uniform2f(int location, float v0, float v1);
  ]-*/;

  /* @see GLContext#uniform3f(int, float, float, float) */
  @Override
  public native void uniform3f(int location, float v0, float v1, float v2)
  /*-[
    gl_uniform3f(int location, float v0, float v1, float v2);
  ]-*/;

  /* @see GLContext#uniform4f(int, float, float, float, float) */
  @Override
  public native void uniform4f(int location, float v0, float v1, float v2, float v3)
  /*-[
    gl_uniform4f(int location, float v0, float v1, float v2, float v3);
  ]-*/;

  /* @see GLContext#uniform1i(int, int) */
  @Override
  public native void uniform1i(int location, int v0)
  /*-[
    gl_uniform1i(int location, int v0);
  ]-*/;

  /* @see GLContext#uniform2i(int, int, int) */
  @Override
  public native void uniform2i(int location, int v0, int v1)
  /*-[
    gl_uniform2i(int location, int v0, int v1);
  ]-*/;

  /* @see GLContext#uniform3i(int, int, int, int) */
  @Override
  public native void uniform3i(int location, int v0, int v1, int v2)
  /*-[
    gl_uniform3i(int location, int v0, int v1, int v2);
  ]-*/;

  /* @see GLContext#uniform4i(int, int, int, int, int) */
  @Override
  public native void uniform4i(int location, int v0, int v1, int v2, int v3)
  /*-[
    gl_uniform4i(int location, int v0, int v1, int v2, int v3);
  ]-*/;

  /* @see GLContext#uniform1fv(int, GLFloats) */
  @Override
  public native void uniform1fv(int location, GLFloats value)
  /*-[
    gl_uniform1fv(int location, GLFloats value);
  ]-*/;

  /* @see GLContext#uniform2fv(int, GLFloats) */
  @Override
  public native void uniform2fv(int location, GLFloats value)
  /*-[
    gl_uniform2fv(int location, GLFloats value);
  ]-*/;

  /* @see GLContext#uniform3fv(int, GLFloats) */
  @Override
  public native void uniform3fv(int location, GLFloats value)
  /*-[
    gl_uniform3fv(int location, GLFloats value);
  ]-*/;

  /* @see GLContext#uniform4fv(int, GLFloats) */
  @Override
  public native void uniform4fv(int location, GLFloats value)
  /*-[
    gl_uniform4fv(int location, GLFloats value);
  ]-*/;

  /* @see GLContext#uniform1iv(int, GLIntegers) */
  @Override
  public native void uniform1iv(int location, GLIntegers value)
  /*-[
    gl_uniform1iv(int location, GLIntegers value);
  ]-*/;

  /* @see GLContext#uniform2iv(int, GLIntegers) */
  @Override
  public native void uniform2iv(int location, GLIntegers value)
  /*-[
    gl_uniform2iv(int location, GLIntegers value);
  ]-*/;

  /* @see GLContext#uniform3iv(int, GLIntegers) */
  @Override
  public native void uniform3iv(int location, GLIntegers value)
  /*-[
    gl_uniform3iv(int location, GLIntegers value);
  ]-*/;

  /* @see GLContext#uniform4iv(int, GLIntegers) */
  @Override
  public native void uniform4iv(int location, GLIntegers value)
  /*-[
    gl_uniform4iv(int location, GLIntegers value);
  ]-*/;

  /* @see GLContext#uniformMatrix2fv(int, boolean, GLFloats) */
  @Override
  public native void uniformMatrix2fv(int location, boolean transpose, GLFloats value)
  /*-[
    gl_uniformMatrix2fv(int location, boolean transpose, GLFloats value);
  ]-*/;

  /* @see GLContext#uniformMatrix3fv(int, boolean, GLFloats) */
  @Override
  public native void uniformMatrix3fv(int location, boolean transpose, GLFloats value)
  /*-[
    gl_uniformMatrix3fv(int location, boolean transpose, GLFloats value);
  ]-*/;

  /* @see GLContext#uniformMatrix4fv(int, boolean, GLFloats) */
  @Override
  public native void uniformMatrix4fv(int location, boolean transpose, GLFloats value)
  /*-[
    gl_uniformMatrix4fv(int location, boolean transpose, GLFloats value);
  ]-*/;

  /* @see GLContext#useProgram(int) */
  @Override
  public native void useProgram(int program)
  /*-[
    gl_useProgram(int program);
  ]-*/;

  /* @see GLContext#validateProgram(int) */
  @Override
  public native void validateProgram(int program)
  /*-[
    gl_validateProgram(int program);
  ]-*/;

  /* @see GLContext#vertexAttrib1f(int, float) */
  @Override
  public native void vertexAttrib1f(int index, float v0)
  /*-[
    gl_vertexAttrib1f(int index, float v0);
  ]-*/;

  /* @see GLContext#vertexAttrib2f(int, float, float) */
  @Override
  public native void vertexAttrib2f(int index, float v0, float v1)
  /*-[
    gl_vertexAttrib2f(int index, float v0, float v1);
  ]-*/;

  /* @see GLContext#vertexAttrib3f(int, float, float, float) */
  @Override
  public native void vertexAttrib3f(int index, float v0, float v1, float v2)
  /*-[
    gl_vertexAttrib3f(int index, float v0, float v1, float v2);
  ]-*/;

  /* @see GLContext#vertexAttrib4f(int, float, float, float, float) */
  @Override
  public native void vertexAttrib4f(int index, float v0, float v1, float v2, float v3)
  /*-[
    gl_vertexAttrib4f(int index, float v0, float v1, float v2, float v3);
  ]-*/;

  /* @see GLContext#vertexAttrib1fv(int, GLFloats) */
  @Override
  public native void vertexAttrib1fv(int index, GLFloats v)
  /*-[
    gl_vertexAttrib1fv(int index, GLFloats v);
  ]-*/;

  /* @see GLContext#vertexAttrib2fv(int, GLFloats) */
  @Override
  public native void vertexAttrib2fv(int index, GLFloats v)
  /*-[
    gl_vertexAttrib2fv(int index, GLFloats v);
  ]-*/;

  /* @see GLContext#vertexAttrib3fv(int, GLFloats) */
  @Override
  public native void vertexAttrib3fv(int index, GLFloats v)
  /*-[
    gl_vertexAttrib3fv(int index, GLFloats v);
  ]-*/;

  /* @see GLContext#vertexAttrib4fv(int, GLFloats) */
  @Override
  public native void vertexAttrib4fv(int index, GLFloats v)
  /*-[
    gl_vertexAttrib4fv(int index, GLFloats v);
  ]-*/;

  /* @see GLContext#vertexAttribPointer(int, int, int, boolean, int, int) */
  @Override
  public native void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset)
  /*-[
    gl_vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset);
  ]-*/;

  /* @see GLContext#vertexAttribPointer(int, int, boolean, boolean, int, GLBytes) */
  @Override
  public native void vertexAttribPointer(int index, int size, boolean isUnsigned, boolean normalized, int stride,
      GLBytes pointer)
  /*-[
    gl_vertexAttribPointer(int index, int size, boolean isUnsigned, boolean normalized, int stride, GLBytes pointer);
  ]-*/;

  /* @see GLContext#vertexAttribPointer(int, int, boolean, boolean, int, GLShorts) */
  @Override
  public native void vertexAttribPointer(int index, int size, boolean isUnsigned, boolean normalized, int stride,
      GLShorts pointer)
  /*-[
    gl_vertexAttribPointer(int index, int size, boolean isUnsigned, boolean normalized, int stride,
      GLShorts pointer);
  ]-*/;

  /* @see GLContext#vertexAttribPointer(int, int, boolean, boolean, int, GLIntegers) */
  @Override
  public native void vertexAttribPointer(int index, int size, boolean isUnsigned, boolean normalized, int stride,
      GLIntegers pointer)
  /*-[
    gl_vertexAttribPointer(int index, int size, boolean isUnsigned, boolean normalized, int stride,
      GLIntegers pointer);
  ]-*/;

  /* @see GLContext#vertexAttribPointer(int, int, boolean, int, GLFloats) */
  @Override
  public native void vertexAttribPointer(int index, int size, boolean normalized, int stride, GLFloats pointer)
  /*-[
    gl_vertexAttribPointer(int index, int size, boolean normalized, int stride, GLFloats pointer);
  ]-*/;

  /* @see GLContext#viewport(int, int, int, int) */
  @Override
  public native void viewport(int x, int y, int width, int height)
  /*-[
    gl_viewport(int x, int y, int width, int height);
  ]-*/;

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

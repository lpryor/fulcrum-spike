/*
 * AndroidGLContext.java
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
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static android.opengl.GLES20.*;
import fulcrum.opengl.GLBytes;
import fulcrum.opengl.GLContext;
import fulcrum.opengl.GLFloats;
import fulcrum.opengl.GLIntegers;
import fulcrum.opengl.GLPrecision;
import fulcrum.opengl.GLShorts;
import fulcrum.opengl.GLVariable;

/**
 * An Android-specific implementation of {@link GLContext}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class AndroidGLContext extends GLContext {

  /** A float array to use inside method implementations. */
  private final float[] floatArray = new float[1];
  /** An integer array to use inside method implementations. */
  private final int[] intArrayA = new int[2];
  /** Another integer array to use inside method implementations. */
  private final int[] intArrayB = new int[1];

  //
  // Accessors for the OpenGL constants.
  //

  /* @see GLContext#ACTIVE_ATTRIBUTE_MAX_LENGTH() */
  @Override
  public int ACTIVE_ATTRIBUTE_MAX_LENGTH() {
    return GL_ACTIVE_ATTRIBUTE_MAX_LENGTH;
  }

  /* @see GLContext#ACTIVE_ATTRIBUTES() */
  @Override
  public int ACTIVE_ATTRIBUTES() {
    return GL_ACTIVE_ATTRIBUTES;
  }

  /* @see GLContext#ACTIVE_TEXTURE() */
  @Override
  public int ACTIVE_TEXTURE() {
    return GL_ACTIVE_TEXTURE;
  }

  /* @see GLContext#ACTIVE_UNIFORM_MAX_LENGTH() */
  @Override
  public int ACTIVE_UNIFORM_MAX_LENGTH() {
    return GL_ACTIVE_UNIFORM_MAX_LENGTH;
  }

  /* @see GLContext#ACTIVE_UNIFORMS() */
  @Override
  public int ACTIVE_UNIFORMS() {
    return GL_ACTIVE_UNIFORMS;
  }

  /* @see GLContext#ALIASED_LINE_WIDTH_RANGE() */
  @Override
  public int ALIASED_LINE_WIDTH_RANGE() {
    return GL_ALIASED_LINE_WIDTH_RANGE;
  }

  /* @see GLContext#ALIASED_POINT_SIZE_RANGE() */
  @Override
  public int ALIASED_POINT_SIZE_RANGE() {
    return GL_ALIASED_POINT_SIZE_RANGE;
  }

  /* @see GLContext#ALPHA() */
  @Override
  public int ALPHA() {
    return GL_ALPHA;
  }

  /* @see GLContext#ALPHA_BITS() */
  @Override
  public int ALPHA_BITS() {
    return GL_ALPHA_BITS;
  }

  /* @see GLContext#ALWAYS() */
  @Override
  public int ALWAYS() {
    return GL_ALWAYS;
  }

  /* @see GLContext#ARRAY_BUFFER() */
  @Override
  public int ARRAY_BUFFER() {
    return GL_ARRAY_BUFFER;
  }

  /* @see GLContext#ARRAY_BUFFER_BINDING() */
  @Override
  public int ARRAY_BUFFER_BINDING() {
    return GL_ARRAY_BUFFER_BINDING;
  }

  /* @see GLContext#ATTACHED_SHADERS() */
  @Override
  public int ATTACHED_SHADERS() {
    return GL_ATTACHED_SHADERS;
  }

  /* @see GLContext#BACK() */
  @Override
  public int BACK() {
    return GL_BACK;
  }

  /* @see GLContext#BLEND() */
  @Override
  public int BLEND() {
    return GL_BLEND;
  }

  /* @see GLContext#BLEND_COLOR() */
  @Override
  public int BLEND_COLOR() {
    return GL_BLEND_COLOR;
  }

  /* @see GLContext#BLEND_DST_ALPHA() */
  @Override
  public int BLEND_DST_ALPHA() {
    return GL_BLEND_DST_ALPHA;
  }

  /* @see GLContext#BLEND_DST_RGB() */
  @Override
  public int BLEND_DST_RGB() {
    return GL_BLEND_DST_RGB;
  }

  /* @see GLContext#BLEND_EQUATION() */
  @Override
  public int BLEND_EQUATION() {
    return GL_BLEND_EQUATION;
  }

  /* @see GLContext#BLEND_EQUATION_ALPHA() */
  @Override
  public int BLEND_EQUATION_ALPHA() {
    return GL_BLEND_EQUATION_ALPHA;
  }

  /* @see GLContext#BLEND_EQUATION_RGB() */
  @Override
  public int BLEND_EQUATION_RGB() {
    return GL_BLEND_EQUATION_RGB;
  }

  /* @see GLContext#BLEND_SRC_ALPHA() */
  @Override
  public int BLEND_SRC_ALPHA() {
    return GL_BLEND_SRC_ALPHA;
  }

  /* @see GLContext#BLEND_SRC_RGB() */
  @Override
  public int BLEND_SRC_RGB() {
    return GL_BLEND_SRC_RGB;
  }

  /* @see GLContext#BLUE_BITS() */
  @Override
  public int BLUE_BITS() {
    return GL_BLUE_BITS;
  }

  /* @see GLContext#BOOL() */
  @Override
  public int BOOL() {
    return GL_BOOL;
  }

  /* @see GLContext#BOOL_VEC2() */
  @Override
  public int BOOL_VEC2() {
    return GL_BOOL_VEC2;
  }

  /* @see GLContext#BOOL_VEC3() */
  @Override
  public int BOOL_VEC3() {
    return GL_BOOL_VEC3;
  }

  /* @see GLContext#BOOL_VEC4() */
  @Override
  public int BOOL_VEC4() {
    return GL_BOOL_VEC4;
  }

  /* @see GLContext#BUFFER_SIZE() */
  @Override
  public int BUFFER_SIZE() {
    return GL_BUFFER_SIZE;
  }

  /* @see GLContext#BUFFER_USAGE() */
  @Override
  public int BUFFER_USAGE() {
    return GL_BUFFER_USAGE;
  }

  /* @see GLContext#BYTE() */
  @Override
  public int BYTE() {
    return GL_BYTE;
  }

  /* @see GLContext#CCW() */
  @Override
  public int CCW() {
    return GL_CCW;
  }

  /* @see GLContext#CLAMP_TO_EDGE() */
  @Override
  public int CLAMP_TO_EDGE() {
    return GL_CLAMP_TO_EDGE;
  }

  /* @see GLContext#COLOR_ATTACHMENT0() */
  @Override
  public int COLOR_ATTACHMENT0() {
    return GL_COLOR_ATTACHMENT0;
  }

  /* @see GLContext#COLOR_BUFFER_BIT() */
  @Override
  public int COLOR_BUFFER_BIT() {
    return GL_COLOR_BUFFER_BIT;
  }

  /* @see GLContext#COLOR_CLEAR_VALUE() */
  @Override
  public int COLOR_CLEAR_VALUE() {
    return GL_COLOR_CLEAR_VALUE;
  }

  /* @see GLContext#COLOR_WRITEMASK() */
  @Override
  public int COLOR_WRITEMASK() {
    return GL_COLOR_WRITEMASK;
  }

  /* @see GLContext#COMPILE_STATUS() */
  @Override
  public int COMPILE_STATUS() {
    return GL_COMPILE_STATUS;
  }

  /* @see GLContext#COMPRESSED_TEXTURE_FORMATS() */
  @Override
  public int COMPRESSED_TEXTURE_FORMATS() {
    return GL_COMPRESSED_TEXTURE_FORMATS;
  }

  /* @see GLContext#CONSTANT_ALPHA() */
  @Override
  public int CONSTANT_ALPHA() {
    return GL_CONSTANT_ALPHA;
  }

  /* @see GLContext#CONSTANT_COLOR() */
  @Override
  public int CONSTANT_COLOR() {
    return GL_CONSTANT_COLOR;
  }

  /* @see GLContext#CULL_FACE() */
  @Override
  public int CULL_FACE() {
    return GL_CULL_FACE;
  }

  /* @see GLContext#CULL_FACE_MODE() */
  @Override
  public int CULL_FACE_MODE() {
    return GL_CULL_FACE_MODE;
  }

  /* @see GLContext#CURRENT_PROGRAM() */
  @Override
  public int CURRENT_PROGRAM() {
    return GL_CURRENT_PROGRAM;
  }

  /* @see GLContext#CURRENT_VERTEX_ATTRIB() */
  @Override
  public int CURRENT_VERTEX_ATTRIB() {
    return GL_CURRENT_VERTEX_ATTRIB;
  }

  /* @see GLContext#CW() */
  @Override
  public int CW() {
    return GL_CW;
  }

  /* @see GLContext#DECR() */
  @Override
  public int DECR() {
    return GL_DECR;
  }

  /* @see GLContext#DECR_WRAP() */
  @Override
  public int DECR_WRAP() {
    return GL_DECR_WRAP;
  }

  /* @see GLContext#DELETE_STATUS() */
  @Override
  public int DELETE_STATUS() {
    return GL_DELETE_STATUS;
  }

  /* @see GLContext#DEPTH_ATTACHMENT() */
  @Override
  public int DEPTH_ATTACHMENT() {
    return GL_DEPTH_ATTACHMENT;
  }

  /* @see GLContext#DEPTH_BITS() */
  @Override
  public int DEPTH_BITS() {
    return GL_DEPTH_BITS;
  }

  /* @see GLContext#DEPTH_BUFFER_BIT() */
  @Override
  public int DEPTH_BUFFER_BIT() {
    return GL_DEPTH_BUFFER_BIT;
  }

  /* @see GLContext#DEPTH_CLEAR_VALUE() */
  @Override
  public int DEPTH_CLEAR_VALUE() {
    return GL_DEPTH_CLEAR_VALUE;
  }

  /* @see GLContext#DEPTH_COMPONENT() */
  @Override
  public int DEPTH_COMPONENT() {
    return GL_DEPTH_COMPONENT;
  }

  /* @see GLContext#DEPTH_COMPONENT16() */
  @Override
  public int DEPTH_COMPONENT16() {
    return GL_DEPTH_COMPONENT16;
  }

  /* @see GLContext#DEPTH_FUNC() */
  @Override
  public int DEPTH_FUNC() {
    return GL_DEPTH_FUNC;
  }

  /* @see GLContext#DEPTH_RANGE() */
  @Override
  public int DEPTH_RANGE() {
    return GL_DEPTH_RANGE;
  }

  /* @see GLContext#DEPTH_TEST() */
  @Override
  public int DEPTH_TEST() {
    return GL_DEPTH_TEST;
  }

  /* @see GLContext#DEPTH_WRITEMASK() */
  @Override
  public int DEPTH_WRITEMASK() {
    return GL_DEPTH_WRITEMASK;
  }

  /* @see GLContext#DITHER() */
  @Override
  public int DITHER() {
    return GL_DITHER;
  }

  /* @see GLContext#DONT_CARE() */
  @Override
  public int DONT_CARE() {
    return GL_DONT_CARE;
  }

  /* @see GLContext#DST_ALPHA() */
  @Override
  public int DST_ALPHA() {
    return GL_DST_ALPHA;
  }

  /* @see GLContext#DST_COLOR() */
  @Override
  public int DST_COLOR() {
    return GL_DST_COLOR;
  }

  /* @see GLContext#DYNAMIC_DRAW() */
  @Override
  public int DYNAMIC_DRAW() {
    return GL_DYNAMIC_DRAW;
  }

  /* @see GLContext#ELEMENT_ARRAY_BUFFER() */
  @Override
  public int ELEMENT_ARRAY_BUFFER() {
    return GL_ELEMENT_ARRAY_BUFFER;
  }

  /* @see GLContext#ELEMENT_ARRAY_BUFFER_BINDING() */
  @Override
  public int ELEMENT_ARRAY_BUFFER_BINDING() {
    return GL_ELEMENT_ARRAY_BUFFER_BINDING;
  }

  /* @see GLContext#EQUAL() */
  @Override
  public int EQUAL() {
    return GL_EQUAL;
  }

  /* @see GLContext#EXTENSIONS() */
  @Override
  public int EXTENSIONS() {
    return GL_EXTENSIONS;
  }

  /* @see GLContext#FALSE() */
  @Override
  public int FALSE() {
    return GL_FALSE;
  }

  /* @see GLContext#FASTEST() */
  @Override
  public int FASTEST() {
    return GL_FASTEST;
  }

  /* @see GLContext#FIXED() */
  @Override
  public int FIXED() {
    return GL_FIXED;
  }

  /* @see GLContext#FLOAT() */
  @Override
  public int FLOAT() {
    return GL_FLOAT;
  }

  /* @see GLContext#FLOAT_MAT2() */
  @Override
  public int FLOAT_MAT2() {
    return GL_FLOAT_MAT2;
  }

  /* @see GLContext#FLOAT_MAT3() */
  @Override
  public int FLOAT_MAT3() {
    return GL_FLOAT_MAT3;
  }

  /* @see GLContext#FLOAT_MAT4() */
  @Override
  public int FLOAT_MAT4() {
    return GL_FLOAT_MAT4;
  }

  /* @see GLContext#FLOAT_VEC2() */
  @Override
  public int FLOAT_VEC2() {
    return GL_FLOAT_VEC2;
  }

  /* @see GLContext#FLOAT_VEC3() */
  @Override
  public int FLOAT_VEC3() {
    return GL_FLOAT_VEC3;
  }

  /* @see GLContext#FLOAT_VEC4() */
  @Override
  public int FLOAT_VEC4() {
    return GL_FLOAT_VEC4;
  }

  /* @see GLContext#FRAGMENT_SHADER() */
  @Override
  public int FRAGMENT_SHADER() {
    return GL_FRAGMENT_SHADER;
  }

  /* @see GLContext#FRAMEBUFFER() */
  @Override
  public int FRAMEBUFFER() {
    return GL_FRAMEBUFFER;
  }

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_OBJECT_NAME() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_OBJECT_NAME() {
    return GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME;
  }

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE() {
    return GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE;
  }

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE() {
    return GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE;
  }

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL() {
    return GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL;
  }

  /* @see GLContext#FRAMEBUFFER_BINDING() */
  @Override
  public int FRAMEBUFFER_BINDING() {
    return GL_FRAMEBUFFER_BINDING;
  }

  /* @see GLContext#FRAMEBUFFER_COMPLETE() */
  @Override
  public int FRAMEBUFFER_COMPLETE() {
    return GL_FRAMEBUFFER_COMPLETE;
  }

  /* @see GLContext#FRAMEBUFFER_INCOMPLETE_ATTACHMENT() */
  @Override
  public int FRAMEBUFFER_INCOMPLETE_ATTACHMENT() {
    return GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT;
  }

  /* @see GLContext#FRAMEBUFFER_INCOMPLETE_DIMENSIONS() */
  @Override
  public int FRAMEBUFFER_INCOMPLETE_DIMENSIONS() {
    return GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS;
  }

  /* @see GLContext#FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT() */
  @Override
  public int FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT() {
    return GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT;
  }

  /* @see GLContext#FRAMEBUFFER_UNSUPPORTED() */
  @Override
  public int FRAMEBUFFER_UNSUPPORTED() {
    return GL_FRAMEBUFFER_UNSUPPORTED;
  }

  /* @see GLContext#FRONT() */
  @Override
  public int FRONT() {
    return GL_FRONT;
  }

  /* @see GLContext#FRONT_AND_BACK() */
  @Override
  public int FRONT_AND_BACK() {
    return GL_FRONT_AND_BACK;
  }

  /* @see GLContext#FRONT_FACE() */
  @Override
  public int FRONT_FACE() {
    return GL_FRONT_FACE;
  }

  /* @see GLContext#FUNC_ADD() */
  @Override
  public int FUNC_ADD() {
    return GL_FUNC_ADD;
  }

  /* @see GLContext#FUNC_REVERSE_SUBTRACT() */
  @Override
  public int FUNC_REVERSE_SUBTRACT() {
    return GL_FUNC_REVERSE_SUBTRACT;
  }

  /* @see GLContext#FUNC_SUBTRACT() */
  @Override
  public int FUNC_SUBTRACT() {
    return GL_FUNC_SUBTRACT;
  }

  /* @see GLContext#GENERATE_MIPMAP_HINT() */
  @Override
  public int GENERATE_MIPMAP_HINT() {
    return GL_GENERATE_MIPMAP_HINT;
  }

  /* @see GLContext#GEQUAL() */
  @Override
  public int GEQUAL() {
    return GL_GEQUAL;
  }

  /* @see GLContext#GREATER() */
  @Override
  public int GREATER() {
    return GL_GREATER;
  }

  /* @see GLContext#GREEN_BITS() */
  @Override
  public int GREEN_BITS() {
    return GL_GREEN_BITS;
  }

  /* @see GLContext#HIGH_FLOAT() */
  @Override
  public int HIGH_FLOAT() {
    return GL_HIGH_FLOAT;
  }

  /* @see GLContext#HIGH_INT() */
  @Override
  public int HIGH_INT() {
    return GL_HIGH_INT;
  }

  /* @see GLContext#IMPLEMENTATION_COLOR_READ_FORMAT() */
  @Override
  public int IMPLEMENTATION_COLOR_READ_FORMAT() {
    return GL_IMPLEMENTATION_COLOR_READ_FORMAT;
  }

  /* @see GLContext#IMPLEMENTATION_COLOR_READ_TYPE() */
  @Override
  public int IMPLEMENTATION_COLOR_READ_TYPE() {
    return GL_IMPLEMENTATION_COLOR_READ_TYPE;
  }

  /* @see GLContext#INCR() */
  @Override
  public int INCR() {
    return GL_INCR;
  }

  /* @see GLContext#INCR_WRAP() */
  @Override
  public int INCR_WRAP() {
    return GL_INCR_WRAP;
  }

  /* @see GLContext#INFO_LOG_LENGTH() */
  @Override
  public int INFO_LOG_LENGTH() {
    return GL_INFO_LOG_LENGTH;
  }

  /* @see GLContext#INT() */
  @Override
  public int INT() {
    return GL_INT;
  }

  /* @see GLContext#INT_VEC2() */
  @Override
  public int INT_VEC2() {
    return GL_INT_VEC2;
  }

  /* @see GLContext#INT_VEC3() */
  @Override
  public int INT_VEC3() {
    return GL_INT_VEC3;
  }

  /* @see GLContext#INT_VEC4() */
  @Override
  public int INT_VEC4() {
    return GL_INT_VEC4;
  }

  /* @see GLContext#INVALID_ENUM() */
  @Override
  public int INVALID_ENUM() {
    return GL_INVALID_ENUM;
  }

  /* @see GLContext#INVALID_FRAMEBUFFER_OPERATION() */
  @Override
  public int INVALID_FRAMEBUFFER_OPERATION() {
    return GL_INVALID_FRAMEBUFFER_OPERATION;
  }

  /* @see GLContext#INVALID_OPERATION() */
  @Override
  public int INVALID_OPERATION() {
    return GL_INVALID_OPERATION;
  }

  /* @see GLContext#INVALID_VALUE() */
  @Override
  public int INVALID_VALUE() {
    return GL_INVALID_VALUE;
  }

  /* @see GLContext#INVERT() */
  @Override
  public int INVERT() {
    return GL_INVERT;
  }

  /* @see GLContext#KEEP() */
  @Override
  public int KEEP() {
    return GL_KEEP;
  }

  /* @see GLContext#LEQUAL() */
  @Override
  public int LEQUAL() {
    return GL_LEQUAL;
  }

  /* @see GLContext#LESS() */
  @Override
  public int LESS() {
    return GL_LESS;
  }

  /* @see GLContext#LINE_LOOP() */
  @Override
  public int LINE_LOOP() {
    return GL_LINE_LOOP;
  }

  /* @see GLContext#LINE_STRIP() */
  @Override
  public int LINE_STRIP() {
    return GL_LINE_STRIP;
  }

  /* @see GLContext#LINE_WIDTH() */
  @Override
  public int LINE_WIDTH() {
    return GL_LINE_WIDTH;
  }

  /* @see GLContext#LINEAR() */
  @Override
  public int LINEAR() {
    return GL_LINEAR;
  }

  /* @see GLContext#LINEAR_MIPMAP_LINEAR() */
  @Override
  public int LINEAR_MIPMAP_LINEAR() {
    return GL_LINEAR_MIPMAP_LINEAR;
  }

  /* @see GLContext#LINEAR_MIPMAP_NEAREST() */
  @Override
  public int LINEAR_MIPMAP_NEAREST() {
    return GL_LINEAR_MIPMAP_NEAREST;
  }

  /* @see GLContext#LINES() */
  @Override
  public int LINES() {
    return GL_LINES;
  }

  /* @see GLContext#LINK_STATUS() */
  @Override
  public int LINK_STATUS() {
    return GL_LINK_STATUS;
  }

  /* @see GLContext#LOW_FLOAT() */
  @Override
  public int LOW_FLOAT() {
    return GL_LOW_FLOAT;
  }

  /* @see GLContext#LOW_INT() */
  @Override
  public int LOW_INT() {
    return GL_LOW_INT;
  }

  /* @see GLContext#LUMINANCE() */
  @Override
  public int LUMINANCE() {
    return GL_LUMINANCE;
  }

  /* @see GLContext#LUMINANCE_ALPHA() */
  @Override
  public int LUMINANCE_ALPHA() {
    return GL_LUMINANCE_ALPHA;
  }

  /* @see GLContext#MAX_COMBINED_TEXTURE_IMAGE_UNITS() */
  @Override
  public int MAX_COMBINED_TEXTURE_IMAGE_UNITS() {
    return GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS;
  }

  /* @see GLContext#MAX_CUBE_MAP_TEXTURE_SIZE() */
  @Override
  public int MAX_CUBE_MAP_TEXTURE_SIZE() {
    return GL_MAX_CUBE_MAP_TEXTURE_SIZE;
  }

  /* @see GLContext#MAX_FRAGMENT_UNIFORM_VECTORS() */
  @Override
  public int MAX_FRAGMENT_UNIFORM_VECTORS() {
    return GL_MAX_FRAGMENT_UNIFORM_VECTORS;
  }

  /* @see GLContext#MAX_RENDERBUFFER_SIZE() */
  @Override
  public int MAX_RENDERBUFFER_SIZE() {
    return GL_MAX_RENDERBUFFER_SIZE;
  }

  /* @see GLContext#MAX_TEXTURE_IMAGE_UNITS() */
  @Override
  public int MAX_TEXTURE_IMAGE_UNITS() {
    return GL_MAX_TEXTURE_IMAGE_UNITS;
  }

  /* @see GLContext#MAX_TEXTURE_SIZE() */
  @Override
  public int MAX_TEXTURE_SIZE() {
    return GL_MAX_TEXTURE_SIZE;
  }

  /* @see GLContext#MAX_VARYING_VECTORS() */
  @Override
  public int MAX_VARYING_VECTORS() {
    return GL_MAX_VARYING_VECTORS;
  }

  /* @see GLContext#MAX_VERTEX_ATTRIBS() */
  @Override
  public int MAX_VERTEX_ATTRIBS() {
    return GL_MAX_VERTEX_ATTRIBS;
  }

  /* @see GLContext#MAX_VERTEX_TEXTURE_IMAGE_UNITS() */
  @Override
  public int MAX_VERTEX_TEXTURE_IMAGE_UNITS() {
    return GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS;
  }

  /* @see GLContext#MAX_VERTEX_UNIFORM_VECTORS() */
  @Override
  public int MAX_VERTEX_UNIFORM_VECTORS() {
    return GL_MAX_VERTEX_UNIFORM_VECTORS;
  }

  /* @see GLContext#MAX_VIEWPORT_DIMS() */
  @Override
  public int MAX_VIEWPORT_DIMS() {
    return GL_MAX_VIEWPORT_DIMS;
  }

  /* @see GLContext#MEDIUM_FLOAT() */
  @Override
  public int MEDIUM_FLOAT() {
    return GL_MEDIUM_FLOAT;
  }

  /* @see GLContext#MEDIUM_INT() */
  @Override
  public int MEDIUM_INT() {
    return GL_MEDIUM_INT;
  }

  /* @see GLContext#MIRRORED_REPEAT() */
  @Override
  public int MIRRORED_REPEAT() {
    return GL_MIRRORED_REPEAT;
  }

  /* @see GLContext#NEAREST() */
  @Override
  public int NEAREST() {
    return GL_NEAREST;
  }

  /* @see GLContext#NEAREST_MIPMAP_LINEAR() */
  @Override
  public int NEAREST_MIPMAP_LINEAR() {
    return GL_NEAREST_MIPMAP_LINEAR;
  }

  /* @see GLContext#NEAREST_MIPMAP_NEAREST() */
  @Override
  public int NEAREST_MIPMAP_NEAREST() {
    return GL_NEAREST_MIPMAP_NEAREST;
  }

  /* @see GLContext#NEVER() */
  @Override
  public int NEVER() {
    return GL_NEVER;
  }

  /* @see GLContext#NICEST() */
  @Override
  public int NICEST() {
    return GL_NICEST;
  }

  /* @see GLContext#NO_ERROR() */
  @Override
  public int NO_ERROR() {
    return GL_NO_ERROR;
  }

  /* @see GLContext#NONE() */
  @Override
  public int NONE() {
    return GL_NONE;
  }

  /* @see GLContext#NOTEQUAL() */
  @Override
  public int NOTEQUAL() {
    return GL_NOTEQUAL;
  }

  /* @see GLContext#NUM_COMPRESSED_TEXTURE_FORMATS() */
  @Override
  public int NUM_COMPRESSED_TEXTURE_FORMATS() {
    return GL_NUM_COMPRESSED_TEXTURE_FORMATS;
  }

  /* @see GLContext#NUM_SHADER_BINARY_FORMATS() */
  @Override
  public int NUM_SHADER_BINARY_FORMATS() {
    return GL_NUM_SHADER_BINARY_FORMATS;
  }

  /* @see GLContext#ONE() */
  @Override
  public int ONE() {
    return GL_ONE;
  }

  /* @see GLContext#ONE_MINUS_CONSTANT_ALPHA() */
  @Override
  public int ONE_MINUS_CONSTANT_ALPHA() {
    return GL_ONE_MINUS_CONSTANT_ALPHA;
  }

  /* @see GLContext#ONE_MINUS_CONSTANT_COLOR() */
  @Override
  public int ONE_MINUS_CONSTANT_COLOR() {
    return GL_ONE_MINUS_CONSTANT_COLOR;
  }

  /* @see GLContext#ONE_MINUS_DST_ALPHA() */
  @Override
  public int ONE_MINUS_DST_ALPHA() {
    return GL_ONE_MINUS_DST_ALPHA;
  }

  /* @see GLContext#ONE_MINUS_DST_COLOR() */
  @Override
  public int ONE_MINUS_DST_COLOR() {
    return GL_ONE_MINUS_DST_COLOR;
  }

  /* @see GLContext#ONE_MINUS_SRC_ALPHA() */
  @Override
  public int ONE_MINUS_SRC_ALPHA() {
    return GL_ONE_MINUS_SRC_ALPHA;
  }

  /* @see GLContext#ONE_MINUS_SRC_COLOR() */
  @Override
  public int ONE_MINUS_SRC_COLOR() {
    return GL_ONE_MINUS_SRC_COLOR;
  }

  /* @see GLContext#OUT_OF_MEMORY() */
  @Override
  public int OUT_OF_MEMORY() {
    return GL_OUT_OF_MEMORY;
  }

  /* @see GLContext#PACK_ALIGNMENT() */
  @Override
  public int PACK_ALIGNMENT() {
    return GL_PACK_ALIGNMENT;
  }

  /* @see GLContext#POINTS() */
  @Override
  public int POINTS() {
    return GL_POINTS;
  }

  /* @see GLContext#POLYGON_OFFSET_FACTOR() */
  @Override
  public int POLYGON_OFFSET_FACTOR() {
    return GL_POLYGON_OFFSET_FACTOR;
  }

  /* @see GLContext#POLYGON_OFFSET_FILL() */
  @Override
  public int POLYGON_OFFSET_FILL() {
    return GL_POLYGON_OFFSET_FILL;
  }

  /* @see GLContext#POLYGON_OFFSET_UNITS() */
  @Override
  public int POLYGON_OFFSET_UNITS() {
    return GL_POLYGON_OFFSET_UNITS;
  }

  /* @see GLContext#RED_BITS() */
  @Override
  public int RED_BITS() {
    return GL_RED_BITS;
  }

  /* @see GLContext#RENDERBUFFER() */
  @Override
  public int RENDERBUFFER() {
    return GL_RENDERBUFFER;
  }

  /* @see GLContext#RENDERBUFFER_ALPHA_SIZE() */
  @Override
  public int RENDERBUFFER_ALPHA_SIZE() {
    return GL_RENDERBUFFER_ALPHA_SIZE;
  }

  /* @see GLContext#RENDERBUFFER_BINDING() */
  @Override
  public int RENDERBUFFER_BINDING() {
    return GL_RENDERBUFFER_BINDING;
  }

  /* @see GLContext#RENDERBUFFER_BLUE_SIZE() */
  @Override
  public int RENDERBUFFER_BLUE_SIZE() {
    return GL_RENDERBUFFER_BLUE_SIZE;
  }

  /* @see GLContext#RENDERBUFFER_DEPTH_SIZE() */
  @Override
  public int RENDERBUFFER_DEPTH_SIZE() {
    return GL_RENDERBUFFER_DEPTH_SIZE;
  }

  /* @see GLContext#RENDERBUFFER_GREEN_SIZE() */
  @Override
  public int RENDERBUFFER_GREEN_SIZE() {
    return GL_RENDERBUFFER_GREEN_SIZE;
  }

  /* @see GLContext#RENDERBUFFER_HEIGHT() */
  @Override
  public int RENDERBUFFER_HEIGHT() {
    return GL_RENDERBUFFER_HEIGHT;
  }

  /* @see GLContext#RENDERBUFFER_INTERNAL_FORMAT() */
  @Override
  public int RENDERBUFFER_INTERNAL_FORMAT() {
    return GL_RENDERBUFFER_INTERNAL_FORMAT;
  }

  /* @see GLContext#RENDERBUFFER_RED_SIZE() */
  @Override
  public int RENDERBUFFER_RED_SIZE() {
    return GL_RENDERBUFFER_RED_SIZE;
  }

  /* @see GLContext#RENDERBUFFER_STENCIL_SIZE() */
  @Override
  public int RENDERBUFFER_STENCIL_SIZE() {
    return GL_RENDERBUFFER_STENCIL_SIZE;
  }

  /* @see GLContext#RENDERBUFFER_WIDTH() */
  @Override
  public int RENDERBUFFER_WIDTH() {
    return GL_RENDERBUFFER_WIDTH;
  }

  /* @see GLContext#RENDERER() */
  @Override
  public int RENDERER() {
    return GL_RENDERER;
  }

  /* @see GLContext#REPEAT() */
  @Override
  public int REPEAT() {
    return GL_REPEAT;
  }

  /* @see GLContext#REPLACE() */
  @Override
  public int REPLACE() {
    return GL_REPLACE;
  }

  /* @see GLContext#RGB() */
  @Override
  public int RGB() {
    return GL_RGB;
  }

  /* @see GLContext#RGB5_A1() */
  @Override
  public int RGB5_A1() {
    return GL_RGB5_A1;
  }

  /* @see GLContext#RGB565() */
  @Override
  public int RGB565() {
    return GL_RGB565;
  }

  /* @see GLContext#RGBA() */
  @Override
  public int RGBA() {
    return GL_RGBA;
  }

  /* @see GLContext#RGBA4() */
  @Override
  public int RGBA4() {
    return GL_RGBA4;
  }

  /* @see GLContext#SAMPLE_ALPHA_TO_COVERAGE() */
  @Override
  public int SAMPLE_ALPHA_TO_COVERAGE() {
    return GL_SAMPLE_ALPHA_TO_COVERAGE;
  }

  /* @see GLContext#SAMPLE_BUFFERS() */
  @Override
  public int SAMPLE_BUFFERS() {
    return GL_SAMPLE_BUFFERS;
  }

  /* @see GLContext#SAMPLE_COVERAGE() */
  @Override
  public int SAMPLE_COVERAGE() {
    return GL_SAMPLE_COVERAGE;
  }

  /* @see GLContext#SAMPLE_COVERAGE_INVERT() */
  @Override
  public int SAMPLE_COVERAGE_INVERT() {
    return GL_SAMPLE_COVERAGE_INVERT;
  }

  /* @see GLContext#SAMPLE_COVERAGE_VALUE() */
  @Override
  public int SAMPLE_COVERAGE_VALUE() {
    return GL_SAMPLE_COVERAGE_VALUE;
  }

  /* @see GLContext#SAMPLER_2D() */
  @Override
  public int SAMPLER_2D() {
    return GL_SAMPLER_2D;
  }

  /* @see GLContext#SAMPLER_CUBE() */
  @Override
  public int SAMPLER_CUBE() {
    return GL_SAMPLER_CUBE;
  }

  /* @see GLContext#SAMPLES() */
  @Override
  public int SAMPLES() {
    return GL_SAMPLES;
  }

  /* @see GLContext#SCISSOR_BOX() */
  @Override
  public int SCISSOR_BOX() {
    return GL_SCISSOR_BOX;
  }

  /* @see GLContext#SCISSOR_TEST() */
  @Override
  public int SCISSOR_TEST() {
    return GL_SCISSOR_TEST;
  }

  /* @see GLContext#SHADER_BINARY_FORMATS() */
  @Override
  public int SHADER_BINARY_FORMATS() {
    return GL_SHADER_BINARY_FORMATS;
  }

  /* @see GLContext#SHADER_COMPILER() */
  @Override
  public int SHADER_COMPILER() {
    return GL_SHADER_COMPILER;
  }

  /* @see GLContext#SHADER_SOURCE_LENGTH() */
  @Override
  public int SHADER_SOURCE_LENGTH() {
    return GL_SHADER_SOURCE_LENGTH;
  }

  /* @see GLContext#SHADER_TYPE() */
  @Override
  public int SHADER_TYPE() {
    return GL_SHADER_TYPE;
  }

  /* @see GLContext#SHADING_LANGUAGE_VERSION() */
  @Override
  public int SHADING_LANGUAGE_VERSION() {
    return GL_SHADING_LANGUAGE_VERSION;
  }

  /* @see GLContext#SHORT() */
  @Override
  public int SHORT() {
    return GL_SHORT;
  }

  /* @see GLContext#SRC_ALPHA() */
  @Override
  public int SRC_ALPHA() {
    return GL_SRC_ALPHA;
  }

  /* @see GLContext#SRC_ALPHA_SATURATE() */
  @Override
  public int SRC_ALPHA_SATURATE() {
    return GL_SRC_ALPHA_SATURATE;
  }

  /* @see GLContext#SRC_COLOR() */
  @Override
  public int SRC_COLOR() {
    return GL_SRC_COLOR;
  }

  /* @see GLContext#STATIC_DRAW() */
  @Override
  public int STATIC_DRAW() {
    return GL_STATIC_DRAW;
  }

  /* @see GLContext#STENCIL_ATTACHMENT() */
  @Override
  public int STENCIL_ATTACHMENT() {
    return GL_STENCIL_ATTACHMENT;
  }

  /* @see GLContext#STENCIL_BACK_FAIL() */
  @Override
  public int STENCIL_BACK_FAIL() {
    return GL_STENCIL_BACK_FAIL;
  }

  /* @see GLContext#STENCIL_BACK_FUNC() */
  @Override
  public int STENCIL_BACK_FUNC() {
    return GL_STENCIL_BACK_FUNC;
  }

  /* @see GLContext#STENCIL_BACK_PASS_DEPTH_FAIL() */
  @Override
  public int STENCIL_BACK_PASS_DEPTH_FAIL() {
    return GL_STENCIL_BACK_PASS_DEPTH_FAIL;
  }

  /* @see GLContext#STENCIL_BACK_PASS_DEPTH_PASS() */
  @Override
  public int STENCIL_BACK_PASS_DEPTH_PASS() {
    return GL_STENCIL_BACK_PASS_DEPTH_PASS;
  }

  /* @see GLContext#STENCIL_BACK_REF() */
  @Override
  public int STENCIL_BACK_REF() {
    return GL_STENCIL_BACK_REF;
  }

  /* @see GLContext#STENCIL_BACK_VALUE_MASK() */
  @Override
  public int STENCIL_BACK_VALUE_MASK() {
    return GL_STENCIL_BACK_VALUE_MASK;
  }

  /* @see GLContext#STENCIL_BACK_WRITEMASK() */
  @Override
  public int STENCIL_BACK_WRITEMASK() {
    return GL_STENCIL_BACK_WRITEMASK;
  }

  /* @see GLContext#STENCIL_BITS() */
  @Override
  public int STENCIL_BITS() {
    return GL_STENCIL_BITS;
  }

  /* @see GLContext#STENCIL_BUFFER_BIT() */
  @Override
  public int STENCIL_BUFFER_BIT() {
    return GL_STENCIL_BUFFER_BIT;
  }

  /* @see GLContext#STENCIL_CLEAR_VALUE() */
  @Override
  public int STENCIL_CLEAR_VALUE() {
    return GL_STENCIL_CLEAR_VALUE;
  }

  /* @see GLContext#STENCIL_FAIL() */
  @Override
  public int STENCIL_FAIL() {
    return GL_STENCIL_FAIL;
  }

  /* @see GLContext#STENCIL_FUNC() */
  @Override
  public int STENCIL_FUNC() {
    return GL_STENCIL_FUNC;
  }

  /* @see GLContext#STENCIL_INDEX() */
  @Override
  public int STENCIL_INDEX() {
    return GL_STENCIL_INDEX;
  }

  /* @see GLContext#STENCIL_INDEX8() */
  @Override
  public int STENCIL_INDEX8() {
    return GL_STENCIL_INDEX8;
  }

  /* @see GLContext#STENCIL_PASS_DEPTH_FAIL() */
  @Override
  public int STENCIL_PASS_DEPTH_FAIL() {
    return GL_STENCIL_PASS_DEPTH_FAIL;
  }

  /* @see GLContext#STENCIL_PASS_DEPTH_PASS() */
  @Override
  public int STENCIL_PASS_DEPTH_PASS() {
    return GL_STENCIL_PASS_DEPTH_PASS;
  }

  /* @see GLContext#STENCIL_REF() */
  @Override
  public int STENCIL_REF() {
    return GL_STENCIL_REF;
  }

  /* @see GLContext#STENCIL_TEST() */
  @Override
  public int STENCIL_TEST() {
    return GL_STENCIL_TEST;
  }

  /* @see GLContext#STENCIL_VALUE_MASK() */
  @Override
  public int STENCIL_VALUE_MASK() {
    return GL_STENCIL_VALUE_MASK;
  }

  /* @see GLContext#STENCIL_WRITEMASK() */
  @Override
  public int STENCIL_WRITEMASK() {
    return GL_STENCIL_WRITEMASK;
  }

  /* @see GLContext#STREAM_DRAW() */
  @Override
  public int STREAM_DRAW() {
    return GL_STREAM_DRAW;
  }

  /* @see GLContext#SUBPIXEL_BITS() */
  @Override
  public int SUBPIXEL_BITS() {
    return GL_SUBPIXEL_BITS;
  }

  /* @see GLContext#TEXTURE() */
  @Override
  public int TEXTURE() {
    return GL_TEXTURE;
  }

  /* @see GLContext#TEXTURE_2D() */
  @Override
  public int TEXTURE_2D() {
    return GL_TEXTURE_2D;
  }

  /* @see GLContext#TEXTURE_BINDING_2D() */
  @Override
  public int TEXTURE_BINDING_2D() {
    return GL_TEXTURE_BINDING_2D;
  }

  /* @see GLContext#TEXTURE_BINDING_CUBE_MAP() */
  @Override
  public int TEXTURE_BINDING_CUBE_MAP() {
    return GL_TEXTURE_BINDING_CUBE_MAP;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP() */
  @Override
  public int TEXTURE_CUBE_MAP() {
    return GL_TEXTURE_CUBE_MAP;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_NEGATIVE_X() */
  @Override
  public int TEXTURE_CUBE_MAP_NEGATIVE_X() {
    return GL_TEXTURE_CUBE_MAP_NEGATIVE_X;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_NEGATIVE_Y() */
  @Override
  public int TEXTURE_CUBE_MAP_NEGATIVE_Y() {
    return GL_TEXTURE_CUBE_MAP_NEGATIVE_Y;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_NEGATIVE_Z() */
  @Override
  public int TEXTURE_CUBE_MAP_NEGATIVE_Z() {
    return GL_TEXTURE_CUBE_MAP_NEGATIVE_Z;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_POSITIVE_X() */
  @Override
  public int TEXTURE_CUBE_MAP_POSITIVE_X() {
    return GL_TEXTURE_CUBE_MAP_POSITIVE_X;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_POSITIVE_Y() */
  @Override
  public int TEXTURE_CUBE_MAP_POSITIVE_Y() {
    return GL_TEXTURE_CUBE_MAP_POSITIVE_Y;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_POSITIVE_Z() */
  @Override
  public int TEXTURE_CUBE_MAP_POSITIVE_Z() {
    return GL_TEXTURE_CUBE_MAP_POSITIVE_Z;
  }

  /* @see GLContext#TEXTURE_MAG_FILTER() */
  @Override
  public int TEXTURE_MAG_FILTER() {
    return GL_TEXTURE_MAG_FILTER;
  }

  /* @see GLContext#TEXTURE_MIN_FILTER() */
  @Override
  public int TEXTURE_MIN_FILTER() {
    return GL_TEXTURE_MIN_FILTER;
  }

  /* @see GLContext#TEXTURE_WRAP_S() */
  @Override
  public int TEXTURE_WRAP_S() {
    return GL_TEXTURE_WRAP_S;
  }

  /* @see GLContext#TEXTURE_WRAP_T() */
  @Override
  public int TEXTURE_WRAP_T() {
    return GL_TEXTURE_WRAP_T;
  }

  /* @see GLContext#TEXTURE0() */
  @Override
  public int TEXTURE0() {
    return GL_TEXTURE0;
  }

  /* @see GLContext#TEXTURE1() */
  @Override
  public int TEXTURE1() {
    return GL_TEXTURE1;
  }

  /* @see GLContext#TEXTURE2() */
  @Override
  public int TEXTURE2() {
    return GL_TEXTURE2;
  }

  /* @see GLContext#TEXTURE3() */
  @Override
  public int TEXTURE3() {
    return GL_TEXTURE3;
  }

  /* @see GLContext#TEXTURE4() */
  @Override
  public int TEXTURE4() {
    return GL_TEXTURE4;
  }

  /* @see GLContext#TEXTURE5() */
  @Override
  public int TEXTURE5() {
    return GL_TEXTURE5;
  }

  /* @see GLContext#TEXTURE6() */
  @Override
  public int TEXTURE6() {
    return GL_TEXTURE6;
  }

  /* @see GLContext#TEXTURE7() */
  @Override
  public int TEXTURE7() {
    return GL_TEXTURE7;
  }

  /* @see GLContext#TEXTURE8() */
  @Override
  public int TEXTURE8() {
    return GL_TEXTURE8;
  }

  /* @see GLContext#TEXTURE9() */
  @Override
  public int TEXTURE9() {
    return GL_TEXTURE9;
  }

  /* @see GLContext#TEXTURE10() */
  @Override
  public int TEXTURE10() {
    return GL_TEXTURE10;
  }

  /* @see GLContext#TEXTURE11() */
  @Override
  public int TEXTURE11() {
    return GL_TEXTURE11;
  }

  /* @see GLContext#TEXTURE12() */
  @Override
  public int TEXTURE12() {
    return GL_TEXTURE12;
  }

  /* @see GLContext#TEXTURE13() */
  @Override
  public int TEXTURE13() {
    return GL_TEXTURE13;
  }

  /* @see GLContext#TEXTURE14() */
  @Override
  public int TEXTURE14() {
    return GL_TEXTURE14;
  }

  /* @see GLContext#TEXTURE15() */
  @Override
  public int TEXTURE15() {
    return GL_TEXTURE15;
  }

  /* @see GLContext#TEXTURE16() */
  @Override
  public int TEXTURE16() {
    return GL_TEXTURE16;
  }

  /* @see GLContext#TEXTURE17() */
  @Override
  public int TEXTURE17() {
    return GL_TEXTURE17;
  }

  /* @see GLContext#TEXTURE18() */
  @Override
  public int TEXTURE18() {
    return GL_TEXTURE18;
  }

  /* @see GLContext#TEXTURE19() */
  @Override
  public int TEXTURE19() {
    return GL_TEXTURE19;
  }

  /* @see GLContext#TEXTURE20() */
  @Override
  public int TEXTURE20() {
    return GL_TEXTURE20;
  }

  /* @see GLContext#TEXTURE21() */
  @Override
  public int TEXTURE21() {
    return GL_TEXTURE21;
  }

  /* @see GLContext#TEXTURE22() */
  @Override
  public int TEXTURE22() {
    return GL_TEXTURE22;
  }

  /* @see GLContext#TEXTURE23() */
  @Override
  public int TEXTURE23() {
    return GL_TEXTURE23;
  }

  /* @see GLContext#TEXTURE24() */
  @Override
  public int TEXTURE24() {
    return GL_TEXTURE24;
  }

  /* @see GLContext#TEXTURE25() */
  @Override
  public int TEXTURE25() {
    return GL_TEXTURE25;
  }

  /* @see GLContext#TEXTURE26() */
  @Override
  public int TEXTURE26() {
    return GL_TEXTURE26;
  }

  /* @see GLContext#TEXTURE27() */
  @Override
  public int TEXTURE27() {
    return GL_TEXTURE27;
  }

  /* @see GLContext#TEXTURE28() */
  @Override
  public int TEXTURE28() {
    return GL_TEXTURE28;
  }

  /* @see GLContext#TEXTURE29() */
  @Override
  public int TEXTURE29() {
    return GL_TEXTURE29;
  }

  /* @see GLContext#TEXTURE30() */
  @Override
  public int TEXTURE30() {
    return GL_TEXTURE30;
  }

  /* @see GLContext#TEXTURE31() */
  @Override
  public int TEXTURE31() {
    return GL_TEXTURE31;
  }

  /* @see GLContext#TRIANGLE_FAN() */
  @Override
  public int TRIANGLE_FAN() {
    return GL_TRIANGLE_FAN;
  }

  /* @see GLContext#TRIANGLE_STRIP() */
  @Override
  public int TRIANGLE_STRIP() {
    return GL_TRIANGLE_STRIP;
  }

  /* @see GLContext#TRIANGLES() */
  @Override
  public int TRIANGLES() {
    return GL_TRIANGLES;
  }

  /* @see GLContext#TRUE() */
  @Override
  public int TRUE() {
    return GL_TRUE;
  }

  /* @see GLContext#UNPACK_ALIGNMENT() */
  @Override
  public int UNPACK_ALIGNMENT() {
    return GL_UNPACK_ALIGNMENT;
  }

  /* @see GLContext#UNSIGNED_BYTE() */
  @Override
  public int UNSIGNED_BYTE() {
    return GL_UNSIGNED_BYTE;
  }

  /* @see GLContext#UNSIGNED_INT() */
  @Override
  public int UNSIGNED_INT() {
    return GL_UNSIGNED_INT;
  }

  /* @see GLContext#UNSIGNED_SHORT() */
  @Override
  public int UNSIGNED_SHORT() {
    return GL_UNSIGNED_SHORT;
  }

  /* @see GLContext#UNSIGNED_SHORT_4_4_4_4() */
  @Override
  public int UNSIGNED_SHORT_4_4_4_4() {
    return GL_UNSIGNED_SHORT_4_4_4_4;
  }

  /* @see GLContext#UNSIGNED_SHORT_5_5_5_1() */
  @Override
  public int UNSIGNED_SHORT_5_5_5_1() {
    return GL_UNSIGNED_SHORT_5_5_5_1;
  }

  /* @see GLContext#UNSIGNED_SHORT_5_6_5() */
  @Override
  public int UNSIGNED_SHORT_5_6_5() {
    return GL_UNSIGNED_SHORT_5_6_5;
  }

  /* @see GLContext#VALIDATE_STATUS() */
  @Override
  public int VALIDATE_STATUS() {
    return GL_VALIDATE_STATUS;
  }

  /* @see GLContext#VENDOR() */
  @Override
  public int VENDOR() {
    return GL_VENDOR;
  }

  /* @see GLContext#VERSION() */
  @Override
  public int VERSION() {
    return GL_VERSION;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_BUFFER_BINDING() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_BUFFER_BINDING() {
    return GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_ENABLED() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_ENABLED() {
    return GL_VERTEX_ATTRIB_ARRAY_ENABLED;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_NORMALIZED() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_NORMALIZED() {
    return GL_VERTEX_ATTRIB_ARRAY_NORMALIZED;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_POINTER() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_POINTER() {
    return GL_VERTEX_ATTRIB_ARRAY_POINTER;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_SIZE() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_SIZE() {
    return GL_VERTEX_ATTRIB_ARRAY_SIZE;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_STRIDE() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_STRIDE() {
    return GL_VERTEX_ATTRIB_ARRAY_STRIDE;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_TYPE() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_TYPE() {
    return GL_VERTEX_ATTRIB_ARRAY_TYPE;
  }

  /* @see GLContext#VERTEX_SHADER() */
  @Override
  public int VERTEX_SHADER() {
    return GL_VERTEX_SHADER;
  }

  /* @see GLContext#VIEWPORT() */
  @Override
  public int VIEWPORT() {
    return GL_VIEWPORT;
  }

  /* @see GLContext#ZERO() */
  @Override
  public int ZERO() {
    return GL_ZERO;
  }

  //
  // Implementations of the OpenGL functions.
  //

  /* @see GLContext#activeTexture(int) */
  @Override
  public void activeTexture(int texture) {
    glActiveTexture(texture);
  }

  /* @see GLContext#attachShader(int, int) */
  @Override
  public void attachShader(int program, int shader) {
    glAttachShader(program, shader);
  }

  /* @see GLContext#bindAttribLocation(int, int, String) */
  @Override
  public void bindAttribLocation(int program, int index, String name) {
    glBindAttribLocation(program, index, name);
  }

  /* @see GLContext#bindBuffer(int, int) */
  @Override
  public void bindBuffer(int target, int buffer) {
    glBindBuffer(target, buffer);
  }

  /* @see GLContext#bindFramebuffer(int, int) */
  @Override
  public void bindFramebuffer(int target, int framebuffer) {
    glBindFramebuffer(target, framebuffer);
  }

  /* @see GLContext#bindRenderbuffer(int, int) */
  @Override
  public void bindRenderbuffer(int target, int renderbuffer) {
    glBindRenderbuffer(target, renderbuffer);
  }

  /* @see GLContext#bindTexture(int, int) */
  @Override
  public void bindTexture(int target, int texture) {
    glBindTexture(target, texture);
  }

  /* @see GLContext#blendColor(float, float, float, float) */
  @Override
  public void blendColor(float red, float green, float blue, float alpha) {
    glBlendColor(red, green, blue, alpha);
  }

  /* @see GLContext#blendEquation(int) */
  @Override
  public void blendEquation(int mode) {
    glBlendEquation(mode);
  }

  /* @see GLContext#blendEquationSeparate(int, int) */
  @Override
  public void blendEquationSeparate(int modeRGB, int modeAlpha) {
    glBlendEquationSeparate(modeRGB, modeAlpha);
  }

  /* @see GLContext#blendFunc(int, int) */
  @Override
  public void blendFunc(int sfactor, int dfactor) {
    glBlendFunc(sfactor, dfactor);
  }

  /* @see GLContext#blendFuncSeparate(int, int, int, int) */
  @Override
  public void blendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
    glBlendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
  }

  /* @see GLContext#bufferData(int, int, int) */
  @Override
  public void bufferData(int target, int size, int usage) {
    glBufferData(target, size, null, usage);
  }

  /* @see GLContext#bufferData(int, GLBytes, int) */
  @Override
  public void bufferData(int target, GLBytes data, int usage) {
    assert data instanceof AndroidGLBytes;
    AndroidGLBytes bytes = (AndroidGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      glBufferData(target, data.size(), buffer, usage);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#bufferSubData(int, int, GLBytes) */
  @Override
  public void bufferSubData(int target, int offset, GLBytes data) {
    assert data instanceof AndroidGLBytes;
    AndroidGLBytes bytes = (AndroidGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      glBufferSubData(target, offset, data.size(), buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#checkFramebufferStatus(int) */
  @Override
  public int checkFramebufferStatus(int target) {
    return glCheckFramebufferStatus(target);
  }

  /* @see GLContext#clear(int) */
  @Override
  public void clear(int mask) {
    glClear(mask);
  }

  /* @see GLContext#clearColor(float, float, float, float) */
  @Override
  public void clearColor(float red, float green, float blue, float alpha) {
    glClearColor(red, green, blue, alpha);
  }

  /* @see GLContext#clearDepthf(float) */
  @Override
  public void clearDepthf(float depth) {
    glClearDepthf(depth);
  }

  /* @see GLContext#clearStencil(int) */
  @Override
  public void clearStencil(int s) {
    glClearStencil(s);
  }

  /* @see GLContext#colorMask(boolean, boolean, boolean, boolean) */
  @Override
  public void colorMask(boolean red, boolean green, boolean blue, boolean alpha) {
    glColorMask(red, green, blue, alpha);
  }

  /* @see GLContext#compileShader(int) */
  @Override
  public void compileShader(int shader) {
    glCompileShader(shader);
  }

  /* @see GLContext#compressedTexImage2D(int, int, int, int, int, int, GLBytes) */
  @Override
  public void compressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
      GLBytes data) {
    assert data instanceof AndroidGLBytes;
    AndroidGLBytes bytes = (AndroidGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      glCompressedTexImage2D(target, level, internalformat, width, height, border, data.size(), buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /*
   * @see GLContext#compressedTexSubImage2D(int, int, int, int, int, int, int, GLBytes)
   */
  @Override
  public void compressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
      int format, GLBytes data) {
    assert data instanceof AndroidGLBytes;
    AndroidGLBytes bytes = (AndroidGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, data.size(), buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#copyTexImage2D(int, int, int, int, int, int, int, int) */
  @Override
  public void copyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border) {
    glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
  }

  /* @see GLContext#copyTexSubImage2D(int, int, int, int, int, int, int, int) */
  @Override
  public void copyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
    glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
  }

  /* @see GLContext#createProgram() */
  @Override
  public int createProgram() {
    return glCreateProgram();
  }

  /* @see GLContext#createShader(int) */
  @Override
  public int createShader(int shaderType) {
    return glCreateShader(shaderType);
  }

  /* @see GLContext#cullFace(int) */
  @Override
  public void cullFace(int mode) {
    glCullFace(mode);
  }

  /* @see GLContext#deleteBuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteBuffers(GLIntegers buffers) {
    assert buffers instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) buffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glDeleteBuffers(buffers.size(), buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#deleteFramebuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteFramebuffers(GLIntegers framebuffers) {
    assert framebuffers instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) framebuffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glDeleteFramebuffers(framebuffers.size(), buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#deleteProgram(int) */
  @Override
  public void deleteProgram(int program) {
    glDeleteProgram(program);
  }

  /* @see GLContext#deleteRenderbuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteRenderbuffers(GLIntegers renderbuffers) {
    assert renderbuffers instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) renderbuffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glDeleteRenderbuffers(renderbuffers.size(), buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#deleteShader(int) */
  @Override
  public void deleteShader(int shader) {
    glDeleteShader(shader);
  }

  /* @see GLContext#deleteTextures(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteTextures(GLIntegers textures) {
    assert textures instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) textures;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glDeleteTextures(textures.size(), buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#depthFunc(int) */
  @Override
  public void depthFunc(int func) {
    glDepthFunc(func);
  }

  /* @see GLContext#depthMask(boolean) */
  @Override
  public void depthMask(boolean flag) {
    glDepthMask(flag);
  }

  /* @see GLContext#depthRangef(float, float) */
  @Override
  public void depthRangef(float nearVal, float farVal) {
    glDepthRangef(nearVal, farVal);
  }

  /* @see GLContext#detachShader(int, int) */
  @Override
  public void detachShader(int program, int shader) {
    glDetachShader(program, shader);
  }

  /* @see GLContext#disable(int) */
  @Override
  public void disable(int cap) {
    glDisable(cap);
  }

  /* @see GLContext#disableVertexAttribArray(int) */
  @Override
  public void disableVertexAttribArray(int index) {
    glDisableVertexAttribArray(index);
  }

  /* @see GLContext#drawArrays(int, int, int) */
  @Override
  public void drawArrays(int mode, int first, int count) {
    glDrawArrays(mode, first, count);
  }

  /* @see GLContext#drawElements(int, int, int, int) */
  @Override
  public void drawElements(int mode, int count, int type, int offset) {
    glDrawElements(mode, count, type, offset);
  }

  /* @see GLContext#drawElements(int, GLBytes) */
  @Override
  public void drawElements(int mode, GLBytes indices) {
    assert indices instanceof AndroidGLBytes;
    AndroidGLBytes bytes = (AndroidGLBytes) indices;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      glDrawElements(mode, indices.size(), GL_UNSIGNED_BYTE, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#drawElements(int, GLShorts) */
  @Override
  public void drawElements(int mode, GLShorts indices) {
    assert indices instanceof AndroidGLShorts;
    AndroidGLShorts shorts = (AndroidGLShorts) indices;
    ShortBuffer buffer = shorts.acquireBuffer();
    try {
      glDrawElements(mode, indices.size(), GL_UNSIGNED_SHORT, buffer);
    } finally {
      shorts.releaseBuffer();
    }
  }

  /* @see GLContext#enable(int) */
  @Override
  public void enable(int cap) {
    glEnable(cap);
  }

  /* @see GLContext#enableVertexAttribArray(int) */
  @Override
  public void enableVertexAttribArray(int index) {
    glEnableVertexAttribArray(index);
  }

  /* @see GLContext#finish() */
  @Override
  public void finish() {
    glFinish();
  }

  /* @see GLContext#flush() */
  @Override
  public void flush() {
    glFlush();
  }

  /* @see GLContext#framebufferRenderbuffer(int, int, int, int) */
  @Override
  public void framebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
    glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer);
  }

  /* @see GLContext#framebufferTexture2D(int, int, int, int, int) */
  @Override
  public void framebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
    glFramebufferTexture2D(target, attachment, textarget, texture, level);
  }

  /* @see GLContext#frontFace(int) */
  @Override
  public void frontFace(int mode) {
    glFrontFace(mode);
  }

  /* @see GLContext#genBuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void genBuffers(GLIntegers buffers) {
    assert buffers instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) buffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glGenBuffers(buffers.size(), buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#genFramebuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void genFramebuffers(GLIntegers framebuffers) {
    assert framebuffers instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) framebuffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glGenFramebuffers(framebuffers.size(), buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#genRenderbuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void genRenderbuffers(GLIntegers renderbuffers) {
    assert renderbuffers instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) renderbuffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glGenRenderbuffers(renderbuffers.size(), buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#genTextures(fulcrum.opengl.GLIntegers) */
  @Override
  public void genTextures(GLIntegers textures) {
    assert textures instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) textures;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glGenTextures(textures.size(), buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#generateMipmap(int) */
  @Override
  public void generateMipmap(int target) {
    glGenerateMipmap(target);
  }

  /* @see GLContext#getBooleanv(int) */
  @Override
  public boolean[] getBooleanv(int pname) {
    boolean[] array = new boolean[parameterSize(pname)];
    glGetBooleanv(pname, array, 0);
    return array;
  }

  /* @see GLContext#getFloatv(int) */
  @Override
  public float[] getFloatv(int pname) {
    float[] array = new float[parameterSize(pname)];
    glGetFloatv(pname, array, 0);
    return array;
  }

  /* @see GLContext#getIntegerv(int) */
  @Override
  public int[] getIntegerv(int pname) {
    int[] array = new int[parameterSize(pname)];
    glGetIntegerv(pname, array, 0);
    return array;
  }

  /** Returns the number of data elements for the specified parameter. */
  private int parameterSize(int pname) {
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
      return getIntegerv(GL_NUM_COMPRESSED_TEXTURE_FORMATS)[0];
    case GL_DEPTH_RANGE:
      return 2;
    case GL_MAX_VIEWPORT_DIMS:
      return 2;
    case GL_SCISSOR_BOX:
      return 4;
    case GL_SHADER_BINARY_FORMATS:
      return getIntegerv(GL_NUM_SHADER_BINARY_FORMATS)[0];
    case GL_VIEWPORT:
      return 4;
    default:
      return 1;
    }
  }

  /* @see GLContext#getActiveAttrib(int, int) */
  @Override
  public GLVariable getActiveAttrib(int program, int index) {
    String name = glGetActiveAttrib(program, index, intArrayA, 0, intArrayB, 0);
    return new GLVariable(name, intArrayA[0], intArrayB[0]);
  }

  /* @see GLContext#getActiveUniform(int, int) */
  @Override
  public GLVariable getActiveUniform(int program, int index) {
    String name = glGetActiveUniform(program, index, intArrayA, 0, intArrayB, 0);
    return new GLVariable(name, intArrayA[0], intArrayB[0]);
  }

  /* @see GLContext#getAttachedShaders(int) */
  @Override
  public int[] getAttachedShaders(int program) {
    int[] array = new int[getProgramiv(program, GL_ATTACHED_SHADERS)];
    glGetAttachedShaders(program, array.length, intArrayA, 0, array, 0);
    return array;
  }

  /* @see GLContext#getAttribLocation(int, String) */
  @Override
  public int getAttribLocation(int program, String name) {
    return glGetAttribLocation(program, name);
  }

  /* @see GLContext#getBufferParameteriv(int, int) */
  @Override
  public int getBufferParameteriv(int target, int value) {
    glGetBufferParameteriv(target, value, intArrayA, 0);
    return intArrayA[0];
  }

  /* @see GLContext#getError() */
  @Override
  public int getError() {
    return glGetError();
  }

  /* @see GLContext#getFramebufferAttachmentParameteriv(int, int, int) */
  @Override
  public int getFramebufferAttachmentParameteriv(int target, int attachment, int pname) {
    glGetFramebufferAttachmentParameteriv(target, attachment, pname, intArrayA, 0);
    return intArrayA[0];
  }

  /* @see GLContext#getProgramInfoLog(int) */
  @Override
  public String getProgramInfoLog(int program) {
    return glGetProgramInfoLog(program);
  }

  /* @see GLContext#getProgramiv(int, int) */
  @Override
  public int getProgramiv(int program, int pname) {
    glGetProgramiv(program, pname, intArrayA, 0);
    return intArrayA[0];
  }

  /* @see GLContext#getRenderbufferParameteriv(int, int) */
  @Override
  public int getRenderbufferParameteriv(int target, int pname) {
    glGetRenderbufferParameteriv(target, pname, intArrayA, 0);
    return intArrayA[0];
  }

  /* @see GLContext#getShaderInfoLog(int) */
  @Override
  public String getShaderInfoLog(int shader) {
    return glGetShaderInfoLog(shader);
  }

  /* @see GLContext#getShaderPrecisionFormat(int, int) */
  @Override
  public GLPrecision getShaderPrecisionFormat(int shaderType, int precisionType) {
    glGetShaderPrecisionFormat(shaderType, precisionType, intArrayA, 0, intArrayB, 0);
    return new GLPrecision(intArrayA[0], intArrayA[1], intArrayB[0]);
  }

  /* @see GLContext#getShaderSource(int) */
  @Override
  public String getShaderSource(int shader) {
    return glGetShaderSource(shader);
  }

  /* @see GLContext#getShaderiv(int, int) */
  @Override
  public int getShaderiv(int shader, int pname) {
    glGetShaderiv(shader, pname, intArrayA, 0);
    return intArrayA[0];
  }

  /* @see GLContext#getString(int) */
  @Override
  public String getString(int name) {
    return glGetString(name);
  }

  /* @see GLContext#getTexParameterfv(int, int) */
  @Override
  public float getTexParameterfv(int target, int pname) {
    glGetTexParameterfv(target, pname, floatArray, 0);
    return floatArray[0];
  }

  /* @see GLContext#getTexParameteriv(int, int) */
  @Override
  public int getTexParameteriv(int target, int pname) {
    glGetTexParameteriv(target, pname, intArrayA, 0);
    return intArrayA[0];
  }

  /* @see GLContext#getUniformfv(int, int, GLFloats) */
  @Override
  public void getUniformfv(int program, int location, GLFloats params) {
    assert params instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) params;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glGetUniformfv(program, location, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#getUniformiv(int, int, GLIntegers) */
  @Override
  public void getUniformiv(int program, int location, GLIntegers params) {
    assert params instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) params;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glGetUniformiv(program, location, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#getUniformLocation(int, String) */
  @Override
  public int getUniformLocation(int program, String name) {
    return glGetUniformLocation(program, name);
  }

  /* @see GLContext#getVertexAttribfv(int, int, GLFloats) */
  @Override
  public void getVertexAttribfv(int index, int pname, GLFloats params) {
    assert params instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) params;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glGetVertexAttribfv(index, pname, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#getVertexAttribiv(int, int, GLIntegers) */
  @Override
  public void getVertexAttribiv(int index, int pname, GLIntegers params) {
    assert params instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) params;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glGetVertexAttribiv(index, pname, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#hint(int, int) */
  @Override
  public void hint(int target, int mode) {
    glHint(target, mode);
  }

  /* @see GLContext#isBuffer(int) */
  @Override
  public boolean isBuffer(int buffer) {
    return glIsBuffer(buffer);
  }

  /* @see GLContext#isEnabled(int) */
  @Override
  public boolean isEnabled(int cap) {
    return glIsEnabled(cap);
  }

  /* @see GLContext#isFramebuffer(int) */
  @Override
  public boolean isFramebuffer(int framebuffer) {
    return glIsFramebuffer(framebuffer);
  }

  /* @see GLContext#isProgram(int) */
  @Override
  public boolean isProgram(int program) {
    return glIsProgram(program);
  }

  /* @see GLContext#isRenderbuffer(int) */
  @Override
  public boolean isRenderbuffer(int renderbuffer) {
    return glIsRenderbuffer(renderbuffer);
  }

  /* @see GLContext#isShader(int) */
  @Override
  public boolean isShader(int shader) {
    return glIsShader(shader);
  }

  /* @see GLContext#isTexture(int) */
  @Override
  public boolean isTexture(int texture) {
    return glIsTexture(texture);
  }

  /* @see GLContext#lineWidth(float) */
  @Override
  public void lineWidth(float width) {
    glLineWidth(width);
  }

  /* @see GLContext#linkProgram(int) */
  @Override
  public void linkProgram(int program) {
    glLinkProgram(program);
  }

  /* @see GLContext#pixelStorei(int, int) */
  @Override
  public void pixelStorei(int pname, int param) {
    glPixelStorei(pname, param);
  }

  /* @see GLContext#polygonOffset(float, float) */
  @Override
  public void polygonOffset(float factor, float units) {
    glPolygonOffset(factor, units);
  }

  /* @see GLContext#readPixels(int, int, int, int, int, GLBytes) */
  @Override
  public void readPixels(int x, int y, int width, int height, int format, GLBytes data) {
    assert data instanceof AndroidGLBytes;
    AndroidGLBytes bytes = (AndroidGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      glReadPixels(x, y, width, height, format, GL_UNSIGNED_BYTE, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#readPixels(int, int, int, int, int, int, GLShorts) */
  @Override
  public void readPixels(int x, int y, int width, int height, int format, int type, GLShorts data) {
    assert data instanceof AndroidGLShorts;
    AndroidGLShorts shorts = (AndroidGLShorts) data;
    ShortBuffer buffer = shorts.acquireBuffer();
    try {
      glReadPixels(x, y, width, height, format, type, buffer);
    } finally {
      shorts.releaseBuffer();
    }
  }

  /* @see GLContext#releaseShaderCompiler() */
  @Override
  public void releaseShaderCompiler() {
    glReleaseShaderCompiler();
  }

  /* @see GLContext#renderbufferStorage(int, int, int, int) */
  @Override
  public void renderbufferStorage(int target, int internalformat, int width, int height) {
    glRenderbufferStorage(target, internalformat, width, height);
  }

  /* @see GLContext#sampleCoverage(float, boolean) */
  @Override
  public void sampleCoverage(float value, boolean invert) {
    glSampleCoverage(value, invert);
  }

  /* @see GLContext#scissor(int, int, int, int) */
  @Override
  public void scissor(int x, int y, int width, int height) {
    glScissor(x, y, width, height);
  }

  /* @see GLContext#shaderBinary(fulcrum.opengl.GLIntegers, int, GLBytes) */
  @Override
  public void shaderBinary(GLIntegers shaders, int binaryformat, GLBytes binary) {
    assert shaders instanceof AndroidGLIntegers;
    assert binary instanceof AndroidGLBytes;
    AndroidGLIntegers integers = (AndroidGLIntegers) shaders;
    AndroidGLBytes bytes = (AndroidGLBytes) binary;
    IntBuffer bufferA = integers.acquireBuffer();
    try {
      ByteBuffer bufferB = bytes.acquireBuffer();
      try {
        glShaderBinary(shaders.size(), bufferA, binaryformat, bufferB, binary.size());
      } finally {
        bytes.releaseBuffer();
      }
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#shaderSource(int, String) */
  @Override
  public void shaderSource(int shader, String string) {
    glShaderSource(shader, string);
  }

  /* @see GLContext#stencilFunc(int, int, int) */
  @Override
  public void stencilFunc(int func, int ref, int mask) {
    glStencilFunc(func, ref, mask);
  }

  /* @see GLContext#stencilFuncSeparate(int, int, int, int) */
  @Override
  public void stencilFuncSeparate(int face, int func, int ref, int mask) {
    glStencilFuncSeparate(face, func, ref, mask);
  }

  /* @see GLContext#stencilMask(int) */
  @Override
  public void stencilMask(int mask) {
    glStencilMask(mask);
  }

  /* @see GLContext#stencilMaskSeparate(int, int) */
  @Override
  public void stencilMaskSeparate(int face, int mask) {
    glStencilMaskSeparate(face, mask);
  }

  /* @see GLContext#stencilOp(int, int, int) */
  @Override
  public void stencilOp(int sfail, int dpfail, int dppass) {
    glStencilOp(sfail, dpfail, dppass);
  }

  /* @see GLContext#stencilOpSeparate(int, int, int, int) */
  @Override
  public void stencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
    glStencilOpSeparate(face, sfail, dpfail, dppass);
  }

  /* @see GLContext#texImage2D(int, int, int, int, int, int, int, GLBytes) */
  @Override
  public void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
      GLBytes data) {
    assert data instanceof AndroidGLBytes;
    AndroidGLBytes bytes = (AndroidGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      glTexImage2D(target, level, internalformat, width, height, border, internalformat, GL_UNSIGNED_BYTE, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#texImage2D(int, int, int, int, int, int, int, int, GLShorts) */
  @Override
  public void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
      int type, GLShorts data) {
    assert data instanceof AndroidGLShorts;
    AndroidGLShorts shorts = (AndroidGLShorts) data;
    ShortBuffer buffer = shorts.acquireBuffer();
    try {
      glTexImage2D(target, level, internalformat, width, height, border, internalformat, type, buffer);
    } finally {
      shorts.releaseBuffer();
    }
  }

  /* @see GLContext#texParameterf(int, int, float) */
  @Override
  public void texParameterf(int target, int pname, float param) {
    glTexParameterf(target, pname, param);
  }

  /* @see GLContext#texParameteri(int, int, int) */
  @Override
  public void texParameteri(int target, int pname, int param) {
    glTexParameteri(target, pname, param);
  }

  /* @see GLContext#texParameterfv(int, int, GLFloats) */
  @Override
  public void texParameterfv(int target, int pname, GLFloats params) {
    assert params instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) params;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glTexParameterfv(target, pname, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#texParameteriv(int, int, GLIntegers) */
  @Override
  public void texParameteriv(int target, int pname, GLIntegers params) {
    assert params instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) params;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glTexParameteriv(target, pname, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#texSubImage2D(int, int, int, int, int, int, int, GLBytes) */
  @Override
  public void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
      GLBytes data) {
    assert data instanceof AndroidGLBytes;
    AndroidGLBytes bytes = (AndroidGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, GL_UNSIGNED_BYTE, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#texSubImage2D(int, int, int, int, int, int, int, int, GLShorts) */
  @Override
  public void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
      int type, GLShorts data) {
    assert data instanceof AndroidGLShorts;
    AndroidGLShorts shorts = (AndroidGLShorts) data;
    ShortBuffer buffer = shorts.acquireBuffer();
    try {
      glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, buffer);
    } finally {
      shorts.releaseBuffer();
    }
  }

  /* @see GLContext#uniform1f(int, float) */
  @Override
  public void uniform1f(int location, float v0) {
    glUniform1f(location, v0);
  }

  /* @see GLContext#uniform2f(int, float, float) */
  @Override
  public void uniform2f(int location, float v0, float v1) {
    glUniform2f(location, v0, v1);
  }

  /* @see GLContext#uniform3f(int, float, float, float) */
  @Override
  public void uniform3f(int location, float v0, float v1, float v2) {
    glUniform3f(location, v0, v1, v2);
  }

  /* @see GLContext#uniform4f(int, float, float, float, float) */
  @Override
  public void uniform4f(int location, float v0, float v1, float v2, float v3) {
    glUniform4f(location, v0, v1, v2, v3);
  }

  /* @see GLContext#uniform1i(int, int) */
  @Override
  public void uniform1i(int location, int v0) {
    glUniform1i(location, v0);
  }

  /* @see GLContext#uniform2i(int, int, int) */
  @Override
  public void uniform2i(int location, int v0, int v1) {
    glUniform2i(location, v0, v1);
  }

  /* @see GLContext#uniform3i(int, int, int, int) */
  @Override
  public void uniform3i(int location, int v0, int v1, int v2) {
    glUniform3i(location, v0, v1, v2);
  }

  /* @see GLContext#uniform4i(int, int, int, int, int) */
  @Override
  public void uniform4i(int location, int v0, int v1, int v2, int v3) {
    glUniform4i(location, v0, v1, v2, v3);
  }

  /* @see GLContext#uniform1fv(int, int, GLFloats) */
  @Override
  public void uniform1fv(int location, int count, GLFloats value) {
    assert value instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glUniform1fv(location, count, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniform2fv(int, int, GLFloats) */
  @Override
  public void uniform2fv(int location, int count, GLFloats value) {
    assert value instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glUniform2fv(location, count, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniform3fv(int, int, GLFloats) */
  @Override
  public void uniform3fv(int location, int count, GLFloats value) {
    assert value instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glUniform3fv(location, count, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniform4fv(int, int, GLFloats) */
  @Override
  public void uniform4fv(int location, int count, GLFloats value) {
    assert value instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glUniform4fv(location, count, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniform1iv(int, int, GLIntegers) */
  @Override
  public void uniform1iv(int location, int count, GLIntegers value) {
    assert value instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) value;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glUniform1iv(location, count, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#uniform2iv(int, int, GLIntegers) */
  @Override
  public void uniform2iv(int location, int count, GLIntegers value) {
    assert value instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) value;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glUniform2iv(location, count, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#uniform3iv(int, int, GLIntegers) */
  @Override
  public void uniform3iv(int location, int count, GLIntegers value) {
    assert value instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) value;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glUniform3iv(location, count, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#uniform4iv(int, int, GLIntegers) */
  @Override
  public void uniform4iv(int location, int count, GLIntegers value) {
    assert value instanceof AndroidGLIntegers;
    AndroidGLIntegers integers = (AndroidGLIntegers) value;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      glUniform4iv(location, count, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#uniformMatrix2fv(int, int, boolean, GLFloats) */
  @Override
  public void uniformMatrix2fv(int location, int count, boolean transpose, GLFloats value) {
    assert value instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glUniformMatrix2fv(location, count, transpose, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniformMatrix3fv(int, int, boolean, GLFloats) */
  @Override
  public void uniformMatrix3fv(int location, int count, boolean transpose, GLFloats value) {
    assert value instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glUniformMatrix3fv(location, count, transpose, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniformMatrix4fv(int, int, boolean, GLFloats) */
  @Override
  public void uniformMatrix4fv(int location, int count, boolean transpose, GLFloats value) {
    assert value instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glUniformMatrix4fv(location, count, transpose, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#useProgram(int) */
  @Override
  public void useProgram(int program) {
    glUseProgram(program);
  }

  /* @see GLContext#validateProgram(int) */
  @Override
  public void validateProgram(int program) {
    glValidateProgram(program);
  }

  /* @see GLContext#vertexAttrib1f(int, float) */
  @Override
  public void vertexAttrib1f(int index, float v0) {
    glVertexAttrib1f(index, v0);
  }

  /* @see GLContext#vertexAttrib2f(int, float, float) */
  @Override
  public void vertexAttrib2f(int index, float v0, float v1) {
    glVertexAttrib2f(index, v0, v1);
  }

  /* @see GLContext#vertexAttrib3f(int, float, float, float) */
  @Override
  public void vertexAttrib3f(int index, float v0, float v1, float v2) {
    glVertexAttrib3f(index, v0, v1, v2);
  }

  /* @see GLContext#vertexAttrib4f(int, float, float, float, float) */
  @Override
  public void vertexAttrib4f(int index, float v0, float v1, float v2, float v3) {
    glVertexAttrib4f(index, v0, v1, v2, v3);
  }

  /* @see GLContext#vertexAttrib1fv(int, GLFloats) */
  @Override
  public void vertexAttrib1fv(int index, GLFloats v) {
    assert v instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) v;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glVertexAttrib1fv(index, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#vertexAttrib2fv(int, GLFloats) */
  @Override
  public void vertexAttrib2fv(int index, GLFloats v) {
    assert v instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) v;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glVertexAttrib2fv(index, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#vertexAttrib3fv(int, GLFloats) */
  @Override
  public void vertexAttrib3fv(int index, GLFloats v) {
    assert v instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) v;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glVertexAttrib3fv(index, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#vertexAttrib4fv(int, GLFloats) */
  @Override
  public void vertexAttrib4fv(int index, GLFloats v) {
    assert v instanceof AndroidGLFloats;
    AndroidGLFloats floats = (AndroidGLFloats) v;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      glVertexAttrib4fv(index, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#vertexAttribPointer(int, int, int, boolean, int, int) */
  @Override
  public void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset) {
    glVertexAttribPointer(index, size, type, normalized, stride, offset);
  }

  /* @see GLContext#vertexAttribPointer(int, int, int, boolean, int, GLBytes) */
  @Override
  public void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, GLBytes pointer) {
    assert pointer instanceof AndroidGLBytes;
    AndroidGLBytes bytes = (AndroidGLBytes) pointer;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      glVertexAttribPointer(index, size, type, normalized, stride, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#viewport(int, int, int, int) */
  @Override
  public void viewport(int x, int y, int width, int height) {
    glViewport(x, y, width, height);
  }

  //
  // Factory methods for the OpenGL data sets.
  //

  /* @see GLContext#allocateBytes(int) */
  @Override
  public GLBytes allocateBytes(int size) {
    assert size >= 0;
    return new AndroidGLBytes(size);
  }

  /* @see GLContext#allocateShorts(int) */
  @Override
  public GLShorts allocateShorts(int size) {
    assert size >= 0;
    return new AndroidGLShorts(size);
  }

  /* @see GLContext#allocateIntegers(int) */
  @Override
  public GLIntegers allocateIntegers(int size) {
    assert size >= 0;
    return new AndroidGLIntegers(size);
  }

  /* @see GLContext#allocateFloats(int) */
  @Override
  public GLFloats allocateFloats(int size) {
    assert size >= 0;
    return new AndroidGLFloats(size);
  }

}

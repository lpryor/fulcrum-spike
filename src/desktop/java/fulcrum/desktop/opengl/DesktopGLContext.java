package fulcrum.desktop.opengl;

/*
 * DesktopGLContext.java
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

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.opengl.ARBES2Compatibility;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import fulcrum.opengl.GLBytes;
import fulcrum.opengl.GLContext;
import fulcrum.opengl.GLFloats;
import fulcrum.opengl.GLIntegers;
import fulcrum.opengl.GLPrecision;
import fulcrum.opengl.GLShorts;
import fulcrum.opengl.GLVariable;

/**
 * An Desktop-specific implementation of {@link GLContext}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class DesktopGLContext extends GLContext {

  /** Local declaration of the GL_RGB565 constant. */
  private static final int GL_RGB565 = 0x8D62;
  /** Local declaration of the GL_SHADER_BINARY_FORMATS constant. */
  private static final int GL_SHADER_BINARY_FORMATS = 0x8DF8;

  /** A byte buffer to use inside method implementations. */
  private final ByteBuffer byteBuffer = new DesktopGLBytes(4 * 4).acquireBuffer();
  /** An integer buffer to use inside method implementations. */
  private final IntBuffer intBufferA = new DesktopGLIntegers(2).acquireBuffer();
  /** Another integer buffer to use inside method implementations. */
  private final IntBuffer intBufferB = new DesktopGLIntegers(1).acquireBuffer();

  //
  // Accessors for the OpenGL constants.
  //

  /* @see GLContext#ACTIVE_ATTRIBUTE_MAX_LENGTH() */
  @Override
  public int ACTIVE_ATTRIBUTE_MAX_LENGTH() {
    return GL20.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH;
  }

  /* @see GLContext#ACTIVE_ATTRIBUTES() */
  @Override
  public int ACTIVE_ATTRIBUTES() {
    return GL20.GL_ACTIVE_ATTRIBUTES;
  }

  /* @see GLContext#ACTIVE_TEXTURE() */
  @Override
  public int ACTIVE_TEXTURE() {
    return GL13.GL_ACTIVE_TEXTURE;
  }

  /* @see GLContext#ACTIVE_UNIFORM_MAX_LENGTH() */
  @Override
  public int ACTIVE_UNIFORM_MAX_LENGTH() {
    return GL20.GL_ACTIVE_UNIFORM_MAX_LENGTH;
  }

  /* @see GLContext#ACTIVE_UNIFORMS() */
  @Override
  public int ACTIVE_UNIFORMS() {
    return GL20.GL_ACTIVE_UNIFORMS;
  }

  /* @see GLContext#ALIASED_LINE_WIDTH_RANGE() */
  @Override
  public int ALIASED_LINE_WIDTH_RANGE() {
    return GL12.GL_ALIASED_LINE_WIDTH_RANGE;
  }

  /* @see GLContext#ALIASED_POINT_SIZE_RANGE() */
  @Override
  public int ALIASED_POINT_SIZE_RANGE() {
    return GL12.GL_ALIASED_POINT_SIZE_RANGE;
  }

  /* @see GLContext#ALPHA() */
  @Override
  public int ALPHA() {
    return GL11.GL_ALPHA;
  }

  /* @see GLContext#ALPHA_BITS() */
  @Override
  public int ALPHA_BITS() {
    return GL11.GL_ALPHA_BITS;
  }

  /* @see GLContext#ALWAYS() */
  @Override
  public int ALWAYS() {
    return GL11.GL_ALWAYS;
  }

  /* @see GLContext#ARRAY_BUFFER() */
  @Override
  public int ARRAY_BUFFER() {
    return GL15.GL_ARRAY_BUFFER;
  }

  /* @see GLContext#ARRAY_BUFFER_BINDING() */
  @Override
  public int ARRAY_BUFFER_BINDING() {
    return GL15.GL_ARRAY_BUFFER_BINDING;
  }

  /* @see GLContext#ATTACHED_SHADERS() */
  @Override
  public int ATTACHED_SHADERS() {
    return GL20.GL_ATTACHED_SHADERS;
  }

  /* @see GLContext#BACK() */
  @Override
  public int BACK() {
    return GL11.GL_BACK;
  }

  /* @see GLContext#BLEND() */
  @Override
  public int BLEND() {
    return GL11.GL_BLEND;
  }

  /* @see GLContext#BLEND_COLOR() */
  @Override
  public int BLEND_COLOR() {
    return GL14.GL_BLEND_COLOR;
  }

  /* @see GLContext#BLEND_DST_ALPHA() */
  @Override
  public int BLEND_DST_ALPHA() {
    return GL14.GL_BLEND_DST_ALPHA;
  }

  /* @see GLContext#BLEND_DST_RGB() */
  @Override
  public int BLEND_DST_RGB() {
    return GL14.GL_BLEND_DST_RGB;
  }

  /* @see GLContext#BLEND_EQUATION() */
  @Override
  public int BLEND_EQUATION() {
    return GL14.GL_BLEND_EQUATION;
  }

  /* @see GLContext#BLEND_EQUATION_ALPHA() */
  @Override
  public int BLEND_EQUATION_ALPHA() {
    return GL20.GL_BLEND_EQUATION_ALPHA;
  }

  /* @see GLContext#BLEND_EQUATION_RGB() */
  @Override
  public int BLEND_EQUATION_RGB() {
    return GL20.GL_BLEND_EQUATION_RGB;
  }

  /* @see GLContext#BLEND_SRC_ALPHA() */
  @Override
  public int BLEND_SRC_ALPHA() {
    return GL14.GL_BLEND_SRC_ALPHA;
  }

  /* @see GLContext#BLEND_SRC_RGB() */
  @Override
  public int BLEND_SRC_RGB() {
    return GL14.GL_BLEND_SRC_RGB;
  }

  /* @see GLContext#BLUE_BITS() */
  @Override
  public int BLUE_BITS() {
    return GL11.GL_BLUE_BITS;
  }

  /* @see GLContext#BOOL() */
  @Override
  public int BOOL() {
    return GL20.GL_BOOL;
  }

  /* @see GLContext#BOOL_VEC2() */
  @Override
  public int BOOL_VEC2() {
    return GL20.GL_BOOL_VEC2;
  }

  /* @see GLContext#BOOL_VEC3() */
  @Override
  public int BOOL_VEC3() {
    return GL20.GL_BOOL_VEC3;
  }

  /* @see GLContext#BOOL_VEC4() */
  @Override
  public int BOOL_VEC4() {
    return GL20.GL_BOOL_VEC4;
  }

  /* @see GLContext#BUFFER_SIZE() */
  @Override
  public int BUFFER_SIZE() {
    return GL15.GL_BUFFER_SIZE;
  }

  /* @see GLContext#BUFFER_USAGE() */
  @Override
  public int BUFFER_USAGE() {
    return GL15.GL_BUFFER_USAGE;
  }

  /* @see GLContext#BYTE() */
  @Override
  public int BYTE() {
    return GL11.GL_BYTE;
  }

  /* @see GLContext#CCW() */
  @Override
  public int CCW() {
    return GL11.GL_CCW;
  }

  /* @see GLContext#CLAMP_TO_EDGE() */
  @Override
  public int CLAMP_TO_EDGE() {
    return GL12.GL_CLAMP_TO_EDGE;
  }

  /* @see GLContext#COLOR_ATTACHMENT0() */
  @Override
  public int COLOR_ATTACHMENT0() {
    return EXTFramebufferObject.GL_COLOR_ATTACHMENT0_EXT;
  }

  /* @see GLContext#COLOR_BUFFER_BIT() */
  @Override
  public int COLOR_BUFFER_BIT() {
    return GL11.GL_COLOR_BUFFER_BIT;
  }

  /* @see GLContext#COLOR_CLEAR_VALUE() */
  @Override
  public int COLOR_CLEAR_VALUE() {
    return GL11.GL_COLOR_CLEAR_VALUE;
  }

  /* @see GLContext#COLOR_WRITEMASK() */
  @Override
  public int COLOR_WRITEMASK() {
    return GL11.GL_COLOR_WRITEMASK;
  }

  /* @see GLContext#COMPILE_STATUS() */
  @Override
  public int COMPILE_STATUS() {
    return GL20.GL_COMPILE_STATUS;
  }

  /* @see GLContext#COMPRESSED_TEXTURE_FORMATS() */
  @Override
  public int COMPRESSED_TEXTURE_FORMATS() {
    return GL13.GL_COMPRESSED_TEXTURE_FORMATS;
  }

  /* @see GLContext#CONSTANT_ALPHA() */
  @Override
  public int CONSTANT_ALPHA() {
    return GL11.GL_CONSTANT_ALPHA;
  }

  /* @see GLContext#CONSTANT_COLOR() */
  @Override
  public int CONSTANT_COLOR() {
    return GL11.GL_CONSTANT_COLOR;
  }

  /* @see GLContext#CULL_FACE() */
  @Override
  public int CULL_FACE() {
    return GL11.GL_CULL_FACE;
  }

  /* @see GLContext#CULL_FACE_MODE() */
  @Override
  public int CULL_FACE_MODE() {
    return GL11.GL_CULL_FACE_MODE;
  }

  /* @see GLContext#CURRENT_PROGRAM() */
  @Override
  public int CURRENT_PROGRAM() {
    return GL20.GL_CURRENT_PROGRAM;
  }

  /* @see GLContext#CURRENT_VERTEX_ATTRIB() */
  @Override
  public int CURRENT_VERTEX_ATTRIB() {
    return GL20.GL_CURRENT_VERTEX_ATTRIB;
  }

  /* @see GLContext#CW() */
  @Override
  public int CW() {
    return GL11.GL_CW;
  }

  /* @see GLContext#DECR() */
  @Override
  public int DECR() {
    return GL11.GL_DECR;
  }

  /* @see GLContext#DECR_WRAP() */
  @Override
  public int DECR_WRAP() {
    return GL14.GL_DECR_WRAP;
  }

  /* @see GLContext#DELETE_STATUS() */
  @Override
  public int DELETE_STATUS() {
    return GL20.GL_DELETE_STATUS;
  }

  /* @see GLContext#DEPTH_ATTACHMENT() */
  @Override
  public int DEPTH_ATTACHMENT() {
    return EXTFramebufferObject.GL_DEPTH_ATTACHMENT_EXT;
  }

  /* @see GLContext#DEPTH_BITS() */
  @Override
  public int DEPTH_BITS() {
    return GL11.GL_DEPTH_BITS;
  }

  /* @see GLContext#DEPTH_BUFFER_BIT() */
  @Override
  public int DEPTH_BUFFER_BIT() {
    return GL11.GL_DEPTH_BUFFER_BIT;
  }

  /* @see GLContext#DEPTH_CLEAR_VALUE() */
  @Override
  public int DEPTH_CLEAR_VALUE() {
    return GL11.GL_DEPTH_CLEAR_VALUE;
  }

  /* @see GLContext#DEPTH_COMPONENT() */
  @Override
  public int DEPTH_COMPONENT() {
    return GL11.GL_DEPTH_COMPONENT;
  }

  /* @see GLContext#DEPTH_COMPONENT16() */
  @Override
  public int DEPTH_COMPONENT16() {
    return GL14.GL_DEPTH_COMPONENT16;
  }

  /* @see GLContext#DEPTH_FUNC() */
  @Override
  public int DEPTH_FUNC() {
    return GL11.GL_DEPTH_FUNC;
  }

  /* @see GLContext#DEPTH_RANGE() */
  @Override
  public int DEPTH_RANGE() {
    return GL11.GL_DEPTH_RANGE;
  }

  /* @see GLContext#DEPTH_TEST() */
  @Override
  public int DEPTH_TEST() {
    return GL11.GL_DEPTH_TEST;
  }

  /* @see GLContext#DEPTH_WRITEMASK() */
  @Override
  public int DEPTH_WRITEMASK() {
    return GL11.GL_DEPTH_WRITEMASK;
  }

  /* @see GLContext#DITHER() */
  @Override
  public int DITHER() {
    return GL11.GL_DITHER;
  }

  /* @see GLContext#DONT_CARE() */
  @Override
  public int DONT_CARE() {
    return GL11.GL_DONT_CARE;
  }

  /* @see GLContext#DST_ALPHA() */
  @Override
  public int DST_ALPHA() {
    return GL11.GL_DST_ALPHA;
  }

  /* @see GLContext#DST_COLOR() */
  @Override
  public int DST_COLOR() {
    return GL11.GL_DST_COLOR;
  }

  /* @see GLContext#DYNAMIC_DRAW() */
  @Override
  public int DYNAMIC_DRAW() {
    return GL15.GL_DYNAMIC_DRAW;
  }

  /* @see GLContext#ELEMENT_ARRAY_BUFFER() */
  @Override
  public int ELEMENT_ARRAY_BUFFER() {
    return GL15.GL_ELEMENT_ARRAY_BUFFER;
  }

  /* @see GLContext#ELEMENT_ARRAY_BUFFER_BINDING() */
  @Override
  public int ELEMENT_ARRAY_BUFFER_BINDING() {
    return GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING;
  }

  /* @see GLContext#EQUAL() */
  @Override
  public int EQUAL() {
    return GL11.GL_EQUAL;
  }

  /* @see GLContext#EXTENSIONS() */
  @Override
  public int EXTENSIONS() {
    return GL11.GL_EXTENSIONS;
  }

  /* @see GLContext#FALSE() */
  @Override
  public int FALSE() {
    return GL11.GL_FALSE;
  }

  /* @see GLContext#FASTEST() */
  @Override
  public int FASTEST() {
    return GL11.GL_FASTEST;
  }

  /* @see GLContext#FIXED() */
  @Override
  public int FIXED() {
    return ARBES2Compatibility.GL_FIXED;
  }

  /* @see GLContext#FLOAT() */
  @Override
  public int FLOAT() {
    return GL11.GL_FLOAT;
  }

  /* @see GLContext#FLOAT_MAT2() */
  @Override
  public int FLOAT_MAT2() {
    return GL20.GL_FLOAT_MAT2;
  }

  /* @see GLContext#FLOAT_MAT3() */
  @Override
  public int FLOAT_MAT3() {
    return GL20.GL_FLOAT_MAT3;
  }

  /* @see GLContext#FLOAT_MAT4() */
  @Override
  public int FLOAT_MAT4() {
    return GL20.GL_FLOAT_MAT4;
  }

  /* @see GLContext#FLOAT_VEC2() */
  @Override
  public int FLOAT_VEC2() {
    return GL20.GL_FLOAT_VEC2;
  }

  /* @see GLContext#FLOAT_VEC3() */
  @Override
  public int FLOAT_VEC3() {
    return GL20.GL_FLOAT_VEC3;
  }

  /* @see GLContext#FLOAT_VEC4() */
  @Override
  public int FLOAT_VEC4() {
    return GL20.GL_FLOAT_VEC4;
  }

  /* @see GLContext#FRAGMENT_SHADER() */
  @Override
  public int FRAGMENT_SHADER() {
    return GL20.GL_FRAGMENT_SHADER;
  }

  /* @see GLContext#FRAMEBUFFER() */
  @Override
  public int FRAMEBUFFER() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_EXT;
  }

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_OBJECT_NAME() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_OBJECT_NAME() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME_EXT;
  }

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE_EXT;
  }

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE_EXT;
  }

  /* @see GLContext#FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL() */
  @Override
  public int FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL_EXT;
  }

  /* @see GLContext#FRAMEBUFFER_BINDING() */
  @Override
  public int FRAMEBUFFER_BINDING() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_BINDING_EXT;
  }

  /* @see GLContext#FRAMEBUFFER_COMPLETE() */
  @Override
  public int FRAMEBUFFER_COMPLETE() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_COMPLETE_EXT;
  }

  /* @see GLContext#FRAMEBUFFER_INCOMPLETE_ATTACHMENT() */
  @Override
  public int FRAMEBUFFER_INCOMPLETE_ATTACHMENT() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT;
  }

  /* @see GLContext#FRAMEBUFFER_INCOMPLETE_DIMENSIONS() */
  @Override
  public int FRAMEBUFFER_INCOMPLETE_DIMENSIONS() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT;
  }

  /* @see GLContext#FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT() */
  @Override
  public int FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT;
  }

  /* @see GLContext#FRAMEBUFFER_UNSUPPORTED() */
  @Override
  public int FRAMEBUFFER_UNSUPPORTED() {
    return EXTFramebufferObject.GL_FRAMEBUFFER_UNSUPPORTED_EXT;
  }

  /* @see GLContext#FRONT() */
  @Override
  public int FRONT() {
    return GL11.GL_FRONT;
  }

  /* @see GLContext#FRONT_AND_BACK() */
  @Override
  public int FRONT_AND_BACK() {
    return GL11.GL_FRONT_AND_BACK;
  }

  /* @see GLContext#FRONT_FACE() */
  @Override
  public int FRONT_FACE() {
    return GL11.GL_FRONT_FACE;
  }

  /* @see GLContext#FUNC_ADD() */
  @Override
  public int FUNC_ADD() {
    return GL14.GL_FUNC_ADD;
  }

  /* @see GLContext#FUNC_REVERSE_SUBTRACT() */
  @Override
  public int FUNC_REVERSE_SUBTRACT() {
    return GL14.GL_FUNC_REVERSE_SUBTRACT;
  }

  /* @see GLContext#FUNC_SUBTRACT() */
  @Override
  public int FUNC_SUBTRACT() {
    return GL14.GL_FUNC_SUBTRACT;
  }

  /* @see GLContext#GENERATE_MIPMAP_HINT() */
  @Override
  public int GENERATE_MIPMAP_HINT() {
    return GL14.GL_GENERATE_MIPMAP_HINT;
  }

  /* @see GLContext#GEQUAL() */
  @Override
  public int GEQUAL() {
    return GL11.GL_GEQUAL;
  }

  /* @see GLContext#GREATER() */
  @Override
  public int GREATER() {
    return GL11.GL_GREATER;
  }

  /* @see GLContext#GREEN_BITS() */
  @Override
  public int GREEN_BITS() {
    return GL11.GL_GREEN_BITS;
  }

  /* @see GLContext#HIGH_FLOAT() */
  @Override
  public int HIGH_FLOAT() {
    return ARBES2Compatibility.GL_HIGH_FLOAT;
  }

  /* @see GLContext#HIGH_INT() */
  @Override
  public int HIGH_INT() {
    return ARBES2Compatibility.GL_HIGH_INT;
  }

  /* @see GLContext#IMPLEMENTATION_COLOR_READ_FORMAT() */
  @Override
  public int IMPLEMENTATION_COLOR_READ_FORMAT() {
    return ARBES2Compatibility.GL_IMPLEMENTATION_COLOR_READ_FORMAT;
  }

  /* @see GLContext#IMPLEMENTATION_COLOR_READ_TYPE() */
  @Override
  public int IMPLEMENTATION_COLOR_READ_TYPE() {
    return ARBES2Compatibility.GL_IMPLEMENTATION_COLOR_READ_TYPE;
  }

  /* @see GLContext#INCR() */
  @Override
  public int INCR() {
    return GL11.GL_INCR;
  }

  /* @see GLContext#INCR_WRAP() */
  @Override
  public int INCR_WRAP() {
    return GL14.GL_INCR_WRAP;
  }

  /* @see GLContext#INFO_LOG_LENGTH() */
  @Override
  public int INFO_LOG_LENGTH() {
    return GL20.GL_INFO_LOG_LENGTH;
  }

  /* @see GLContext#INT() */
  @Override
  public int INT() {
    return GL11.GL_INT;
  }

  /* @see GLContext#INT_VEC2() */
  @Override
  public int INT_VEC2() {
    return GL20.GL_INT_VEC2;
  }

  /* @see GLContext#INT_VEC3() */
  @Override
  public int INT_VEC3() {
    return GL20.GL_INT_VEC3;
  }

  /* @see GLContext#INT_VEC4() */
  @Override
  public int INT_VEC4() {
    return GL20.GL_INT_VEC4;
  }

  /* @see GLContext#INVALID_ENUM() */
  @Override
  public int INVALID_ENUM() {
    return GL11.GL_INVALID_ENUM;
  }

  /* @see GLContext#INVALID_FRAMEBUFFER_OPERATION() */
  @Override
  public int INVALID_FRAMEBUFFER_OPERATION() {
    return EXTFramebufferObject.GL_INVALID_FRAMEBUFFER_OPERATION_EXT;
  }

  /* @see GLContext#INVALID_OPERATION() */
  @Override
  public int INVALID_OPERATION() {
    return GL11.GL_INVALID_OPERATION;
  }

  /* @see GLContext#INVALID_VALUE() */
  @Override
  public int INVALID_VALUE() {
    return GL11.GL_INVALID_VALUE;
  }

  /* @see GLContext#INVERT() */
  @Override
  public int INVERT() {
    return GL11.GL_INVERT;
  }

  /* @see GLContext#KEEP() */
  @Override
  public int KEEP() {
    return GL11.GL_KEEP;
  }

  /* @see GLContext#LEQUAL() */
  @Override
  public int LEQUAL() {
    return GL11.GL_LEQUAL;
  }

  /* @see GLContext#LESS() */
  @Override
  public int LESS() {
    return GL11.GL_LESS;
  }

  /* @see GLContext#LINE_LOOP() */
  @Override
  public int LINE_LOOP() {
    return GL11.GL_LINE_LOOP;
  }

  /* @see GLContext#LINE_STRIP() */
  @Override
  public int LINE_STRIP() {
    return GL11.GL_LINE_STRIP;
  }

  /* @see GLContext#LINE_WIDTH() */
  @Override
  public int LINE_WIDTH() {
    return GL11.GL_LINE_WIDTH;
  }

  /* @see GLContext#LINEAR() */
  @Override
  public int LINEAR() {
    return GL11.GL_LINEAR;
  }

  /* @see GLContext#LINEAR_MIPMAP_LINEAR() */
  @Override
  public int LINEAR_MIPMAP_LINEAR() {
    return GL11.GL_LINEAR_MIPMAP_LINEAR;
  }

  /* @see GLContext#LINEAR_MIPMAP_NEAREST() */
  @Override
  public int LINEAR_MIPMAP_NEAREST() {
    return GL11.GL_LINEAR_MIPMAP_NEAREST;
  }

  /* @see GLContext#LINES() */
  @Override
  public int LINES() {
    return GL11.GL_LINES;
  }

  /* @see GLContext#LINK_STATUS() */
  @Override
  public int LINK_STATUS() {
    return GL20.GL_LINK_STATUS;
  }

  /* @see GLContext#LOW_FLOAT() */
  @Override
  public int LOW_FLOAT() {
    return ARBES2Compatibility.GL_LOW_FLOAT;
  }

  /* @see GLContext#LOW_INT() */
  @Override
  public int LOW_INT() {
    return ARBES2Compatibility.GL_LOW_INT;
  }

  /* @see GLContext#LUMINANCE() */
  @Override
  public int LUMINANCE() {
    return GL11.GL_LUMINANCE;
  }

  /* @see GLContext#LUMINANCE_ALPHA() */
  @Override
  public int LUMINANCE_ALPHA() {
    return GL11.GL_LUMINANCE_ALPHA;
  }

  /* @see GLContext#MAX_COMBINED_TEXTURE_IMAGE_UNITS() */
  @Override
  public int MAX_COMBINED_TEXTURE_IMAGE_UNITS() {
    return GL20.GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS;
  }

  /* @see GLContext#MAX_CUBE_MAP_TEXTURE_SIZE() */
  @Override
  public int MAX_CUBE_MAP_TEXTURE_SIZE() {
    return GL13.GL_MAX_CUBE_MAP_TEXTURE_SIZE;
  }

  /* @see GLContext#MAX_FRAGMENT_UNIFORM_VECTORS() */
  @Override
  public int MAX_FRAGMENT_UNIFORM_VECTORS() {
    return ARBES2Compatibility.GL_MAX_FRAGMENT_UNIFORM_VECTORS;
  }

  /* @see GLContext#MAX_RENDERBUFFER_SIZE() */
  @Override
  public int MAX_RENDERBUFFER_SIZE() {
    return EXTFramebufferObject.GL_MAX_RENDERBUFFER_SIZE_EXT;
  }

  /* @see GLContext#MAX_TEXTURE_IMAGE_UNITS() */
  @Override
  public int MAX_TEXTURE_IMAGE_UNITS() {
    return GL20.GL_MAX_TEXTURE_IMAGE_UNITS;
  }

  /* @see GLContext#MAX_TEXTURE_SIZE() */
  @Override
  public int MAX_TEXTURE_SIZE() {
    return GL11.GL_MAX_TEXTURE_SIZE;
  }

  /* @see GLContext#MAX_VARYING_VECTORS() */
  @Override
  public int MAX_VARYING_VECTORS() {
    return ARBES2Compatibility.GL_MAX_VARYING_VECTORS;
  }

  /* @see GLContext#MAX_VERTEX_ATTRIBS() */
  @Override
  public int MAX_VERTEX_ATTRIBS() {
    return GL20.GL_MAX_VERTEX_ATTRIBS;
  }

  /* @see GLContext#MAX_VERTEX_TEXTURE_IMAGE_UNITS() */
  @Override
  public int MAX_VERTEX_TEXTURE_IMAGE_UNITS() {
    return GL20.GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS;
  }

  /* @see GLContext#MAX_VERTEX_UNIFORM_VECTORS() */
  @Override
  public int MAX_VERTEX_UNIFORM_VECTORS() {
    return ARBES2Compatibility.GL_MAX_VERTEX_UNIFORM_VECTORS;
  }

  /* @see GLContext#MAX_VIEWPORT_DIMS() */
  @Override
  public int MAX_VIEWPORT_DIMS() {
    return GL11.GL_MAX_VIEWPORT_DIMS;
  }

  /* @see GLContext#MEDIUM_FLOAT() */
  @Override
  public int MEDIUM_FLOAT() {
    return ARBES2Compatibility.GL_MEDIUM_FLOAT;
  }

  /* @see GLContext#MEDIUM_INT() */
  @Override
  public int MEDIUM_INT() {
    return ARBES2Compatibility.GL_MEDIUM_INT;
  }

  /* @see GLContext#MIRRORED_REPEAT() */
  @Override
  public int MIRRORED_REPEAT() {
    return GL14.GL_MIRRORED_REPEAT;
  }

  /* @see GLContext#NEAREST() */
  @Override
  public int NEAREST() {
    return GL11.GL_NEAREST;
  }

  /* @see GLContext#NEAREST_MIPMAP_LINEAR() */
  @Override
  public int NEAREST_MIPMAP_LINEAR() {
    return GL11.GL_NEAREST_MIPMAP_LINEAR;
  }

  /* @see GLContext#NEAREST_MIPMAP_NEAREST() */
  @Override
  public int NEAREST_MIPMAP_NEAREST() {
    return GL11.GL_NEAREST_MIPMAP_NEAREST;
  }

  /* @see GLContext#NEVER() */
  @Override
  public int NEVER() {
    return GL11.GL_NEVER;
  }

  /* @see GLContext#NICEST() */
  @Override
  public int NICEST() {
    return GL11.GL_NICEST;
  }

  /* @see GLContext#NO_ERROR() */
  @Override
  public int NO_ERROR() {
    return GL11.GL_NO_ERROR;
  }

  /* @see GLContext#NONE() */
  @Override
  public int NONE() {
    return GL11.GL_NONE;
  }

  /* @see GLContext#NOTEQUAL() */
  @Override
  public int NOTEQUAL() {
    return GL11.GL_NOTEQUAL;
  }

  /* @see GLContext#NUM_COMPRESSED_TEXTURE_FORMATS() */
  @Override
  public int NUM_COMPRESSED_TEXTURE_FORMATS() {
    return GL13.GL_NUM_COMPRESSED_TEXTURE_FORMATS;
  }

  /* @see GLContext#NUM_SHADER_BINARY_FORMATS() */
  @Override
  public int NUM_SHADER_BINARY_FORMATS() {
    return ARBES2Compatibility.GL_NUM_SHADER_BINARY_FORMATS;
  }

  /* @see GLContext#ONE() */
  @Override
  public int ONE() {
    return GL11.GL_ONE;
  }

  /* @see GLContext#ONE_MINUS_CONSTANT_ALPHA() */
  @Override
  public int ONE_MINUS_CONSTANT_ALPHA() {
    return GL11.GL_ONE_MINUS_CONSTANT_ALPHA;
  }

  /* @see GLContext#ONE_MINUS_CONSTANT_COLOR() */
  @Override
  public int ONE_MINUS_CONSTANT_COLOR() {
    return GL11.GL_ONE_MINUS_CONSTANT_COLOR;
  }

  /* @see GLContext#ONE_MINUS_DST_ALPHA() */
  @Override
  public int ONE_MINUS_DST_ALPHA() {
    return GL11.GL_ONE_MINUS_DST_ALPHA;
  }

  /* @see GLContext#ONE_MINUS_DST_COLOR() */
  @Override
  public int ONE_MINUS_DST_COLOR() {
    return GL11.GL_ONE_MINUS_DST_COLOR;
  }

  /* @see GLContext#ONE_MINUS_SRC_ALPHA() */
  @Override
  public int ONE_MINUS_SRC_ALPHA() {
    return GL11.GL_ONE_MINUS_SRC_ALPHA;
  }

  /* @see GLContext#ONE_MINUS_SRC_COLOR() */
  @Override
  public int ONE_MINUS_SRC_COLOR() {
    return GL11.GL_ONE_MINUS_SRC_COLOR;
  }

  /* @see GLContext#OUT_OF_MEMORY() */
  @Override
  public int OUT_OF_MEMORY() {
    return GL11.GL_OUT_OF_MEMORY;
  }

  /* @see GLContext#PACK_ALIGNMENT() */
  @Override
  public int PACK_ALIGNMENT() {
    return GL11.GL_PACK_ALIGNMENT;
  }

  /* @see GLContext#POINTS() */
  @Override
  public int POINTS() {
    return GL11.GL_POINTS;
  }

  /* @see GLContext#POLYGON_OFFSET_FACTOR() */
  @Override
  public int POLYGON_OFFSET_FACTOR() {
    return GL11.GL_POLYGON_OFFSET_FACTOR;
  }

  /* @see GLContext#POLYGON_OFFSET_FILL() */
  @Override
  public int POLYGON_OFFSET_FILL() {
    return GL11.GL_POLYGON_OFFSET_FILL;
  }

  /* @see GLContext#POLYGON_OFFSET_UNITS() */
  @Override
  public int POLYGON_OFFSET_UNITS() {
    return GL11.GL_POLYGON_OFFSET_UNITS;
  }

  /* @see GLContext#RED_BITS() */
  @Override
  public int RED_BITS() {
    return GL11.GL_RED_BITS;
  }

  /* @see GLContext#RENDERBUFFER() */
  @Override
  public int RENDERBUFFER() {
    return EXTFramebufferObject.GL_RENDERBUFFER_EXT;
  }

  /* @see GLContext#RENDERBUFFER_ALPHA_SIZE() */
  @Override
  public int RENDERBUFFER_ALPHA_SIZE() {
    return EXTFramebufferObject.GL_RENDERBUFFER_ALPHA_SIZE_EXT;
  }

  /* @see GLContext#RENDERBUFFER_BINDING() */
  @Override
  public int RENDERBUFFER_BINDING() {
    return EXTFramebufferObject.GL_RENDERBUFFER_BINDING_EXT;
  }

  /* @see GLContext#RENDERBUFFER_BLUE_SIZE() */
  @Override
  public int RENDERBUFFER_BLUE_SIZE() {
    return EXTFramebufferObject.GL_RENDERBUFFER_BLUE_SIZE_EXT;
  }

  /* @see GLContext#RENDERBUFFER_DEPTH_SIZE() */
  @Override
  public int RENDERBUFFER_DEPTH_SIZE() {
    return EXTFramebufferObject.GL_RENDERBUFFER_DEPTH_SIZE_EXT;
  }

  /* @see GLContext#RENDERBUFFER_GREEN_SIZE() */
  @Override
  public int RENDERBUFFER_GREEN_SIZE() {
    return EXTFramebufferObject.GL_RENDERBUFFER_GREEN_SIZE_EXT;
  }

  /* @see GLContext#RENDERBUFFER_HEIGHT() */
  @Override
  public int RENDERBUFFER_HEIGHT() {
    return EXTFramebufferObject.GL_RENDERBUFFER_HEIGHT_EXT;
  }

  /* @see GLContext#RENDERBUFFER_INTERNAL_FORMAT() */
  @Override
  public int RENDERBUFFER_INTERNAL_FORMAT() {
    return EXTFramebufferObject.GL_RENDERBUFFER_INTERNAL_FORMAT_EXT;
  }

  /* @see GLContext#RENDERBUFFER_RED_SIZE() */
  @Override
  public int RENDERBUFFER_RED_SIZE() {
    return EXTFramebufferObject.GL_RENDERBUFFER_RED_SIZE_EXT;
  }

  /* @see GLContext#RENDERBUFFER_STENCIL_SIZE() */
  @Override
  public int RENDERBUFFER_STENCIL_SIZE() {
    return EXTFramebufferObject.GL_RENDERBUFFER_STENCIL_SIZE_EXT;
  }

  /* @see GLContext#RENDERBUFFER_WIDTH() */
  @Override
  public int RENDERBUFFER_WIDTH() {
    return EXTFramebufferObject.GL_RENDERBUFFER_WIDTH_EXT;
  }

  /* @see GLContext#RENDERER() */
  @Override
  public int RENDERER() {
    return GL11.GL_RENDERER;
  }

  /* @see GLContext#REPEAT() */
  @Override
  public int REPEAT() {
    return GL11.GL_REPEAT;
  }

  /* @see GLContext#REPLACE() */
  @Override
  public int REPLACE() {
    return GL11.GL_REPLACE;
  }

  /* @see GLContext#RGB() */
  @Override
  public int RGB() {
    return GL11.GL_RGB;
  }

  /* @see GLContext#RGB5_A1() */
  @Override
  public int RGB5_A1() {
    return GL11.GL_RGB5_A1;
  }

  /* @see GLContext#RGB565() */
  @Override
  public int RGB565() {
    return GL_RGB565;
  }

  /* @see GLContext#RGBA() */
  @Override
  public int RGBA() {
    return GL11.GL_RGBA;
  }

  /* @see GLContext#RGBA4() */
  @Override
  public int RGBA4() {
    return GL11.GL_RGBA4;
  }

  /* @see GLContext#SAMPLE_ALPHA_TO_COVERAGE() */
  @Override
  public int SAMPLE_ALPHA_TO_COVERAGE() {
    return GL13.GL_SAMPLE_ALPHA_TO_COVERAGE;
  }

  /* @see GLContext#SAMPLE_BUFFERS() */
  @Override
  public int SAMPLE_BUFFERS() {
    return GL13.GL_SAMPLE_BUFFERS;
  }

  /* @see GLContext#SAMPLE_COVERAGE() */
  @Override
  public int SAMPLE_COVERAGE() {
    return GL13.GL_SAMPLE_COVERAGE;
  }

  /* @see GLContext#SAMPLE_COVERAGE_INVERT() */
  @Override
  public int SAMPLE_COVERAGE_INVERT() {
    return GL13.GL_SAMPLE_COVERAGE_INVERT;
  }

  /* @see GLContext#SAMPLE_COVERAGE_VALUE() */
  @Override
  public int SAMPLE_COVERAGE_VALUE() {
    return GL13.GL_SAMPLE_COVERAGE_VALUE;
  }

  /* @see GLContext#SAMPLER_2D() */
  @Override
  public int SAMPLER_2D() {
    return GL20.GL_SAMPLER_2D;
  }

  /* @see GLContext#SAMPLER_CUBE() */
  @Override
  public int SAMPLER_CUBE() {
    return GL20.GL_SAMPLER_CUBE;
  }

  /* @see GLContext#SAMPLES() */
  @Override
  public int SAMPLES() {
    return GL13.GL_SAMPLES;
  }

  /* @see GLContext#SCISSOR_BOX() */
  @Override
  public int SCISSOR_BOX() {
    return GL11.GL_SCISSOR_BOX;
  }

  /* @see GLContext#SCISSOR_TEST() */
  @Override
  public int SCISSOR_TEST() {
    return GL11.GL_SCISSOR_TEST;
  }

  /* @see GLContext#SHADER_BINARY_FORMATS() */
  @Override
  public int SHADER_BINARY_FORMATS() {
    return GL_SHADER_BINARY_FORMATS;
  }

  /* @see GLContext#SHADER_COMPILER() */
  @Override
  public int SHADER_COMPILER() {
    return ARBES2Compatibility.GL_SHADER_COMPILER;
  }

  /* @see GLContext#SHADER_SOURCE_LENGTH() */
  @Override
  public int SHADER_SOURCE_LENGTH() {
    return GL20.GL_SHADER_SOURCE_LENGTH;
  }

  /* @see GLContext#SHADER_TYPE() */
  @Override
  public int SHADER_TYPE() {
    return GL20.GL_SHADER_TYPE;
  }

  /* @see GLContext#SHADING_LANGUAGE_VERSION() */
  @Override
  public int SHADING_LANGUAGE_VERSION() {
    return GL20.GL_SHADING_LANGUAGE_VERSION;
  }

  /* @see GLContext#SHORT() */
  @Override
  public int SHORT() {
    return GL11.GL_SHORT;
  }

  /* @see GLContext#SRC_ALPHA() */
  @Override
  public int SRC_ALPHA() {
    return GL11.GL_SRC_ALPHA;
  }

  /* @see GLContext#SRC_ALPHA_SATURATE() */
  @Override
  public int SRC_ALPHA_SATURATE() {
    return GL11.GL_SRC_ALPHA_SATURATE;
  }

  /* @see GLContext#SRC_COLOR() */
  @Override
  public int SRC_COLOR() {
    return GL11.GL_SRC_COLOR;
  }

  /* @see GLContext#STATIC_DRAW() */
  @Override
  public int STATIC_DRAW() {
    return GL15.GL_STATIC_DRAW;
  }

  /* @see GLContext#STENCIL_ATTACHMENT() */
  @Override
  public int STENCIL_ATTACHMENT() {
    return EXTFramebufferObject.GL_STENCIL_ATTACHMENT_EXT;
  }

  /* @see GLContext#STENCIL_BACK_FAIL() */
  @Override
  public int STENCIL_BACK_FAIL() {
    return GL20.GL_STENCIL_BACK_FAIL;
  }

  /* @see GLContext#STENCIL_BACK_FUNC() */
  @Override
  public int STENCIL_BACK_FUNC() {
    return GL20.GL_STENCIL_BACK_FUNC;
  }

  /* @see GLContext#STENCIL_BACK_PASS_DEPTH_FAIL() */
  @Override
  public int STENCIL_BACK_PASS_DEPTH_FAIL() {
    return GL20.GL_STENCIL_BACK_PASS_DEPTH_FAIL;
  }

  /* @see GLContext#STENCIL_BACK_PASS_DEPTH_PASS() */
  @Override
  public int STENCIL_BACK_PASS_DEPTH_PASS() {
    return GL20.GL_STENCIL_BACK_PASS_DEPTH_PASS;
  }

  /* @see GLContext#STENCIL_BACK_REF() */
  @Override
  public int STENCIL_BACK_REF() {
    return GL20.GL_STENCIL_BACK_REF;
  }

  /* @see GLContext#STENCIL_BACK_VALUE_MASK() */
  @Override
  public int STENCIL_BACK_VALUE_MASK() {
    return GL20.GL_STENCIL_BACK_VALUE_MASK;
  }

  /* @see GLContext#STENCIL_BACK_WRITEMASK() */
  @Override
  public int STENCIL_BACK_WRITEMASK() {
    return GL20.GL_STENCIL_BACK_WRITEMASK;
  }

  /* @see GLContext#STENCIL_BITS() */
  @Override
  public int STENCIL_BITS() {
    return GL11.GL_STENCIL_BITS;
  }

  /* @see GLContext#STENCIL_BUFFER_BIT() */
  @Override
  public int STENCIL_BUFFER_BIT() {
    return GL11.GL_STENCIL_BUFFER_BIT;
  }

  /* @see GLContext#STENCIL_CLEAR_VALUE() */
  @Override
  public int STENCIL_CLEAR_VALUE() {
    return GL11.GL_STENCIL_CLEAR_VALUE;
  }

  /* @see GLContext#STENCIL_FAIL() */
  @Override
  public int STENCIL_FAIL() {
    return GL11.GL_STENCIL_FAIL;
  }

  /* @see GLContext#STENCIL_FUNC() */
  @Override
  public int STENCIL_FUNC() {
    return GL11.GL_STENCIL_FUNC;
  }

  /* @see GLContext#STENCIL_INDEX() */
  @Override
  public int STENCIL_INDEX() {
    return GL11.GL_STENCIL_INDEX;
  }

  /* @see GLContext#STENCIL_INDEX8() */
  @Override
  public int STENCIL_INDEX8() {
    return EXTFramebufferObject.GL_STENCIL_INDEX8_EXT;
  }

  /* @see GLContext#STENCIL_PASS_DEPTH_FAIL() */
  @Override
  public int STENCIL_PASS_DEPTH_FAIL() {
    return GL11.GL_STENCIL_PASS_DEPTH_FAIL;
  }

  /* @see GLContext#STENCIL_PASS_DEPTH_PASS() */
  @Override
  public int STENCIL_PASS_DEPTH_PASS() {
    return GL11.GL_STENCIL_PASS_DEPTH_PASS;
  }

  /* @see GLContext#STENCIL_REF() */
  @Override
  public int STENCIL_REF() {
    return GL11.GL_STENCIL_REF;
  }

  /* @see GLContext#STENCIL_TEST() */
  @Override
  public int STENCIL_TEST() {
    return GL11.GL_STENCIL_TEST;
  }

  /* @see GLContext#STENCIL_VALUE_MASK() */
  @Override
  public int STENCIL_VALUE_MASK() {
    return GL11.GL_STENCIL_VALUE_MASK;
  }

  /* @see GLContext#STENCIL_WRITEMASK() */
  @Override
  public int STENCIL_WRITEMASK() {
    return GL11.GL_STENCIL_WRITEMASK;
  }

  /* @see GLContext#STREAM_DRAW() */
  @Override
  public int STREAM_DRAW() {
    return GL15.GL_STREAM_DRAW;
  }

  /* @see GLContext#SUBPIXEL_BITS() */
  @Override
  public int SUBPIXEL_BITS() {
    return GL11.GL_SUBPIXEL_BITS;
  }

  /* @see GLContext#TEXTURE() */
  @Override
  public int TEXTURE() {
    return GL11.GL_TEXTURE;
  }

  /* @see GLContext#TEXTURE_2D() */
  @Override
  public int TEXTURE_2D() {
    return GL11.GL_TEXTURE_2D;
  }

  /* @see GLContext#TEXTURE_BINDING_2D() */
  @Override
  public int TEXTURE_BINDING_2D() {
    return GL11.GL_TEXTURE_BINDING_2D;
  }

  /* @see GLContext#TEXTURE_BINDING_CUBE_MAP() */
  @Override
  public int TEXTURE_BINDING_CUBE_MAP() {
    return GL13.GL_TEXTURE_BINDING_CUBE_MAP;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP() */
  @Override
  public int TEXTURE_CUBE_MAP() {
    return GL13.GL_TEXTURE_CUBE_MAP;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_NEGATIVE_X() */
  @Override
  public int TEXTURE_CUBE_MAP_NEGATIVE_X() {
    return GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_X;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_NEGATIVE_Y() */
  @Override
  public int TEXTURE_CUBE_MAP_NEGATIVE_Y() {
    return GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_NEGATIVE_Z() */
  @Override
  public int TEXTURE_CUBE_MAP_NEGATIVE_Z() {
    return GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_POSITIVE_X() */
  @Override
  public int TEXTURE_CUBE_MAP_POSITIVE_X() {
    return GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_POSITIVE_Y() */
  @Override
  public int TEXTURE_CUBE_MAP_POSITIVE_Y() {
    return GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Y;
  }

  /* @see GLContext#TEXTURE_CUBE_MAP_POSITIVE_Z() */
  @Override
  public int TEXTURE_CUBE_MAP_POSITIVE_Z() {
    return GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Z;
  }

  /* @see GLContext#TEXTURE_MAG_FILTER() */
  @Override
  public int TEXTURE_MAG_FILTER() {
    return GL11.GL_TEXTURE_MAG_FILTER;
  }

  /* @see GLContext#TEXTURE_MIN_FILTER() */
  @Override
  public int TEXTURE_MIN_FILTER() {
    return GL11.GL_TEXTURE_MIN_FILTER;
  }

  /* @see GLContext#TEXTURE_WRAP_S() */
  @Override
  public int TEXTURE_WRAP_S() {
    return GL11.GL_TEXTURE_WRAP_S;
  }

  /* @see GLContext#TEXTURE_WRAP_T() */
  @Override
  public int TEXTURE_WRAP_T() {
    return GL11.GL_TEXTURE_WRAP_T;
  }

  /* @see GLContext#TEXTURE0() */
  @Override
  public int TEXTURE0() {
    return GL13.GL_TEXTURE0;
  }

  /* @see GLContext#TEXTURE1() */
  @Override
  public int TEXTURE1() {
    return GL13.GL_TEXTURE1;
  }

  /* @see GLContext#TEXTURE2() */
  @Override
  public int TEXTURE2() {
    return GL13.GL_TEXTURE2;
  }

  /* @see GLContext#TEXTURE3() */
  @Override
  public int TEXTURE3() {
    return GL13.GL_TEXTURE3;
  }

  /* @see GLContext#TEXTURE4() */
  @Override
  public int TEXTURE4() {
    return GL13.GL_TEXTURE4;
  }

  /* @see GLContext#TEXTURE5() */
  @Override
  public int TEXTURE5() {
    return GL13.GL_TEXTURE5;
  }

  /* @see GLContext#TEXTURE6() */
  @Override
  public int TEXTURE6() {
    return GL13.GL_TEXTURE6;
  }

  /* @see GLContext#TEXTURE7() */
  @Override
  public int TEXTURE7() {
    return GL13.GL_TEXTURE7;
  }

  /* @see GLContext#TEXTURE8() */
  @Override
  public int TEXTURE8() {
    return GL13.GL_TEXTURE8;
  }

  /* @see GLContext#TEXTURE9() */
  @Override
  public int TEXTURE9() {
    return GL13.GL_TEXTURE9;
  }

  /* @see GLContext#TEXTURE10() */
  @Override
  public int TEXTURE10() {
    return GL13.GL_TEXTURE10;
  }

  /* @see GLContext#TEXTURE11() */
  @Override
  public int TEXTURE11() {
    return GL13.GL_TEXTURE11;
  }

  /* @see GLContext#TEXTURE12() */
  @Override
  public int TEXTURE12() {
    return GL13.GL_TEXTURE12;
  }

  /* @see GLContext#TEXTURE13() */
  @Override
  public int TEXTURE13() {
    return GL13.GL_TEXTURE13;
  }

  /* @see GLContext#TEXTURE14() */
  @Override
  public int TEXTURE14() {
    return GL13.GL_TEXTURE14;
  }

  /* @see GLContext#TEXTURE15() */
  @Override
  public int TEXTURE15() {
    return GL13.GL_TEXTURE15;
  }

  /* @see GLContext#TEXTURE16() */
  @Override
  public int TEXTURE16() {
    return GL13.GL_TEXTURE16;
  }

  /* @see GLContext#TEXTURE17() */
  @Override
  public int TEXTURE17() {
    return GL13.GL_TEXTURE17;
  }

  /* @see GLContext#TEXTURE18() */
  @Override
  public int TEXTURE18() {
    return GL13.GL_TEXTURE18;
  }

  /* @see GLContext#TEXTURE19() */
  @Override
  public int TEXTURE19() {
    return GL13.GL_TEXTURE19;
  }

  /* @see GLContext#TEXTURE20() */
  @Override
  public int TEXTURE20() {
    return GL13.GL_TEXTURE20;
  }

  /* @see GLContext#TEXTURE21() */
  @Override
  public int TEXTURE21() {
    return GL13.GL_TEXTURE21;
  }

  /* @see GLContext#TEXTURE22() */
  @Override
  public int TEXTURE22() {
    return GL13.GL_TEXTURE22;
  }

  /* @see GLContext#TEXTURE23() */
  @Override
  public int TEXTURE23() {
    return GL13.GL_TEXTURE23;
  }

  /* @see GLContext#TEXTURE24() */
  @Override
  public int TEXTURE24() {
    return GL13.GL_TEXTURE24;
  }

  /* @see GLContext#TEXTURE25() */
  @Override
  public int TEXTURE25() {
    return GL13.GL_TEXTURE25;
  }

  /* @see GLContext#TEXTURE26() */
  @Override
  public int TEXTURE26() {
    return GL13.GL_TEXTURE26;
  }

  /* @see GLContext#TEXTURE27() */
  @Override
  public int TEXTURE27() {
    return GL13.GL_TEXTURE27;
  }

  /* @see GLContext#TEXTURE28() */
  @Override
  public int TEXTURE28() {
    return GL13.GL_TEXTURE28;
  }

  /* @see GLContext#TEXTURE29() */
  @Override
  public int TEXTURE29() {
    return GL13.GL_TEXTURE29;
  }

  /* @see GLContext#TEXTURE30() */
  @Override
  public int TEXTURE30() {
    return GL13.GL_TEXTURE30;
  }

  /* @see GLContext#TEXTURE31() */
  @Override
  public int TEXTURE31() {
    return GL13.GL_TEXTURE31;
  }

  /* @see GLContext#TRIANGLE_FAN() */
  @Override
  public int TRIANGLE_FAN() {
    return GL11.GL_TRIANGLE_FAN;
  }

  /* @see GLContext#TRIANGLE_STRIP() */
  @Override
  public int TRIANGLE_STRIP() {
    return GL11.GL_TRIANGLE_STRIP;
  }

  /* @see GLContext#TRIANGLES() */
  @Override
  public int TRIANGLES() {
    return GL11.GL_TRIANGLES;
  }

  /* @see GLContext#TRUE() */
  @Override
  public int TRUE() {
    return GL11.GL_TRUE;
  }

  /* @see GLContext#UNPACK_ALIGNMENT() */
  @Override
  public int UNPACK_ALIGNMENT() {
    return GL11.GL_UNPACK_ALIGNMENT;
  }

  /* @see GLContext#UNSIGNED_BYTE() */
  @Override
  public int UNSIGNED_BYTE() {
    return GL11.GL_UNSIGNED_BYTE;
  }

  /* @see GLContext#UNSIGNED_INT() */
  @Override
  public int UNSIGNED_INT() {
    return GL11.GL_UNSIGNED_INT;
  }

  /* @see GLContext#UNSIGNED_SHORT() */
  @Override
  public int UNSIGNED_SHORT() {
    return GL11.GL_UNSIGNED_SHORT;
  }

  /* @see GLContext#UNSIGNED_SHORT_4_4_4_4() */
  @Override
  public int UNSIGNED_SHORT_4_4_4_4() {
    return GL12.GL_UNSIGNED_SHORT_4_4_4_4;
  }

  /* @see GLContext#UNSIGNED_SHORT_5_5_5_1() */
  @Override
  public int UNSIGNED_SHORT_5_5_5_1() {
    return GL12.GL_UNSIGNED_SHORT_5_5_5_1;
  }

  /* @see GLContext#UNSIGNED_SHORT_5_6_5() */
  @Override
  public int UNSIGNED_SHORT_5_6_5() {
    return GL12.GL_UNSIGNED_SHORT_5_6_5;
  }

  /* @see GLContext#VALIDATE_STATUS() */
  @Override
  public int VALIDATE_STATUS() {
    return GL20.GL_VALIDATE_STATUS;
  }

  /* @see GLContext#VENDOR() */
  @Override
  public int VENDOR() {
    return GL11.GL_VENDOR;
  }

  /* @see GLContext#VERSION() */
  @Override
  public int VERSION() {
    return GL11.GL_VERSION;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_BUFFER_BINDING() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_BUFFER_BINDING() {
    return GL15.GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_ENABLED() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_ENABLED() {
    return GL20.GL_VERTEX_ATTRIB_ARRAY_ENABLED;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_NORMALIZED() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_NORMALIZED() {
    return GL20.GL_VERTEX_ATTRIB_ARRAY_NORMALIZED;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_POINTER() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_POINTER() {
    return GL20.GL_VERTEX_ATTRIB_ARRAY_POINTER;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_SIZE() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_SIZE() {
    return GL20.GL_VERTEX_ATTRIB_ARRAY_SIZE;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_STRIDE() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_STRIDE() {
    return GL20.GL_VERTEX_ATTRIB_ARRAY_STRIDE;
  }

  /* @see GLContext#VERTEX_ATTRIB_ARRAY_TYPE() */
  @Override
  public int VERTEX_ATTRIB_ARRAY_TYPE() {
    return GL20.GL_VERTEX_ATTRIB_ARRAY_TYPE;
  }

  /* @see GLContext#VERTEX_SHADER() */
  @Override
  public int VERTEX_SHADER() {
    return GL20.GL_VERTEX_SHADER;
  }

  /* @see GLContext#VIEWPORT() */
  @Override
  public int VIEWPORT() {
    return GL11.GL_VIEWPORT;
  }

  /* @see GLContext#ZERO() */
  @Override
  public int ZERO() {
    return GL11.GL_ZERO;
  }

  //
  // Implementations of the OpenGL functions.
  //

  /* @see GLContext#activeTexture(int) */
  @Override
  public void activeTexture(int texture) {
    GL13.glActiveTexture(texture);
  }

  /* @see GLContext#attachShader(int, int) */
  @Override
  public void attachShader(int program, int shader) {
    GL20.glAttachShader(program, shader);
  }

  /* @see GLContext#bindAttribLocation(int, int, String) */
  @Override
  public void bindAttribLocation(int program, int index, String name) {
    GL20.glBindAttribLocation(program, index, name);
  }

  /* @see GLContext#bindBuffer(int, int) */
  @Override
  public void bindBuffer(int target, int buffer) {
    GL15.glBindBuffer(target, buffer);
  }

  /* @see GLContext#bindFramebuffer(int, int) */
  @Override
  public void bindFramebuffer(int target, int framebuffer) {
    EXTFramebufferObject.glBindFramebufferEXT(target, framebuffer);
  }

  /* @see GLContext#bindRenderbuffer(int, int) */
  @Override
  public void bindRenderbuffer(int target, int renderbuffer) {
    EXTFramebufferObject.glBindRenderbufferEXT(target, renderbuffer);
  }

  /* @see GLContext#bindTexture(int, int) */
  @Override
  public void bindTexture(int target, int texture) {
    GL11.glBindTexture(target, texture);
  }

  /* @see GLContext#blendColor(float, float, float, float) */
  @Override
  public void blendColor(float red, float green, float blue, float alpha) {
    GL14.glBlendColor(red, green, blue, alpha);
  }

  /* @see GLContext#blendEquation(int) */
  @Override
  public void blendEquation(int mode) {
    GL14.glBlendEquation(mode);
  }

  /* @see GLContext#blendEquationSeparate(int, int) */
  @Override
  public void blendEquationSeparate(int modeRGB, int modeAlpha) {
    GL20.glBlendEquationSeparate(modeRGB, modeAlpha);
  }

  /* @see GLContext#blendFunc(int, int) */
  @Override
  public void blendFunc(int sfactor, int dfactor) {
    GL11.glBlendFunc(sfactor, dfactor);
  }

  /* @see GLContext#blendFuncSeparate(int, int, int, int) */
  @Override
  public void blendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
    GL14.glBlendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
  }

  /* @see GLContext#bufferData(int, int, int) */
  @Override
  public void bufferData(int target, int size, int usage) {
    GL15.glBufferData(target, size, usage);
  }

  /* @see GLContext#bufferData(int, GLBytes, int) */
  @Override
  public void bufferData(int target, GLBytes data, int usage) {
    assert data instanceof DesktopGLBytes;
    DesktopGLBytes bytes = (DesktopGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      GL15.glBufferData(target, buffer, usage);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#bufferSubData(int, int, GLBytes) */
  @Override
  public void bufferSubData(int target, int offset, GLBytes data) {
    assert data instanceof DesktopGLBytes;
    DesktopGLBytes bytes = (DesktopGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      GL15.glBufferSubData(target, offset, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#checkFramebufferStatus(int) */
  @Override
  public int checkFramebufferStatus(int target) {
    return EXTFramebufferObject.glCheckFramebufferStatusEXT(target);
  }

  /* @see GLContext#clear(int) */
  @Override
  public void clear(int mask) {
    GL11.glClear(mask);
  }

  /* @see GLContext#clearColor(float, float, float, float) */
  @Override
  public void clearColor(float red, float green, float blue, float alpha) {
    GL11.glClearColor(red, green, blue, alpha);
  }

  /* @see GLContext#clearDepthf(float) */
  @Override
  public void clearDepthf(float depth) {
    ARBES2Compatibility.glClearDepthf(depth);
  }

  /* @see GLContext#clearStencil(int) */
  @Override
  public void clearStencil(int s) {
    GL11.glClearStencil(s);
  }

  /* @see GLContext#colorMask(boolean, boolean, boolean, boolean) */
  @Override
  public void colorMask(boolean red, boolean green, boolean blue, boolean alpha) {
    GL11.glColorMask(red, green, blue, alpha);
  }

  /* @see GLContext#compileShader(int) */
  @Override
  public void compileShader(int shader) {
    GL20.glCompileShader(shader);
  }

  /* @see GLContext#compressedTexImage2D(int, int, int, int, int, int, GLBytes) */
  @Override
  public void compressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
      GLBytes data) {
    assert data instanceof DesktopGLBytes;
    DesktopGLBytes bytes = (DesktopGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, buffer);
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
    assert data instanceof DesktopGLBytes;
    DesktopGLBytes bytes = (DesktopGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#copyTexImage2D(int, int, int, int, int, int, int, int) */
  @Override
  public void copyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border) {
    GL11.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
  }

  /* @see GLContext#copyTexSubImage2D(int, int, int, int, int, int, int, int) */
  @Override
  public void copyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
    GL11.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
  }

  /* @see GLContext#createProgram() */
  @Override
  public int createProgram() {
    return GL20.glCreateProgram();
  }

  /* @see GLContext#createShader(int) */
  @Override
  public int createShader(int shaderType) {
    return GL20.glCreateShader(shaderType);
  }

  /* @see GLContext#cullFace(int) */
  @Override
  public void cullFace(int mode) {
    GL11.glCullFace(mode);
  }

  /* @see GLContext#deleteBuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteBuffers(GLIntegers buffers) {
    assert buffers instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) buffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL15.glDeleteBuffers(buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#deleteFramebuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteFramebuffers(GLIntegers framebuffers) {
    assert framebuffers instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) framebuffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      EXTFramebufferObject.glDeleteFramebuffersEXT(buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#deleteProgram(int) */
  @Override
  public void deleteProgram(int program) {
    GL20.glDeleteProgram(program);
  }

  /* @see GLContext#deleteRenderbuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteRenderbuffers(GLIntegers renderbuffers) {
    assert renderbuffers instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) renderbuffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      EXTFramebufferObject.glDeleteRenderbuffersEXT(buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#deleteShader(int) */
  @Override
  public void deleteShader(int shader) {
    GL20.glDeleteShader(shader);
  }

  /* @see GLContext#deleteTextures(fulcrum.opengl.GLIntegers) */
  @Override
  public void deleteTextures(GLIntegers textures) {
    assert textures instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) textures;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL11.glDeleteTextures(buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#depthFunc(int) */
  @Override
  public void depthFunc(int func) {
    GL11.glDepthFunc(func);
  }

  /* @see GLContext#depthMask(boolean) */
  @Override
  public void depthMask(boolean flag) {
    GL11.glDepthMask(flag);
  }

  /* @see GLContext#depthRangef(float, float) */
  @Override
  public void depthRangef(float nearVal, float farVal) {
    ARBES2Compatibility.glDepthRangef(nearVal, farVal);
  }

  /* @see GLContext#detachShader(int, int) */
  @Override
  public void detachShader(int program, int shader) {
    GL20.glDetachShader(program, shader);
  }

  /* @see GLContext#disable(int) */
  @Override
  public void disable(int cap) {
    GL11.glDisable(cap);
  }

  /* @see GLContext#disableVertexAttribArray(int) */
  @Override
  public void disableVertexAttribArray(int index) {
    GL20.glDisableVertexAttribArray(index);
  }

  /* @see GLContext#drawArrays(int, int, int) */
  @Override
  public void drawArrays(int mode, int first, int count) {
    GL11.glDrawArrays(mode, first, count);
  }

  /* @see GLContext#drawElements(int, int, int, int) */
  @Override
  public void drawElements(int mode, int count, int type, int offset) {
    GL11.glDrawElements(mode, count, type, offset);
  }

  /* @see GLContext#drawElements(int, GLBytes) */
  @Override
  public void drawElements(int mode, GLBytes indices) {
    assert indices instanceof DesktopGLBytes;
    DesktopGLBytes bytes = (DesktopGLBytes) indices;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      GL11.glDrawElements(mode, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#drawElements(int, GLShorts) */
  @Override
  public void drawElements(int mode, GLShorts indices) {
    assert indices instanceof DesktopGLShorts;
    DesktopGLShorts shorts = (DesktopGLShorts) indices;
    ShortBuffer buffer = shorts.acquireBuffer();
    try {
      GL11.glDrawElements(mode, buffer);
    } finally {
      shorts.releaseBuffer();
    }
  }

  /* @see GLContext#enable(int) */
  @Override
  public void enable(int cap) {
    GL11.glEnable(cap);
  }

  /* @see GLContext#enableVertexAttribArray(int) */
  @Override
  public void enableVertexAttribArray(int index) {
    GL20.glEnableVertexAttribArray(index);
  }

  /* @see GLContext#finish() */
  @Override
  public void finish() {
    GL11.glFinish();
  }

  /* @see GLContext#flush() */
  @Override
  public void flush() {
    GL11.glFlush();
  }

  /* @see GLContext#framebufferRenderbuffer(int, int, int, int) */
  @Override
  public void framebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
    EXTFramebufferObject.glFramebufferRenderbufferEXT(target, attachment, renderbuffertarget, renderbuffer);
  }

  /* @see GLContext#framebufferTexture2D(int, int, int, int, int) */
  @Override
  public void framebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
    EXTFramebufferObject.glFramebufferTexture2DEXT(target, attachment, textarget, texture, level);
  }

  /* @see GLContext#frontFace(int) */
  @Override
  public void frontFace(int mode) {
    GL11.glFrontFace(mode);
  }

  /* @see GLContext#genBuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void genBuffers(GLIntegers buffers) {
    assert buffers instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) buffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL15.glGenBuffers(buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#genFramebuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void genFramebuffers(GLIntegers framebuffers) {
    assert framebuffers instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) framebuffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      EXTFramebufferObject.glGenFramebuffersEXT(buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#genRenderbuffers(fulcrum.opengl.GLIntegers) */
  @Override
  public void genRenderbuffers(GLIntegers renderbuffers) {
    assert renderbuffers instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) renderbuffers;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      EXTFramebufferObject.glGenRenderbuffersEXT(buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#genTextures(fulcrum.opengl.GLIntegers) */
  @Override
  public void genTextures(GLIntegers textures) {
    assert textures instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) textures;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL11.glGenTextures(buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#generateMipmap(int) */
  @Override
  public void generateMipmap(int target) {
    EXTFramebufferObject.glGenerateMipmapEXT(target);
  }

  /* @see GLContext#getBooleanv(int) */
  @Override
  public boolean[] getBooleanv(int pname) {
    boolean[] array = new boolean[parameterSize(pname)];
    GL11.glGetBoolean(pname, byteBuffer);
    for (int i = 0; i < array.length; ++i)
      if (byteBuffer.get(i) != 0)
        array[i] = true;
    return array;
  }

  /* @see GLContext#getFloatv(int) */
  @Override
  public float[] getFloatv(int pname) {
    float[] array = new float[parameterSize(pname)];
    FloatBuffer buffer = byteBuffer.asFloatBuffer();
    GL11.glGetFloat(pname, buffer);
    buffer.get(array);
    return array;
  }

  /* @see GLContext#getIntegerv(int) */
  @Override
  public int[] getIntegerv(int pname) {
    int[] array = new int[parameterSize(pname)];
    IntBuffer buffer = byteBuffer.asIntBuffer();
    GL11.glGetInteger(pname, buffer);
    buffer.get(array);
    return array;
  }

  /** Returns the number of data elements for the specified parameter. */
  private int parameterSize(int pname) {
    switch (pname) {
    case GL12.GL_ALIASED_LINE_WIDTH_RANGE:
      return 2;
    case GL12.GL_ALIASED_POINT_SIZE_RANGE:
      return 2;
    case GL14.GL_BLEND_COLOR:
      return 4;
    case GL11.GL_COLOR_CLEAR_VALUE:
      return 4;
    case GL11.GL_COLOR_WRITEMASK:
      return 4;
    case GL13.GL_COMPRESSED_TEXTURE_FORMATS:
      return getIntegerv(GL13.GL_NUM_COMPRESSED_TEXTURE_FORMATS)[0];
    case GL11.GL_DEPTH_RANGE:
      return 2;
    case GL11.GL_MAX_VIEWPORT_DIMS:
      return 2;
    case GL11.GL_SCISSOR_BOX:
      return 4;
    case GL_SHADER_BINARY_FORMATS:
      return getIntegerv(ARBES2Compatibility.GL_NUM_SHADER_BINARY_FORMATS)[0];
    case GL11.GL_VIEWPORT:
      return 4;
    default:
      return 1;
    }
  }

  /* @see GLContext#getActiveAttrib(int, int) */
  @Override
  public GLVariable getActiveAttrib(int program, int index) {
    IntBuffer buffer = byteBuffer.asIntBuffer();
    String name = GL20.glGetActiveAttrib(program, index, 256, buffer);
    return new GLVariable(name, buffer.get(0), buffer.get(1));
  }

  /* @see GLContext#getActiveUniform(int, int) */
  @Override
  public GLVariable getActiveUniform(int program, int index) {
    IntBuffer buffer = byteBuffer.asIntBuffer();
    String name = GL20.glGetActiveUniform(program, index, 256, buffer);
    return new GLVariable(name, buffer.get(0), buffer.get(1));
  }

  /* @see GLContext#getAttachedShaders(int) */
  @Override
  public int[] getAttachedShaders(int program) {
    int[] array = new int[getProgramiv(program, GL20.GL_ATTACHED_SHADERS)[0]];
    IntBuffer buffer = new DesktopGLIntegers(array.length).acquireBuffer();
    GL20.glGetAttachedShaders(program, byteBuffer.asIntBuffer(), buffer);
    buffer.get(array);
    return array;
  }

  /* @see GLContext#getAttribLocation(int, String) */
  @Override
  public int getAttribLocation(int program, String name) {
    return GL20.glGetAttribLocation(program, name);
  }

  /* @see GLContext#getBufferParameteriv(int, int) */
  @Override
  public int[] getBufferParameteriv(int target, int value) {
    return new int[] { GL15.glGetBufferParameteri(target, value) };
  }

  /* @see GLContext#getError() */
  @Override
  public int getError() {
    return GL11.glGetError();
  }

  /* @see GLContext#getFramebufferAttachmentParameteriv(int, int, int) */
  @Override
  public int[] getFramebufferAttachmentParameteriv(int target, int attachment, int pname) {
    return new int[] { EXTFramebufferObject.glGetFramebufferAttachmentParameteriEXT(target, attachment, pname) };
  }

  /* @see GLContext#getProgramInfoLog(int) */
  @Override
  public String getProgramInfoLog(int program) {
    return GL20.glGetProgramInfoLog(program, getProgramiv(program, GL20.GL_INFO_LOG_LENGTH)[0]);
  }

  /* @see GLContext#getProgramiv(int, int) */
  @Override
  public int[] getProgramiv(int program, int pname) {
    return new int[] { GL20.glGetProgrami(program, pname) };
  }

  /* @see GLContext#getRenderbufferParameteriv(int, int) */
  @Override
  public int[] getRenderbufferParameteriv(int target, int pname) {
    return new int[] { EXTFramebufferObject.glGetRenderbufferParameteriEXT(target, pname) };
  }

  /* @see GLContext#getShaderInfoLog(int) */
  @Override
  public String getShaderInfoLog(int shader) {
    return GL20.glGetShaderInfoLog(shader, getShaderiv(shader, GL20.GL_INFO_LOG_LENGTH)[0]);
  }

  /* @see GLContext#getShaderPrecisionFormat(int, int) */
  @Override
  public GLPrecision getShaderPrecisionFormat(int shaderType, int precisionType) {
    ARBES2Compatibility.glGetShaderPrecisionFormat(shaderType, precisionType, intBufferA, intBufferB);
    return new GLPrecision(intBufferA.get(0), intBufferA.get(1), intBufferB.get(0));
  }

  /* @see GLContext#getShaderSource(int) */
  @Override
  public String getShaderSource(int shader) {
    return GL20.glGetShaderSource(shader, getShaderiv(shader, GL20.GL_SHADER_SOURCE_LENGTH)[0]);
  }

  /* @see GLContext#getShaderiv(int, int) */
  @Override
  public int[] getShaderiv(int shader, int pname) {
    return new int[] { GL20.glGetShaderi(shader, pname) };
  }

  /* @see GLContext#getString(int) */
  @Override
  public String getString(int name) {
    return GL11.glGetString(name);
  }

  /* @see GLContext#getTexParameterfv(int, int) */
  @Override
  public float[] getTexParameterfv(int target, int pname) {
    return new float[] { GL11.glGetTexParameterf(target, pname) };
  }

  /* @see GLContext#getTexParameteriv(int, int) */
  @Override
  public int[] getTexParameteriv(int target, int pname) {
    return new int[] { GL11.glGetTexParameteri(target, pname) };
  }

  /* @see GLContext#getUniformfv(int, int, GLFloats) */
  @Override
  public void getUniformfv(int program, int location, GLFloats params) {
    assert params instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) params;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL20.glGetUniform(program, location, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#getUniformiv(int, int, GLIntegers) */
  @Override
  public void getUniformiv(int program, int location, GLIntegers params) {
    assert params instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) params;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL20.glGetUniform(program, location, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#getUniformLocation(int, String) */
  @Override
  public int getUniformLocation(int program, String name) {
    return GL20.glGetUniformLocation(program, name);
  }

  /* @see GLContext#getVertexAttribfv(int, int, GLFloats) */
  @Override
  public void getVertexAttribfv(int index, int pname, GLFloats params) {
    assert params instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) params;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL20.glGetVertexAttrib(index, pname, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#getVertexAttribiv(int, int, GLIntegers) */
  @Override
  public void getVertexAttribiv(int index, int pname, GLIntegers params) {
    assert params instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) params;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL20.glGetVertexAttrib(index, pname, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#hint(int, int) */
  @Override
  public void hint(int target, int mode) {
    GL11.glHint(target, mode);
  }

  /* @see GLContext#isBuffer(int) */
  @Override
  public boolean isBuffer(int buffer) {
    return GL15.glIsBuffer(buffer);
  }

  /* @see GLContext#isEnabled(int) */
  @Override
  public boolean isEnabled(int cap) {
    return GL11.glIsEnabled(cap);
  }

  /* @see GLContext#isFramebuffer(int) */
  @Override
  public boolean isFramebuffer(int framebuffer) {
    return EXTFramebufferObject.glIsFramebufferEXT(framebuffer);
  }

  /* @see GLContext#isProgram(int) */
  @Override
  public boolean isProgram(int program) {
    return GL20.glIsProgram(program);
  }

  /* @see GLContext#isRenderbuffer(int) */
  @Override
  public boolean isRenderbuffer(int renderbuffer) {
    return EXTFramebufferObject.glIsRenderbufferEXT(renderbuffer);
  }

  /* @see GLContext#isShader(int) */
  @Override
  public boolean isShader(int shader) {
    return GL20.glIsShader(shader);
  }

  /* @see GLContext#isTexture(int) */
  @Override
  public boolean isTexture(int texture) {
    return GL11.glIsTexture(texture);
  }

  /* @see GLContext#lineWidth(float) */
  @Override
  public void lineWidth(float width) {
    GL11.glLineWidth(width);
  }

  /* @see GLContext#linkProgram(int) */
  @Override
  public void linkProgram(int program) {
    GL20.glLinkProgram(program);
  }

  /* @see GLContext#pixelStorei(int, int) */
  @Override
  public void pixelStorei(int pname, int param) {
    GL11.glPixelStorei(pname, param);
  }

  /* @see GLContext#polygonOffset(float, float) */
  @Override
  public void polygonOffset(float factor, float units) {
    GL11.glPolygonOffset(factor, units);
  }

  /* @see GLContext#readPixels(int, int, int, int, int, GLBytes) */
  @Override
  public void readPixels(int x, int y, int width, int height, int format, GLBytes data) {
    assert data instanceof DesktopGLBytes;
    DesktopGLBytes bytes = (DesktopGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      GL11.glReadPixels(x, y, width, height, format, GL11.GL_UNSIGNED_BYTE, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#readPixels(int, int, int, int, int, int, GLShorts) */
  @Override
  public void readPixels(int x, int y, int width, int height, int format, int type, GLShorts data) {
    assert data instanceof DesktopGLShorts;
    DesktopGLShorts shorts = (DesktopGLShorts) data;
    ShortBuffer buffer = shorts.acquireBuffer();
    try {
      GL11.glReadPixels(x, y, width, height, format, type, buffer);
    } finally {
      shorts.releaseBuffer();
    }
  }

  /* @see GLContext#releaseShaderCompiler() */
  @Override
  public void releaseShaderCompiler() {
    ARBES2Compatibility.glReleaseShaderCompiler();
  }

  /* @see GLContext#renderbufferStorage(int, int, int, int) */
  @Override
  public void renderbufferStorage(int target, int internalformat, int width, int height) {
    EXTFramebufferObject.glRenderbufferStorageEXT(target, internalformat, width, height);
  }

  /* @see GLContext#sampleCoverage(float, boolean) */
  @Override
  public void sampleCoverage(float value, boolean invert) {
    GL13.glSampleCoverage(value, invert);
  }

  /* @see GLContext#scissor(int, int, int, int) */
  @Override
  public void scissor(int x, int y, int width, int height) {
    GL11.glScissor(x, y, width, height);
  }

  /* @see GLContext#shaderBinary(fulcrum.opengl.GLIntegers, int, GLBytes) */
  @Override
  public void shaderBinary(GLIntegers shaders, int binaryformat, GLBytes binary) {
    assert shaders instanceof DesktopGLIntegers;
    assert binary instanceof DesktopGLBytes;
    DesktopGLIntegers integers = (DesktopGLIntegers) shaders;
    DesktopGLBytes bytes = (DesktopGLBytes) binary;
    IntBuffer bufferA = integers.acquireBuffer();
    try {
      ByteBuffer bufferB = bytes.acquireBuffer();
      try {
        ARBES2Compatibility.glShaderBinary(bufferA, binaryformat, bufferB);
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
    GL20.glShaderSource(shader, string);
  }

  /* @see GLContext#stencilFunc(int, int, int) */
  @Override
  public void stencilFunc(int func, int ref, int mask) {
    GL11.glStencilFunc(func, ref, mask);
  }

  /* @see GLContext#stencilFuncSeparate(int, int, int, int) */
  @Override
  public void stencilFuncSeparate(int face, int func, int ref, int mask) {
    GL20.glStencilFuncSeparate(face, func, ref, mask);
  }

  /* @see GLContext#stencilMask(int) */
  @Override
  public void stencilMask(int mask) {
    GL11.glStencilMask(mask);
  }

  /* @see GLContext#stencilMaskSeparate(int, int) */
  @Override
  public void stencilMaskSeparate(int face, int mask) {
    GL20.glStencilMaskSeparate(face, mask);
  }

  /* @see GLContext#stencilOp(int, int, int) */
  @Override
  public void stencilOp(int sfail, int dpfail, int dppass) {
    GL11.glStencilOp(sfail, dpfail, dppass);
  }

  /* @see GLContext#stencilOpSeparate(int, int, int, int) */
  @Override
  public void stencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
    GL20.glStencilOpSeparate(face, sfail, dpfail, dppass);
  }

  /* @see GLContext#texImage2D(int, int, int, int, int, int, int, GLBytes) */
  @Override
  public void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
      GLBytes data) {
    assert data instanceof DesktopGLBytes;
    DesktopGLBytes bytes = (DesktopGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      GL11.glTexImage2D(target, level, internalformat, width, height, border, internalformat, GL11.GL_UNSIGNED_BYTE,
          buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#texImage2D(int, int, int, int, int, int, int, int, GLShorts) */
  @Override
  public void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
      int type, GLShorts data) {
    assert data instanceof DesktopGLShorts;
    DesktopGLShorts shorts = (DesktopGLShorts) data;
    ShortBuffer buffer = shorts.acquireBuffer();
    try {
      GL11.glTexImage2D(target, level, internalformat, width, height, border, internalformat, type, buffer);
    } finally {
      shorts.releaseBuffer();
    }
  }

  /* @see GLContext#texParameterf(int, int, float) */
  @Override
  public void texParameterf(int target, int pname, float param) {
    GL11.glTexParameterf(target, pname, param);
  }

  /* @see GLContext#texParameteri(int, int, int) */
  @Override
  public void texParameteri(int target, int pname, int param) {
    GL11.glTexParameteri(target, pname, param);
  }

  /* @see GLContext#texParameterfv(int, int, GLFloats) */
  @Override
  public void texParameterfv(int target, int pname, GLFloats params) {
    assert params instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) params;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL11.glTexParameter(target, pname, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#texParameteriv(int, int, GLIntegers) */
  @Override
  public void texParameteriv(int target, int pname, GLIntegers params) {
    assert params instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) params;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL11.glTexParameter(target, pname, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#texSubImage2D(int, int, int, int, int, int, int, GLBytes) */
  @Override
  public void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
      GLBytes data) {
    assert data instanceof DesktopGLBytes;
    DesktopGLBytes bytes = (DesktopGLBytes) data;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, GL11.GL_UNSIGNED_BYTE, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#texSubImage2D(int, int, int, int, int, int, int, int, GLShorts) */
  @Override
  public void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
      int type, GLShorts data) {
    assert data instanceof DesktopGLShorts;
    DesktopGLShorts shorts = (DesktopGLShorts) data;
    ShortBuffer buffer = shorts.acquireBuffer();
    try {
      GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, buffer);
    } finally {
      shorts.releaseBuffer();
    }
  }

  /* @see GLContext#uniform1f(int, float) */
  @Override
  public void uniform1f(int location, float v0) {
    GL20.glUniform1f(location, v0);
  }

  /* @see GLContext#uniform2f(int, float, float) */
  @Override
  public void uniform2f(int location, float v0, float v1) {
    GL20.glUniform2f(location, v0, v1);
  }

  /* @see GLContext#uniform3f(int, float, float, float) */
  @Override
  public void uniform3f(int location, float v0, float v1, float v2) {
    GL20.glUniform3f(location, v0, v1, v2);
  }

  /* @see GLContext#uniform4f(int, float, float, float, float) */
  @Override
  public void uniform4f(int location, float v0, float v1, float v2, float v3) {
    GL20.glUniform4f(location, v0, v1, v2, v3);
  }

  /* @see GLContext#uniform1i(int, int) */
  @Override
  public void uniform1i(int location, int v0) {
    GL20.glUniform1i(location, v0);
  }

  /* @see GLContext#uniform2i(int, int, int) */
  @Override
  public void uniform2i(int location, int v0, int v1) {
    GL20.glUniform2i(location, v0, v1);
  }

  /* @see GLContext#uniform3i(int, int, int, int) */
  @Override
  public void uniform3i(int location, int v0, int v1, int v2) {
    GL20.glUniform3i(location, v0, v1, v2);
  }

  /* @see GLContext#uniform4i(int, int, int, int, int) */
  @Override
  public void uniform4i(int location, int v0, int v1, int v2, int v3) {
    GL20.glUniform4i(location, v0, v1, v2, v3);
  }

  /* @see GLContext#uniform1fv(int, int, GLFloats) */
  @Override
  public void uniform1fv(int location, GLFloats value) {
    assert value instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL20.glUniform1(location, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniform2fv(int, int, GLFloats) */
  @Override
  public void uniform2fv(int location, GLFloats value) {
    assert value instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL20.glUniform2(location, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniform3fv(int, int, GLFloats) */
  @Override
  public void uniform3fv(int location, GLFloats value) {
    assert value instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL20.glUniform3(location, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniform4fv(int, int, GLFloats) */
  @Override
  public void uniform4fv(int location, GLFloats value) {
    assert value instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL20.glUniform4(location, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniform1iv(int, int, GLIntegers) */
  @Override
  public void uniform1iv(int location, GLIntegers value) {
    assert value instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) value;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL20.glUniform1(location, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#uniform2iv(int, int, GLIntegers) */
  @Override
  public void uniform2iv(int location, GLIntegers value) {
    assert value instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) value;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL20.glUniform2(location, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#uniform3iv(int, int, GLIntegers) */
  @Override
  public void uniform3iv(int location, GLIntegers value) {
    assert value instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) value;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL20.glUniform3(location, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#uniform4iv(int, int, GLIntegers) */
  @Override
  public void uniform4iv(int location, GLIntegers value) {
    assert value instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) value;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL20.glUniform4(location, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#uniformMatrix2fv(int, int, boolean, GLFloats) */
  @Override
  public void uniformMatrix2fv(int location, boolean transpose, GLFloats value) {
    assert value instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL20.glUniformMatrix2(location, transpose, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniformMatrix3fv(int, int, boolean, GLFloats) */
  @Override
  public void uniformMatrix3fv(int location, boolean transpose, GLFloats value) {
    assert value instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL20.glUniformMatrix3(location, transpose, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#uniformMatrix4fv(int, int, boolean, GLFloats) */
  @Override
  public void uniformMatrix4fv(int location, boolean transpose, GLFloats value) {
    assert value instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) value;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL20.glUniformMatrix4(location, transpose, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#useProgram(int) */
  @Override
  public void useProgram(int program) {
    GL20.glUseProgram(program);
  }

  /* @see GLContext#validateProgram(int) */
  @Override
  public void validateProgram(int program) {
    GL20.glValidateProgram(program);
  }

  /* @see GLContext#vertexAttrib1f(int, float) */
  @Override
  public void vertexAttrib1f(int index, float v0) {
    GL20.glVertexAttrib1f(index, v0);
  }

  /* @see GLContext#vertexAttrib2f(int, float, float) */
  @Override
  public void vertexAttrib2f(int index, float v0, float v1) {
    GL20.glVertexAttrib2f(index, v0, v1);
  }

  /* @see GLContext#vertexAttrib3f(int, float, float, float) */
  @Override
  public void vertexAttrib3f(int index, float v0, float v1, float v2) {
    GL20.glVertexAttrib3f(index, v0, v1, v2);
  }

  /* @see GLContext#vertexAttrib4f(int, float, float, float, float) */
  @Override
  public void vertexAttrib4f(int index, float v0, float v1, float v2, float v3) {
    GL20.glVertexAttrib4f(index, v0, v1, v2, v3);
  }

  /* @see GLContext#vertexAttrib1fv(int, GLFloats) */
  @Override
  public void vertexAttrib1fv(int index, GLFloats v) {
    assert v != null;
    GL20.glVertexAttrib1f(index, v.get(0));
  }

  /* @see GLContext#vertexAttrib2fv(int, GLFloats) */
  @Override
  public void vertexAttrib2fv(int index, GLFloats v) {
    assert v != null;
    GL20.glVertexAttrib2f(index, v.get(0), v.get(1));
  }

  /* @see GLContext#vertexAttrib3fv(int, GLFloats) */
  @Override
  public void vertexAttrib3fv(int index, GLFloats v) {
    assert v != null;
    GL20.glVertexAttrib3f(index, v.get(0), v.get(1), v.get(2));
  }

  /* @see GLContext#vertexAttrib4fv(int, GLFloats) */
  @Override
  public void vertexAttrib4fv(int index, GLFloats v) {
    assert v != null;
    GL20.glVertexAttrib4f(index, v.get(0), v.get(1), v.get(2), v.get(3));
  }

  /* @see GLContext#vertexAttribPointer(int, int, int, boolean, int, int) */
  @Override
  public void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset) {
    GL20.glVertexAttribPointer(index, size, type, normalized, stride, offset);
  }

  /* @see GLContext#vertexAttribPointer(int, int, boolean, boolean, int, GLBytes) */
  @Override
  public void vertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, GLBytes pointer) {
    assert pointer instanceof DesktopGLBytes;
    DesktopGLBytes bytes = (DesktopGLBytes) pointer;
    ByteBuffer buffer = bytes.acquireBuffer();
    try {
      GL20.glVertexAttribPointer(index, size, unsigned, normalized, stride, buffer);
    } finally {
      bytes.releaseBuffer();
    }
  }

  /* @see GLContext#vertexAttribPointer(int, int, boolean, boolean, int, GLShorts) */
  @Override
  public void vertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride,
      GLShorts pointer) {
    assert pointer instanceof DesktopGLShorts;
    DesktopGLShorts shorts = (DesktopGLShorts) pointer;
    ShortBuffer buffer = shorts.acquireBuffer();
    try {
      GL20.glVertexAttribPointer(index, size, unsigned, normalized, stride, buffer);
    } finally {
      shorts.releaseBuffer();
    }
  }

  /* @see GLContext#vertexAttribPointer(int, int, boolean, boolean, int, GLIntegers) */
  @Override
  public void vertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride,
      GLIntegers pointer) {
    assert pointer instanceof DesktopGLIntegers;
    DesktopGLIntegers integers = (DesktopGLIntegers) pointer;
    IntBuffer buffer = integers.acquireBuffer();
    try {
      GL20.glVertexAttribPointer(index, size, unsigned, normalized, stride, buffer);
    } finally {
      integers.releaseBuffer();
    }
  }

  /* @see GLContext#vertexAttribPointer(int, int, boolean, int, GLFloats) */
  @Override
  public void vertexAttribPointer(int index, int size, boolean normalized, int stride, GLFloats pointer) {
    assert pointer instanceof DesktopGLFloats;
    DesktopGLFloats floats = (DesktopGLFloats) pointer;
    FloatBuffer buffer = floats.acquireBuffer();
    try {
      GL20.glVertexAttribPointer(index, size, normalized, stride, buffer);
    } finally {
      floats.releaseBuffer();
    }
  }

  /* @see GLContext#viewport(int, int, int, int) */
  @Override
  public void viewport(int x, int y, int width, int height) {
    GL11.glViewport(x, y, width, height);
  }

  //
  // Factory methods for the OpenGL data sets.
  //

  /* @see GLContext#allocateBytes(int) */
  @Override
  public GLBytes allocateBytes(int size) {
    assert size >= 0;
    return new DesktopGLBytes(size);
  }

  /* @see GLContext#allocateShorts(int) */
  @Override
  public GLShorts allocateShorts(int size) {
    assert size >= 0;
    return new DesktopGLShorts(size);
  }

  /* @see GLContext#allocateIntegers(int) */
  @Override
  public GLIntegers allocateIntegers(int size) {
    assert size >= 0;
    return new DesktopGLIntegers(size);
  }

  /* @see GLContext#allocateFloats(int) */
  @Override
  public GLFloats allocateFloats(int size) {
    assert size >= 0;
    return new DesktopGLFloats(size);
  }

}

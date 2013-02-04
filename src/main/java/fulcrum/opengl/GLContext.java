/*
 * GLContext.java
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
 * Base class for implementations of the OpenGL ES 2.0 graphics API.
 * 
 * @see http://www.khronos.org/opengles/sdk/docs/man/
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public abstract class GLContext {

  //
  // Accessors for the OpenGL constants.
  //

  public abstract int ACTIVE_ATTRIBUTE_MAX_LENGTH();

  public abstract int ACTIVE_ATTRIBUTES();

  public abstract int ACTIVE_TEXTURE();

  public abstract int ACTIVE_UNIFORM_MAX_LENGTH();

  public abstract int ACTIVE_UNIFORMS();

  public abstract int ALIASED_LINE_WIDTH_RANGE();

  public abstract int ALIASED_POINT_SIZE_RANGE();

  public abstract int ALPHA();

  public abstract int ALPHA_BITS();

  public abstract int ALWAYS();

  public abstract int ARRAY_BUFFER();

  public abstract int ARRAY_BUFFER_BINDING();

  public abstract int ATTACHED_SHADERS();

  public abstract int BACK();

  public abstract int BLEND();

  public abstract int BLEND_COLOR();

  public abstract int BLEND_DST_ALPHA();

  public abstract int BLEND_DST_RGB();

  public abstract int BLEND_EQUATION();

  public abstract int BLEND_EQUATION_ALPHA();

  public abstract int BLEND_EQUATION_RGB();

  public abstract int BLEND_SRC_ALPHA();

  public abstract int BLEND_SRC_RGB();

  public abstract int BLUE_BITS();

  public abstract int BOOL();

  public abstract int BOOL_VEC2();

  public abstract int BOOL_VEC3();

  public abstract int BOOL_VEC4();

  public abstract int BUFFER_SIZE();

  public abstract int BUFFER_USAGE();

  public abstract int BYTE();

  public abstract int CCW();

  public abstract int CLAMP_TO_EDGE();

  public abstract int COLOR_ATTACHMENT0();

  public abstract int COLOR_BUFFER_BIT();

  public abstract int COLOR_CLEAR_VALUE();

  public abstract int COLOR_WRITEMASK();

  public abstract int COMPILE_STATUS();

  public abstract int COMPRESSED_TEXTURE_FORMATS();

  public abstract int CONSTANT_ALPHA();

  public abstract int CONSTANT_COLOR();

  public abstract int CULL_FACE();

  public abstract int CULL_FACE_MODE();

  public abstract int CURRENT_PROGRAM();

  public abstract int CURRENT_VERTEX_ATTRIB();

  public abstract int CW();

  public abstract int DECR();

  public abstract int DECR_WRAP();

  public abstract int DELETE_STATUS();

  public abstract int DEPTH_ATTACHMENT();

  public abstract int DEPTH_BITS();

  public abstract int DEPTH_BUFFER_BIT();

  public abstract int DEPTH_CLEAR_VALUE();

  public abstract int DEPTH_COMPONENT();

  public abstract int DEPTH_COMPONENT16();

  public abstract int DEPTH_FUNC();

  public abstract int DEPTH_RANGE();

  public abstract int DEPTH_TEST();

  public abstract int DEPTH_WRITEMASK();

  public abstract int DITHER();

  public abstract int DONT_CARE();

  public abstract int DST_ALPHA();

  public abstract int DST_COLOR();

  public abstract int DYNAMIC_DRAW();

  public abstract int ELEMENT_ARRAY_BUFFER();

  public abstract int ELEMENT_ARRAY_BUFFER_BINDING();

  public abstract int EQUAL();

  public abstract int EXTENSIONS();

  public abstract int FALSE();

  public abstract int FASTEST();

  public abstract int FIXED();

  public abstract int FLOAT();

  public abstract int FLOAT_MAT2();

  public abstract int FLOAT_MAT3();

  public abstract int FLOAT_MAT4();

  public abstract int FLOAT_VEC2();

  public abstract int FLOAT_VEC3();

  public abstract int FLOAT_VEC4();

  public abstract int FRAGMENT_SHADER();

  public abstract int FRAMEBUFFER();

  public abstract int FRAMEBUFFER_ATTACHMENT_OBJECT_NAME();

  public abstract int FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE();

  public abstract int FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE();

  public abstract int FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL();

  public abstract int FRAMEBUFFER_BINDING();

  public abstract int FRAMEBUFFER_COMPLETE();

  public abstract int FRAMEBUFFER_INCOMPLETE_ATTACHMENT();

  public abstract int FRAMEBUFFER_INCOMPLETE_DIMENSIONS();

  public abstract int FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT();

  public abstract int FRAMEBUFFER_UNSUPPORTED();

  public abstract int FRONT();

  public abstract int FRONT_AND_BACK();

  public abstract int FRONT_FACE();

  public abstract int FUNC_ADD();

  public abstract int FUNC_REVERSE_SUBTRACT();

  public abstract int FUNC_SUBTRACT();

  public abstract int GENERATE_MIPMAP_HINT();

  public abstract int GEQUAL();

  public abstract int GREATER();

  public abstract int GREEN_BITS();

  public abstract int HIGH_FLOAT();

  public abstract int HIGH_INT();

  public abstract int IMPLEMENTATION_COLOR_READ_FORMAT();

  public abstract int IMPLEMENTATION_COLOR_READ_TYPE();

  public abstract int INCR();

  public abstract int INCR_WRAP();

  public abstract int INFO_LOG_LENGTH();

  public abstract int INT();

  public abstract int INT_VEC2();

  public abstract int INT_VEC3();

  public abstract int INT_VEC4();

  public abstract int INVALID_ENUM();

  public abstract int INVALID_FRAMEBUFFER_OPERATION();

  public abstract int INVALID_OPERATION();

  public abstract int INVALID_VALUE();

  public abstract int INVERT();

  public abstract int KEEP();

  public abstract int LEQUAL();

  public abstract int LESS();

  public abstract int LINE_LOOP();

  public abstract int LINE_STRIP();

  public abstract int LINE_WIDTH();

  public abstract int LINEAR();

  public abstract int LINEAR_MIPMAP_LINEAR();

  public abstract int LINEAR_MIPMAP_NEAREST();

  public abstract int LINES();

  public abstract int LINK_STATUS();

  public abstract int LOW_FLOAT();

  public abstract int LOW_INT();

  public abstract int LUMINANCE();

  public abstract int LUMINANCE_ALPHA();

  public abstract int MAX_COMBINED_TEXTURE_IMAGE_UNITS();

  public abstract int MAX_CUBE_MAP_TEXTURE_SIZE();

  public abstract int MAX_FRAGMENT_UNIFORM_VECTORS();

  public abstract int MAX_RENDERBUFFER_SIZE();

  public abstract int MAX_TEXTURE_IMAGE_UNITS();

  public abstract int MAX_TEXTURE_SIZE();

  public abstract int MAX_VARYING_VECTORS();

  public abstract int MAX_VERTEX_ATTRIBS();

  public abstract int MAX_VERTEX_TEXTURE_IMAGE_UNITS();

  public abstract int MAX_VERTEX_UNIFORM_VECTORS();

  public abstract int MAX_VIEWPORT_DIMS();

  public abstract int MEDIUM_FLOAT();

  public abstract int MEDIUM_INT();

  public abstract int MIRRORED_REPEAT();

  public abstract int NEAREST();

  public abstract int NEAREST_MIPMAP_LINEAR();

  public abstract int NEAREST_MIPMAP_NEAREST();

  public abstract int NEVER();

  public abstract int NICEST();

  public abstract int NO_ERROR();

  public abstract int NONE();

  public abstract int NOTEQUAL();

  public abstract int NUM_COMPRESSED_TEXTURE_FORMATS();

  public abstract int NUM_SHADER_BINARY_FORMATS();

  public abstract int ONE();

  public abstract int ONE_MINUS_CONSTANT_ALPHA();

  public abstract int ONE_MINUS_CONSTANT_COLOR();

  public abstract int ONE_MINUS_DST_ALPHA();

  public abstract int ONE_MINUS_DST_COLOR();

  public abstract int ONE_MINUS_SRC_ALPHA();

  public abstract int ONE_MINUS_SRC_COLOR();

  public abstract int OUT_OF_MEMORY();

  public abstract int PACK_ALIGNMENT();

  public abstract int POINTS();

  public abstract int POLYGON_OFFSET_FACTOR();

  public abstract int POLYGON_OFFSET_FILL();

  public abstract int POLYGON_OFFSET_UNITS();

  public abstract int RED_BITS();

  public abstract int RENDERBUFFER();

  public abstract int RENDERBUFFER_ALPHA_SIZE();

  public abstract int RENDERBUFFER_BINDING();

  public abstract int RENDERBUFFER_BLUE_SIZE();

  public abstract int RENDERBUFFER_DEPTH_SIZE();

  public abstract int RENDERBUFFER_GREEN_SIZE();

  public abstract int RENDERBUFFER_HEIGHT();

  public abstract int RENDERBUFFER_INTERNAL_FORMAT();

  public abstract int RENDERBUFFER_RED_SIZE();

  public abstract int RENDERBUFFER_STENCIL_SIZE();

  public abstract int RENDERBUFFER_WIDTH();

  public abstract int RENDERER();

  public abstract int REPEAT();

  public abstract int REPLACE();

  public abstract int RGB();

  public abstract int RGB5_A1();

  public abstract int RGB565();

  public abstract int RGBA();

  public abstract int RGBA4();

  public abstract int SAMPLE_ALPHA_TO_COVERAGE();

  public abstract int SAMPLE_BUFFERS();

  public abstract int SAMPLE_COVERAGE();

  public abstract int SAMPLE_COVERAGE_INVERT();

  public abstract int SAMPLE_COVERAGE_VALUE();

  public abstract int SAMPLER_2D();

  public abstract int SAMPLER_CUBE();

  public abstract int SAMPLES();

  public abstract int SCISSOR_BOX();

  public abstract int SCISSOR_TEST();

  public abstract int SHADER_BINARY_FORMATS();

  public abstract int SHADER_COMPILER();

  public abstract int SHADER_SOURCE_LENGTH();

  public abstract int SHADER_TYPE();

  public abstract int SHADING_LANGUAGE_VERSION();

  public abstract int SHORT();

  public abstract int SRC_ALPHA();

  public abstract int SRC_ALPHA_SATURATE();

  public abstract int SRC_COLOR();

  public abstract int STATIC_DRAW();

  public abstract int STENCIL_ATTACHMENT();

  public abstract int STENCIL_BACK_FAIL();

  public abstract int STENCIL_BACK_FUNC();

  public abstract int STENCIL_BACK_PASS_DEPTH_FAIL();

  public abstract int STENCIL_BACK_PASS_DEPTH_PASS();

  public abstract int STENCIL_BACK_REF();

  public abstract int STENCIL_BACK_VALUE_MASK();

  public abstract int STENCIL_BACK_WRITEMASK();

  public abstract int STENCIL_BITS();

  public abstract int STENCIL_BUFFER_BIT();

  public abstract int STENCIL_CLEAR_VALUE();

  public abstract int STENCIL_FAIL();

  public abstract int STENCIL_FUNC();

  public abstract int STENCIL_INDEX();

  public abstract int STENCIL_INDEX8();

  public abstract int STENCIL_PASS_DEPTH_FAIL();

  public abstract int STENCIL_PASS_DEPTH_PASS();

  public abstract int STENCIL_REF();

  public abstract int STENCIL_TEST();

  public abstract int STENCIL_VALUE_MASK();

  public abstract int STENCIL_WRITEMASK();

  public abstract int STREAM_DRAW();

  public abstract int SUBPIXEL_BITS();

  public abstract int TEXTURE();

  public abstract int TEXTURE_2D();

  public abstract int TEXTURE_BINDING_2D();

  public abstract int TEXTURE_BINDING_CUBE_MAP();

  public abstract int TEXTURE_CUBE_MAP();

  public abstract int TEXTURE_CUBE_MAP_NEGATIVE_X();

  public abstract int TEXTURE_CUBE_MAP_NEGATIVE_Y();

  public abstract int TEXTURE_CUBE_MAP_NEGATIVE_Z();

  public abstract int TEXTURE_CUBE_MAP_POSITIVE_X();

  public abstract int TEXTURE_CUBE_MAP_POSITIVE_Y();

  public abstract int TEXTURE_CUBE_MAP_POSITIVE_Z();

  public abstract int TEXTURE_MAG_FILTER();

  public abstract int TEXTURE_MIN_FILTER();

  public abstract int TEXTURE_WRAP_S();

  public abstract int TEXTURE_WRAP_T();

  public abstract int TEXTURE0();

  public abstract int TEXTURE1();

  public abstract int TEXTURE2();

  public abstract int TEXTURE3();

  public abstract int TEXTURE4();

  public abstract int TEXTURE5();

  public abstract int TEXTURE6();

  public abstract int TEXTURE7();

  public abstract int TEXTURE8();

  public abstract int TEXTURE9();

  public abstract int TEXTURE10();

  public abstract int TEXTURE11();

  public abstract int TEXTURE12();

  public abstract int TEXTURE13();

  public abstract int TEXTURE14();

  public abstract int TEXTURE15();

  public abstract int TEXTURE16();

  public abstract int TEXTURE17();

  public abstract int TEXTURE18();

  public abstract int TEXTURE19();

  public abstract int TEXTURE20();

  public abstract int TEXTURE21();

  public abstract int TEXTURE22();

  public abstract int TEXTURE23();

  public abstract int TEXTURE24();

  public abstract int TEXTURE25();

  public abstract int TEXTURE26();

  public abstract int TEXTURE27();

  public abstract int TEXTURE28();

  public abstract int TEXTURE29();

  public abstract int TEXTURE30();

  public abstract int TEXTURE31();

  public abstract int TRIANGLE_FAN();

  public abstract int TRIANGLE_STRIP();

  public abstract int TRIANGLES();

  public abstract int TRUE();

  public abstract int UNPACK_ALIGNMENT();

  public abstract int UNSIGNED_BYTE();

  public abstract int UNSIGNED_INT();

  public abstract int UNSIGNED_SHORT();

  public abstract int UNSIGNED_SHORT_4_4_4_4();

  public abstract int UNSIGNED_SHORT_5_5_5_1();

  public abstract int UNSIGNED_SHORT_5_6_5();

  public abstract int VALIDATE_STATUS();

  public abstract int VENDOR();

  public abstract int VERSION();

  public abstract int VERTEX_ATTRIB_ARRAY_BUFFER_BINDING();

  public abstract int VERTEX_ATTRIB_ARRAY_ENABLED();

  public abstract int VERTEX_ATTRIB_ARRAY_NORMALIZED();

  public abstract int VERTEX_ATTRIB_ARRAY_POINTER();

  public abstract int VERTEX_ATTRIB_ARRAY_SIZE();

  public abstract int VERTEX_ATTRIB_ARRAY_STRIDE();

  public abstract int VERTEX_ATTRIB_ARRAY_TYPE();

  public abstract int VERTEX_SHADER();

  public abstract int VIEWPORT();

  public abstract int ZERO();

  //
  // Declarations of the OpenGL functions.
  //

  /** Select active texture unit. */
  public abstract void activeTexture(int texture);

  /** Attach a shader object to a program object. */
  public abstract void attachShader(int program, int shader);

  /** Associate a generic vertex attribute index with a named attribute variable. */
  public abstract void bindAttribLocation(int program, int index, String name);

  /** Bind a named buffer object. */
  public abstract void bindBuffer(int target, int buffer);

  /** Bind a named framebuffer object. */
  public abstract void bindFramebuffer(int target, int framebuffer);

  /** Bind a named renderbuffer object. */
  public abstract void bindRenderbuffer(int target, int renderbuffer);

  /** Bind a named texture to a texturing target. */
  public abstract void bindTexture(int target, int texture);

  /** Set the blend color. */
  public abstract void blendColor(float red, float green, float blue, float alpha);

  /** Specify the equation used for both the RGB blend equation and the Alpha blend equation. */
  public abstract void blendEquation(int mode);

  /** Set the RGB blend equation and the alpha blend equation separately. */
  public abstract void blendEquationSeparate(int modeRGB, int modeAlpha);

  /** Specify pixel arithmetic. */
  public abstract void blendFunc(int sfactor, int dfactor);

  /** Specify pixel arithmetic for RGB and alpha components separately. */
  public abstract void blendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha);

  /** Create a buffer object's data store. */
  public abstract void bufferData(int target, int size, int usage);

  /** Create and initialize a buffer object's data store. */
  public abstract void bufferData(int target, GLBytes data, int usage);

  /** Update a subset of a buffer object's data store. */
  public abstract void bufferSubData(int target, int offset, GLBytes data);

  /** Return the framebuffer completeness status of a framebuffer object. */
  public abstract int checkFramebufferStatus(int target);

  /** Clear buffers to preset values. */
  public abstract void clear(int mask);

  /** Specify clear values for the color buffers. */
  public abstract void clearColor(float red, float green, float blue, float alpha);

  /** Specify the clear value for the depth buffer. */
  public abstract void clearDepthf(float depth);

  /** Specify the clear value for the stencil buffer. */
  public abstract void clearStencil(int s);

  /** Enable and disable writing of frame buffer color components. */
  public abstract void colorMask(boolean red, boolean green, boolean blue, boolean alpha);

  /** Compile a shader object. */
  public abstract void compileShader(int shader);

  /** Specify a two-dimensional texture image in a compressed format. */
  public abstract void compressedTexImage2D(int target, int level, int internalformat, int width, int height,
      int border, GLBytes data);

  /** Specify a two-dimensional texture sub-image in a compressed format. */
  public abstract void compressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
      int format, GLBytes data);

  /** Copy pixels into a 2D texture image. */
  public abstract void copyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height,
      int border);

  /** Copy a two-dimensional texture sub-image. */
  public abstract void copyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width,
      int height);

  /** Create a program object. */
  public abstract int createProgram();

  /** Create a shader object. */
  public abstract int createShader(int shaderType);

  /** Specify whether front- or back-facing facets can be culled. */
  public abstract void cullFace(int mode);

  /** Delete named buffer objects. */
  public abstract void deleteBuffers(GLIntegers buffers);

  /** Delete named framebuffer objects. */
  public abstract void deleteFramebuffers(GLIntegers framebuffers);

  /** Delete a program object. */
  public abstract void deleteProgram(int program);

  /** Delete named renderbuffer objects. */
  public abstract void deleteRenderbuffers(GLIntegers renderbuffers);

  /** Delete a shader object. */
  public abstract void deleteShader(int shader);

  /** Delete named textures. */
  public abstract void deleteTextures(GLIntegers textures);

  /** Specify the value used for depth buffer comparisons. */
  public abstract void depthFunc(int func);

  /** Enable or disable writing into the depth buffer. */
  public abstract void depthMask(boolean flag);

  /** Specify mapping of depth values from normalized device coordinates to window coordinates. */
  public abstract void depthRangef(float nearVal, float farVal);

  /** Detach a shader object from a program object. */
  public abstract void detachShader(int program, int shader);

  /** Enable or disable server-side GL capabilities. */
  public abstract void disable(int cap);

  /** Enable or disable a generic vertex attribute array. */
  public abstract void disableVertexAttribArray(int index);

  /** Render primitives from array data. */
  public abstract void drawArrays(int mode, int first, int count);

  /** Render primitives from array data. */
  public abstract void drawElements(int mode, int count, int type, int offset);

  /** Render primitives from array data. */
  public abstract void drawElements(int mode, GLBytes indices);

  /** Render primitives from array data. */
  public abstract void drawElements(int mode, GLShorts indices);

  /** Enable or disable server-side GL capabilities. */
  public abstract void enable(int cap);

  /** Enable or disable a generic vertex attribute array. */
  public abstract void enableVertexAttribArray(int index);

  /** Block until all GL execution is complete. */
  public abstract void finish();

  /** Force execution of GL commands in finite time. */
  public abstract void flush();

  /** Attach a renderbuffer object to a framebuffer object. */
  public abstract void framebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer);

  /** Attach a texture image to a framebuffer object. */
  public abstract void framebufferTexture2D(int target, int attachment, int textarget, int texture, int level);

  /** Define front- and back-facing polygons. */
  public abstract void frontFace(int mode);

  /** Generate buffer object names. */
  public abstract void genBuffers(GLIntegers buffers);

  /** Generate framebuffer object names. */
  public abstract void genFramebuffers(GLIntegers framebuffers);

  /** Generate renderbuffer object names. */
  public abstract void genRenderbuffers(GLIntegers renderbuffers);

  /** Generate texture names. */
  public abstract void genTextures(GLIntegers textures);

  /** Generate a complete set of mipmaps for a texture object. */
  public abstract void generateMipmap(int target);

  /** Return the value or values of a selected parameter. */
  public abstract boolean[] getBooleanv(int pname);

  /** Return the value or values of a selected parameter. */
  public abstract float[] getFloatv(int pname);

  /** Return the value or values of a selected parameter. */
  public abstract int[] getIntegerv(int pname);

  /** Return information about an active attribute variable. */
  public abstract GLVariable getActiveAttrib(int program, int index);

  /** Return information about an active uniform variable. */
  public abstract GLVariable getActiveUniform(int program, int index);

  /** Return the handles of the shader objects attached to a program object. */
  public abstract int[] getAttachedShaders(int program);

  /** Return the location of an attribute variable. */
  public abstract int getAttribLocation(int program, String name);

  /** Return parameters of a buffer object. */
  public abstract int[] getBufferParameteriv(int target, int value);

  /** Return error information. */
  public abstract int getError();

  /** Return attachment parameters of a framebuffer object. */
  public abstract int[] getFramebufferAttachmentParameteriv(int target, int attachment, int pname);

  /** Return the information log for a program object. */
  public abstract String getProgramInfoLog(int program);

  /** Return a parameter from a program object. */
  public abstract int[] getProgramiv(int program, int pname);

  /** Return parameters of a renderbuffer object. */
  public abstract int[] getRenderbufferParameteriv(int target, int pname);

  /** Return the information log for a shader object. */
  public abstract String getShaderInfoLog(int shader);

  /** Return the range and precision for different shader numeric formats. */
  public abstract GLPrecision getShaderPrecisionFormat(int shaderType, int precisionType);

  /** Return the source code string from a shader object. */
  public abstract String getShaderSource(int shader);

  /** Return a parameter from a shader object. */
  public abstract int[] getShaderiv(int shader, int pname);

  /** Return a string describing the current GL connection. */
  public abstract String getString(int name);

  /** Return texture parameter values. */
  public abstract float[] getTexParameterfv(int target, int pname);

  /** Return texture parameter values. */
  public abstract int[] getTexParameteriv(int target, int pname);

  /** Return the value of a uniform variable. */
  public abstract void getUniformfv(int program, int location, GLFloats params);

  /** Return the value of a uniform variable. */
  public abstract void getUniformiv(int program, int location, GLIntegers params);

  /** Return the location of a uniform variable. */
  public abstract int getUniformLocation(int program, String name);

  /** Return a generic vertex attribute parameter. */
  public abstract void getVertexAttribfv(int index, int pname, GLFloats params);

  /** Return a generic vertex attribute parameter. */
  public abstract void getVertexAttribiv(int index, int pname, GLIntegers params);

  /** Specify implementation-specific hints. */
  public abstract void hint(int target, int mode);

  /** Determine if a name corresponds to a buffer object. */
  public abstract boolean isBuffer(int buffer);

  /** Test whether a capability is enabled. */
  public abstract boolean isEnabled(int cap);

  /** Determine if a name corresponds to a framebuffer object. */
  public abstract boolean isFramebuffer(int framebuffer);

  /** Determine if a name corresponds to a program object. */
  public abstract boolean isProgram(int program);

  /** Determine if a name corresponds to a renderbuffer object. */
  public abstract boolean isRenderbuffer(int renderbuffer);

  /** Determine if a name corresponds to a shader object. */
  public abstract boolean isShader(int shader);

  /** Determine if a name corresponds to a texture. */
  public abstract boolean isTexture(int texture);

  /** Specify the width of rasterized lines. */
  public abstract void lineWidth(float width);

  /** Link a program object. */
  public abstract void linkProgram(int program);

  /** Get pixel storage modes. */
  public abstract void pixelStorei(int pname, int param);

  /** Set the scale and units used to calculate depth values. */
  public abstract void polygonOffset(float factor, float units);

  /** Read a block of pixels from the frame buffer. */
  public abstract void readPixels(int x, int y, int width, int height, int format, GLBytes data);

  /** Read a block of pixels from the frame buffer. */
  public abstract void readPixels(int x, int y, int width, int height, int format, int type, GLShorts data);

  /** Release resources allocated by the shader compiler. */
  public abstract void releaseShaderCompiler();

  /** Create and initialize a renderbuffer object's data store. */
  public abstract void renderbufferStorage(int target, int internalformat, int width, int height);

  /** Specify multisample coverage parameters. */
  public abstract void sampleCoverage(float value, boolean invert);

  /** Define the scissor box. */
  public abstract void scissor(int x, int y, int width, int height);

  /** Load a precompiled shader binary. */
  public abstract void shaderBinary(GLIntegers shaders, int binaryformat, GLBytes binary);

  /** Replace the source code in a shader object. */
  public abstract void shaderSource(int shader, String string);

  /** Set front and back function and reference value for stencil testing. */
  public abstract void stencilFunc(int func, int ref, int mask);

  /** Set front and/or back function and reference value for stencil testing. */
  public abstract void stencilFuncSeparate(int face, int func, int ref, int mask);

  /** Control the front and back writing of individual bits in the stencil planes. */
  public abstract void stencilMask(int mask);

  /** Control the front and/or back writing of individual bits in the stencil planes. */
  public abstract void stencilMaskSeparate(int face, int mask);

  /** Set front and back stencil test actions. */
  public abstract void stencilOp(int sfail, int dpfail, int dppass);

  /** Set front and/or back stencil test actions. */
  public abstract void stencilOpSeparate(int face, int sfail, int dpfail, int dppass);

  /** Specify a two-dimensional texture image. */
  public abstract void texImage2D(int target, int level, int internalformat, int width, int height, int border,
      int format, GLBytes data);

  /** Specify a two-dimensional texture image. */
  public abstract void texImage2D(int target, int level, int internalformat, int width, int height, int border,
      int format, int type, GLShorts data);

  /** Set texture parameters. */
  public abstract void texParameterf(int target, int pname, float param);

  /** Set texture parameters. */
  public abstract void texParameteri(int target, int pname, int param);

  /** Set texture parameters. */
  public abstract void texParameterfv(int target, int pname, GLFloats params);

  /** Set texture parameters. */
  public abstract void texParameteriv(int target, int pname, GLIntegers params);

  /** Specify a two-dimensional texture subimage. */
  public abstract void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
      int format, GLBytes data);

  /** Specify a two-dimensional texture subimage. */
  public abstract void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
      int format, int type, GLShorts data);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform1f(int location, float v0);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform2f(int location, float v0, float v1);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform3f(int location, float v0, float v1, float v2);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform4f(int location, float v0, float v1, float v2, float v3);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform1i(int location, int v0);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform2i(int location, int v0, int v1);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform3i(int location, int v0, int v1, int v2);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform4i(int location, int v0, int v1, int v2, int v3);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform1fv(int location, GLFloats value);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform2fv(int location, GLFloats value);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform3fv(int location, GLFloats value);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform4fv(int location, GLFloats value);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform1iv(int location, GLIntegers value);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform2iv(int location, GLIntegers value);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform3iv(int location, GLIntegers value);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniform4iv(int location, GLIntegers value);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniformMatrix2fv(int location, boolean transpose, GLFloats value);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniformMatrix3fv(int location, boolean transpose, GLFloats value);

  /** Specify the value of a uniform variable for the current program object. */
  public abstract void uniformMatrix4fv(int location, boolean transpose, GLFloats value);

  /** Install a program object as part of current rendering state. */
  public abstract void useProgram(int program);

  /** Validate a program object. */
  public abstract void validateProgram(int program);

  /** Specify the value of a generic vertex attribute. */
  public abstract void vertexAttrib1f(int index, float v0);

  /** Specify the value of a generic vertex attribute. */
  public abstract void vertexAttrib2f(int index, float v0, float v1);

  /** Specify the value of a generic vertex attribute. */
  public abstract void vertexAttrib3f(int index, float v0, float v1, float v2);

  /** Specify the value of a generic vertex attribute. */
  public abstract void vertexAttrib4f(int index, float v0, float v1, float v2, float v3);

  /** Specify the value of a generic vertex attribute. */
  public abstract void vertexAttrib1fv(int index, GLFloats v);

  /** Specify the value of a generic vertex attribute. */
  public abstract void vertexAttrib2fv(int index, GLFloats v);

  /** Specify the value of a generic vertex attribute. */
  public abstract void vertexAttrib3fv(int index, GLFloats v);

  /** Specify the value of a generic vertex attribute. */
  public abstract void vertexAttrib4fv(int index, GLFloats v);

  /** Define an array of generic vertex attribute data. */
  public abstract void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset);

  /** Define an array of generic vertex attribute data. */
  public abstract void vertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride,
      GLBytes pointer);

  /** Define an array of generic vertex attribute data. */
  public abstract void vertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride,
      GLShorts pointer);

  /** Define an array of generic vertex attribute data. */
  public abstract void vertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride,
      GLIntegers pointer);

  /** Define an array of generic vertex attribute data. */
  public abstract void vertexAttribPointer(int index, int size, boolean normalized, int stride, GLFloats pointer);

  /** Set the viewport. */
  public abstract void viewport(int x, int y, int width, int height);

  //
  // Factory methods for the OpenGL data sets.
  //

  /** Returns a new instance of {@link GLBytes} of the specified size. */
  public abstract GLBytes allocateBytes(int size);

  /** Returns a new instance of {@link GLBytes} with the specified data. */
  public final GLBytes createBytes(byte... values) {
    assert values != null;
    GLBytes result = allocateBytes(values.length);
    result.copyFrom(values);
    return result;
  }

  /** Returns a new instance of {@link GLShorts} of the specified size. */
  public abstract GLShorts allocateShorts(int size);

  /** Returns a new instance of {@link GLShorts} with the specified data. */
  public final GLShorts createShorts(short... values) {
    assert values != null;
    GLShorts result = allocateShorts(values.length);
    result.copyFrom(values);
    return result;
  }

  /** Returns a new instance of {@link GLIntegers} of the specified size. */
  public abstract GLIntegers allocateIntegers(int size);

  /** Returns a new instance of {@link GLIntegers} with the specified data. */
  public final GLIntegers createIntegers(int... values) {
    assert values != null;
    GLIntegers result = allocateIntegers(values.length);
    result.copyFrom(values);
    return result;
  }

  /** Returns a new instance of {@link GLFloats} of the specified size. */
  public abstract GLFloats allocateFloats(int size);

  /** Returns a new instance of {@link GLFloats} with the specified data. */
  public final GLFloats createFloats(float... values) {
    assert values != null;
    GLFloats result = allocateFloats(values.length);
    result.copyFrom(values);
    return result;
  }

}

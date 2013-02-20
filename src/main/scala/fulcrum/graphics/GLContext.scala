/*
 * GLContext.scala
 * 
 * Copyright (c) 2013 Lonnie Pryor III
 */
package fulcrum.graphics

import java.nio.Buffer
import java.nio.IntBuffer
import java.nio.FloatBuffer

/**
 * Interface that exposes the underlying OpenGL ES 2.0 API.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
abstract class GLContext {

  val GL_ACTIVE_ATTRIBUTES: Int
  val GL_ACTIVE_ATTRIBUTE_MAX_LENGTH: Int
  val GL_ACTIVE_TEXTURE: Int
  val GL_ACTIVE_UNIFORMS: Int
  val GL_ACTIVE_UNIFORM_MAX_LENGTH: Int
  val GL_ALIASED_LINE_WIDTH_RANGE: Int
  val GL_ALIASED_POINT_SIZE_RANGE: Int
  val GL_ALPHA: Int
  val GL_ALPHA_BITS: Int
  val GL_ALWAYS: Int
  val GL_ARRAY_BUFFER: Int
  val GL_ARRAY_BUFFER_BINDING: Int
  val GL_ATTACHED_SHADERS: Int
  val GL_BACK: Int
  val GL_BLEND: Int
  val GL_BLEND_COLOR: Int
  val GL_BLEND_DST_ALPHA: Int
  val GL_BLEND_DST_RGB: Int
  val GL_BLEND_EQUATION: Int
  val GL_BLEND_EQUATION_ALPHA: Int
  val GL_BLEND_EQUATION_RGB: Int
  val GL_BLEND_SRC_ALPHA: Int
  val GL_BLEND_SRC_RGB: Int
  val GL_BLUE_BITS: Int
  val GL_BOOL: Int
  val GL_BOOL_VEC2: Int
  val GL_BOOL_VEC3: Int
  val GL_BOOL_VEC4: Int
  val GL_BUFFER_SIZE: Int
  val GL_BUFFER_USAGE: Int
  val GL_BYTE: Int
  val GL_CCW: Int
  val GL_CLAMP_TO_EDGE: Int
  val GL_COLOR_ATTACHMENT0: Int
  val GL_COLOR_BUFFER_BIT: Int
  val GL_COLOR_CLEAR_VALUE: Int
  val GL_COLOR_WRITEMASK: Int
  val GL_COMPILE_STATUS: Int
  val GL_COMPRESSED_TEXTURE_FORMATS: Int
  val GL_CONSTANT_ALPHA: Int
  val GL_CONSTANT_COLOR: Int
  val GL_CULL_FACE: Int
  val GL_CULL_FACE_MODE: Int
  val GL_CURRENT_PROGRAM: Int
  val GL_CURRENT_VERTEX_ATTRIB: Int
  val GL_CW: Int
  val GL_DECR: Int
  val GL_DECR_WRAP: Int
  val GL_DELETE_STATUS: Int
  val GL_DEPTH_ATTACHMENT: Int
  val GL_DEPTH_BITS: Int
  val GL_DEPTH_BUFFER_BIT: Int
  val GL_DEPTH_CLEAR_VALUE: Int
  val GL_DEPTH_COMPONENT: Int
  val GL_DEPTH_COMPONENT16: Int
  val GL_DEPTH_FUNC: Int
  val GL_DEPTH_RANGE: Int
  val GL_DEPTH_TEST: Int
  val GL_DEPTH_WRITEMASK: Int
  val GL_DITHER: Int
  val GL_DONT_CARE: Int
  val GL_DST_ALPHA: Int
  val GL_DST_COLOR: Int
  val GL_DYNAMIC_DRAW: Int
  val GL_ELEMENT_ARRAY_BUFFER: Int
  val GL_ELEMENT_ARRAY_BUFFER_BINDING: Int
  val GL_EQUAL: Int
  val GL_EXTENSIONS: Int
  val GL_FALSE: Int
  val GL_FASTEST: Int
  val GL_FIXED: Int
  val GL_FLOAT: Int
  val GL_FLOAT_MAT2: Int
  val GL_FLOAT_MAT3: Int
  val GL_FLOAT_MAT4: Int
  val GL_FLOAT_VEC2: Int
  val GL_FLOAT_VEC3: Int
  val GL_FLOAT_VEC4: Int
  val GL_FRAGMENT_SHADER: Int
  val GL_FRAMEBUFFER: Int
  val GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME: Int
  val GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE: Int
  val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE: Int
  val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL: Int
  val GL_FRAMEBUFFER_BINDING: Int
  val GL_FRAMEBUFFER_COMPLETE: Int
  val GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT: Int
  val GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS: Int
  val GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT: Int
  val GL_FRAMEBUFFER_UNSUPPORTED: Int
  val GL_FRONT: Int
  val GL_FRONT_AND_BACK: Int
  val GL_FRONT_FACE: Int
  val GL_FUNC_ADD: Int
  val GL_FUNC_REVERSE_SUBTRACT: Int
  val GL_FUNC_SUBTRACT: Int
  val GL_GENERATE_MIPMAP_HINT: Int
  val GL_GEQUAL: Int
  val GL_GREATER: Int
  val GL_GREEN_BITS: Int
  val GL_HIGH_FLOAT: Int
  val GL_HIGH_INT: Int
  val GL_IMPLEMENTATION_COLOR_READ_FORMAT: Int
  val GL_IMPLEMENTATION_COLOR_READ_TYPE: Int
  val GL_INCR: Int
  val GL_INCR_WRAP: Int
  val GL_INFO_LOG_LENGTH: Int
  val GL_INT: Int
  val GL_INT_VEC2: Int
  val GL_INT_VEC3: Int
  val GL_INT_VEC4: Int
  val GL_INVALID_ENUM: Int
  val GL_INVALID_FRAMEBUFFER_OPERATION: Int
  val GL_INVALID_OPERATION: Int
  val GL_INVALID_VALUE: Int
  val GL_INVERT: Int
  val GL_KEEP: Int
  val GL_LEQUAL: Int
  val GL_LESS: Int
  val GL_LINEAR: Int
  val GL_LINEAR_MIPMAP_LINEAR: Int
  val GL_LINEAR_MIPMAP_NEAREST: Int
  val GL_LINES: Int
  val GL_LINE_LOOP: Int
  val GL_LINE_STRIP: Int
  val GL_LINE_WIDTH: Int
  val GL_LINK_STATUS: Int
  val GL_LOW_FLOAT: Int
  val GL_LOW_INT: Int
  val GL_LUMINANCE: Int
  val GL_LUMINANCE_ALPHA: Int
  val GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS: Int
  val GL_MAX_CUBE_MAP_TEXTURE_SIZE: Int
  val GL_MAX_FRAGMENT_UNIFORM_VECTORS: Int
  val GL_MAX_RENDERBUFFER_SIZE: Int
  val GL_MAX_TEXTURE_IMAGE_UNITS: Int
  val GL_MAX_TEXTURE_SIZE: Int
  val GL_MAX_VARYING_VECTORS: Int
  val GL_MAX_VERTEX_ATTRIBS: Int
  val GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS: Int
  val GL_MAX_VERTEX_UNIFORM_VECTORS: Int
  val GL_MAX_VIEWPORT_DIMS: Int
  val GL_MEDIUM_FLOAT: Int
  val GL_MEDIUM_INT: Int
  val GL_MIRRORED_REPEAT: Int
  val GL_NEAREST: Int
  val GL_NEAREST_MIPMAP_LINEAR: Int
  val GL_NEAREST_MIPMAP_NEAREST: Int
  val GL_NEVER: Int
  val GL_NICEST: Int
  val GL_NONE: Int
  val GL_NOTEQUAL: Int
  val GL_NO_ERROR: Int
  val GL_NUM_COMPRESSED_TEXTURE_FORMATS: Int
  val GL_NUM_SHADER_BINARY_FORMATS: Int
  val GL_ONE: Int
  val GL_ONE_MINUS_CONSTANT_ALPHA: Int
  val GL_ONE_MINUS_CONSTANT_COLOR: Int
  val GL_ONE_MINUS_DST_ALPHA: Int
  val GL_ONE_MINUS_DST_COLOR: Int
  val GL_ONE_MINUS_SRC_ALPHA: Int
  val GL_ONE_MINUS_SRC_COLOR: Int
  val GL_OUT_OF_MEMORY: Int
  val GL_PACK_ALIGNMENT: Int
  val GL_POINTS: Int
  val GL_POLYGON_OFFSET_FACTOR: Int
  val GL_POLYGON_OFFSET_FILL: Int
  val GL_POLYGON_OFFSET_UNITS: Int
  val GL_RED_BITS: Int
  val GL_RENDERBUFFER: Int
  val GL_RENDERBUFFER_ALPHA_SIZE: Int
  val GL_RENDERBUFFER_BINDING: Int
  val GL_RENDERBUFFER_BLUE_SIZE: Int
  val GL_RENDERBUFFER_DEPTH_SIZE: Int
  val GL_RENDERBUFFER_GREEN_SIZE: Int
  val GL_RENDERBUFFER_HEIGHT: Int
  val GL_RENDERBUFFER_INTERNAL_FORMAT: Int
  val GL_RENDERBUFFER_RED_SIZE: Int
  val GL_RENDERBUFFER_STENCIL_SIZE: Int
  val GL_RENDERBUFFER_WIDTH: Int
  val GL_RENDERER: Int
  val GL_REPEAT: Int
  val GL_REPLACE: Int
  val GL_RGB: Int
  val GL_RGB565: Int
  val GL_RGB5_A1: Int
  val GL_RGBA: Int
  val GL_RGBA4: Int
  val GL_SAMPLER_2D: Int
  val GL_SAMPLER_CUBE: Int
  val GL_SAMPLES: Int
  val GL_SAMPLE_ALPHA_TO_COVERAGE: Int
  val GL_SAMPLE_BUFFERS: Int
  val GL_SAMPLE_COVERAGE: Int
  val GL_SAMPLE_COVERAGE_INVERT: Int
  val GL_SAMPLE_COVERAGE_VALUE: Int
  val GL_SCISSOR_BOX: Int
  val GL_SCISSOR_TEST: Int
  val GL_SHADER_BINARY_FORMATS: Int
  val GL_SHADER_COMPILER: Int
  val GL_SHADER_SOURCE_LENGTH: Int
  val GL_SHADER_TYPE: Int
  val GL_SHADING_LANGUAGE_VERSION: Int
  val GL_SHORT: Int
  val GL_SRC_ALPHA: Int
  val GL_SRC_ALPHA_SATURATE: Int
  val GL_SRC_COLOR: Int
  val GL_STATIC_DRAW: Int
  val GL_STENCIL_ATTACHMENT: Int
  val GL_STENCIL_BACK_FAIL: Int
  val GL_STENCIL_BACK_FUNC: Int
  val GL_STENCIL_BACK_PASS_DEPTH_FAIL: Int
  val GL_STENCIL_BACK_PASS_DEPTH_PASS: Int
  val GL_STENCIL_BACK_REF: Int
  val GL_STENCIL_BACK_VALUE_MASK: Int
  val GL_STENCIL_BACK_WRITEMASK: Int
  val GL_STENCIL_BITS: Int
  val GL_STENCIL_BUFFER_BIT: Int
  val GL_STENCIL_CLEAR_VALUE: Int
  val GL_STENCIL_FAIL: Int
  val GL_STENCIL_FUNC: Int
  val GL_STENCIL_INDEX: Int
  val GL_STENCIL_INDEX8: Int
  val GL_STENCIL_PASS_DEPTH_FAIL: Int
  val GL_STENCIL_PASS_DEPTH_PASS: Int
  val GL_STENCIL_REF: Int
  val GL_STENCIL_TEST: Int
  val GL_STENCIL_VALUE_MASK: Int
  val GL_STENCIL_WRITEMASK: Int
  val GL_STREAM_DRAW: Int
  val GL_SUBPIXEL_BITS: Int
  val GL_TEXTURE: Int
  val GL_TEXTURE0: Int
  val GL_TEXTURE1: Int
  val GL_TEXTURE10: Int
  val GL_TEXTURE11: Int
  val GL_TEXTURE12: Int
  val GL_TEXTURE13: Int
  val GL_TEXTURE14: Int
  val GL_TEXTURE15: Int
  val GL_TEXTURE16: Int
  val GL_TEXTURE17: Int
  val GL_TEXTURE18: Int
  val GL_TEXTURE19: Int
  val GL_TEXTURE2: Int
  val GL_TEXTURE20: Int
  val GL_TEXTURE21: Int
  val GL_TEXTURE22: Int
  val GL_TEXTURE23: Int
  val GL_TEXTURE24: Int
  val GL_TEXTURE25: Int
  val GL_TEXTURE26: Int
  val GL_TEXTURE27: Int
  val GL_TEXTURE28: Int
  val GL_TEXTURE29: Int
  val GL_TEXTURE3: Int
  val GL_TEXTURE30: Int
  val GL_TEXTURE31: Int
  val GL_TEXTURE4: Int
  val GL_TEXTURE5: Int
  val GL_TEXTURE6: Int
  val GL_TEXTURE7: Int
  val GL_TEXTURE8: Int
  val GL_TEXTURE9: Int
  val GL_TEXTURE_2D: Int
  val GL_TEXTURE_BINDING_2D: Int
  val GL_TEXTURE_BINDING_CUBE_MAP: Int
  val GL_TEXTURE_CUBE_MAP: Int
  val GL_TEXTURE_CUBE_MAP_NEGATIVE_X: Int
  val GL_TEXTURE_CUBE_MAP_NEGATIVE_Y: Int
  val GL_TEXTURE_CUBE_MAP_NEGATIVE_Z: Int
  val GL_TEXTURE_CUBE_MAP_POSITIVE_X: Int
  val GL_TEXTURE_CUBE_MAP_POSITIVE_Y: Int
  val GL_TEXTURE_CUBE_MAP_POSITIVE_Z: Int
  val GL_TEXTURE_MAG_FILTER: Int
  val GL_TEXTURE_MIN_FILTER: Int
  val GL_TEXTURE_WRAP_S: Int
  val GL_TEXTURE_WRAP_T: Int
  val GL_TRIANGLES: Int
  val GL_TRIANGLE_FAN: Int
  val GL_TRIANGLE_STRIP: Int
  val GL_TRUE: Int
  val GL_UNPACK_ALIGNMENT: Int
  val GL_UNSIGNED_BYTE: Int
  val GL_UNSIGNED_INT: Int
  val GL_UNSIGNED_SHORT: Int
  val GL_UNSIGNED_SHORT_4_4_4_4: Int
  val GL_UNSIGNED_SHORT_5_5_5_1: Int
  val GL_UNSIGNED_SHORT_5_6_5: Int
  val GL_VALIDATE_STATUS: Int
  val GL_VENDOR: Int
  val GL_VERSION: Int
  val GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING: Int
  val GL_VERTEX_ATTRIB_ARRAY_ENABLED: Int
  val GL_VERTEX_ATTRIB_ARRAY_NORMALIZED: Int
  val GL_VERTEX_ATTRIB_ARRAY_POINTER: Int
  val GL_VERTEX_ATTRIB_ARRAY_SIZE: Int
  val GL_VERTEX_ATTRIB_ARRAY_STRIDE: Int
  val GL_VERTEX_ATTRIB_ARRAY_TYPE: Int
  val GL_VERTEX_SHADER: Int
  val GL_VIEWPORT: Int
  val GL_ZERO: Int
  
  def glActiveTexture(texture: Int): Unit
  def glAttachShader(program: Int, shader: Int): Unit
  def glBindAttribLocation(program: Int, index: Int, name: String): Unit
  def glBindBuffer(target: Int, buffer: Int): Unit
  def glBindFramebuffer(target: Int, framebuffer: Int): Unit
  def glBindRenderbuffer(target: Int, renderbuffer: Int): Unit
  def glBindTexture(target: Int, texture: Int): Unit
  def glBlendColor(red: Float, green: Float, blue: Float, alpha: Float): Unit
  def glBlendEquation(mode: Int): Unit
  def glBlendEquationSeparate(modeRGB: Int, modeAlpha: Int): Unit
  def glBlendFunc(sfactor: Int, dfactor: Int): Unit
  def glBlendFuncSeparate(srcRGB: Int, dstRGB: Int, srcAlpha: Int, dstAlpha: Int): Unit
  def glBufferData(target: Int, size: Int, data: Buffer, usage: Int): Unit
  def glBufferSubData(target: Int, offset: Int, size: Int, data: Buffer): Unit
  def glCheckFramebufferStatus(target: Int): Int
  def glClear(mask: Int): Unit
  def glClearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit
  def glClearDepthf(depth: Float): Unit
  def glClearStencil(s: Int): Unit
  def glColorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean): Unit
  def glCompileShader(shader: Int): Unit
  def glCompressedTexImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int, imageSize: Int, data: Buffer): Unit
  def glCompressedTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: Int, imageSize: Int, data: Buffer): Unit
  def glCopyTexImage2D(target: Int, level: Int, internalformat: Int, x: Int, y: Int, width: Int, height: Int, border: Int): Unit
  def glCopyTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, x: Int, y: Int, width: Int, height: Int): Unit
  def glCreateProgram(): Int
  def glCreateShader(_type: Int): Int
  def glCullFace(mode: Int): Unit
  def glDeleteBuffers(n: Int, buffers: Array[Int], offset: Int): Unit
  def glDeleteBuffers(n: Int, buffers: IntBuffer): Unit
  def glDeleteFramebuffers(n: Int, framebuffers: IntBuffer): Unit
  def glDeleteFramebuffers(n: Int, framebuffers: Array[Int], offset: Int): Unit
  def glDeleteProgram(program: Int): Unit
  def glDeleteRenderbuffers(n: Int, renderbuffers: IntBuffer): Unit
  def glDeleteRenderbuffers(n: Int, renderbuffers: Array[Int], offset: Int): Unit
  def glDeleteShader(shader: Int): Unit
  def glDeleteTextures(n: Int, textures: IntBuffer): Unit
  def glDeleteTextures(n: Int, textures: Array[Int], offset: Int): Unit
  def glDepthFunc(func: Int): Unit
  def glDepthMask(flag: Boolean): Unit
  def glDepthRangef(zNear: Float, zFar: Float): Unit
  def glDetachShader(program: Int, shader: Int): Unit
  def glDisable(cap: Int): Unit
  def glDisableVertexAttribArray(index: Int): Unit
  def glDrawArrays(mode: Int, first: Int, count: Int): Unit
  def glDrawElements(mode: Int, count: Int, _type: Int, indices: Buffer): Unit
  def glDrawElements(mode: Int, count: Int, _type: Int, offset: Int): Unit
  def glEnable(cap: Int): Unit
  def glEnableVertexAttribArray(index: Int): Unit
  def glFinish(): Unit
  def glFlush(): Unit
  def glFramebufferRenderbuffer(target: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: Int): Unit
  def glFramebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Int, level: Int): Unit
  def glFrontFace(mode: Int): Unit
  def glGenBuffers(n: Int, buffers: IntBuffer): Unit
  def glGenBuffers(n: Int, buffers: Array[Int], offset: Int): Unit
  def glGenFramebuffers(n: Int, framebuffers: IntBuffer): Unit
  def glGenFramebuffers(n: Int, framebuffers: Array[Int], offset: Int): Unit
  def glGenRenderbuffers(n: Int, renderbuffers: Array[Int], offset: Int): Unit
  def glGenRenderbuffers(n: Int, renderbuffers: IntBuffer): Unit
  def glGenTextures(n: Int, textures: IntBuffer): Unit
  def glGenTextures(n: Int, textures: Array[Int], offset: Int): Unit
  def glGenerateMipmap(target: Int): Unit
  def glGetActiveAttrib(program: Int, index: Int, bufsize: Int, length: IntBuffer, size: IntBuffer, _type: IntBuffer, name: Byte): Unit
  def glGetActiveAttrib(program: Int, index: Int, size: IntBuffer, _type: IntBuffer): String
  def glGetActiveAttrib(program: Int, index: Int, size: Array[Int], sizeOffset: Int, _type: Array[Int], typeOffset: Int): String
  def glGetActiveAttrib(program: Int, index: Int, bufsize: Int, length: Array[Int], lengthOffset: Int, size: Array[Int], sizeOffset: Int, _type: Array[Int], typeOffset: Int, name: Array[Byte], nameOffset: Int): Unit
  def glGetActiveUniform(program: Int, index: Int, bufsize: Int, length: IntBuffer, size: IntBuffer, _type: IntBuffer, name: Byte): Unit
  def glGetActiveUniform(program: Int, index: Int, bufsize: Int, length: Array[Int], lengthOffset: Int, size: Array[Int], sizeOffset: Int, _type: Array[Int], typeOffset: Int, name: Array[Byte], nameOffset: Int): Unit
  def glGetActiveUniform(program: Int, index: Int, size: Array[Int], sizeOffset: Int, _type: Array[Int], typeOffset: Int): String
  def glGetActiveUniform(program: Int, index: Int, size: IntBuffer, _type: IntBuffer): String
  def glGetAttachedShaders(program: Int, maxcount: Int, count: Array[Int], countOffset: Int, shaders: Array[Int], shadersOffset: Int): Unit
  def glGetAttachedShaders(program: Int, maxcount: Int, count: IntBuffer, shaders: IntBuffer): Unit
  def glGetAttribLocation(program: Int, name: String): Int
  def glGetBooleanv(pname: Int, params: Array[Boolean], offset: Int): Unit
  def glGetBooleanv(pname: Int, params: IntBuffer): Unit
  def glGetBufferParameteriv(target: Int, pname: Int, params: IntBuffer): Unit
  def glGetBufferParameteriv(target: Int, pname: Int, params: Array[Int], offset: Int): Unit
  def glGetError(): Int
  def glGetFloatv(pname: Int, params: FloatBuffer): Unit
  def glGetFloatv(pname: Int, params: Array[Float], offset: Int): Unit
  def glGetFramebufferAttachmentParameteriv(target: Int, attachment: Int, pname: Int, params: IntBuffer): Unit
  def glGetFramebufferAttachmentParameteriv(target: Int, attachment: Int, pname: Int, params: Array[Int], offset: Int): Unit
  def glGetIntegerv(pname: Int, params: IntBuffer): Unit
  def glGetIntegerv(pname: Int, params: Array[Int], offset: Int): Unit
  def glGetProgramInfoLog(program: Int): String
  def glGetProgramiv(program: Int, pname: Int, params: IntBuffer): Unit
  def glGetProgramiv(program: Int, pname: Int, params: Array[Int], offset: Int): Unit
  def glGetRenderbufferParameteriv(target: Int, pname: Int, params: Array[Int], offset: Int): Unit
  def glGetRenderbufferParameteriv(target: Int, pname: Int, params: IntBuffer): Unit
  def glGetShaderInfoLog(shader: Int): String
  def glGetShaderPrecisionFormat(shadertype: Int, precisiontype: Int, range: Array[Int], rangeOffset: Int, precision: Array[Int], precisionOffset: Int): Unit
  def glGetShaderPrecisionFormat(shadertype: Int, precisiontype: Int, range: IntBuffer, precision: IntBuffer): Unit
  def glGetShaderSource(shader: Int): String
  def glGetShaderSource(shader: Int, bufsize: Int, length: Array[Int], lengthOffset: Int, source: Array[Byte], sourceOffset: Int): Unit
  def glGetShaderSource(shader: Int, bufsize: Int, length: IntBuffer, source: Byte): Unit
  def glGetShaderiv(shader: Int, pname: Int, params: IntBuffer): Unit
  def glGetShaderiv(shader: Int, pname: Int, params: Array[Int], offset: Int): Unit
  def glGetString(name: Int): String
  def glGetTexParameterfv(target: Int, pname: Int, params: Array[Float], offset: Int): Unit
  def glGetTexParameterfv(target: Int, pname: Int, params: FloatBuffer): Unit
  def glGetTexParameteriv(target: Int, pname: Int, params: Array[Int], offset: Int): Unit
  def glGetTexParameteriv(target: Int, pname: Int, params: IntBuffer): Unit
  def glGetUniformLocation(program: Int, name: String): Int
  def glGetUniformfv(program: Int, location: Int, params: FloatBuffer): Unit
  def glGetUniformfv(program: Int, location: Int, params: Array[Float], offset: Int): Unit
  def glGetUniformiv(program: Int, location: Int, params: IntBuffer): Unit
  def glGetUniformiv(program: Int, location: Int, params: Array[Int], offset: Int): Unit
  def glGetVertexAttribfv(index: Int, pname: Int, params: Array[Float], offset: Int): Unit
  def glGetVertexAttribfv(index: Int, pname: Int, params: FloatBuffer): Unit
  def glGetVertexAttribiv(index: Int, pname: Int, params: IntBuffer): Unit
  def glGetVertexAttribiv(index: Int, pname: Int, params: Array[Int], offset: Int): Unit
  def glHint(target: Int, mode: Int): Unit
  def glIsBuffer(buffer: Int): Boolean
  def glIsEnabled(cap: Int): Boolean
  def glIsFramebuffer(framebuffer: Int): Boolean
  def glIsProgram(program: Int): Boolean
  def glIsRenderbuffer(renderbuffer: Int): Boolean
  def glIsShader(shader: Int): Boolean
  def glIsTexture(texture: Int): Boolean
  def glLineWidth(width: Float): Unit
  def glLinkProgram(program: Int): Unit
  def glPixelStorei(pname: Int, param: Int): Unit
  def glPolygonOffset(factor: Float, units: Float): Unit
  def glReadPixels(x: Int, y: Int, width: Int, height: Int, format: Int, _type: Int, pixels: Buffer): Unit
  def glReleaseShaderCompiler(): Unit
  def glRenderbufferStorage(target: Int, internalformat: Int, width: Int, height: Int): Unit
  def glSampleCoverage(value: Float, invert: Boolean): Unit
  def glScissor(x: Int, y: Int, width: Int, height: Int): Unit
  def glShaderBinary(n: Int, shaders: Array[Int], offset: Int, binaryformat: Int, binary: Buffer, length: Int): Unit
  def glShaderBinary(n: Int, shaders: IntBuffer, binaryformat: Int, binary: Buffer, length: Int): Unit
  def glShaderSource(shader: Int, string: String): Unit
  def glStencilFunc(func: Int, ref: Int, mask: Int): Unit
  def glStencilFuncSeparate(face: Int, func: Int, ref: Int, mask: Int): Unit
  def glStencilMask(mask: Int): Unit
  def glStencilMaskSeparate(face: Int, mask: Int): Unit
  def glStencilOp(fail: Int, zfail: Int, zpass: Int): Unit
  def glStencilOpSeparate(face: Int, fail: Int, zfail: Int, zpass: Int): Unit
  def glTexImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int, format: Int, _type: Int, pixels: Buffer): Unit
  def glTexParameterf(target: Int, pname: Int, param: Float): Unit
  def glTexParameterfv(target: Int, pname: Int, params: Array[Float], offset: Int): Unit
  def glTexParameterfv(target: Int, pname: Int, params: FloatBuffer): Unit
  def glTexParameteri(target: Int, pname: Int, param: Int): Unit
  def glTexParameteriv(target: Int, pname: Int, params: IntBuffer): Unit
  def glTexParameteriv(target: Int, pname: Int, params: Array[Int], offset: Int): Unit
  def glTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: Int, _type: Int, pixels: Buffer): Unit
  def glUniform1f(location: Int, x: Float): Unit
  def glUniform1fv(location: Int, count: Int, v: FloatBuffer): Unit
  def glUniform1fv(location: Int, count: Int, v: Array[Float], offset: Int): Unit
  def glUniform1i(location: Int, x: Int): Unit
  def glUniform1iv(location: Int, count: Int, v: IntBuffer): Unit
  def glUniform1iv(location: Int, count: Int, v: Array[Int], offset: Int): Unit
  def glUniform2f(location: Int, x: Float, y: Float): Unit
  def glUniform2fv(location: Int, count: Int, v: FloatBuffer): Unit
  def glUniform2fv(location: Int, count: Int, v: Array[Float], offset: Int): Unit
  def glUniform2i(location: Int, x: Int, y: Int): Unit
  def glUniform2iv(location: Int, count: Int, v: IntBuffer): Unit
  def glUniform2iv(location: Int, count: Int, v: Array[Int], offset: Int): Unit
  def glUniform3f(location: Int, x: Float, y: Float, z: Float): Unit
  def glUniform3fv(location: Int, count: Int, v: Array[Float], offset: Int): Unit
  def glUniform3fv(location: Int, count: Int, v: FloatBuffer): Unit
  def glUniform3i(location: Int, x: Int, y: Int, z: Int): Unit
  def glUniform3iv(location: Int, count: Int, v: Array[Int], offset: Int): Unit
  def glUniform3iv(location: Int, count: Int, v: IntBuffer): Unit
  def glUniform4f(location: Int, x: Float, y: Float, z: Float, w: Float): Unit
  def glUniform4fv(location: Int, count: Int, v: FloatBuffer): Unit
  def glUniform4fv(location: Int, count: Int, v: Array[Float], offset: Int): Unit
  def glUniform4i(location: Int, x: Int, y: Int, z: Int, w: Int): Unit
  def glUniform4iv(location: Int, count: Int, v: Array[Int], offset: Int): Unit
  def glUniform4iv(location: Int, count: Int, v: IntBuffer): Unit
  def glUniformMatrix2fv(location: Int, count: Int, transpose: Boolean, value: FloatBuffer): Unit
  def glUniformMatrix2fv(location: Int, count: Int, transpose: Boolean, value: Array[Float], offset: Int): Unit
  def glUniformMatrix3fv(location: Int, count: Int, transpose: Boolean, value: Array[Float], offset: Int): Unit
  def glUniformMatrix3fv(location: Int, count: Int, transpose: Boolean, value: FloatBuffer): Unit
  def glUniformMatrix4fv(location: Int, count: Int, transpose: Boolean, value: Array[Float], offset: Int): Unit
  def glUniformMatrix4fv(location: Int, count: Int, transpose: Boolean, value: FloatBuffer): Unit
  def glUseProgram(program: Int): Unit
  def glValidateProgram(program: Int): Unit
  def glVertexAttrib1f(indx: Int, x: Float): Unit
  def glVertexAttrib1fv(indx: Int, values: FloatBuffer): Unit
  def glVertexAttrib1fv(indx: Int, values: Array[Float], offset: Int): Unit
  def glVertexAttrib2f(indx: Int, x: Float, y: Float): Unit
  def glVertexAttrib2fv(indx: Int, values: Array[Float], offset: Int): Unit
  def glVertexAttrib2fv(indx: Int, values: FloatBuffer): Unit
  def glVertexAttrib3f(indx: Int, x: Float, y: Float, z: Float): Unit
  def glVertexAttrib3fv(indx: Int, values: FloatBuffer): Unit
  def glVertexAttrib3fv(indx: Int, values: Array[Float], offset: Int): Unit
  def glVertexAttrib4f(indx: Int, x: Float, y: Float, z: Float, w: Float): Unit
  def glVertexAttrib4fv(indx: Int, values: FloatBuffer): Unit
  def glVertexAttrib4fv(indx: Int, values: Array[Float], offset: Int): Unit
  def glVertexAttribPointer(indx: Int, size: Int, _type: Int, normalized: Boolean, stride: Int, offset: Int): Unit
  def glVertexAttribPointer(indx: Int, size: Int, _type: Int, normalized: Boolean, stride: Int, ptr: Buffer): Unit
  def glViewport(x: Int, y: Int, width: Int, height: Int): Unit

}
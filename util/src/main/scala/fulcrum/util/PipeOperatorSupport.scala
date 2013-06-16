/*
 * PipeOperatorSupport.scala
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
package fulcrum.util

import fulcrum.code.{ Code, Macro }

import language.experimental.macros
import reflect.macros.Context

/**
 * A view that enables the pipe operator `|>` on any value with no extra overhead.
 */
final class PipeOperatorSupport[I](val input: I) extends AnyVal {

  import PipeOperatorSupportMacros._

  /** Applies the specified function to the input value. */
  def |>[O](function: I => O): O = macro PipeOperatorSupportCls.pipe[I, O]

}

/**
 * Definitions of the package macro logic.
 */
object PipeOperatorSupportMacros {

  /**
   * Implementations of the pipe operator support macro logic.
   */
  trait PipeOperatorSupportCls extends Macro with Code {

    import c.universe._

    /** Generates the pipe expansion. */
    def pipe[I: WeakTypeTag, O: WeakTypeTag](function: Expr[I => O]): Expr[O] = 
      $expr(function) $apply reify($prefix[PipeOperatorSupport[I]].splice.input)

  }

  /**
   * Expansions of the pipe operator support macro logic.
   */
  object PipeOperatorSupportCls {

    /** Transforms the non-empty option's value. */
    def pipe[I: c.WeakTypeTag, O: c.WeakTypeTag](c: Context)(function: c.Expr[I => O]): c.Expr[O] =
      new Macro.Bundle[c.type](c) with PipeOperatorSupportCls {}.pipe[I, O](function)

  }

}
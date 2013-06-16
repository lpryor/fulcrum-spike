/*
 * package.scala
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
package fulcrum

import fulcrum.code.Macro

import language.experimental.macros
import language.implicitConversions
import reflect.macros.Context

/**
 * Contains the global definitions from the `utilities` package.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
package object util {

  /**
   * An implicit view that enables the pipe operator `|>` on any value.
   */
  implicit def anyToPipeOperatorSupport[I](input: I): PipeOperatorSupport[I] = //
  macro PackageMacros.PackageObj.anyToPipeOperatorSupport[I]

  /**
   * Definitions of the package macro logic.
   */
  object PackageMacros {

    /**
     * Implementations of the package object macro logic.
     */
    trait PackageObj extends Macro {

      import c.universe._

      /** Generates the pipe implementation. */
      def anyToPipeOperatorSupport[I: WeakTypeTag](input: Expr[I]): Expr[PipeOperatorSupport[I]] =
        reify(new PipeOperatorSupport(input.splice))

    }

    /**
     * Expansions of the package object macro logic.
     */
    object PackageObj {

      /** Generates the pipe expansion. */
      def anyToPipeOperatorSupport[I: c.WeakTypeTag](c: Context)(input: c.Expr[I]): c.Expr[PipeOperatorSupport[I]] =
        bundle(c).anyToPipeOperatorSupport[I](input)

      /** Creates a new macro bundle implementation. */
      def bundle(c: Context) = new Macro.Bundle[c.type](c) with PackageObj

    }

  }

}
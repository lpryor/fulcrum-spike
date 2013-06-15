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

import language.experimental.macros
import reflect.macros.Context

/**
 * Global utilities provided by the `code` package..
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
package object code {

  /** Returns the source code of supplied expression. */
  def showSource(code: Any): String = macro PackageMacros.showSource

  /** Returns the syntax tree code of supplied expression. */
  def showSyntaxTree(code: Any): String = macro PackageMacros.showSyntaxTree

  /**
   * Implementations of the global macros.
   */
  object PackageMacros {

    /** The implementation of the show macro. */
    def showSource(c: Context)(code: c.Expr[Any]): c.Expr[String] = c.literal(c.universe.show(code.tree))

    /** The implementation of the show raw macro. */
    def showSyntaxTree(c: Context)(code: c.Expr[Any]): c.Expr[String] = c.literal(c.universe.showRaw(code.tree))

  }

}
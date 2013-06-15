/*
 * Loops.scala
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
package fulcrum.code

/**
 * Definitions of the looping constructs.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
trait Loops { self: Macro with Basics =>

  import c.universe._

  /**
   * Factory for while expressions.
   */
  object $while {

    /** Creates a while expression using the specified condition and body. */
    def apply[T](condition: Expr[Boolean])(body: => Expr[T]): $expr[Unit] =
      apply("while", condition)(body)

    /** Creates a freshly-named while expression using the specified condition and body. */
    def apply[T](name: String, condition: Expr[Boolean])(body: => Expr[T]): $expr[Unit] =
      apply(newTermName(c.fresh(name)), condition)(body)

    /** Creates a named while expression using the specified condition and body. */
    def apply[T](name: TermName, condition: Expr[Boolean])(body: => Expr[T]): $expr[Unit] = {
      val block = $block($expr(body))
      $expr(LabelDef(name, List(),
        If(condition.tree,
          Block(List(block.tree), Apply(Ident(name), List())),
          Literal(Constant(()))
        )
      ), weakTypeOf[Unit])
    }

  }

  /**
   * Factory for do/while expressions.
   */
  object $do {

    /** Creates a factory for do/while expressions using the specified body. */
    def apply[T](body: => Expr[T]): Condition =
      apply("do")(body)

    /** Creates a factory for freshly-named do/while expressions using the specified body. */
    def apply[T](name: String)(body: => Expr[T]): Condition =
      apply(newTermName(c.fresh(name)))(body)

    /** Creates a factory for named do/while expressions from the specified body. */
    def apply[T](name: TermName)(body: => Expr[T]): Condition = new Condition {
      override def $while(condition: Expr[Boolean]) = {
        val block = $block($expr(body))
        $expr(LabelDef(name, List(),
          Block(List(block.tree),
            If(condition.tree,
              Apply(Ident(name), List()),
              Literal(Constant(())))
          )
        ), weakTypeOf[Unit])
      }
    }

    /**
     * Factory that completes the construction of a do/while expression.
     */
    trait Condition {

      /** Completes the construction of a do/while expression using the specified condition. */
      def $while(condition: Expr[Boolean]): $expr[Unit]

    }

  }

}
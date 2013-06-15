/*
 * LoopsSpecMacros.scala
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

import language.experimental.macros
import reflect.macros.Context

/**
 * Test suite for [[Loops]] that executes tests during macro expansion.
 */
object LoopsSpecMacros {

  /** Verifies [[$while]]. */
  def shouldProvideAWhileConstruct(): Unit = macro Macros.shouldProvideAWhileConstruct

  /** Verifies [[$do]]. */
  def shouldProvideADoWhileConstruct(): Unit = macro Macros.shouldProvideADoWhileConstruct

  /**
   * Definition of the testing macro bundle.
   */
  trait Macros extends Macro with BaseMacros {

    import c.universe._

    /** Verifies [[$while]]. */
    def shouldProvideAWhileConstruct(): Expr[Unit] = test {
      val name = "x"
      val term = newTermName(name)
      val cond = $literal(true)
      val value = $literal("Hello")
      $while(cond) {
        value
      } should not be (null)
      $while(name, cond) {
        value
      } should not be (null)
      $while(term, cond) {
        value
      } should be(LabelDef(term, List(),
        If(cond.tree,
          Block(List(value.tree), Apply(Ident(term), List())),
          Literal(Constant(()))
        )
      ) :: weakTypeOf[Unit])
    }

    /** Verifies [[$do]]. */
    def shouldProvideADoWhileConstruct(): Expr[Unit] = test {
      val name = "x"
      val term = newTermName(name)
      val cond = $literal(true)
      val value = $literal("Hello")
      $do {
        value
      } $while (cond) should not be (null)
      $do(name) {
        value
      } $while (cond) should not be (null)
      $do(term) {
        value
      } $while (cond) should be(LabelDef(name, List(),
        Block(List(value.tree),
          If(cond.tree,
            Apply(Ident(term), List()),
            Literal(Constant(())))
        )
      ) :: weakTypeOf[Unit])
    }

  }

  /**
   * Declarations of the testing macros.
   */
  object Macros {

    /** Verifies [[$while]]. */
    def shouldProvideAWhileConstruct(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideAWhileConstruct()

    /** Verifies [[$do]]. */
    def shouldProvideADoWhileConstruct(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideADoWhileConstruct()

    /** Creates a macro bundle instance. */
    def bundle(c: Context) = new Macro.Bundle[c.type](c) with Macros

  }

}
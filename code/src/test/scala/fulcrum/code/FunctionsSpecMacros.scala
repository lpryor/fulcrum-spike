/*
 * FunctionsSpecMacros.scala
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
 * Test suite for [[Functions]] that executes tests during macro expansion.
 */
object FunctionsSpecMacros {

  /** Verifies [[$function]]. */
  def shouldProvideFactoriesForAnonymousFunctions(): Unit = macro Macros.shouldProvideFactoriesForAnonymousFunctions

  /** Verifies [[$apply]]. */
  def shouldInlineAnonymousFunctionApplications(pf: PartialFunction[String, Int]): Unit = //
  macro Macros.shouldInlineAnonymousFunctionApplications

  /** Verifies [[$applyOrElse]]. */
  def shouldInlineAnonymousPartialFunctionApplications(pf: PartialFunction[String, Int]): Unit = //
  macro Macros.shouldInlineAnonymousPartialFunctionApplications

  /**
   * Definition of the testing macro bundle.
   */
  trait Macros extends Macro with BaseMacros {

    import c.universe._

    /** Verifies [[$function]]. */
    def shouldProvideFactoriesForAnonymousFunctions(): Expr[Unit] = test {
      // Function1
      val name1 = "x"
      val term1 = newTermName(name1)
      val value = $literal(7).as[AnyVal]
      $function((_: $id[String]) => value) should not be (null)
      $function(name1)((_: $id[String]) => value) should not be (null)
      val f1 = $function(term1)((_: $id[String]) => value)
      f1 should be(Function(List(
        ValDef(Modifiers(Flag.PARAM), term1, TypeTree(weakTypeOf[String]), EmptyTree)),
        value.tree
      ) :: weakTypeOf[String => Int])
      f1.tag should be(weakTypeTag[String => AnyVal])
      // Function2
      val name2 = "y"
      val term2 = newTermName(name2)
      $function((_: $id[String], _: $id[Int]) => value) should not be (null)
      $function(name1, name2)((_: $id[String], _: $id[Int]) => value) should not be (null)
      val f2 = $function(term1, term2)((_: $id[String], _: $id[Int]) => value)
      f2 should be(Function(List(
        ValDef(Modifiers(Flag.PARAM), term1, TypeTree(weakTypeOf[String]), EmptyTree),
        ValDef(Modifiers(Flag.PARAM), term2, TypeTree(weakTypeOf[Int]), EmptyTree)),
        value.tree
      ) :: weakTypeOf[(String, Int) => Int])
      f2.tag should be(weakTypeTag[(String, Int) => AnyVal])
    }

    /** Verifies [[$apply]]. */
    def shouldInlineAnonymousFunctionApplications(pf: Expr[PartialFunction[String, Int]]): Expr[Unit] = test {
      // Anonymous Function1
      val term1 = newTermName("s")
      val param1 = $literal("Hello")
      val value = $literal(5)
      val f1 = $function(term1)((s: $id[String]) => value)
      f1 $apply param1 should be(Block(List(
        ValDef(Modifiers(), term1, TypeTree(weakTypeOf[String]), param1.tree)),
        value.tree
      ) :: weakTypeOf[Int])
      // Function1 Reference
      $block {
        val f1r = $val(newTermName("f"), f1)
        f1r $apply param1 should
          be(Apply(Select(Ident(newTermName("f")), newTermName("apply")), List(param1.tree)) :: weakTypeOf[Int])
        $literalUnit
      }
      // Anonymous PartialFunction
      $expr(pf).as[String => Any] $apply param1 should be(Match(param1.tree, List(
        CaseDef(
          Bind(term1, Ident(nme.WILDCARD)),
          Apply(Select(Ident(term1), newTermName("$eq$eq")), List($literal("Hi").tree)),
          $literal(1).tree
        ),
        CaseDef(
          Bind(term1, Ident(nme.WILDCARD)),
          EmptyTree,
          $literal(2).tree
        )
      )) :: weakTypeOf[Int])
      // PartialFunction Reference
      $block {
        val pfr = $val(newTermName("f"), $expr(pf).as[String => Any])
        pfr $apply param1 should
          be(Apply(Select(Ident(newTermName("f")), newTermName("apply")), List(param1.tree)) :: weakTypeOf[Int])
        $literalUnit
      }
      // Anonymous Function2
      val term2 = newTermName("i")
      val param2 = $literal(3)
      val f2 = $function(term1, term2)((s: $id[String], i: $id[Int]) => value)
      f2 $apply (param1, param2) should be(Block(List(
        ValDef(Modifiers(), term1, TypeTree(weakTypeOf[String]), param1.tree),
        ValDef(Modifiers(), term2, TypeTree(weakTypeOf[Int]), param2.tree)),
        value.tree
      ) :: weakTypeOf[Int])
      // Function2 Reference
      $block {
        val f2r = $val(newTermName("f"), f2)
        f2r $apply (param1, param2) should be(Apply(
          Select(Ident(newTermName("f")), newTermName("apply")), List(param1.tree, param2.tree)
        ) :: weakTypeOf[Int])
        $literalUnit
      }
    }

    /** Verifies [[$applyOrElse]]. */
    def shouldInlineAnonymousPartialFunctionApplications(pf: Expr[PartialFunction[String, Int]]): Expr[Unit] = test {
      // Anonymous PartialFunction
      val term1 = newTermName("s")
      val param1 = $literal("Hello")
      val default = $function(term1)((s: $id[String]) => $literal(0))
      val value = $literal(5)
      $expr(pf) $applyOrElse (param1, default) should be(Match(param1.tree, List(
        CaseDef(
          Bind(term1, Ident(nme.WILDCARD)),
          Apply(Select(Ident(term1), newTermName("$eq$eq")), List($literal("Hi").tree)),
          $literal(1).tree
        ),
        CaseDef(
          Bind(term1, Ident(nme.WILDCARD)),
          EmptyTree,
          $literal(2).tree
        ),
        CaseDef(
          Bind(newTermName("defaultCase$"), Ident(nme.WILDCARD)),
          EmptyTree,
          Block(List(ValDef(Modifiers(), term1, TypeTree(), Ident(newTermName("defaultCase$")))), $literal(0).tree)
        )
      )) :: weakTypeOf[Int])
      // PartialFunction Reference
      $block {
        val pfr = $val(newTermName("f"), $expr(pf))
        pfr $applyOrElse (param1, default) should be(Apply(
          Select(Ident(newTermName("f")), newTermName("applyOrElse")), List(param1.tree, default.tree)
        ) :: weakTypeOf[Int])
        $literalUnit
      }
    }

  }

  /**
   * Declarations of the testing macros.
   */
  object Macros {

    /** Verifies [[$function]]. */
    def shouldProvideFactoriesForAnonymousFunctions(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideFactoriesForAnonymousFunctions()

    /** Verifies [[$apply]]. */
    def shouldInlineAnonymousFunctionApplications(c: Context)(pf: c.Expr[PartialFunction[String, Int]]): c.Expr[Unit] =
      bundle(c).shouldInlineAnonymousFunctionApplications(pf)

    /** Verifies [[$applyOrElse]]. */
    def shouldInlineAnonymousPartialFunctionApplications //
    (c: Context)(pf: c.Expr[PartialFunction[String, Int]]): c.Expr[Unit] =
      bundle(c).shouldInlineAnonymousPartialFunctionApplications(pf)

    /** Creates a macro bundle instance. */
    def bundle(c: Context) = new Macro.Bundle[c.type](c) with Macros

  }

}
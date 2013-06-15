/*
 * TypesSpecMacros.scala
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
 * Test suite for [[Types]] that executes tests during macro expansion.
 */
object TypesSpecMacros {

  /** Verifies [[$type]] utility operations. */
  def shouldProvideDistinctNarrowAndIntersectTypeOperations(): Unit = //
  macro Macros.shouldProvideDistinctNarrowAndIntersectTypeOperations

  /** Verifies [[$type]] extra type operations. */
  def shouldProvideExtraOperationsOnTypes(): Unit = macro Macros.shouldProvideExtraOperationsOnTypes

  /**
   * Definition of the testing macro bundle.
   */
  trait Macros extends Macro with BaseMacros {

    import c.universe._

    /** Verifies [[$type]] utility operations. */
    def shouldProvideDistinctNarrowAndIntersectTypeOperations(): Expr[Unit] = test {
      import $type._
      // distinct
      distinct(Seq()) should be(Seq())
      distinct(Seq(weakTypeOf[Int])) should be(Seq(weakTypeOf[Int]))
      distinct(Seq(weakTypeOf[Int], weakTypeOf[Int])) should be(Seq(weakTypeOf[Int]))
      distinct(Seq(weakTypeOf[Int], weakTypeOf[Int], weakTypeOf[String])) should
        be(Seq(weakTypeOf[Int], weakTypeOf[String]))
      // narrow
      narrow(Seq()) should be(Seq())
      narrow(Seq(weakTypeOf[Int])) should be(Seq(weakTypeOf[Int]))
      narrow(Seq(weakTypeOf[Int], weakTypeOf[String])) should be(Seq(weakTypeOf[Int], weakTypeOf[String]))
      narrow(Seq(weakTypeOf[Int], weakTypeOf[Any], weakTypeOf[AnyRef], weakTypeOf[String])) should
        be(Seq(weakTypeOf[Int], weakTypeOf[String]))
      // intersect
      intersect(Seq(), Seq()) should be(Seq())
      intersect(Seq(weakTypeOf[Any], weakTypeOf[String]), Seq()) should be(Seq())
      intersect(Seq(), Seq(weakTypeOf[Any], weakTypeOf[String])) should be(Seq())
      intersect(Seq(weakTypeOf[Any], weakTypeOf[String]), Seq(weakTypeOf[Any], weakTypeOf[Int])) should
        be(Seq(weakTypeOf[Any]))
    }

    /** Verifies [[$type]] extra type operations. */
    def shouldProvideExtraOperationsOnTypes(): Expr[Unit] = test {
      // baseTypes
      weakTypeOf[Any].baseTypes should be(Seq(weakTypeOf[Any]))
      weakTypeOf[Int].baseTypes should
        be(Seq(weakTypeOf[Int], weakTypeOf[AnyVal], weakTypeOf[NotNull], weakTypeOf[Any]))
      // unrefined
      weakTypeOf[Int].unrefined should be(Seq(weakTypeOf[Int]))
      weakTypeOf[String].unrefined should be(Seq(weakTypeOf[String]))
      RefinedType(List(weakTypeOf[CharSequence], weakTypeOf[Cloneable]), newScopeWith()).unrefined should
        be(Seq(weakTypeOf[CharSequence], weakTypeOf[Cloneable]))
      //union
      weakTypeOf[Any] |:| weakTypeOf[Int] should =:=(weakTypeOf[Int])
      weakTypeOf[Int] |:| weakTypeOf[Any] should =:=(weakTypeOf[Int])
      weakTypeOf[CharSequence] |:| weakTypeOf[java.io.Serializable] should
        =:=(RefinedType(List(weakTypeOf[CharSequence], weakTypeOf[java.io.Serializable]), newScopeWith()))
      weakTypeOf[java.io.Serializable] |:| weakTypeOf[CharSequence] should
        =:=(RefinedType(List(weakTypeOf[java.io.Serializable], weakTypeOf[CharSequence]), newScopeWith()))
      // intersect
      weakTypeOf[Any] &:& weakTypeOf[Int] should =:=(weakTypeOf[Any])
      weakTypeOf[Int] &:& weakTypeOf[Any] should =:=(weakTypeOf[Any])
      weakTypeOf[String] &:& weakTypeOf[Unit] should =:=(weakTypeOf[Any])
      weakTypeOf[String] &:& weakTypeOf[java.lang.StringBuilder] should
        =:=(RefinedType(List(weakTypeOf[CharSequence], weakTypeOf[java.io.Serializable]), newScopeWith()))
    }

  }

  /**
   * Declarations of the testing macros.
   */
  object Macros {

    /** Verifies [[$type]] utility operations. */
    def shouldProvideDistinctNarrowAndIntersectTypeOperations(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideDistinctNarrowAndIntersectTypeOperations()

    /** Verifies [[$type]] extra type operations. */
    def shouldProvideExtraOperationsOnTypes(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideExtraOperationsOnTypes()

    /** Creates a macro bundle instance. */
    def bundle(c: Context) = new Macro.Bundle[c.type](c) with Macros

  }

}
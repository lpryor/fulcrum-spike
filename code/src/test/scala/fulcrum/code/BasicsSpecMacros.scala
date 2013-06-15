/*
 * BasicsSpecMacros.scala
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
 * Test suite for [[Core]] that executes tests during macro expansion.
 */
object BasicsSpecMacros {

  /** Verifies [[$code]]. */
  def shouldProvideABaseTypeForCodeConstructs(): Unit = macro Macros.shouldProvideABaseTypeForCodeConstructs

  /** Verifies [[$code.Scope]]. */
  def shouldProvideAnInternalStackOfScopes(): Unit = macro Macros.shouldProvideAnInternalStackOfScopes

  /** Verifies [[$expr]]. */
  def shouldProvideATypedReplacementForMacroExpressions(seq: Seq[String]): Unit = //
  macro Macros.shouldProvideATypedReplacementForMacroExpressions

  /** Verifies [[$literal]]. */
  def shouldProvideAliasesForCreatingLiteralExpressions(): Unit = //
  macro Macros.shouldProvideAliasesForCreatingLiteralExpressions

  /** Verifies [[$prefix]]. */
  def shouldProvideAnAliasForThePrefix(): Unit = macro Macros.shouldProvideAnAliasForThePrefix

  /** Verifies [[$id]]. */
  def shouldProvideBasicSupportForLocalIdentifiers(): Unit = macro Macros.shouldProvideBasicSupportForLocalIdentifiers

  /** Verifies [[$val]]. */
  def shouldProvideSupportForDeclaringImmutableValues(): Unit = //
  macro Macros.shouldProvideSupportForDeclaringImmutableValues

  /** Verifies [[$var]]. */
  def shouldProvideSupportForDeclaringMutableVariables(): Unit = //
  macro Macros.shouldProvideSupportForDeclaringMutableVariables

  /** Verifies [[$block]]. */
  def shouldProvideSupportForBlocksWithStatements(): Unit = macro Macros.shouldProvideSupportForBlocksWithStatements

  /**
   * Definition of the testing macro bundle.
   */
  trait Macros extends Macro with BaseMacros {

    import c.universe._

    /** Verifies [[$code]]. */
    def shouldProvideABaseTypeForCodeConstructs(): Expr[Unit] = test {
      class Impl[T: WeakTypeTag](override val tpe: Option[Type]) extends $code.Support[T, Impl] {
        override def tag: WeakTypeTag[_ <: T] = weakTypeTag[T]
        override def as[U: WeakTypeTag] = new Impl[U](tpe)
      }
      new Impl[Any](None).typed should =:=(weakTypeOf[Any])
      new Impl[Any](Some(weakTypeOf[Int])).typed should =:=(weakTypeOf[Int])
    }

    /** Verifies [[$code.Scope]]. */
    def shouldProvideAnInternalStackOfScopes(): Expr[Unit] = test {
      val tree1 = reify(1).tree
      val tree2 = reify("2").tree
      $code.Scope.withScope {
        $code.Scope.appendToScope(tree1)
        $code.Scope.withScope {
          $code.Scope.appendToScope(tree2)
          3L
        }
      } should be(List(tree1), (List(tree2), 3L))
    }

    /** Verifies [[$expr]]. */
    def shouldProvideATypedReplacementForMacroExpressions(seq: Expr[Seq[String]]): Expr[Unit] = test {
      val tpe = weakTypeOf[List[String]]
      $expr(seq) should be(seq :: tpe)
      $expr(seq, null: Type) should be(seq :: None)
      $expr(seq, tpe) should be(seq :: tpe)
      $expr(seq, None) should be(seq :: None)
      $expr(seq, Some(tpe)) should be(seq :: tpe)
      $expr($expr(seq)) should be(seq :: tpe)
      $expr($expr(seq), null: Type) should be(seq :: None)
      $expr($expr(seq), tpe) should be(seq :: tpe)
      $expr($expr(seq), None) should be(seq :: None)
      $expr($expr(seq), Some(tpe)) should be(seq :: tpe)
      $expr[Seq[String]](seq.tree) should be(seq :: tpe)
      $expr[Seq[String]](seq.tree, null: Type) should be(seq :: None)
      $expr[Seq[String]](seq.tree, tpe) should be(seq :: tpe)
      val en = $expr[Seq[String]](seq.tree, None)
      en should be(seq :: None)
      en.tag should be(weakTypeTag[Seq[String]])
      (en: Expr[Seq[String]]).tree should equalStructure(seq.tree)
      en.as[AnyRef] should be(seq.tree :: None)
      en.as[AnyRef].tag should be(weakTypeTag[AnyRef])
      val $expr(enTree, enTpe) = en
      enTree should equalStructure(seq.tree)
      enTpe should be(None)
      val es = $expr[Seq[String]](seq.tree, Some(tpe))
      es should be(seq :: tpe)
      es.tag should be(weakTypeTag[Seq[String]])
      (es: Expr[Seq[String]]).tree should equalStructure(seq.tree)
      es.as[AnyRef] should be(seq :: tpe)
      es.as[AnyRef].tag should be(weakTypeTag[AnyRef])
      val $expr(esTree, esTpe) = es
      esTree should equalStructure(seq.tree)
      esTpe should =:=(Some(tpe))
      $code.Scope.withScope {
        +en
        +es
        0
      } should be(List(enTree, esTree), 0)
    }

    /** Verifies [[$literal]]. */
    def shouldProvideAliasesForCreatingLiteralExpressions(): Expr[Unit] = test {
      $literalUnit should be(c.literalUnit :: weakTypeOf[Unit])
      $literalNull should be(c.literalNull :: weakTypeOf[Null])
      $literal(true) should be(c.literal(true) :: weakTypeOf[Boolean])
      $literal(1.toByte) should be(c.literal(1.toByte) :: weakTypeOf[Byte])
      $literal(1.toShort) should be(c.literal(1.toShort) :: weakTypeOf[Short])
      $literal('c') should be(c.literal('c') :: weakTypeOf[Char])
      $literal(1) should be(c.literal(1) :: weakTypeOf[Int])
      $literal(1L) should be(c.literal(1L) :: weakTypeOf[Long])
      $literal(1f) should be(c.literal(1f) :: weakTypeOf[Float])
      $literal(1d) should be(c.literal(1d) :: weakTypeOf[Double])
      $literal("s") should be(c.literal("s") :: weakTypeOf[String])
    }

    /** Verifies [[$prefix]]. */
    def shouldProvideAnAliasForThePrefix(): Expr[Unit] = test {
      val tpe = weakTypeOf[BasicsSpecMacros.type]
      val p = $prefix[BasicsSpecMacros.type]
      p should be(c.prefix.tree :: tpe)
      p.tag should be(weakTypeTag[BasicsSpecMacros.type])
    }

    /** Verifies [[$id]]. */
    def shouldProvideBasicSupportForLocalIdentifiers(): Expr[Unit] = test {
      val name = newTermName("i")
      $id[String](name) should be(name :: None)
      $id[String](name, null: Type) should be(name :: None)
      $id[String](name, weakTypeOf[String]) should be(name :: weakTypeOf[String])
      val in = $id[String](name, None)
      in should be(name :: None)
      in.tag should be(weakTypeTag[String])
      (in: $expr[String]) should be(Ident(name) :: None)
      (in: Expr[String]).tree should equalStructure(Ident(name))
      in.as[AnyRef] should be(name :: None)
      in.as[AnyRef].tag should be(weakTypeTag[AnyRef])
      val $id(inName, inTpe) = in
      inName should be(name)
      inTpe should =:=(None)
      val is = $id[String](name, Some(weakTypeOf[String]))
      is should be(name :: weakTypeOf[String])
      is.tag should be(weakTypeTag[String])
      (is: $expr[String]) should be(Ident(name) :: weakTypeOf[String])
      (is: Expr[String]).tree should equalStructure(Ident(name))
      is.as[AnyRef] should be(name :: weakTypeOf[String])
      is.as[AnyRef].tag should be(weakTypeTag[AnyRef])
      val $id(isName, isTpe) = is
      isName should be(name)
      isTpe should =:=(Some(weakTypeOf[String]))
    }

    /** Verifies [[$val]]. */
    def shouldProvideSupportForDeclaringImmutableValues(): Expr[Unit] = test {
      val name = "x"
      val term = newTermName(name)
      val value = $literal("Hello")
      $block {
        $val(value) should be(name :: weakTypeOf[String])
        $val(name, value) should be(name :: weakTypeOf[String])
        $val(name, null: Type, value) should be(name :: None)
        $val(name, weakTypeOf[String], value) should be(name :: weakTypeOf[String])
        $val(name, None, value) should be(name :: None)
        $val(name, Some(weakTypeOf[String]), value) should be(name :: weakTypeOf[String])
        $val(term, value) should be(term :: weakTypeOf[String])
        $val(term, null: Type, value) should be(term :: None)
        $val(term, weakTypeOf[String], value) should be(term :: weakTypeOf[String])
        $literalUnit
      }
      $block {
        val vn = $val(term, None, value)
        vn should be(term :: None)
        vn.tag should be(weakTypeTag[String])
        vn.toExpr should be(Ident(term) :: None)
        vn.as[AnyRef] should be(term :: None)
        vn.as[AnyRef].tag should be(weakTypeTag[AnyRef])
        val $val(vnName, vnTpe) = vn
        vnName should be(term)
        vnTpe should =:=(None)
        vn
      } should be(Block(List(
        ValDef(Modifiers(), term, TypeTree(), value.tree)),
        Ident(term)) :: None)
      $block {
        val vs = $val(term, Some(weakTypeOf[String]), value)
        vs should be(term :: weakTypeOf[String])
        vs.tag should be(weakTypeTag[String])
        vs.toExpr should be(Ident(term) :: weakTypeOf[String])
        vs.as[AnyRef] should be(term :: weakTypeOf[String])
        vs.as[AnyRef].tag should be(weakTypeTag[AnyRef])
        val $val(vsName, vsTpe) = vs
        vsName should be(term)
        vsTpe should =:=(Some(weakTypeOf[String]))
        vs
      } should be(Block(List(
        ValDef(Modifiers(), term, TypeTree(), value.tree)),
        Ident(term)) :: weakTypeOf[String])
    }

    /** Verifies [[$var]]. */
    def shouldProvideSupportForDeclaringMutableVariables(): Expr[Unit] = test {
      val name = "x"
      val term = newTermName(name)
      val value = $literal("Hello")
      val value2 = $literal("World")
      $block {
        $var(value) should be(name :: weakTypeOf[String])
        $var(name, value) should be(name :: weakTypeOf[String])
        $var(name, null: Type, value) should be(name :: None)
        $var(name, weakTypeOf[String], value) should be(name :: weakTypeOf[String])
        $var(name, None, value) should be(name :: None)
        $var(name, Some(weakTypeOf[String]), value) should be(name :: weakTypeOf[String])
        $var(term, value) should be(term :: weakTypeOf[String])
        $var(term, null: Type, value) should be(term :: None)
        $var(term, weakTypeOf[String], value) should be(term :: weakTypeOf[String])
        $literalUnit
      }
      $block {
        val vn = $var(term, None, value)
        vn should be(term :: None)
        vn.tag should be(weakTypeTag[String])
        vn.toExpr should be(Ident(term) :: None)
        vn.as[AnyRef] should be(term :: None)
        vn.as[AnyRef].tag should be(weakTypeTag[AnyRef])
        vn := value2
        val $var(vnName, vnTpe) = vn
        vnName should be(term)
        vnTpe should =:=(None)
        vn
      } should be(Block(List(
        ValDef(Modifiers(Flag.MUTABLE), term, TypeTree(), value.tree),
        Assign(Ident(term), value2.tree)),
        Ident(term)) :: None)
      $block {
        val vs = $var(term, Some(weakTypeOf[String]), value)
        vs should be(term :: weakTypeOf[String])
        vs.tag should be(weakTypeTag[String])
        vs.toExpr should be(Ident(term) :: weakTypeOf[String])
        vs.as[AnyRef] should be(term :: weakTypeOf[String])
        vs.as[AnyRef].tag should be(weakTypeTag[AnyRef])
        vs := value2
        val $var(vsName, vsTpe) = vs
        vsName should be(term)
        vsTpe should =:=(Some(weakTypeOf[String]))
        vs
      } should be(Block(List(
        ValDef(Modifiers(Flag.MUTABLE), term, TypeTree(), value.tree),
        Assign(Ident(term), value2.tree)),
        Ident(term)) :: weakTypeOf[String])
    }

    /** Verifies [[$block]]. */
    def shouldProvideSupportForBlocksWithStatements(): Expr[Unit] = test {
      val in = newTermName("i")
      val jn = newTermName("j")
      val iv = $literal(0)
      val jv = $literal(5)
      $block {
        val i = $var(in, iv)
        +$block {
          val j = $val(jn, jv)
          i := j
        }
        $block(i)
      } should be(Block(List(
        ValDef(Modifiers(Flag.MUTABLE), in, TypeTree(), iv.tree),
        Block(List(
          ValDef(Modifiers(), jn, TypeTree(), jv.tree),
          Assign(Ident(in), Ident(jn))),
          $literalUnit.tree
        )),
        Ident(in)
      ) :: weakTypeOf[Int])
    }

  }

  /**
   * Declarations of the testing macros.
   */
  object Macros {

    /** Verifies [[$code]]. */
    def shouldProvideABaseTypeForCodeConstructs(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideABaseTypeForCodeConstructs()

    /** Verifies [[$code.Scope]]. */
    def shouldProvideAnInternalStackOfScopes(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideAnInternalStackOfScopes()

    /** Verifies [[$expr]]. */
    def shouldProvideATypedReplacementForMacroExpressions(c: Context)(seq: c.Expr[Seq[String]]): c.Expr[Unit] =
      bundle(c).shouldProvideATypedReplacementForMacroExpressions(seq)

    /** Verifies [[$literal]]. */
    def shouldProvideAliasesForCreatingLiteralExpressions(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideAliasesForCreatingLiteralExpressions()

    /** Verifies [[$preifx]]. */
    def shouldProvideAnAliasForThePrefix(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideAnAliasForThePrefix()

    /** Verifies [[$id]]. */
    def shouldProvideBasicSupportForLocalIdentifiers(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideBasicSupportForLocalIdentifiers()

    /** Verifies [[$val]]. */
    def shouldProvideSupportForDeclaringImmutableValues(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideSupportForDeclaringImmutableValues()

    /** Verifies [[$var]]. */
    def shouldProvideSupportForDeclaringMutableVariables(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideSupportForDeclaringMutableVariables()

    /** Verifies [[$block]]. */
    def shouldProvideSupportForBlocksWithStatements(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideSupportForBlocksWithStatements()

    /** Creates a macro bundle instance. */
    def bundle(c: Context) = new Macro.Bundle[c.type](c) with Macros

  }

}
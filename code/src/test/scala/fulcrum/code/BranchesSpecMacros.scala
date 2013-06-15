/*
 * BranchesSpecMacros.scala
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
 * Test suite for [[Branches]] that executes tests during macro expansion.
 */
object BranchesSpecMacros {

  /** Verifies [[$if]]. */
  def shouldProvideAnIfElseIfElseConstruct(): Unit = macro Macros.shouldProvideAnIfElseIfElseConstruct

  /** Verifies [[$match]] and [[$case]]. */
  def shouldProvideAMatchCaseConstruct(): Unit = macro Macros.shouldProvideAMatchCaseConstruct

  /**
   * Definition of the testing macro bundle.
   */
  trait Macros extends Macro with BaseMacros {

    import c.universe._

    /** Verifies [[$if]]. */
    def shouldProvideAnIfElseIfElseConstruct(): Expr[Unit] = test {
      val n = $literal(5)
      // if
      val _if_cnd = reify(n.splice < 0)
      val _if_bdy = $literal(0)
      val _if = $if(_if_cnd) {
        _if_bdy
      }
      _if.branches.length should be(1)
      _if.branches(0)._1 should be(_if_cnd)
      _if.branches(0)._2 should be(_if_bdy.tree :: _if_bdy.tpe)
      _if.tpe should =:=(Some(weakTypeOf[Int]))
      _if.tag should be(weakTypeTag[Int])
      (_if: $expr[Any]) should
        be(If(_if_cnd.tree, _if_bdy.tree, $literalUnit.tree) :: weakTypeOf[AnyVal])
      (_if: Expr[Any]).tree should
        equalStructure(If(_if_cnd.tree, _if_bdy.tree, $literalUnit.tree))
      _if.as[Any].branches.length should be(1)
      _if.as[Any].branches(0)._1 should be(_if_cnd)
      _if.as[Any].branches(0)._2 should be(_if_bdy.tree :: _if_bdy.tpe)
      _if.as[Any].tpe should =:=(Some(weakTypeOf[Int]))
      _if.as[Any].tag should be(weakTypeTag[Any])
      // else if
      val _else_if_cnd = reify(n.splice < 10)
      val _else_if_bdy = $literal(1)
      val _else_if = _if.$else_if(_else_if_cnd) {
        _else_if_bdy
      }
      _else_if.branches.length should be(2)
      _else_if.branches(0)._1 should be(_if_cnd)
      _else_if.branches(0)._2 should be(_if_bdy.tree :: _if_bdy.tpe)
      _else_if.branches(1)._1 should be(_else_if_cnd)
      _else_if.branches(1)._2 should be(_else_if_bdy.tree :: _else_if_bdy.tpe)
      _else_if.tpe should =:=(Some(weakTypeOf[Int]))
      _else_if.tag should be(weakTypeTag[Int])
      (_else_if: $expr[Any]) should
        be(If(_if_cnd.tree, _if_bdy.tree, If(_else_if_cnd.tree, _else_if_bdy.tree, $literalUnit.tree))
          :: weakTypeOf[AnyVal])
      (_else_if: Expr[Any]).tree should
        equalStructure(If(_if_cnd.tree, _if_bdy.tree, If(_else_if_cnd.tree, _else_if_bdy.tree, $literalUnit.tree)))
      _else_if.as[Any].branches.length should be(2)
      _else_if.as[Any].branches(0)._1 should be(_if_cnd)
      _else_if.as[Any].branches(0)._2 should be(_if_bdy.tree :: _if_bdy.tpe)
      _else_if.as[Any].branches(1)._1 should be(_else_if_cnd)
      _else_if.as[Any].branches(1)._2 should be(_else_if_bdy.tree :: _else_if_bdy.tpe)
      _else_if.as[Any].tpe should =:=(Some(weakTypeOf[Int]))
      _else_if.as[Any].tag should be(weakTypeTag[Any])
      // else
      val _else_bdy = $literal(2)
      val _else = _else_if.$else {
        _else_bdy
      }
      _else should
        be(If(_if_cnd.tree, _if_bdy.tree, If(_else_if_cnd.tree, _else_if_bdy.tree, _else_bdy.tree)) :: weakTypeOf[Int])
    }

    /** Verifies [[$match]] and [[$case]]. */
    def shouldProvideAMatchCaseConstruct(): Expr[Unit] = test {
      val v = $literal(5)
      val pattern = Bind(newTermName("i"), Typed(Ident(nme.WILDCARD), Ident(newTypeName("scala.Int"))))
      val guard = reify($id[Int](newTermName("i")).splice < 0)
      val body1 = $literal("one")
      val body2 = $literal("two")
      // case
      val case1 = $case(pattern, guard) {
        body1
      }
      case1.branches.length should be(1)
      case1.branches(0)._1 should be(pattern)
      case1.branches(0)._2 should be(Some(guard))
      case1.branches(0)._3 should be(body1.tree :: body1.tpe)
      case1.tpe should =:=(Some(weakTypeOf[String]))
      case1.tag should be(weakTypeTag[String])
      case1.as[Any].branches.length should be(1)
      case1.as[Any].branches(0)._1 should be(pattern)
      case1.as[Any].branches(0)._2 should be(Some(guard))
      case1.as[Any].branches(0)._3 should be(body1.tree :: body1.tpe)
      case1.as[Any].tpe should =:=(Some(weakTypeOf[String]))
      case1.as[Any].tag should be(weakTypeTag[Any])
      val case2 = case1.$case(pattern) {
        body2
      }
      case2.branches.length should be(2)
      case2.branches(0)._1 should be(pattern)
      case2.branches(0)._2 should be(Some(guard))
      case2.branches(0)._3 should be(body1.tree :: body1.tpe)
      case2.branches(1)._1 should be(pattern)
      case2.branches(1)._2 should be(None)
      case2.branches(1)._3 should be(body2.tree :: body2.tpe)
      case2.tpe should =:=(Some(weakTypeOf[String]))
      case2.tag should be(weakTypeTag[String])
      case2.as[Any].branches.length should be(2)
      case2.as[Any].branches(0)._1 should be(pattern)
      case2.as[Any].branches(0)._2 should be(Some(guard))
      case2.as[Any].branches(0)._3 should be(body1.tree :: body1.tpe)
      case2.as[Any].branches(1)._1 should be(pattern)
      case2.as[Any].branches(1)._2 should be(None)
      case2.as[Any].branches(1)._3 should be(body2.tree :: body2.tpe)
      case2.as[Any].tpe should =:=(Some(weakTypeOf[String]))
      case2.as[Any].tag should be(weakTypeTag[Any])
      // match
      val match1 = v $match {
        case2
      }
      match1 should be(Match(v.tree, List(
        CaseDef(pattern, guard.tree, body1.tree),
        CaseDef(pattern, EmptyTree, body2.tree)
      )) :: weakTypeOf[String])
      match1.tag should be(weakTypeTag[String])
      val match2 = v.toExpression $match {
        case2
      }
      match2 should be(Match(v.tree, List(
        CaseDef(pattern, guard.tree, body1.tree),
        CaseDef(pattern, EmptyTree, body2.tree)
      )) :: weakTypeOf[String])
      match2.tag should be(weakTypeTag[String])
    }

  }

  /**
   * Declarations of the testing macros.
   */
  object Macros {

    /** Verifies [[$if]]. */
    def shouldProvideAnIfElseIfElseConstruct(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideAnIfElseIfElseConstruct()

    /** Verifies [[$match]] and [[$case]]. */
    def shouldProvideAMatchCaseConstruct(c: Context)(): c.Expr[Unit] =
      bundle(c).shouldProvideAMatchCaseConstruct()

    /** Creates a macro bundle instance. */
    def bundle(c: Context) = new Macro.Bundle[c.type](c) with Macros

  }

}
/*
 * Branches.scala
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

import language.{
  higherKinds,
  implicitConversions
}

/**
 * Definitions of the branching constructs.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
trait Branches { self: Macro with Basics with Types =>

  import c.universe._

  /** An implicit view that exposes the match behavior on all code expressions. */
  implicit def expressionToMatchAdapter[T](expr: Expr[T]): $match.Adapter[T] = new $match.Adapter(expr)

  /** An implicit view that exposes the match behavior on all code expressions. */
  implicit def expressionLikeToMatchAdapter[E[X], T](expr: E[T])(implicit ev: E[T] => Expr[T]): $match.Adapter[T] =
    expressionToMatchAdapter(ev(expr))

  /**
   * Representation of an if/else statement in the process of being built.
   */
  trait $if[+T] extends $code[T, $if] {

    import $if._

    /** The branches defined in this if/else statement. */
    def branches: Vector[Branch[T]]

    /** Extends this if/else statement with another condition and body. */
    def $else_if(condition: Expr[Boolean]): Body[T]

    /** Terminates this if/else statement and returns the resulting expression. */
    def $else[U >: T: WeakTypeTag](body: => $expr[U]): $expr[U]

    /** Converts this partial if/else statement to an expression. */
    def toExpr: $expr[Any]

  }

  /**
   * Operations and types associated with if/else statements.
   */
  object $if {

    /** The type of branch used in if/else statements. */
    type Branch[+T] = (Expr[Boolean], $expr[T])

    /** Allows using if/else statements as code expressions. */
    implicit def ifToExpr[T](_if: $if[T]): $expr[Any] = _if.toExpr

    /** Allows using if/else statements as macro expressions. */
    implicit def ifToExpression[T](_if: $if[T]): Expr[Any] = _if.toExpr.toExpression

    /** Creates a new if/else statement with a single condition and body. */
    def apply(condition: Expr[Boolean]): Body[Nothing] = new Body[Nothing] {
      override def apply[U >: Nothing: WeakTypeTag](body: => $expr[U]) = {
        val block = $block(body)
        new Impl[U](Vector((condition, block)), block.tpe)
      }
    }

    /** Trait that delays the selection of type `U >: T` until the body is specified. */
    trait Body[+T] {

      /** Specifies the body for the preceding condition. */
      def apply[U >: T: WeakTypeTag](body: => $expr[U]): $if[U]

    }

    /** Support class for [[$if]] implementations. */
    trait Support[+T] extends $code.Support[T, $if] with $if[T] {

      /** @inheritdoc */
      override def $else_if(condition: Expr[Boolean]) = new Body[T] {
        override def apply[U >: T: WeakTypeTag](body: => $expr[U]) = {
          val block = $block(body)
          new Impl[U](branches :+ (condition, block), tpe flatMap (t => block.tpe map (t &:& _)))
        }
      }

      /** @inheritdoc */
      override def $else[U >: T: WeakTypeTag](body: => $expr[U]): $expr[U] = {
        val block = $block(body)
        val tree = (branches :\ block.tree)((i, e) => If(i._1.tree, i._2.tree, e))
        $expr[U](tree, tpe flatMap (t => block.tpe map (t &:& _)))
      }

      /** @inheritdoc */
      override def toExpr: $expr[Any] = $else($literalUnit)

    }

    /** Basic [[$if]] implementation. */
    final class Impl[+T: WeakTypeTag](override val branches: Vector[Branch[T]], override val tpe: Option[Type])
      extends Support[T] {

      /** @inheritdoc */
      override def tag: WeakTypeTag[_ <: T] = weakTypeTag[T]

      /** @inheritdoc */
      override def as[U: WeakTypeTag] = new Impl[U](branches map (b => (b._1, b._2.as[U])), tpe)

    }

  }

  /**
   * Factory for match expressions.
   */
  object $match {

    /** Creates a match expression from the specified input and cases. */
    def apply[I, O: WeakTypeTag](input: Expr[I])(cases: $case[O]): $expr[O] =
      $expr[O](Match(input.tree, cases.branches.map {
        case (pat, guard, body) => CaseDef(pat, guard map (_.tree) getOrElse EmptyTree, body.tree)
      }.toList), cases.tpe)

    /** A wrapper that exposes the match behavior on all code expressions. */
    final class Adapter[I](input: Expr[I]) {

      /** Matches against the underlying input. */
      def $match[O: WeakTypeTag](cases: $case[O]): $expr[O] = apply(input)(cases)

    }

  }

  /**
   * Representation of a sequence of case statements in the process of being built.
   */
  trait $case[+T] extends $code[T, $case] {

    import Branches.this.$case._

    /** The branches in this sequence of cases. */
    def branches: Vector[Branch[T]]

    /** Extends this sequence of cases with another case statement. */
    def $case(pat: Tree): Body[T]

    /** Extends this sequence of cases with another case statement including a nullable guard. */
    def $case(pat: Tree, guard: Expr[Boolean]): Body[T]

    /** Extends this sequence of cases with another case statement including an optional guard. */
    def $case(pat: Tree, guard: Option[Expr[Boolean]]): Body[T]

  }

  /**
   * Operations and types associated with case statements.
   */
  object $case {

    /** The type of branch used in case statements. */
    type Branch[+T] = (Tree, Option[Expr[Boolean]], $expr[T])

    /** Creates an initial case statement. */
    def apply(pat: Tree): Body[Nothing] = apply(pat, None)

    /** Creates an initial case statement including a nullable guard. */
    def apply(pat: Tree, guard: Expr[Boolean]): Body[Nothing] = apply(pat, Option(guard))

    /** Creates an initial case statement including an optional guard. */
    def apply(pat: Tree, guard: Option[Expr[Boolean]]): Body[Nothing] = new Body[Nothing] {
      override def apply[U >: Nothing: WeakTypeTag](body: => $expr[U]) = {
        val block = $block(body)
        new Impl(Vector((pat, guard, block)), block.tpe)
      }
    }

    /** Trait that delays the selection of type `U >: T` until the body is specified. */
    trait Body[+T] {

      /** Specifies the body for the preceding pattern and optional guard. */
      def apply[U >: T: WeakTypeTag](body: => $expr[U]): $case[U]

    }

    /** Support class for [[$case]] implementations. */
    trait Support[+T] extends $code.Support[T, $case] with $case[T] {

      /** @inheritdoc */
      override def $case(pat: Tree) = $case(pat, None)

      /** @inheritdoc */
      override def $case(pat: Tree, guard: Expr[Boolean]) = $case(pat, Option(guard))

      /** @inheritdoc */
      override def $case(pat: Tree, guard: Option[Expr[Boolean]]) = new Body[T] {
        override def apply[U >: T: WeakTypeTag](body: => $expr[U]) = {
          val block = $block(body)
          new Impl(branches :+ (pat, guard, block), tpe flatMap (t => block.tpe map (t &:& _)))
        }
      }

    }

    /** Basic [[$case]] implementation. */
    final class Impl[+T: WeakTypeTag](override val branches: Vector[Branch[T]], override val tpe: Option[Type])
      extends Support[T] {

      /** @inheritdoc */
      override def tag: WeakTypeTag[_ <: T] = weakTypeTag[T]

      /** @inheritdoc */
      override def as[U: WeakTypeTag] = new Impl[U](branches map (b => (b._1, b._2, b._3.as[U])), tpe)

    }

  }

}
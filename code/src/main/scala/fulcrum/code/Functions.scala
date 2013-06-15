/*
 * Functions.scala
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
 * Mix-in for working with functions in macro bundles.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
trait Functions { self: Macro with Basics with Branches =>

  import c.universe._

  /** An implicit view that exposes the apply behavior on all function-typed code expressions. */
  implicit def function1ToApplyAdapter1[I: WeakTypeTag, O: WeakTypeTag](f: $expr[I => O]): $apply.Adapter1[I, O] =
    new $apply.Adapter1(f)

  /** An implicit view that exposes the apply behavior on all function-typed code expressions. */
  implicit def function1LikeToApplyAdapter1[F[X], I: WeakTypeTag, O: WeakTypeTag] //
  (f: F[I => O])(implicit ev: F[I => O] => $expr[I => O]): $apply.Adapter1[I, O] =
    new $apply.Adapter1(ev(f))

  /** An implicit view that exposes the apply behavior on all function-typed code expressions. */
  implicit def function2ToApplyAdapter2[I1: WeakTypeTag, I2: WeakTypeTag, O: WeakTypeTag] //
  (f: $expr[(I1, I2) => O]): $apply.Adapter2[I1, I2, O] =
    new $apply.Adapter2(f)

  /** An implicit view that exposes the apply behavior on all function-typed code expressions. */
  implicit def function2LikeToApplyAdapter2[F[X], I1: WeakTypeTag, I2: WeakTypeTag, O: WeakTypeTag] //
  (f: F[(I1, I2) => O])(implicit ev: F[(I1, I2) => O] => $expr[(I1, I2) => O]): $apply.Adapter2[I1, I2, O] =
    new $apply.Adapter2(ev(f))

  /** An implicit view that exposes the apply-or-else behavior on all partial-function-typed code expressions. */
  implicit def partialFunctionToApplyOrElseAdapter[I: WeakTypeTag, O: WeakTypeTag] //
  (f: $expr[PartialFunction[I, O]]): $applyOrElse.Adapter[I, O] =
    new $applyOrElse.Adapter(f)

  /** An implicit view that exposes the apply-or-else behavior on all partial-function-typed code expressions. */
  implicit def partialFunctionLikeToApplyOrElseAdapter[F[X], I: WeakTypeTag, O: WeakTypeTag] //
  (f: F[PartialFunction[I, O]]) //
  (implicit ev: F[PartialFunction[I, O]] => $expr[PartialFunction[I, O]]): $applyOrElse.Adapter[I, O] =
    new $applyOrElse.Adapter(ev(f))

  /**
   * Factory for anonymous function expressions.
   */
  object $function {

    /** Creates a new function with the specified body. */
    def apply[I: WeakTypeTag, O: WeakTypeTag](body: $id[I] => $expr[O]): $expr[I => O] =
      apply[I, O]("x")(body)

    /** Creates a new function with the specified base parameter name and body. */
    def apply[I: WeakTypeTag, O: WeakTypeTag](name: String)(body: $id[I] => $expr[O]): $expr[I => O] =
      apply[I, O](newTermName(c.fresh(name)))(body)

    /** Creates a new function with the specified parameter name and body. */
    def apply[I: WeakTypeTag, O: WeakTypeTag](name: TermName)(body: $id[I] => $expr[O]): $expr[I => O] = {
      val it = weakTypeOf[I]
      val block = $block(body($id[I](name, it)))
      $expr[I => O](Function(List(
        ValDef(Modifiers(Flag.PARAM), name, TypeTree(it), EmptyTree)),
        block.tree
      ), block.tpe map { ot =>
        val TypeRef(f1Prefix, f1Symbol, _) = weakTypeOf[I => O]
        TypeRef(f1Prefix, f1Symbol, List(it, ot))
      })
    }

    /** Creates a new function with the specified body. */
    def apply[I1: WeakTypeTag, I2: WeakTypeTag, O: WeakTypeTag] //
    (body: ($id[I1], $id[I2]) => $expr[O]): $expr[(I1, I2) => O] =
      apply[I1, I2, O]("x", "y")(body)

    /** Creates a new function with the specified base parameter names and body. */
    def apply[I1: WeakTypeTag, I2: WeakTypeTag, O: WeakTypeTag] //
    (name1: String, name2: String)(body: ($id[I1], $id[I2]) => $expr[O]): $expr[(I1, I2) => O] =
      apply[I1, I2, O](newTermName(c.fresh(name1)), newTermName(c.fresh(name2)))(body)

    /** Creates a new function with the specified parameter names and body. */
    def apply[I1: WeakTypeTag, I2: WeakTypeTag, O: WeakTypeTag] //
    (name1: TermName, name2: TermName)(body: ($id[I1], $id[I2]) => $expr[O]): $expr[(I1, I2) => O] = {
      val it1 = weakTypeOf[I1]
      val it2 = weakTypeOf[I2]
      val block = $block(body($id[I1](name1, it1), $id[I2](name2, it2)))
      $expr[(I1, I2) => O](Function(List(
        ValDef(Modifiers(Flag.PARAM), name1, TypeTree(it1), EmptyTree),
        ValDef(Modifiers(Flag.PARAM), name2, TypeTree(it2), EmptyTree)),
        block.tree
      ), block.tpe map { ot =>
        val TypeRef(f2Prefix, f2Symbol, _) = weakTypeOf[(I1, I2) => O]
        TypeRef(f2Prefix, f2Symbol, List(it1, it2, ot))
      })
    }

  }

  /**
   * Factory for optimized function application expressions.
   */
  object $apply {

    /** Generates an optimal expression that applies `f` to `input`. */
    def apply[I: WeakTypeTag, O: WeakTypeTag](f: $expr[I => O], input: Expr[I]): $expr[O] = {
      val tpe = f.tpe collect {
        case tpe @ TypeRef(_, _, List(_, t)) if tpe <:< weakTypeOf[Nothing => Any] => t
      }
      f match {
        case $expr(AnonFunction1(_, name, body), _) => $block {
          $val(name, $expr[I](input))
          val bodyTpe = Option(body.tpe) orElse tpe
          $expr[O](c.resetAllAttrs(body), bodyTpe)
        }
        case $expr(AnonPartialFunction(cases), _) =>
          val m = $expr(input) $match {
            val init = cases.init map { caseDef =>
              (caseDef.pat,
                Some(caseDef.guard) filter (_ != EmptyTree) map (c.Expr[Boolean](_)),
                $expr[O](caseDef.body))
            }
            val h = init.head
            ($case(h._1, h._2)(h._3) /: init.tail)((p, t) => p.$case(t._1, t._2)(t._3))
          }
          if (m.tpe.isDefined) m else $expr[O](m, tpe)
        case _ =>
          $expr[O](reify(f.splice(input.splice)), tpe)
      }
    }

    /** Generates an optimal expression that applies `f` to `input1` and `input2`. */
    def apply[I1: WeakTypeTag, I2: WeakTypeTag, O: WeakTypeTag] //
    (f: $expr[(I1, I2) => O], input1: Expr[I1], input2: Expr[I2]): $expr[O] = {
      val tpe = f.tpe collect {
        case tpe @ TypeRef(_, _, List(_, _, t)) if tpe <:< weakTypeOf[(Nothing, Nothing) => Any] => t
      }
      f match {
        case $expr(AnonFunction2(_, name1, _, name2, body), _) => $block {
          $val(name1, $expr[I1](input1))
          $val(name2, $expr[I2](input2))
          val bodyTpe = Option(body.tpe) orElse tpe
          $expr[O](c.resetAllAttrs(body), bodyTpe)
        }
        case _ =>
          $expr[O](reify(f.splice(input1.splice, input2.splice)), tpe)
      }
    }

    /** A wrapper that exposes the apply behavior on all function1-typed code expressions. */
    final class Adapter1[I: WeakTypeTag, O: WeakTypeTag](f: $expr[I => O]) {

      /** Applies the underlying function to the specified input. */
      def $apply(input: Expr[I]): $expr[O] = apply(f, input)

    }

    /** A wrapper that exposes the apply behavior on all function2-typed code expressions. */
    final class Adapter2[I1: WeakTypeTag, I2: WeakTypeTag, O: WeakTypeTag](f: $expr[(I1, I2) => O]) {

      /** Applies the underlying function to the specified input. */
      def $apply(input1: Expr[I1], input2: Expr[I2]): $expr[O] = apply(f, input1, input2)

    }

  }

  /**
   * Factory for optimized partial function application expressions.
   */
  object $applyOrElse {

    /** Generates an optimal expression that applies `f` to `input`. */
    def apply[I: WeakTypeTag, O: WeakTypeTag] //
    (f: $expr[PartialFunction[I, O]], input: Expr[I], default: $expr[I => O]): $expr[O] = {
      val tpe = f.tpe collect {
        case tpe @ TypeRef(_, _, List(_, t)) if tpe <:< weakTypeOf[PartialFunction[Nothing, Any]] => t
      }
      f match {
        case $expr(AnonPartialFunction(cases), _) =>
          val m = $expr(input) $match {
            val all = cases map { caseDef =>
              (caseDef.pat,
                Some(caseDef.guard) filter (_ != EmptyTree) map (c.Expr[Boolean](_)),
                $expr[O](caseDef.body))
            }
            val init = all.init
            val h = init.head
            val l = all.last
            ($case(h._1, h._2)(h._3) /: init.tail)((p, t) => p.$case(t._1, t._2)(t._3)).$case(l._1, l._2) {
              val Bind(defaultCase, _) = l._1
              default $apply c.Expr[I](Ident(defaultCase))
            }
          }
          if (m.tpe.isDefined) m else $expr[O](m, tpe)
        case _ =>
          $expr[O](reify(f.splice.applyOrElse(input.splice, default.splice)), tpe)
      }
    }

    /** A wrapper that exposes the apply behavior on all function1-typed code expressions. */
    final class Adapter[I: WeakTypeTag, O: WeakTypeTag](f: $expr[PartialFunction[I, O]]) {

      /** Applies the underlying function to the specified input. */
      def $applyOrElse(input: Expr[I], default: $expr[I => O]): $expr[O] = apply(f, input, default)

    }

  }

  /**
   * Extractor for anonymous function values.
   */
  private[this] object AnonFunction1 {

    /** Extracts the parameter information and body from an anonymous function declaration. */
    def unapply(tree: Tree): Option[(Modifiers, TermName, Tree)] = tree match {
      case Function(List(ValDef(mods, name, _, _)), body) =>
        Some((mods, name, body))
      case Block(List(), Function(List(ValDef(mods, name, _, _)), body)) =>
        Some((mods, name, body))
      case _ => None
    }

  }

  /**
   * Extractor for anonymous function values.
   */
  private[this] object AnonFunction2 {

    /** Extracts the parameter information and body from an anonymous function declaration. */
    def unapply(tree: Tree): Option[(Modifiers, TermName, Modifiers, TermName, Tree)] = tree match {
      case Function(List(ValDef(mods1, name1, _, _), ValDef(mods2, name2, _, _)), body) =>
        Some((mods1, name1, mods2, name2, body))
      case Block(List(), Function(List(ValDef(mods1, name1, _, _), ValDef(mods2, name2, _, _)), body)) =>
        Some((mods1, name1, mods2, name2, body))
      case _ => None
    }

  }

  /**
   * Extractor for anonymous partial function values.
   */
  private[this] object AnonPartialFunction {

    /** Extracts the parameter information and body from an anonymous function declaration. */
    def unapply(tree: Tree): Option[List[CaseDef]] = tree match {
      // FIXME Hack required until a SYNTHETIC flag is available.
      case Typed(Block(List(clsTree @ ClassDef(mods, _, _, _)), _), _) if show(mods) contains "<synthetic>" =>
        clsTree collect {
          case defTree @ DefDef(_, name, _, _, _, _) if name.decoded == "applyOrElse" => defTree
        } match {
          case defTree :: Nil => defTree collect {
            case matchTree @ Match(_, _) => matchTree
          } match {
            case matchTree :: Nil => Some(matchTree.cases)
            case _ => None
          }
          case _ => None
        }
      case _ => None
    }

  }

}
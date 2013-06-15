/*
 * Basics.scala
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
 * Basic constructs that form the foundation of the macro API.
 */
trait Basics { self: Macro =>

  import c.universe._

  /**
   * Base type for all code constructs.
   */
  trait $code[+T, +Self[X] <: $code[X, Self]] {

    /** The tagged expression type. */
    def tag: WeakTypeTag[_ <: T]

    /** The type of the syntax tree if it is available. */
    def tpe: Option[Type]

    /** The syntax tree type or, if that is not available, the tag type. */
    def typed: Type

    /** Returns this expression with the specified type. */
    def as[U: WeakTypeTag]: Self[U]

  }

  /**
   * Operations and types associated with code constructs.
   */
  object $code {

    /** Support class for [[$code]] implementations. */
    trait Support[+T, +Self[X] <: $code[X, Self]] extends $code[T, Self] {

      /** @inheritdoc */
      override def typed = tpe getOrElse tag.tpe

    }

    /** Internal interface onto the scope stack. */
    private[code] object Scope {

      /** The stack of active scopes. */
      private var scopes = List.empty[Scope]

      /** Executes the specified code in a scope and statements and the code's result. */
      def withScope[T](code: => T): (List[Tree], T) = {
        val scope = new Scope(Vector.empty)
        scopes = scope :: scopes
        val result = try code finally scopes = scopes.tail
        (scope.elements.toList, result)
      }

      /** Appends a tree to the current scope as a statement. */
      def appendToScope(statement: Tree) {
        scopes.head.elements :+= statement
      }

    }

    /** Internal representation of a single scope. */
    private class Scope(var elements: Vector[Tree])

  }

  /**
   * Representation of expression constructs.
   */
  trait $expr[+T] extends $code[T, $expr] {

    /** Appends this expression to the current scope. */
    def unary_+ : $expr[Unit]

    /** The syntax tree that describes this expression. */
    def tree: Tree

    /** Creates a macro expression from this code expression. */
    def toExpression: Expr[T]

  }

  /**
   * Operations and types associated with expressions.
   */
  object $expr {

    /** Allows using code expressions as macro expressions. */
    implicit def exprToExpression[T](expr: $expr[T]): Expr[T] = expr.toExpression

    /** Converts a code expression into another code expression. */
    def apply[T: WeakTypeTag](expr: $expr[T]): $expr[T] = apply[T](expr, expr.tpe)

    /** Converts a code expression and nullable type into another code expression. */
    def apply[T: WeakTypeTag](expr: $expr[T], tpe: Type): $expr[T] = apply[T](expr, Option(tpe))

    /** Converts a code expression and optional type into another code expression. */
    def apply[T: WeakTypeTag](expr: $expr[T], tpe: Option[Type]): $expr[T] = apply[T](expr.tree, tpe)

    /** Converts a macro expression into a code expression. */
    def apply[T: WeakTypeTag](expr: Expr[T]): $expr[T] = apply[T](expr, expr.tree.tpe)

    /** Converts a macro expression and nullable type into a code expression. */
    def apply[T: WeakTypeTag](expr: Expr[T], tpe: Type): $expr[T] = apply[T](expr, Option(tpe))

    /** Converts a macro expression and optional type into a code expression. */
    def apply[T: WeakTypeTag](expr: Expr[T], tpe: Option[Type]): $expr[T] = apply[T](expr.tree, tpe)

    /** Converts a syntax tree into a code expression. */
    def apply[T: WeakTypeTag](tree: Tree): $expr[T] = apply[T](tree, tree.tpe)

    /** Converts a syntax tree and nullable type into a code expression. */
    def apply[T: WeakTypeTag](tree: Tree, tpe: Type): $expr[T] = apply[T](tree, Option(tpe))

    /** Converts a syntax tree and optional type into a code expression. */
    def apply[T: WeakTypeTag](tree: Tree, tpe: Option[Type]): $expr[T] = new Impl[T](tree, tpe)

    /** Extracts the tree and type from the supplied expression. */
    def unapply[T](expr: $expr[T]): Option[(Tree, Option[Type])] =
      Some((expr.tree, expr.tpe))

    /** Support class for [[$expr]] implementations. */
    trait Support[+T] extends $code.Support[T, $expr] with $expr[T] {

      /** @inheritdoc */
      override def unary_+ = {
        $code.Scope.appendToScope(tree)
        $literalUnit
      }

    }

    /** Basic [[$expr]] implementation. */
    final class Impl[+T: WeakTypeTag](override val tree: Tree, override val tpe: Option[Type]) extends Support[T] {

      /** @inheritdoc */
      override def tag: WeakTypeTag[_ <: T] = weakTypeTag[T]

      /** @inheritdoc */
      override def as[U: WeakTypeTag] = new Impl[U](tree, tpe)

      /** @inheritdoc */
      override def toExpression = c.Expr[T](tree)

    }

  }

  /** Operations and types associated with literal values. */
  def $literalUnit: $expr[Unit] = $expr(c.literalUnit, weakTypeOf[Unit])

  /** Operations and types associated with literal values. */
  def $literalNull: $expr[Null] = $expr(c.literalNull, weakTypeOf[Null])

  /**
   * Operations and types associated with literal values.
   */
  object $literal {

    /** Creates a typed literal boolean expression. */
    def apply(b: Boolean): $expr[Boolean] = $expr(c.literal(b), weakTypeOf[Boolean])

    /** Creates a typed literal byte expression. */
    def apply(b: Byte): $expr[Byte] = $expr(c.literal(b), weakTypeOf[Byte])

    /** Creates a typed literal short expression. */
    def apply(s: Short): $expr[Short] = $expr(c.literal(s), weakTypeOf[Short])

    /** Creates a typed literal character expression. */
    def apply(ch: Char): $expr[Char] = $expr(c.literal(ch), weakTypeOf[Char])

    /** Creates a typed literal integer expression. */
    def apply(i: Int): $expr[Int] = $expr(c.literal(i), weakTypeOf[Int])

    /** Creates a typed literal float expression. */
    def apply(f: Float): $expr[Float] = $expr(c.literal(f), weakTypeOf[Float])

    /** Creates a typed literal long expression. */
    def apply(l: Long): $expr[Long] = $expr(c.literal(l), weakTypeOf[Long])

    /** Creates a typed literal double expression. */
    def apply(d: Double): $expr[Double] = $expr(c.literal(d), weakTypeOf[Double])

    /** Creates a typed literal string expression. */
    def apply(s: String): $expr[String] = $expr(c.literal(s), weakTypeOf[String])

  }

  /** A shortcut for obtaining the prefix data as the specified type. */
  def $prefix[T: WeakTypeTag]: $expr[T] = $expr[T](c.prefix.tree)

  /**
   * Operations used to emit blocks of code.
   */
  object $block {

    /** Constructs a block that records statements during the execution of `body`. */
    def apply[T: WeakTypeTag](body: => $expr[T]): $expr[T] =
      $code.Scope.withScope(body) match {
        case (Nil, result) =>
          result
        case (statements, result) =>
          $expr[T](Block(statements, result.tree), result.tpe)
      }

  }

  /**
   * Base type for local values and variables.
   */
  trait $id[+T] extends $code[T, $id] {

    /** The name of the underlying local. */
    def name: TermName

    /** Creates an expression that evaluates to the value of the underlying local. */
    def toExpr: $expr[T]

  }

  /**
   * Operations and types associated with local values and variables.
   */
  object $id {

    /** Allows using IDs as code expressions. */
    implicit def idToExpr[T](id: $id[T]): $expr[T] = id.toExpr

    /** Allows using IDs as macro expressions. */
    implicit def idToExpression[T](id: $id[T]): Expr[T] = id.toExpr.toExpression

    /** Converts a name into an ID. */
    def apply[T: WeakTypeTag](name: TermName): $id[T] = apply[T](name, None)

    /** Converts a name and nullable type into an ID. */
    def apply[T: WeakTypeTag](name: TermName, tpe: Type): $id[T] = apply[T](name, Option(tpe))

    /** Converts a name and optional type into an ID. */
    def apply[T: WeakTypeTag](name: TermName, tpe: Option[Type]): $id[T] = new Impl[T](name, tpe)

    /** Extracts the name and type from the supplied ID. */
    def unapply[T](id: $id[T]): Option[(TermName, Option[Type])] =
      Some((id.name, id.tpe))

    /** Support class for [[$id]] implementations. */
    trait Support[+T] extends $code.Support[T, $id] with $id[T]

    /** Basic [[$id]] implementation. */
    final class Impl[+T: WeakTypeTag](override val name: TermName, override val tpe: Option[Type]) extends Support[T] {

      /** @inheritdoc */
      override def tag: WeakTypeTag[_ <: T] = weakTypeTag[T]

      /** @inheritdoc */
      override def as[U: WeakTypeTag] = new Impl[U](name, tpe)

      /** @inheritdoc */
      override def toExpr = $expr[T](Ident(name), tpe)

    }

  }

  /**
   * Representation of local values.
   */
  trait $val[+T] extends $id[T] with $code[T, $val]

  /**
   * Operations and types associated with local values.
   */
  object $val {

    /** Declares a val in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](value: $expr[T]): $val[T] =
      apply[T]("x", value)

    /** Declares a val in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: String, value: $expr[T]): $val[T] =
      apply[T](name, value.tpe, value)

    /** Declares a val in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: String, tpe: Type, value: $expr[T]): $val[T] =
      apply[T](name, Option(tpe), value)

    /** Declares a val in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: String, tpe: Option[Type], value: $expr[T]): $val[T] =
      apply[T](newTermName(c.fresh(name)), tpe, value)

    /** Declares a val in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: TermName, value: $expr[T]): $val[T] =
      apply[T](name, value.tpe, value)

    /** Declares a val in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: TermName, tpe: Type, value: $expr[T]): $val[T] =
      apply[T](name, Option(tpe), value)

    /** Declares a val in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: TermName, tpe: Option[Type], value: $expr[T]): $val[T] = {
      $code.Scope.appendToScope(ValDef(Modifiers(), name, TypeTree(), value.tree))
      new Impl[T](name, tpe)
    }

    /** Extracts the name and type from the supplied val. */
    def unapply[T](v: $val[T]): Option[(TermName, Option[Type])] =
      Some((v.name, v.tpe))

    /** Support class for [[$val]] implementations. */
    trait Support[+T] extends $id.Support[T] with $val[T]

    /** Basic [[$val]] implementation. */
    final class Impl[+T: WeakTypeTag](override val name: TermName, override val tpe: Option[Type]) extends Support[T] {

      /** @inheritdoc */
      override def tag: WeakTypeTag[_ <: T] = weakTypeTag[T]

      /** @inheritdoc */
      override def as[U: WeakTypeTag] = new Impl[U](name, tpe)

      /** @inheritdoc */
      override def toExpr = $expr[T](Ident(name), tpe)

    }

  }

  /**
   * Representation of local variables.
   */
  trait $var[T] extends $id[T] with $code[T, $var] {

    /** Appends an assignment to this variable to the current scope. */
    def :=(newValue: $expr[T]): $expr[Unit]

  }

  /**
   * Operations and types associated with local variables.
   */
  object $var {

    /** Declares a var in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](initialValue: $expr[T]): $var[T] =
      apply[T]("x", initialValue)

    /** Declares a var in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: String, initialValue: $expr[T]): $var[T] =
      apply[T](name, initialValue.tpe, initialValue)

    /** Declares a var in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: String, tpe: Type, initialValue: $expr[T]): $var[T] =
      apply[T](name, Option(tpe), initialValue)

    /** Declares a var in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: String, tpe: Option[Type], initialValue: $expr[T]): $var[T] =
      apply[T](newTermName(c.fresh(name)), tpe, initialValue)

    /** Declares a var in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: TermName, initialValue: $expr[T]): $var[T] =
      apply[T](name, initialValue.tpe, initialValue)

    /** Declares a var in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: TermName, tpe: Type, initialValue: $expr[T]): $var[T] =
      apply[T](name, Option(tpe), initialValue)

    /** Declares a var in the current scope and returns its identifier. */
    def apply[T: WeakTypeTag](name: TermName, tpe: Option[Type], initialValue: $expr[T]): $var[T] = {
      $code.Scope.appendToScope(ValDef(Modifiers(Flag.MUTABLE), name, TypeTree(), initialValue.tree))
      new Impl[T](name, tpe)
    }

    /** Extracts the name and type from the supplied var. */
    def unapply[T](v: $var[T]): Option[(TermName, Option[Type])] =
      Some((v.name, v.tpe))

    /** Support class for [[$var]] implementations. */
    trait Support[T] extends $id.Support[T] with $var[T] {

      /** @inheritdoc */
      override def :=(newValue: $expr[T]) = {
        $code.Scope.appendToScope(Assign(Ident(name), newValue.tree))
        $literalUnit
      }

    }

    /** Basic [[$var]] implementation. */
    final class Impl[T: WeakTypeTag](override val name: TermName, override val tpe: Option[Type]) extends Support[T] {

      /** @inheritdoc */
      override def tag: WeakTypeTag[_ <: T] = weakTypeTag[T]

      /** @inheritdoc */
      override def as[U: WeakTypeTag] = new Impl[U](name, tpe)

      /** @inheritdoc */
      override def toExpr = $expr[T](Ident(name), tpe)

    }

  }

}
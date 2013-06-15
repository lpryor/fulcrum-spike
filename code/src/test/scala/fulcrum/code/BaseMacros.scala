/*
 * BaseMacros.scala
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

import language.implicitConversions
import org.scalatest.matchers.{
  BeMatcher,
  MatchResult
}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.matchers.Matcher
import java.io.{
  ByteArrayInputStream,
  ByteArrayOutputStream,
  ObjectInputStream,
  ObjectOutputStream
}

import sun.misc.{
  BASE64Encoder,
  BASE64Decoder
}

/**
 * Base trait for testing macro bundles.
 */
trait BaseMacros extends Code with ShouldMatchers { self: Macro =>

  import c.universe._

  /** Support for extra operators on types. */
  implicit def typeToTypeOptionOps(tpe: Type): TypeOptionOps = new TypeOptionOps(Option(tpe))

  /** Support for extra operators on type options. */
  implicit def typeOptionToTypeOptionOps(tpe: Option[Type]): TypeOptionOps = new TypeOptionOps(tpe)

  /** Alias for using the "=:=" type matcher. */
  val =:= = TypeMatching

  /** Alias for using the "equalsStructure" tree matcher. */
  val equalStructure = TreeMatching

  /** Returns an expression that throws the exception thrown by `tests` or a literal unit if no exception is thrown. */
  def test(tests: => Unit): Expr[Unit] = {
    val result = c.literalUnit
    try {
      tests
      result
    } catch {
      case e: Exception =>
        val bytes = new ByteArrayOutputStream
        val out = new ObjectOutputStream(bytes)
        out.writeObject(e)
        out.flush()
        val encoded = c.literal(new BASE64Encoder().encode(bytes.toByteArray))
        reify(throw new ObjectInputStream(new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(encoded.splice)))
          .readObject().asInstanceOf[Exception])
    }
  }

  /** Adds matcher factory methods to type options. */
  final class TypeOptionOps(val tpe: Option[Type]) {

    /** Creates a matcher for the underlying type and specified name. */
    def ::(string: String): BeMatcher[$id[Any]] = new BeMatcher[$id[Any]] {
      override def apply(e: $id[Any]) = {
        val stringsMatch = StringMatching.check(e.name.decoded, string)
        val typesMatch = TypeMatching.check(e.tpe, tpe)
        MatchResult(
          stringsMatch && typesMatch,
          if (stringsMatch)
            TypeMatching.doesNotMatchMsg(e.tpe, tpe)
          else if (typesMatch)
            StringMatching.doesNotMatchMsg(e.name.decoded, string)
          else
            StringMatching.doesNotMatchMsg(e.name.decoded, string) + ", " + TypeMatching.doesNotMatchMsg(e.tpe, tpe),
          if (!stringsMatch)
            TypeMatching.matchesMsg(e.tpe, tpe)
          else if (!typesMatch)
            StringMatching.matchesMsg(e.name.decoded, string)
          else
            StringMatching.matchesMsg(e.name.decoded, string) + ", " + TypeMatching.matchesMsg(e.tpe, tpe)
        )
      }
    }

    /** Creates a matcher for the underlying type and specified name. */
    def ::(name: Name): BeMatcher[$id[Any]] = new BeMatcher[$id[Any]] {
      override def apply(e: $id[Any]) = {
        val namesMatch = NameMatching.check(e.name, name)
        val typesMatch = TypeMatching.check(e.tpe, tpe)
        MatchResult(
          namesMatch && typesMatch,
          if (namesMatch)
            TypeMatching.doesNotMatchMsg(e.tpe, tpe)
          else if (typesMatch)
            NameMatching.doesNotMatchMsg(e.name, name)
          else
            NameMatching.doesNotMatchMsg(e.name, name) + ", " + TypeMatching.doesNotMatchMsg(e.tpe, tpe),
          if (!namesMatch)
            TypeMatching.matchesMsg(e.tpe, tpe)
          else if (!typesMatch)
            NameMatching.matchesMsg(e.name, name)
          else
            NameMatching.matchesMsg(e.name, name) + ", " + TypeMatching.matchesMsg(e.tpe, tpe)
        )
      }
    }

    /** Creates a matcher for the underlying type and specified tree. */
    def ::(tree: Tree): BeMatcher[$expr[Any]] = new BeMatcher[$expr[Any]] {
      override def apply(e: $expr[Any]) = {
        val treesMatch = TreeMatching.check(e.tree, tree)
        val typesMatch = TypeMatching.check(e.tpe, tpe)
        MatchResult(
          treesMatch && typesMatch,
          if (treesMatch)
            TypeMatching.doesNotMatchMsg(e.tpe, tpe)
          else if (typesMatch)
            TreeMatching.doesNotMatchMsg(e.tree, tree)
          else
            TreeMatching.doesNotMatchMsg(e.tree, tree) + ", " + TypeMatching.doesNotMatchMsg(e.tpe, tpe),
          if (!treesMatch)
            TypeMatching.matchesMsg(e.tpe, tpe)
          else if (!typesMatch)
            TreeMatching.matchesMsg(e.tree, tree)
          else
            TreeMatching.matchesMsg(e.tree, tree) + ", " + TypeMatching.matchesMsg(e.tpe, tpe)
        )
      }
    }

    /** Creates a matcher for the underlying type and specified expression's tree. */
    def ::(expr: Expr[Any]): BeMatcher[$expr[Any]] = expr.tree :: this

  }

  /**
   * Base trait for matcher factories.
   */
  trait Matching[T] {

    /** The string to prefix messages with. */
    def msgPrefix: String = ""

    /** Returns a matcher that implements this instance's matching logic. */
    def apply(expected: T): Matcher[T] = new Matcher[T] {
      def apply(actual: T): MatchResult = MatchResult(
        check(actual, expected),
        doesNotMatchMsg(actual, expected),
        matchesMsg(actual, expected)
      )
    }

    /** Returns true if the specified items match. */
    def check(left: T, right: T): Boolean

    /** Returns the message that describes a match. */
    def matchesMsg(left: T, right: T): String =
      Matching.genericMatchesMsg(msgPrefix, left, right)

    /** Returns the message that describes a failure to match. */
    def doesNotMatchMsg(left: T, right: T): String =
      Matching.genericDoesNotMatchMsg(msgPrefix, left, right)

  }

  object Matching {

    /** Returns the message that describes a match. */
    def genericMatchesMsg(msgPrefix: String, left: Any, right: Any): String =
      "%s \"%s\" matches \"%s\"" format (msgPrefix, left, right)

    /** Returns the message that describes a failure to match. */
    def genericDoesNotMatchMsg(msgPrefix: String, left: Any, right: Any): String =
      "%s \"%s\" does not match \"%s\"" format (msgPrefix, left, right)

  }

  /**
   * Base trait for matcher factories.
   */
  trait OptionMatching[T] extends Matching[T] {

    /** Returns a matcher that implements this instance's matching logic with optional values. */
    def apply(expected: Option[T]): Matcher[Option[T]] = new Matcher[Option[T]] {
      def apply(actual: Option[T]): MatchResult = MatchResult(
        check(actual, expected),
        doesNotMatchMsg(actual, expected),
        matchesMsg(actual, expected)
      )
    }

    /** Returns true if the specified optional items match. */
    def check(left: Option[T], right: Option[T]): Boolean =
      left.isEmpty == right.isEmpty && left.forall(check(_, right.get))

    /** Returns the message that describes a match of optional values. */
    def matchesMsg(left: Option[T], right: Option[T]): String =
      Matching.genericMatchesMsg(msgPrefix, left, right)

    /** Returns the message that describes a failure to match optional values. */
    def doesNotMatchMsg(left: Option[T], right: Option[T]): String =
      Matching.genericDoesNotMatchMsg(msgPrefix, left, right)

  }

  /**
   * Utility for matching equivalent base names.
   */
  object StringMatching extends Matching[String] {

    /** @inheritdoc */
    override def msgPrefix = "Base name"

    /** @inheritdoc */
    override def check(left: String, right: String) = left != right && left.contains(right)

  }

  /**
   * Utility for matching equivalent names.
   */
  object NameMatching extends Matching[Name] {

    /** @inheritdoc */
    override def msgPrefix = "Name"

    /** @inheritdoc */
    override def check(left: Name, right: Name) = left == right

  }

  /**
   * Utility for matching equivalent types and type options.
   */
  object TypeMatching extends OptionMatching[Type] {

    /** @inheritdoc */
    override def msgPrefix = "Type"

    /** @inheritdoc */
    override def check(left: Type, right: Type) = left =:= right

  }

  /**
   * Utility for matching structurally-equivalent trees.
   */
  object TreeMatching extends Matching[Tree] {

    /** @inheritdoc */
    override def msgPrefix = "Tree"

    /** @inheritdoc */
    override def check(left: Tree, right: Tree) = left equalsStructure right

  }

}
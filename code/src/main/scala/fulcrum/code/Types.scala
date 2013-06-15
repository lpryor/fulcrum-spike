/*
 * Types.scala
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

/**
 * Definitions of the extensions to the macro type system.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
trait Types { self: Macro with Basics =>

  import c.universe._

  /** An implicit view that exposes extra operations on types. */
  implicit def typeToTypeOps(t: Type): $type.Ops = new $type.Ops(t)

  /**
   * Utilities for working with types.
   */
  object $type {

    /** Removes any duplicate types from the supplied sequence. */
    def distinct(types: Seq[Type]): Seq[Type] = {
      var results = Vector.empty[Type]
      for (t <- types.view if !results.exists(_ =:= t))
        results :+= t
      results
    }

    /** Filters out types that have a subtype present in the supplied sequence. */
    def narrow(types: Seq[Type]): Seq[Type] =
      types filterNot (t => types exists (u => !(u =:= t) && u <:< t))

    /** Returns the sequence of types that are present in all of the supplied sequences. */
    def intersect(types: Seq[Type], otherTypes: Seq[Type]): Seq[Type] =
      types filter (t => otherTypes exists (_ =:= t))

    /**
     * Extra operations made available on types.
     */
    final class Ops(self: Type) {

      /** Returns the base types of the underlying type. */
      def baseTypes: Seq[Type] = self.baseClasses map self.baseType

      /** Expands all nested refined types into a flat sequence of types. */
      def unrefined: Seq[Type] = self match {
        case RefinedType(types, _) => types flatMap (_.unrefined)
        case t => Seq(t)
      }

      /** Returns a type that represents the union of the underlying and specified types. */
      def |:|(that: Type): Type =
        if (self <:< that)
          self
        else if (that <:< self)
          that
        else
          narrow(distinct(unrefined ++ that.unrefined)) match {
            case Seq() => weakTypeOf[Nothing]
            case Seq(t) => t
            case seq => RefinedType(seq.toList, newScopeWith())
          }

      /** Returns a type that represents the intersection of the underlying and specified types. */
      def &:&(that: Type): Type =
        if (self <:< that)
          that
        else if (that <:< self)
          self
        else
          distinct(unrefined ++ that.unrefined) map (_.baseTypes) reduceLeft intersect match {
            case Seq() => weakTypeOf[Nothing]
            case Seq(t) => t
            case seq => seq reduceLeft (_ |:| _)
          }

    }

  }

}
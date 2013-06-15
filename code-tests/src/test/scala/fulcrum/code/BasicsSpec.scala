/*
 * BasicsSpec.scala
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

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

/**
 * Test suite for [[Core]].
 */
@org.junit.runner.RunWith(classOf[org.scalatest.junit.JUnitRunner])
class BasicsSpec extends FunSpec with ShouldMatchers {

  describe("Basics") {

    it("should provide a base type for code constructs") {
      BasicsSpecMacros.shouldProvideABaseTypeForCodeConstructs()
    }

    it("should provide an internal stack of scopes") {
      BasicsSpecMacros.shouldProvideAnInternalStackOfScopes()
    }

    it("should provide a typed replacement for macro expressions") {
      BasicsSpecMacros.shouldProvideATypedReplacementForMacroExpressions(List("Hello", "World"))
    }

    it("should provide aliases for creating literal expressions") {
      BasicsSpecMacros.shouldProvideAliasesForCreatingLiteralExpressions()
    }

    it("should provide an alias for the prefix") {
      BasicsSpecMacros.shouldProvideAnAliasForThePrefix()
    }

    it("should provide basic support for local identifiers") {
      BasicsSpecMacros.shouldProvideBasicSupportForLocalIdentifiers()
    }

    it("should provide support for declaring immutable values") {
      BasicsSpecMacros.shouldProvideSupportForDeclaringImmutableValues()
    }

    it("should provide support for declaring mutable variables") {
      BasicsSpecMacros.shouldProvideSupportForDeclaringMutableVariables()
    }
    
    it("should provide support for blocks with statements") {
      BasicsSpecMacros.shouldProvideSupportForBlocksWithStatements()
    }

  }

}
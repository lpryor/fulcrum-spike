/*
 * BranchesSpec.scala
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
 * Test suite for [[Branches]].
 */
@org.junit.runner.RunWith(classOf[org.scalatest.junit.JUnitRunner])
class BranchesSpec extends FunSpec with ShouldMatchers {

  describe("Branches") {

    it("should provide an if / else if / else construct") {
      BranchesSpecMacros.shouldProvideAnIfElseIfElseConstruct()
    }

    it("should provide a match / case construct") {
      BranchesSpecMacros.shouldProvideAMatchCaseConstruct()
    }
    
  }

}
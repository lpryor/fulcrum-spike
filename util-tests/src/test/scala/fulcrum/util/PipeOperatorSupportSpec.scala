/*
 * PipeOperatorSupportSpec.scala
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
package fulcrum.util

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

/**
 * Test case for [[PipeOperatorSupport]].
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
@org.junit.runner.RunWith(classOf[org.scalatest.junit.JUnitRunner])
class PipeOperatorSupportSpec extends FunSpec with ShouldMatchers {

  describe("PipeOperatorSupport") {

    it("should apply functions passed to the pipe") {
      val s = "Hello"
      val toLowerCase = ((_: String).toLowerCase)
      s |> (_.toUpperCase) should be("HELLO")
      s |> { case s => s.toUpperCase } should be("HELLO")
      s |> (_.toUpperCase) |> toLowerCase should be("hello")
    }

  }

}
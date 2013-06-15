/*
 * Macro.scala
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
 * Base trait for types that define macro bundles.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
trait Macro {

  /** The singleton context instance. */
  val c: Context

}

/**
 * Factory for macro bundle implementations.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
object Macro {

  /**
   * Base class for instantiating macro bundles.
   */
  abstract class Bundle[C <: Context]( final override val c: C) extends Macro

}
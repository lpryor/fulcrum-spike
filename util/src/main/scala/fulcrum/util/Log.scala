/*
 * Log.scala
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

import language.experimental.macros
import reflect.macros.Context

import fulcrum.code.{ Code, Macro }

import java.util.logging.{
  Level => JLevel,
  Logger => JLogger
}

/**
 * An inlined wrapper around a logger.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class Log(val jLogger: JLogger) extends AnyVal {

  import LogMacros._

  /** Returns true if the specified level is enabled. */
  def isErrorEnabled: Boolean = macro LogCls.isErrorEnabled

  /** Returns true if the specified level is enabled. */
  def isWarnEnabled: Boolean = macro LogCls.isWarnEnabled

  /** Returns true if the specified level is enabled. */
  def isInfoEnabled: Boolean = macro LogCls.isInfoEnabled

  /** Returns true if the specified level is enabled. */
  def isDebugEnabled: Boolean = macro LogCls.isDebugEnabled

  /** Returns true if the specified level is enabled. */
  def isEnabled(level: Log.Level): Boolean = macro LogCls.isEnabled

  /** Logs a message if the error level is enabled. */
  def error(message: String): Unit = macro LogCls.error

  /** Logs a message and throwable if the error level is enabled. */
  def error(message: String, thrown: Throwable): Unit = macro LogCls.errorWith

  /** Logs a message if the warn level is enabled. */
  def warn(message: String): Unit = macro LogCls.warn

  /** Logs a message and throwable if the warn level is enabled. */
  def warn(message: String, thrown: Throwable): Unit = macro LogCls.warnWith

  /** Logs a message if the info level is enabled. */
  def info(message: String): Unit = macro LogCls.info

  /** Logs a message and throwable if the info level is enabled. */
  def info(message: String, thrown: Throwable): Unit = macro LogCls.infoWith

  /** Logs a message if the debug level is enabled. */
  def debug(message: String): Unit = macro LogCls.debug

  /** Logs a message and throwable if the debug level is enabled. */
  def debug(message: String, thrown: Throwable): Unit = macro LogCls.debugWith

  /** Logs a message if the specified level is enabled. */
  def apply(level: Log.Level, message: String): Unit = macro LogCls.apply

  /** Logs a message and throwable if the specified level is enabled. */
  def apply(level: Log.Level, message: String, thrown: Throwable): Unit = macro LogCls.applyWith

}

/**
 * Factory object for logs.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
object Log {

  import LogMacros._

  /** Creates a logger object for the calling type. */
  def apply(): Log = macro LogObj.apply

  /** Creates a logger object for the specified name. */
  def apply(name: String): Log = macro LogObj.applyWithName

  /**
   * An inlined wrapper around log levels.
   */
  final class Level(val jLevel: JLevel) extends AnyVal

  /**
   * Definitions of the available log levels.
   */
  object Level {

    /** The error level. */
    def Error: Log.Level = macro LevelObj.Error

    /** The warn level. */
    def Warn: Log.Level = macro LevelObj.Warn

    /** The info level. */
    def Info: Log.Level = macro LevelObj.Info

    /** The debug level. */
    def Debug: Log.Level = macro LevelObj.Debug

  }

  /**
   * Factory type used to instantiate Logger instances.
   */
  trait Factory extends (String => JLogger)

  /**
   * Definition of the default log factory.
   */
  object Factory {

    /**
     * The default log factory.
     */
    implicit object Default extends Factory {

      /** @inheritdoc */
      override def apply(name: String) = JLogger.getLogger(name)

    }

  }

}

/**
 * Definitions of the logging macro logic.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
object LogMacros {

  /**
   * Implementations of the log class macro logic.
   */
  trait LogCls extends Macro with Code {

    import c.universe._

    /** The prefix of the macro application. */
    def prefix = $prefix[Log]

    /** Captures the name of the enclosing class of the call. */
    def enclosingClass = c.literal(c.enclosingClass.symbol.fullName)

    /** Captures the name of the enclosing method of the call. */
    def enclosingMethod =
      c.literal(if (c.enclosingMethod == null) "<init>" else c.enclosingMethod.symbol.name.decoded)

    /** Macro that expands to a test of the log's level. */
    def isErrorEnabled: Expr[Boolean] = isEnabled(reify(new Log.Level(JLevel.SEVERE)))

    /** Macro that expands to a test of the log's level. */
    def isWarnEnabled: Expr[Boolean] = isEnabled(reify(new Log.Level(JLevel.WARNING)))

    /** Macro that expands to a test of the log's level. */
    def isInfoEnabled: Expr[Boolean] = isEnabled(reify(new Log.Level(JLevel.INFO)))

    /** Macro that expands to a test of the log's level. */
    def isDebugEnabled: Expr[Boolean] = isEnabled(reify(new Log.Level(JLevel.CONFIG)))

    /** Macro that expands to a test of the log's level. */
    def isEnabled(level: Expr[Log.Level]): Expr[Boolean] =
      reify(prefix.splice.jLogger.isLoggable(level.splice.jLevel))

    /** Macro that expands to the conditional submission of an error log entry. */
    def error(message: Expr[String]): Expr[Unit] = apply(reify(new Log.Level(JLevel.SEVERE)), message)

    /** Macro that expands to the conditional submission of an error log entry. */
    def errorWith(message: Expr[String], thrown: Expr[Throwable]): Expr[Unit] =
      applyWith(reify(new Log.Level(JLevel.SEVERE)), message, thrown)

    /** Macro that expands to the conditional submission of a warn log entry. */
    def warn(message: Expr[String]): Expr[Unit] = apply(reify(new Log.Level(JLevel.WARNING)), message)

    /** Macro that expands to the conditional submission of a warn log entry. */
    def warnWith(message: Expr[String], thrown: Expr[Throwable]): Expr[Unit] =
      applyWith(reify(new Log.Level(JLevel.WARNING)), message, thrown)

    /** Macro that expands to the conditional submission of an info log entry. */
    def info(message: Expr[String]): Expr[Unit] = apply(reify(new Log.Level(JLevel.INFO)), message)

    /** Macro that expands to the conditional submission of an info log entry. */
    def infoWith(message: Expr[String], thrown: Expr[Throwable]): Expr[Unit] =
      applyWith(reify(new Log.Level(JLevel.INFO)), message, thrown)

    /** Macro that expands to the conditional submission of a debug log entry. */
    def debug(message: Expr[String]): Expr[Unit] = apply(reify(new Log.Level(JLevel.CONFIG)), message)

    /** Macro that expands to the conditional submission of a debug log entry. */
    def debugWith(message: Expr[String], thrown: Expr[Throwable]): Expr[Unit] =
      applyWith(reify(new Log.Level(JLevel.CONFIG)), message, thrown)

    /** Macro that expands to the conditional submission of a log entry. */
    def apply(level: Expr[Log.Level], message: Expr[String]): Expr[Unit] = reify {
      val jLogger = prefix.splice.jLogger
      val jLevel = level.splice.jLevel
      if (jLogger.isLoggable(jLevel))
        jLogger.logp(
          jLevel,
          enclosingClass.splice,
          enclosingMethod.splice,
          message.splice)
    }

    /** Macro that expands to the conditional submission of a log entry with a throwable. */
    def applyWith(level: Expr[Log.Level], message: Expr[String], thrown: Expr[Throwable]): Expr[Unit] = reify {
      val jLogger = prefix.splice.jLogger
      val jLevel = level.splice.jLevel
      if (jLogger.isLoggable(jLevel))
        jLogger.logp(
          jLevel,
          enclosingClass.splice,
          enclosingMethod.splice,
          message.splice,
          thrown.splice)
    }

  }

  /**
   * Expansions of the log class macro logic.
   */
  object LogCls {

    /** Macro that expands to a test of the log's level. */
    def isErrorEnabled(c: Context): c.Expr[Boolean] = bundle(c).isErrorEnabled

    /** Macro that expands to a test of the log's level. */
    def isWarnEnabled(c: Context): c.Expr[Boolean] = bundle(c).isWarnEnabled

    /** Macro that expands to a test of the log's level. */
    def isInfoEnabled(c: Context): c.Expr[Boolean] = bundle(c).isInfoEnabled

    /** Macro that expands to a test of the log's level. */
    def isDebugEnabled(c: Context): c.Expr[Boolean] = bundle(c).isDebugEnabled

    /** Macro that expands to a test of the log's level. */
    def isEnabled(c: Context)(level: c.Expr[Log.Level]): c.Expr[Boolean] = bundle(c).isEnabled(level)

    /** Macro that expands to the conditional submission of an error log entry. */
    def error(c: Context)(message: c.Expr[String]): c.Expr[Unit] = bundle(c).error(message)

    /** Macro that expands to the conditional submission of an error log entry. */
    def errorWith(c: Context)(message: c.Expr[String], thrown: c.Expr[Throwable]): c.Expr[Unit] =
      bundle(c).errorWith(message, thrown)

    /** Macro that expands to the conditional submission of a warn log entry. */
    def warn(c: Context)(message: c.Expr[String]): c.Expr[Unit] = bundle(c).warn(message)

    /** Macro that expands to the conditional submission of a warn log entry. */
    def warnWith(c: Context)(message: c.Expr[String], thrown: c.Expr[Throwable]): c.Expr[Unit] =
      bundle(c).warnWith(message, thrown)

    /** Macro that expands to the conditional submission of an info log entry. */
    def info(c: Context)(message: c.Expr[String]): c.Expr[Unit] = bundle(c).info(message)

    /** Macro that expands to the conditional submission of an info log entry. */
    def infoWith(c: Context)(message: c.Expr[String], thrown: c.Expr[Throwable]): c.Expr[Unit] =
      bundle(c).infoWith(message, thrown)

    /** Macro that expands to the conditional submission of a debug log entry. */
    def debug(c: Context)(message: c.Expr[String]): c.Expr[Unit] = bundle(c).debug(message)

    /** Macro that expands to the conditional submission of a debug log entry. */
    def debugWith(c: Context)(message: c.Expr[String], thrown: c.Expr[Throwable]): c.Expr[Unit] =
      bundle(c).debugWith(message, thrown)

    /** Macro that expands to the conditional submission of a log entry. */
    def apply(c: Context)(level: c.Expr[Log.Level], message: c.Expr[String]): c.Expr[Unit] =
      bundle(c).apply(level, message)

    /** Macro that expands to the conditional submission of a log entry. */
    def applyWith(c: Context)(
      level: c.Expr[Log.Level],
      message: c.Expr[String],
      thrown: c.Expr[Throwable]): c.Expr[Unit] =
      bundle(c).applyWith(level, message, thrown)

    /** Creates a new macro bundle implementation. */
    def bundle(c: Context) = new Macro.Bundle[c.type](c) with LogCls

  }

  /**
   * Implementations of the log object macro logic.
   */
  trait LogObj extends Macro {

    import c.universe._

    /** Macro that expands to the creation of the logger object. */
    def apply(): Expr[Log] = applyWithName(c.literal(c.enclosingClass.symbol.fullName))

    /** Macro that expands to the creation of the logger object. */
    def applyWithName(name: Expr[String]): Expr[Log] =
      c.universe.reify(new Log(c.Expr[Log.Factory](c.inferImplicitValue(c.typeOf[Log.Factory])).splice(name.splice)))

  }

  /**
   * Expansions of the log object macro logic.
   */
  object LogObj {

    /** Macro that expands to the creation of the logger object. */
    def apply(c: Context)(): c.Expr[Log] = bundle(c).apply()

    /** Macro that expands to the creation of the logger object. */
    def applyWithName(c: Context)(name: c.Expr[String]): c.Expr[Log] = bundle(c).applyWithName(name)

    /** Creates a new macro bundle implementation. */
    def bundle(c: Context) = new Macro.Bundle[c.type](c) with LogObj

  }

  /**
   * Implementations of the level object macro logic.
   */
  trait LevelObj extends Macro {

    import c.universe._

    /** Macro that expands to the creation of the error level. */
    def Error: Expr[Log.Level] = reify(new Log.Level(JLevel.SEVERE))

    /** Macro that expands to the creation of the warn level. */
    def Warn: Expr[Log.Level] = reify(new Log.Level(JLevel.WARNING))

    /** Macro that expands to the creation of the info level. */
    def Info: Expr[Log.Level] = reify(new Log.Level(JLevel.INFO))

    /** Macro that expands to the creation of the debug level. */
    def Debug: Expr[Log.Level] = reify(new Log.Level(JLevel.CONFIG))

  }

  /**
   * Expansions of the level object macro logic.
   */
  object LevelObj {

    /** Macro that expands to the creation of the error level. */
    def Error(c: Context): c.Expr[Log.Level] = bundle(c).Error

    /** Macro that expands to the creation of the warn level. */
    def Warn(c: Context): c.Expr[Log.Level] = bundle(c).Warn

    /** Macro that expands to the creation of the info level. */
    def Info(c: Context): c.Expr[Log.Level] = bundle(c).Info

    /** Macro that expands to the creation of the debug level. */
    def Debug(c: Context): c.Expr[Log.Level] = bundle(c).Debug

    /** Creates a new macro bundle implementation. */
    def bundle(c: Context) = new Macro.Bundle[c.type](c) with LevelObj

  }

}
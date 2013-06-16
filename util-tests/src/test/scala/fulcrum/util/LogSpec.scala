/*
 * LogSpec.scala
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

import java.util.logging.{
  Level => JLevel,
  Logger => JLogger
}

/**
 * Test case for [[Log]].
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
@org.junit.runner.RunWith(classOf[org.scalatest.junit.JUnitRunner])
class LogSpec extends FunSpec with ShouldMatchers {

  import MockLogger._

  val message = "message"
  val thrown = new IllegalStateException
  val sourceClass = getClass.getName

  describe("Log") {

    it("should use java loggers and levels for its internal representation") {
      val logger = new MockLogger("test")
      implicit val factory = new Factory(logger)
      Log("test") should equal(new Log(logger))
      Log.Level.Error should equal(new Log.Level(JLevel.SEVERE))
      Log.Level.Warn should equal(new Log.Level(JLevel.WARNING))
      Log.Level.Info should equal(new Log.Level(JLevel.INFO))
      Log.Level.Debug should equal(new Log.Level(JLevel.CONFIG))
    }

    it("should delegate to the appropriate java log level test methods") {
      val logger = new MockLogger("test")
      implicit val factory = new Factory(logger)
      val log = Log("test")
      logger.expecting(_.isLoggable(JLevel.SEVERE))(log.isErrorEnabled)
      logger.expecting(_.isLoggable(JLevel.SEVERE))(log.isEnabled(Log.Level.Error))
      logger.expecting(_.isLoggable(JLevel.WARNING))(log.isWarnEnabled)
      logger.expecting(_.isLoggable(JLevel.WARNING))(log.isEnabled(Log.Level.Warn))
      logger.expecting(_.isLoggable(JLevel.INFO))(log.isInfoEnabled)
      logger.expecting(_.isLoggable(JLevel.INFO))(log.isEnabled(Log.Level.Info))
      logger.expecting(_.isLoggable(JLevel.CONFIG))(log.isDebugEnabled)
      logger.expecting(_.isLoggable(JLevel.CONFIG))(log.isEnabled(Log.Level.Debug))
    }

    it("should submit log entries only when configured to do so") {
      evaluateLoggerBehavior(JLevel.SEVERE, "logError", logError)
      evaluateLoggerBehavior(JLevel.WARNING, "logWarn", logWarn)
      evaluateLoggerBehavior(JLevel.INFO, "logInfo", logInfo)
      evaluateLoggerBehavior(JLevel.CONFIG, "logDebug", logDebug)
    }

    it("should create actual JVM loggers") {
      val log = Log(sourceClass)
      if (log.isDebugEnabled)
        assert(log.isInfoEnabled === true)
      if (log.isInfoEnabled)
        assert(log.isWarnEnabled === true)
      if (log.isWarnEnabled)
        assert(log.isErrorEnabled === true)
    }

  }

  /** Tests at a specific level, both enabled and disabled. */
  def evaluateLoggerBehavior(level: JLevel, methodName: String, behavior: (Log, () => String) => Unit) {
    {
      val logger = new MockLogger(sourceClass, level)
      implicit val factory = new Factory(logger)
      val log = Log()
      logger.expecting { l =>
        l.isLoggable(level)
        l.logp(level, sourceClass, methodName, message)
        l.isLoggable(level)
        l.logp(level, sourceClass, methodName, message)
        l.isLoggable(level)
        l.logp(level, sourceClass, methodName, message, thrown)
        l.isLoggable(level)
        l.logp(level, sourceClass, methodName, message, thrown)
      }(behavior(log, () => message))
    }
    {
      val logger = new MockLogger(sourceClass, JLevel.OFF)
      implicit val factory = new Factory(logger)
      val log = Log()
      logger.expecting { l =>
        l.isLoggable(level)
        l.isLoggable(level)
        l.isLoggable(level)
        l.isLoggable(level)
      }(behavior(log, () => throw new IllegalStateException))
    }
  }

  /** Logs error messages with a predictable method name. */
  def logError(log: Log, msg: () => String) {
    log.error(msg())
    log(Log.Level.Error, msg())
    log.error(msg(), thrown)
    log(Log.Level.Error, msg(), thrown)
  }

  /** Logs warning messages with a predictable method name. */
  def logWarn(log: Log, msg: () => String) {
    log.warn(msg())
    log(Log.Level.Warn, msg())
    log.warn(msg(), thrown)
    log(Log.Level.Warn, msg(), thrown)
  }

  /** Logs info messages with a predictable method name. */
  def logInfo(log: Log, msg: () => String) {
    log.info(msg())
    log(Log.Level.Info, msg())
    log.info(msg(), thrown)
    log(Log.Level.Info, msg(), thrown)
  }

  /** Logs debug messages with a predictable method name. */
  def logDebug(log: Log, msg: () => String) {
    log.debug(msg())
    log(Log.Level.Debug, msg())
    log.debug(msg(), thrown)
    log(Log.Level.Debug, msg(), thrown)
  }

}

/**
 * A mock extension of [[java.util.logging.Logger]] to test with.
 */
class MockLogger(val name: String, level: JLevel = JLevel.ALL) extends JLogger(name, null) {

  import MockLogger._

  /** True if expectations are being recorded. */
  private var recording = false
  /** The active list of expectations. */
  private var expectations = Vector.empty[Invocation]

  /** Configures the expected invocations and runs a test case. */
  def expecting[A](expectations: JLogger => A)(action: => Unit) {
    this.recording = true
    try
      expectations(this)
    finally
      recording = false
    try {
      action
      require(this.expectations.isEmpty, "Expected " + this.expectations.mkString(" then "))
    } finally
      this.expectations = Vector.empty[Invocation]
  }

  /** Called when a mocked method is invoked. */
  private def invoked(invocation: Invocation) {
    if (recording)
      expectations :+= invocation
    else {
      require(expectations.nonEmpty, "Unexpected invocation " + invocation)
      val expectation = expectations.head
      expectations = expectations.tail
      require(expectation == invocation, "Expected " + expectation + " found " + invocation)
    }
  }

  /** @inheritdoc */
  override def isLoggable(level: JLevel) = {
    invoked(IsLoggable(level))
    level.intValue >= this.level.intValue
  }

  /** @inheritdoc */
  override def logp(level: JLevel, className: String, methodName: String, message: String) =
    invoked(Logp(level, className, methodName, message))

  /** @inheritdoc */
  override def logp(level: JLevel, className: String, methodName: String, message: String, thrown: Throwable) =
    invoked(LogpWith(level, className, methodName, message, thrown))

}

/**
 * Definition of utility classes for the mock logger.
 */
object MockLogger {

  /**
   * Base class for invocation objects.
   */
  sealed abstract class Invocation

  /**
   * Represents an invocation of `isLoggable`.
   */
  final case class IsLoggable(level: JLevel) extends Invocation

  /**
   * Represents an invocation of `logp`.
   */
  final case class Logp(level: JLevel, className: String, methodName: String, message: String) extends Invocation

  /**
   * Represents an invocation of `logp` with a throwable.
   */
  final case class LogpWith(level: JLevel, className: String, methodName: String, message: String, thrown: Throwable)
    extends Invocation

  /**
   * A factory that returns a mock logger.
   */
  class Factory(logger: MockLogger) extends Log.Factory {

    /** @inheritdoc */
    override def apply(name: String) = {
      require(logger.name == name, "Expected " + logger.name + " found " + name)
      logger
    }

  }

}
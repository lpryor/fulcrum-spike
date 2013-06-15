/*
 * Build.scala
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
import sbt._
import Keys._
import de.johoop.jacoco4sbt._
import JacocoPlugin._

/**
 * Global settings.
 */
object Settings {

  /** Default settings for all projects. */
  val defaults = Defaults.defaultSettings ++ Seq(
    organization := "fulcrum",
    version := "0.1",
    scalaVersion := "2.10.2",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
  ) ++ jacoco.settings

  /** Scala reflection library. */
  val reflect = scalaVersion("org.scala-lang" % "scala-reflect" % _)
  
  /** Scalatest (primary testing framework). */
  val scalatest = "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"
  
  /** JUnit (for easy IDE integration). */
  val junit = "junit" % "junit" % "4.11" % "test"

}

/**
 * Project descriptors.
 */
object Fulcrum extends Build {

  /** Root project. */
  lazy val root: Project = Project(
    id = "fulcrum",
    base = file("."),
    settings = Settings.defaults
  ) aggregate (code, code_tests)

  /** The code project. */
  lazy val code: Project = Project(
    id = "fulcrum-code",
    base = file("code"),
    settings = Settings.defaults ++ Seq(
      libraryDependencies <+= Settings.reflect,
      libraryDependencies += Settings.scalatest
    )
  )

  /** The code tests project. */
  lazy val code_tests: Project = Project(
    id = "fulcrum-code-tests",
    base = file("code-tests"),
    settings = Settings.defaults ++ Seq(
      libraryDependencies += Settings.junit,
      libraryDependencies += Settings.scalatest
    )
  ) dependsOn (code % "test->test")

}
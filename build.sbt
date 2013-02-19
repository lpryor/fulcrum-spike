name := "fulcrum"

version := "0.1"

scalaVersion := "2.10.1-RC1"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= Seq(
   "junit" % "junit" % "4.9" % "test",
   "com.novocode" % "junit-interface" % "0.8" % "test->default"
)
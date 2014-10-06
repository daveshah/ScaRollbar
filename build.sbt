name := "project name"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
    "org.scalaj" %% "scalaj-http" % "0.3.16",
    "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
    "com.typesafe.akka" %% "akka-actor" % "2.3.6"
)




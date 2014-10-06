name := "scarollbar"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.2"

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
    artifact.name + "-" + module.revision + "." + artifact.extension
}

libraryDependencies ++= Seq(
    "org.scalaj" %% "scalaj-http" % "0.3.16",
    "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
    "com.typesafe.akka" %% "akka-actor" % "2.3.6",
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2"
)




name := "class2sql"

organization := "com.github.piotr-kalanski"

version := "0.1.6"

scalaVersion := "2.11.8"

licenses := Seq("Apache License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

homepage := Some(url("https://github.com/piotr-kalanski/class2sql"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/piotr-kalanski/class2sql"),
    "scm:git:ssh://github.com/piotr-kalanski/class2sql.git"
  )
)

developers := List(
  Developer(
    id    = "kalan",
    name  = "Piotr Kalanski",
    email = "piotr.kalanski@gmail.com",
    url   = url("https://github.com/piotr-kalanski")
  )
)

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.2",
  "com.github.piotr-kalanski" %% "csv2class" % "0.3.3",
  "junit" % "junit" % "4.10" % "test",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "com.h2database" % "h2" % "1.4.195" % "test"
)

coverageExcludedPackages := "com.datawizards.class2sql.examples.*"

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

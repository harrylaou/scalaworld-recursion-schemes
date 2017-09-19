lazy val compilerSettings = Seq(
  scalacOptions ++= Seq(
    "-language:higherKinds",
    "-Ypartial-unification",
    "-encoding", "UTF-8",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-language:existentials"
  ),
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")
)

lazy val commonResolvers = Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.jcenterRepo
)

lazy val buildSettings = Seq(
  scalaVersion := "2.12.3",
  name := "hack-the-tower-recursion-schemes",
  version := "0.1.0-SNAPSHOT"
)

lazy val commonSettings = Seq(
  resolvers := commonResolvers,
  libraryDependencies ++= Seq(
    "com.slamdata" %% "matryoshka-core" % "0.21.1",
    "org.scalaz" %% "scalaz-core" % "7.2.15",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
    "com.lihaoyi" % "ammonite" % "1.0.2" % "test" cross CrossVersion.full
  ),
  sourceGenerators in Test += Def.task {
    val file = (sourceManaged in Test).value / "amm.scala"
    IO.write(file, """object amm extends App { ammonite.Main().run() }""")
    Seq(file)
  }.taskValue
) ++ compilerSettings

lazy val root = (project in file(".")).settings(
  buildSettings,
  commonSettings
)

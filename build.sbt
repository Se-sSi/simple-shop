name := "simple-shop"
organization := "org.dmonix"
version := "0.0.1"
mainClass in (Compile, run) := Some("org.dmonix.shop.Main")
crossPaths := false //no need to cross-compile as this is a plain Java project
libraryDependencies ++= Seq(
  "junit" % "junit" % "4.13" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test" //needed to run Java Junit tests in SBT
)


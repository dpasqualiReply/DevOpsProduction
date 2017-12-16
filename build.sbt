name := "DevOpsProduction-PipelineTest"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.2" % "test"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.2.0",
)
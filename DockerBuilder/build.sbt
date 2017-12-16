name := "DockerBuilder"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.2" % "test"

import com.typesafe.sbt.packager.docker._


enablePlugins(UniversalPlugin)
enablePlugins(DockerPlugin)
//enablePlugins(sbtdocker.DockerPlugin)

val orgName = "datareply"
val repoName = "devops-cm-iac"
val imageVersion = "1"
val libsFolder = ""


//************ IMAGE SETTINGS *******************

packageName in Docker := repoName
version in Docker := imageVersion

//************ DOCKERFILE SETTINGS **************

//dockerBaseImage := "centos:centos7.4.1708"

dockerCommands := Seq(

  // Image
  Cmd("FROM", "centos:centos7.4.1708"),

  // Envirnoment
//  Cmd("ENV", "SHARE", "/opt/"),
//  Cmd("ENV", "SCALA_HOME", "$SHARE/scala"),
//  Cmd("ENV", "SBT_HOME", "$SHARE/sbt"),
//  Cmd("ENV", "JAVA_HOME", "$SHARE/java"),
//  Cmd("ENV", "PATH", "$SCALA_HOME/bin:$JAVA_HOME/bin:$SBT_HOME/bin:$PATH"),

  // User
  Cmd("USER", "root"),
  Cmd("WORKDIR", "/opt/"),

  // Tools
  ExecCmd("RUN", "yum", "update", "-y"),
  ExecCmd("RUN", "yum", "install", "wget", "-y"),
  ExecCmd("RUN", "yum", "install", "curl", "-y"),

  // Opts
  ExecCmd("RUN", "yum", "install", "java-1.8.0-openjdk", "-y"),
  ExecCmd("RUN", "wget", "http://dl.bintray.com/sbt/rpm/sbt-0.13.12.rpm"),
  ExecCmd("RUN", "yum", "localinstall", "sbt-0.13.12.rpm", "-y"),

  //Dockerception
  ExecCmd("RUN", "curl", "-fsSL https://get.docker.com/ | sh"),
  ExecCmd("RUN", "systemctl start docker"),
  ExecCmd("RUN", "systemctl enable docker"),

  //SETUP
  Cmd("WORKDIR", "/root"),
  ExecCmd("CMD", "/bin/bash")
)
pipeline {
  agent any
  stages {
    stage('Config System') {
      steps {
        echo 'Setup the system'
        sh 'sudo yum install wget -y'
        sh 'sudo yum install curl -y'
        sh 'sudo yum install java-1.8.0-openjdk'
        sh 'sudo wget http://dl.bintray.com/sbt/rpm/sbt-0.13.12.rpm'
        sh 'sudo yum install sbt-0.13.12.rpm -y'
        sh 'sudo wget http://it.apache.contactlab.it/spark/spark-2.2.0/spark-2.2.0-bin-hadoop2.7.tgz'
        sh 'sudo tar xvf spark-2.2.0-bin-hadoop2.7.tgz'
      }
    }
    stage('Test the System') {
      steps {
        sh 'java -version'
        sh 'sbt about'
      }
    }
  }
}
pipeline {
  agent {
    dockerfile true
  }
  stages {
    stage('Test') {
      steps {
        echo 'Hello Dockerfile'
        sh 'sbt about'
        sh 'java -version'
        sh 'ls -la'
        sh 'sbt clean coverage test coverageReport'
      }
    }
  }
}
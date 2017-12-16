pipeline {
  agent {
    dockerfile true
  }
  stages {
    stage('Test') {
      steps {
        echo 'Hello Dockerfile'
        sh 'ls -la'
        sh 'sbt clean test coverage'
      }
    }
  }
}
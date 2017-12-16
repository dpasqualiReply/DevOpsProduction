pipeline {
  agent {
    dockerfile true
  }
  stages {
    stage('Test') {
      steps {
        echo 'Hello Dockerfile'
        sh '''sbt about
java -version

           '''
      }
    }
  }
}
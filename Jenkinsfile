pipeline {
  agent {
    dockerfile {
      dir 'DockerBuilder/legacy'
    }
    
  }
  stages {
    stage('Test') {
      steps {
        echo 'Hello Dockerfile'
        sh '''sbt about
java -version
ls -la
           '''
      }
    }
  }
}
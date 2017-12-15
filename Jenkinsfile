pipeline {
  agent {
    dockerfile {
      filename 'Dockerfile'
    }
    
  }
  stages {
    stage('Test') {
      steps {
        sh 'ls -la'
        sh 'pwd'
        sh 'sbt sbtVersion'
      }
    }
  }
}
pipeline {
  agent any
  stages {
    stage('No-op') {
      steps {
        sh 'll'
      }
    }
  }
  post {
    always {
      echo 'One way or another, I have finished'
      deleteDir()
      
    }
    
    success {
      echo 'I succeeeded!'
      
    }
    
    unstable {
      echo 'I am unstable :/'
      
    }
    
    failure {
      echo 'I failed :('
      
    }
    
    changed {
      echo 'Things were different before...'
      
    }
    
  }
}
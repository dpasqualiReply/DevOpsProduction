pipeline {
  agent any
  stages {
    stage('No-op') {
      steps {
        sh 'ls'
      }
    }
  }
  environment {
    header = 'HEAD'
    message = "${header}\nfailed :scream:"
    color = '#CC0000'
  }
  post {
    always {
      echo 'One way or another, I have finished'
      deleteDir()
      
    }
    
    success {
      echo "Message ${message}"
      script {
        MESS = "cazzooooooo"
        MESS += "AAAAAAA"
      }
      
      echo "Message ${MESS}"
      slackSend(message: MESS, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')
      
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
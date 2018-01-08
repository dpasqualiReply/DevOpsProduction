pipeline {
  agent any
  stages {
    stage('No-op') {
      steps {
        sh 'ls'
      }
    }
  }
  post {
    always {
      echo 'One way or another, I have finished'
      deleteDir()
      
    }
    
    success {
      script {
        HEAD = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
        HEAD += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
        MESS = "${HEAD}\nsuccessful :smiley:"
        
        AUTH = sh(script: "git log -1 --pretty=%an ${commit}", returnStdout: true).trim()
        COMM_MESS = sh(script: "git log -1 --pretty=%B ${commit}", returnStdout: true).trim()
        MESS += " Commit by <@${AUTH}> (${AUTH}): ``` ${COMM_MESS} ``` "
        color = '#00CC00'
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
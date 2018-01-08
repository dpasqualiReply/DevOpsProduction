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
      echo "Message ${message}"
      script {
        header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
        header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
        message = "${header}\nsuccessful :smiley:"
        
        author = sh(script: "git log -1 --pretty=%an ${commit}", returnStdout: true).trim()
        commitMsg = sh(script: "git log -1 --pretty=%B ${commit}", returnStdout: true).trim()
        message += " Commit by <@${author}> (${author}): ``` ${commitMsg} ``` "
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
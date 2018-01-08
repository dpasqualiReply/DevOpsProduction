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
      
    }
    
    success {
      script {
        header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
        header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
        message = "${message}\nsuccessful :smiley:"
        
        author = sh(script: "git log -1 --pretty=%an", returnStdout: true).trim()
        commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
        message += " Commit by <@${author}> (${author}): ``` ${commitMessage} ``` "
        color = '#00CC00'
      }
      
      echo "Message ${message}"
      slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')
      
    }
    
    unstable {
      script {
        header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
        header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
        message = "${message}\nsuccessful :smiley:"
        
        author = sh(script: "git log -1 --pretty=%an", returnStdout: true).trim()
        commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
        message += " Commit by <@${author}> (${author}): ``` ${commitMessage} ``` "
        color = '#00CC00'
      }
      
      echo "Message ${message}"
      slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')
      
    }
    
    failure {
      script {
        header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
        header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
        message = "${message}\nsFAILED :(:"
        
        author = sh(script: "git log -1 --pretty=%an", returnStdout: true).trim()
        commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
        message += " Commit by <@${author}> (${author}): ``` ${commitMessage} ``` "
        color = '#FFDD12'
      }
      
      echo "Message ${message}"
      slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')
      
    }
    
    changed {
      echo 'Things were different before...'
      
    }
    
  }
}
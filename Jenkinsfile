pipeline {
  agent any
  stages {
    stage('Config System') {
      steps {
        echo 'Setup the system'
        sh 'sudo yum install wget -y'
        sh 'sudo yum install curl -y'
        sh 'sudo yum install java-1.8.0-openjdk'
        sh 'sudo wget http://dl.bintray.com/sbt/rpm/sbt-0.13.12.rpm'
        sh 'sudo yum localinstall sbt-0.13.12.rpm -y'
      }
    }
    stage('Notify') {
      steps {
        slackSend(message: 'Build Done', baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: 'blue', token: 'ihoCVUPB7hqGz2xI1htD8x0F')
      }
    }
  }
  post {
    failure {
      script {
        if (!env.SKIP_BUILD) {
          header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
          header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
          message = "${header}\n${specificMessage}"
          
          author = sh(script: "git log -1 --pretty=%an ${commit}", returnStdout: true).trim()
          commitMsg = sh(script: "git log -1 --pretty=%B ${commit}", returnStdout: true).trim()
          message += " Commit by <@${author}> (${author}): ``` ${commitMsg} ``` "
          
          echo "Message ${message}"
          color = '#CC0000'
          slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')
          //sendMessage '#CC0000', 'failed :scream:'
        }
      }
      
      
    }
    
    unstable {
      script {
        header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
        header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
        message = "${header}\n${specificMessage}"
        
        author = sh(script: "git log -1 --pretty=%an ${commit}", returnStdout: true).trim()
        commitMsg = sh(script: "git log -1 --pretty=%B ${commit}", returnStdout: true).trim()
        message += " Commit by <@${author}> (${author}): ``` ${commitMsg} ``` "
        
        echo "Message ${message}"
        color = '#FFA500'
        slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')
      }
      
      
    }
    
    success {
      script {
        header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
        header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
        message = "${header}\n${specificMessage}"
        
        author = sh(script: "git log -1 --pretty=%an ${commit}", returnStdout: true).trim()
        commitMsg = sh(script: "git log -1 --pretty=%B ${commit}", returnStdout: true).trim()
        message += " Commit by <@${author}> (${author}): ``` ${commitMsg} ``` "
        
        echo "Message ${message}"
        color = '#00CC00'
        slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')
      }
      
      
    }
    
  }
}
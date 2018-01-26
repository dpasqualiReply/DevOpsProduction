pipeline {
  agent any
  stages {
    stage('No-op') {
      steps {
        script {
          link= "${env.BUILD_URL}input/Async-input/proceedEmpty"
          abort= "${env.BUILD_URL}input/Async-input/abort"
          header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
          header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
          message = "${header}\n"
          author = sh(script: "git log -1 --pretty=%an", returnStdout: true).trim()
          commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
          message += " Commit by <@${author}> (${author}): ``` ${commitMessage} ``` "
          message += "---"
          message += "\nThe new Batch ETL commit pass Unit and Integration tests"
          message += "\nThis session will be available for 60 second, make a CHOICE!"
          message += "\nPlease <${env.RUN_DISPLAY_URL}|Manual Deploy> it if you want!"
          //message += "\n<${link}|Deploy to Stageing env> or <${abort}|Abort>"
          color = '#FFDD12'

          slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')
          //input id: 'Async-input', message: 'Waiting for remote system'

          script{

            try {
              timeout(time: 60, unit: 'SECONDS') { // change to a convenient timeout for you
                userInput = input(
                        id: 'DeployBETL', message: 'Deploy in Production??')
              }
            } catch(err) { // timeout reached or input false
                error("Deploy session expired or aborted")
            }
          }
        }

        echo 'Soooooooooo lets deploy this shit'
      }
    }
    stage('Production Deploy') {
      steps {
        echo 'Safe to Deploy in Production, Great Job :D'
//          sh 'ansible-playbook -i \'worker-test,\' --private-key=/home/xxpasquxx/.ssh/ansible_rsa_key /opt/DevOpsProduction-Orchestrator/ansible/deploy/batch_etl_deploy.yml  -e \'ansible_ssh_user=xxpasquxx\' -e \'host_key_checking=False\''
        sh 'sudo ansible -i /opt/DevOpsProduction-Orchestrator/ansible/hosts test --private-key=/home/xxpasquxx/.ssh/ansible_rsa_key  -e \'ansible_ssh_user=xxpasquxx\' -e \'host_key_checking=False\' -m ping'
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
        message = "${header}\n :smiley: New Batch ETL release deployed in Production"

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
        message = "${header}\n:glitch_crab: Oh No!!! there is something wrong"

        author = sh(script: "git log -1 --pretty=%an", returnStdout: true).trim()
        commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
        message += " Commit by <@${author}> (${author}): ``` ${commitMessage} ``` "
        color = '#ffff00'
      }

      echo "Message ${message}"
      slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')

    }

    failure {
      script {
        header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
        header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
        message = "${header}\nsFAILED The Build Failed, Release not ready for production!:"

        author = sh(script: "git log -1 --pretty=%an", returnStdout: true).trim()
        commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
        message += " Commit by <@${author}> (${author}): ``` ${commitMessage} ``` "
        color = '#FFDD12'
      }

      echo "Message ${message}"
      slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')

    }
  }
}
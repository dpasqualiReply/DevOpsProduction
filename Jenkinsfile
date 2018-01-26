pipeline {
  agent any
  stages {
    stage('No-op') {
      steps {
        echo 'Soooooooooo lets deploy this shit'
      }
    }
    stage('Simple Tests') {
      steps {
        sh 'sbt clean test'
      }
    }
    stage('Fake Build') {
      steps {
        sh 'sbt clean compile package'
      }
    }
    stage('Production Deploy') {
      steps {
        echo 'Safe to Deploy in Production, Great Job :D'
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
        message = "${header}\nsuccessful :smiley:"
        
        author = sh(script: "git log -1 --pretty=%an", returnStdout: true).trim()
        commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
        message += " Commit by @${author}: ``` ${commitMessage} ``` "
        color = '#00CC00'
      }
      
      notifySlack('', slackNotificationChannel, [
                              [
                                          title: "<${env.JOB_URL}|${env.BRANCH_NAME}>, build #${env.BUILD_NUMBER}",
                                          title_link: "${env.BUILD_URL}",
                                          color: "#00CC00",
                                          text: "Success!",
                                          "mrkdwn_in": ["fields"],
                                          fields: [
                                                      [
                                                                  title: "Branch",
                                                                  value: "${env.GIT_BRANCH}",
                                                                  short: true
                                                          ],
                                                          //[
                                                            //          title: "Test Results",
                                                            //          value: "${getTestSummary()}",
                                                            //          short: true
                                                            //  ],
                                                              [
                                                                          title: "Last Commit",
                                                                          value: "${commitMessage}",
                                                                          short: false
                                                                  ]
                                                          ]
                                                  ]
                                          ])
                  
                }
                
              }
            }
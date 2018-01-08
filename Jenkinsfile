
def sendMessage = {color, specificMessage->
    // Print a message to the console and to Slack.
    header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
    header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
    message = "${header}\n${specificMessage}"
    
    author = sh(script: "git log -1 --pretty=%an ${commit}", returnStdout: true).trim()
    commitMsg = sh(script: "git log -1 --pretty=%B ${commit}", returnStdout: true).trim()
    message += " Commit by <@${author}> (${author}): ``` ${commitMsg} ``` "

    echo "Message ${message}"

    /* (optional snippet)
    // Send a Slack message. (Note that you need to configure a Slack access token in the Jenkins system settings).
    slackSend channel: 'yourchannelid', teamDomain: 'yourdomain', color: color, message: message, failOnError: true
    */
    slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')
                
}
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
        /*
        stage('Test scalatest') {
            steps {
                sh 'sbt clean test coverage coverageReport'
                archiveArtifacts 'target/test-reports/*.xml'
            }
        }
        stage('Build') {
            steps {
                sh 'sbt clean compile package'
                archiveArtifacts 'target/scala-2.11/devopsproduction-pipelinetest_2.11-0.1.jar'
            }
        }
        stage('Deploy') {
            steps {
                sh 'sudo cp target/scala-2.11/devopsproduction-pipelinetest_2.11-0.1.jar /opt/deploy/'
            }
        }
        */
        stage('Notify') {
            steps {
                slackSend(message: 'Build Donw', baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: 'blue', token: 'ihoCVUPB7hqGz2xI1htD8x0F')
            }
        }
    }
    post {
        failure {
            script {
                if (!env.SKIP_BUILD) {
                    sendMessage '#CC0000', 'failed :scream:'
                }
            }
        }
        unstable {
            script {
                sendMessage '#FFA500', 'unstable :grimacing:'
            }
        }
        success {
            script {
                sendMessage '#00CC00', 'successful :smiley:'
            }
        }
    }
}

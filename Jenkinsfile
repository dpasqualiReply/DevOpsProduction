import groovy.json.JsonOutput
import java.util.Optional
import hudson.tasks.test.AbstractTestResultAction
import hudson.model.Actionable
import hudson.tasks.junit.CaseResult

def slackNotificationChannel = 'general'

@NonCPS
def getTestSummary = { ->
  def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
  def summary = ""

  if (testResultAction != null) {
    total = testResultAction.getTotalCount()
    failed = testResultAction.getFailCount()
    skipped = testResultAction.getSkipCount()

    summary = "Passed: " + (total - failed - skipped)
    summary = summary + (", Failed: " + failed)
    summary = summary + (", Skipped: " + skipped)
  } else {
    summary = "No tests found"
  }
  return summary
}

//@NonCPS
//def getFailedTests = { ->
//  def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
//  def failedTestsString = "```"
//
//  if (testResultAction != null) {
//    def failedTests = testResultAction.getFailedTests()
//
//    if (failedTests.size() > 9) {
//      failedTests = failedTests.subList(0, 8)
//    }
//
//    for(CaseResult cr : failedTests) {
//      failedTestsString = failedTestsString + "${cr.getFullDisplayName()}:\n${cr.getErrorDetails()}\n\n"
//    }
//    failedTestsString = failedTestsString + "```"
//  }
//  return failedTestsString
//}

def notifySlack(text, channel, attachments) {
  def slackURL = 'https://hooks.slack.com/services/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'
  def jenkinsIcon = 'https://www.shareicon.net/data/64x64/2016/08/06/807455_power_512x512.png'

  def payload = JsonOutput.toJson([text: text,
                                   channel: channel,
                                   username: "Jenkins",
                                   icon_url: jenkinsIcon,
                                   attachments: attachments
  ])

  sh "curl -X POST --data-urlencode \'payload=${payload}\' ${slackURL}"
}

pipeline {
  agent any
  stages {
    stage('No-op') {
      steps {
        echo 'Soooooooooo lets deploy this shit'
        notifySlack("Success!", slackNotificationChannel, [])
      }
    }
    stage('Simple Tests') {
      steps {
        echo 'sbt clean test'
      }
    }
    stage('Fake Build') {
      steps {
        echo 'sbt clean compile package'
        step $class: 'JUnitResultArchiver', testResults: '**/TEST-*.xml'
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
//
//      echo "Message ${message}"
//      slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')

      notifySlack("", slackNotificationChannel, [
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
                              [
                                      title: "Test Results",
                                      value: "${getTestSummary()}",
                                      short: true
                              ],
                              [
                                      title: "Last Commit",
                                      value: "${commitMessage}",
                                      short: false
                              ]
                      ]
              ]
      ])

    }

    unstable {
//      script {
//        header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
//        header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
//        message = "${header}\nsuccessful :smiley:"
//
//        author = sh(script: "git log -1 --pretty=%an", returnStdout: true).trim()
//        commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
//        message += " Commit by @${author}: ``` ${commitMessage} ``` "
//        color = '#00CC00'
//      }
//
//      echo "Message ${message}"
//      slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')

    }

    failure {
//      script {
//        header = "Job <${env.JOB_URL}|${env.BRANCH_NAME}> <${env.JOB_DISPLAY_URL}|(Blue)>"
//        header += " build <${env.BUILD_URL}|${env.BUILD_DISPLAY_NAME}> <${env.RUN_DISPLAY_URL}|(Blue)>:"
//        message = "${header}\nsFAILED :(:"
//
//        author = sh(script: "git log -1 --pretty=%an", returnStdout: true).trim()
//        commitMessage = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
//        message += " Commit by @${author}: ``` ${commitMessage} ``` "
//        color = '#FFDD12'
//      }
//
//      echo "Message ${message}"
//      slackSend(message: message, baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: color, token: 'ihoCVUPB7hqGz2xI1htD8x0F')

    }

  }
}
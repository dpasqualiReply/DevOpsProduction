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
  }
  post {
    failure {
      script {
        if (!env.SKIP_BUILD) {
          slackSend(message: 'failed :scream:', baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: '#CC0000', token: 'ihoCVUPB7hqGz2xI1htD8x0F')
        }
      }
      
      
    }
    
    unstable {
      script {
        slackSend(message: 'unstable :grimacing:', baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: '#FFA500', token: 'ihoCVUPB7hqGz2xI1htD8x0F')
      }
      
      
    }
    
    success {
      script {
        slackSend(message: 'successful :smiley:', baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: '#00CC00', token: 'ihoCVUPB7hqGz2xI1htD8x0F')
      }
      
      
    }
    
  }
}
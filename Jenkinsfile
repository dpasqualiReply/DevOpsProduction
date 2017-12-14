pipeline {
  agent {
    docker {
      image 'centos:7'
    }
    
  }
  stages {
    stage('Setup') {
      steps {
        echo 'setup'
        sh 'sudo yum update -y'
      }
    }
    stage('Test') {
      agent any
      steps {
        echo 'test on docker'
      }
    }
    stage('Deploy') {
      steps {
        echo 'deploy'
      }
    }
  }
}
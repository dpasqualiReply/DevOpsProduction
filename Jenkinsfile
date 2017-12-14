pipeline {
  agent any
  stages {
    stage('Setup') {
      steps {
        echo 'setup'
      }
    }
    stage('Test') {
      agent {
        docker {
          image 'centos:7'
        }
        
      }
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
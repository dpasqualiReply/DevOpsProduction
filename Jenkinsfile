pipeline {
  agent {
    docker {
      image 'centos:7'
    }
    
  }
  stages {
    stage('Test') {
      steps {
        echo 'test pipeline'
        sh '''sudo yum update -y
echo install java...
sudo yum install java-1.8.0-openjdk -y
java --version'''
      }
    }
    stage('deploy') {
      steps {
        sh 'echo done'
      }
    }
  }
}
pipeline {
  agent {
    docker {
      image 'centos:centos7.4.1708'
    }
    
  }
  stages {
    stage('Test') {
      agent {
        dockerfile {
          filename 'devops_docker'
        }
        
      }
      steps {
        echo 'ciaoooo'
      }
    }
  }
}
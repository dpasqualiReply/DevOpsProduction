pipeline {
  agent {
    docker {
      image 'centos:7'
    }    
  }
  stages {
    stage('SetupVM') {
      agent {
        docker {
          image 'centos:7'
        }
        
      }
      steps {
        echo 'Load Config Variables from environment (put them in src/test/conf)'
      }
    }
    stage('Test') {
        agent {
          docker {
            image 'centos:7'
          }            
        }
        steps {
          echo 'Execute some test...'
        }
    }
    stage('Deploy') {
      agent {
        docker {
          image 'centos:7'
        }            
      }
      steps {
        echo 'Deploy it :D'
      }
    }
    stage('DONE') {
      steps {
        echo 'DONE'
      }
    }
  }
  environment {
    TEST_ENV = 'good'
  }
}

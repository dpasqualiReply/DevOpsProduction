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
        echo "Load Config Variables from environment (put them in src/test/conf)"
        echo "Install required stuff in the docker container"
        echo "TODO make a dockerfile and enable Configuration Management"

        sudo yum update -y
        sudo yum install wget -y

        ## Install Java

        sudo yum install java-1.8.0-openjdk -y

        ## Install SBT

        sudo wget https://dl.bintray.com/sbt/rpm/sbt-1.0.0.rpm
        sudo yum localinstall sbt-1.0.0.rpm -y

        echo "Test the setup"
        java --version
        sbt about
      }
    }
    stage('Test') {
      parallel {
        stage('Test Jenkins') {
          agent {
            docker {
              image 'centos:7'
            }
            
          }
          steps {
            #cd PipelineTester
            echo "Test starting..."
            sbt about
            #sbt clean coverage test coverageReport
          }
        }
        stage('Test BatchETL') {
          steps {
            cd BatchETL
            ls -la
            echo "Test starting..."
            echo "TODO"
            #sbt clean coverage test coverageReport
          }
        }
        stage('Test RealTimeETL') {
          agent {
            docker {
              image 'centos:7'
            }
            
          }
          steps {
            cd RealTimeETL
            ls -la
            echo "Test starting..."
            echo "TODO"
            #sbt clean coverage test coverageReport
          }
        }
        stage('Test MRSpark2') {
          agent {
            docker {
              image 'centos:7'
            }
            
          }
          steps {
            cd MRSpark2
            ls -la
            echo "Test starting..."
            echo "TODO"
            #sbt clean coverage test coverageReport
          }
        }
        stage('Test RealTimeMovieRec') {
          agent {
            docker {
              image 'centos:7'
            }
            
          }
          steps {
            scd RealTimeMovieRec
            ls -la
            echo "Test starting..."
            echo "TODO"
            #sbt clean coverage test coverageReport

          }
        }
      }
    }
    stage('Deploy') {
      parallel {
        stage('Deploy Jenkins') {
          agent {
            docker {
              image 'centos:7'
            }
            
          }
          steps {
            echo "Deploy some build code to production"
            #sudo cp build.jar /opt/
          }
        }
        stage('Deploy BatchETL') {
          agent {
            docker {
              image 'centos:7'
            }
            
          }
          steps {
            echo "Deploy Batch ETL spark job to Cloudera VM"
          }
        }
        stage('Deploy RealTimeETL') {
          agent {
            docker {
              image 'centos:7'
            }
            
          }
          steps {
            echo "Deploy Real Time ETL to devops-worker VM"
          }
        }
        stage('Deploy MRSpark2') {
          agent {
            docker {
              image 'centos:7'
            }
            
          }
          steps {
            echo "Deploy Movie Recommender to devops-worker VM"
          }
        }
        stage('Deploy RealTimeMovieRec') {
          agent {
            docker {
              image 'centos:7'
            }
            
          }
          steps {
            echo "Deploy recommender web app and put it online"
          }
        }
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

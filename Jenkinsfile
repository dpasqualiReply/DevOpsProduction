pipeline {
  agent {
    dockerfile true
  }
  stages {
    stage('Test Pipeline') {
          steps {
            echo 'Hello Dockerfile'
            sh 'sbt test'
          }
    }
    stage('Test Spark Submit') {
        steps {
            sh 'sbt clean compile package'
            sh 'spark-submit --class HelloWorld --master local[*] target/scala-2.11/devopsproduction-pipelinetest_2.11-0.1.jar -s'
          }
        }
  }
}
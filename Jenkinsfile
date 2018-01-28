pipeline {
  agent any
  stages {
    stage('No-op') {
      steps {
        sh 'sbt clean test'
        junit(testResults: 'target/test-reports/DockerfileSpec.xml', allowEmptyResults: true)
        echo 'dd lets deploy this shit'
      }
    }
  }
}
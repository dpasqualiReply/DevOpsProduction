pipeline {
    agent {
        docker { image 'hseeberger/scala-sbt' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'cd testsbt'
                sh 'ls -la'
                sh 'pwd'
                sh 'sbt sbtVersion'
            }
        }
    }
}

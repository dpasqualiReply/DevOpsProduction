pipeline {
    agent {
        docker { image 'hseeberger/scala-sbt' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'sbt sbtVersion'
            }
        }
    }
}

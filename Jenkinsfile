pipeline {
    agent {
        docker { image 'hseeberger/scala-sbt' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'll'
                sh 'pwd'
            }
        }
    }
}

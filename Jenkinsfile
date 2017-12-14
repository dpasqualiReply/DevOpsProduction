pipeline {
    agent {
        docker { image 'hseeberger/scala-sbt' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'ls -la'
                sh 'pwd'
            }
        }
    }
}

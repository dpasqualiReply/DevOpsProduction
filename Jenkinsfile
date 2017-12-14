pipeline {
    agent {
        docker { image 'centos:7' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'whami'
                sh 'pwd'
                sh 'sudo yum -h'
            }
        }
    }
}

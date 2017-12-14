pipeline {
    agent {
        docker { image 'centos:7' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'whoami'
                sh 'pwd'
                sh 'sudo yum -h'
            }
        }
    }
}

pipeline {
  agent any
  stages {
    stage('No-op') {
      steps {
        echo 'Soooooooooo lets deploy this shit'
      }
    }
    stage('Production Deploy') {
      steps {
        echo 'Safe to Deploy in Production, Great Job :D'
        sh 'sudo ansible -i /opt/DevOpsProduction-Orchestrator/ansible/hosts test --private-key=/home/xxpasquxx/.ssh/ansible_rsa_key  -e \'ansible_ssh_user=xxpasquxx\' -e \'host_key_checking=False\' -m ping'
      }
    }
  }
  post {
    always {
      echo 'One way or another, I have finished'
      
    }
    
    success {
      slackSend(message: 'ciao', baseUrl: 'https://devops-pasquali-cm.slack.com/services/hooks/jenkins-ci/', color: '#00CC00', token: 'ihoCVUPB7hqGz2xI1htD8x0F')
      
    }
    
  }
}
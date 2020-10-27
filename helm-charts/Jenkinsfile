node {
        stage('Clone Repository') {
            checkout scm
        }
        stage('Install new release') {
            
            withKubeConfig(caCertificate: '', 
                           clusterName: 'k8s.dev.hejunjie3617.me', 
                           contextName: 'k8s.dev.hejunjie3617.me', 
                           credentialsId: '08c14baf-7489-4e97-adac-7638f765329e', 
                           namespace: '', 
                           serverUrl: 'https://api.k8s.dev.hejunjie3617.me') {
                sh "helm upgrade backend ./backend"
		sh "helm upgrade frontend ./frontend"
            }
        }
}

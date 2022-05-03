pipeline {
    agent any

    environment {
        imageName = 'test:0.0.1'
        registryCredential = 'kahmnkk'
        dockerImage = ''
    }

    stages {
        stage('Build Maven') {
            agent any

            steps {
                sh 'mvn clean package'
                archiveArtifacts 'target/*.jar'
            }

            post {
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }

        stage('Build Docker') {
            agent any

            steps {
                script {
                    dockerImage = docker.build imageName
                }
            }

            post {
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }

        stage('Run Docker') {
            agent any

            steps {
                script {
                    sh 'docker rm -f test'
                    sh 'docker run -d -p 8081:8081 --name test test:0.0.1'
                }
            }

            post {
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }
    }
}
pipeline {
    agent any
    environment {
        DOCKERHUB_USERNAME = "prvinsm21"
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
        DOCKERIMAGE_NAME = "prvinsm21/gaming-site:${BUILD_NUMBER}"
    }

    stages {
        stage ('Git Checkout') {
            steps {
                sh 'echo Passed'
            }
        }
        stage ('Unit testing') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('Integration testing') {
            steps {
                sh 'mvn verify -DskipUnitTests'
            }
        }
        stage ('Build Stage') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage ('Static Code analysis') {
            steps {
                script {
                     withSonarQubeEnv(credentialsId: 'sonar-api') {
                        sh 'mvn clean package sonar:sonar'
                     }
                }
            }
        }
        stage ('Quality gate status') {
            steps {
                script {
                    waitForQualityGate abortPipeline: false, credentialsId: 'sonar-api'
                }
            }
        }
        stage ('Build and Push Docker Image') {
            steps {
                script {
                    sh 'docker build -t ${DOCKERIMAGE_NAME} .'
                    def dockerImage = docker.image("${DOCKER_IMAGE}")
                    docker.withRegistry('https://index.docker.io/v1/', "dockerhub") {
                    dockerImage.push()
                    }
                }
            }
        }
    }
}
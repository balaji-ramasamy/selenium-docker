pipeline{
    agent any

    stages{
        stage('Build JAR'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build image'){
            steps{
                bat "docker build -t=baramasa27/selenium:latest ."
            }
        }
        stage('Push image'){
            environment{
                DOCKER_HUB = credentials('dockerhub-credentials')
            }
            steps{
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat 'docker push baramasa27/selenium:latest'
                bat "docker tag baramasa27/selenium:latest baramasa27/selenium:${env.BUILD_NUMBER}"
                bat "docker push baramasa27/selenium:${env.BUILD_NUMBER}"
            }
        }
    }

    post{
        always{
            bat "docker logout"
        }
    }
}
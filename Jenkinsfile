pipeline{
    agent any

    stages{
    stage('check git'){
        steps{
             bat 'pwd'
             bat 'ls -la'
             bat 'git status || echo "Not a git repository"'
            }
        }
        stage('Build JAR'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build image'){
            steps{
                bat "docker build -t=baramasa27/selenium ."
            }
        }
        stage('Push image'){
            steps{
                bat "docker push baramasa27/selenium"
            }
        }
    }
}
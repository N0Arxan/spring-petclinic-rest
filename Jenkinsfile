pipeline {
    agent any
    tools {
        maven 'Maven 3'  
        jdk 'Java 17'    
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean verify jacoco:report'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('MySonarServer') { 
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
}
pipeline {
    agent any
    stages {
        stage('Build & Unit Tests') {
		   agent {
		        docker {
		            image 'maven:3-alpine'
		            args '-v /root/.m2:/root/.m2'
		        }
		    }
            steps {
                sh 'mvn clean package'
            }
             post {
                always {
                    junit 'target/surefire-reports/*.xml' 
                }
            }
        } 
        
        stage('Integration Tests') {
            steps {
       	        script{
       	             docker.image('maven:3-alpine'){ c -> sh 'mvn clean verify'}    
       	        }       	                  
            }       
        }
    }
}
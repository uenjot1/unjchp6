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
        
        stage('Run Unit and Integration Tests') {
			docker.image('mysql:5.7').withRun('-e MYSQL_DATABASE=notepad -e MYSQL_ROOT_PASSWORD=root -p 3306:3306') { mysql ->
				docker.image('maven:3-alpine').inside("--link ${mysql.id}:mysql -e ENV_TEST_MYSQL_HOST=mysql -v /root/.m2:/root/.m2") {
					sh 'mvn clean verify'
				}
			}
		}
        
       
    }
}
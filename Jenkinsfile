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
			  steps {
			  	script{
					docker.image('postgres').withRun('-e POSTGRES_DB=unjchp6_db -e POSTGRES_PASSWORD=user1 -e POSTGRES_USER=user1 -p 5432:5432') { postgres ->
						docker.image('maven:3-alpine').inside("--link ${postgres.id}:postgres -e ENV_TEST-POSTGRES_HOST=postgres -v /root/.m2:/root/.m2") {
							sh 'mvn clean verify'
						}
					}
				}
			}
			
		}
	    stage('Clean Work Space') {
	      steps {
	           cleanWs()
	           sh 'pwd'
	           sh 'ls'
	       }
	       }
   }
        
}
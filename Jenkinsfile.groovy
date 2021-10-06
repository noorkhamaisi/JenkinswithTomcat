pipeline {
    agent any
    stages {
        ///cloning from github
        stage('clone') {
            steps {
		        git 'https://github.com/jleetutorial/maven-project.git'
            }
        }
        ///
        stage('build') {
            steps {
        	
        		sh "mvn package"
        	
        	
            }
        }
        ///
        stage('Archive the artifact') {
            steps {
        		archiveArtifacts artifacts: "**/*.war", followSymlinks: false
            }
        }
        /// Deploy 
        stage('Deploy') {
            steps {
        		deploy adapters: [tomcat9(credentialsId: 'f5cc7c62-5611-4dea-a417-3aae1444df0c', path: '', url: 'http://54.195.17.87:8888/manager/html')], contextPath: null, war: '**/*.war'
            }
        }
    }
}
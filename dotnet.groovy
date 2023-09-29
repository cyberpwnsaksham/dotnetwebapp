pipeline {
    agent any
    environment {
        dockerImage = 'my-dotnet-app'
        registry = 'bsaksham/dotnetwebapp:second'
        registryCredential = 'dockerhub'
    }
    stages {
        stage('Build dotnet') {
            steps {
                script {
                    bat "dotnet restore"
                    bat "dotnet build"
                }
            }
        }
        stage('Test dotnet') {
            steps {
                script {
                    bat "dotnet test"
                }
            }
        }
        stage('Building a Docker Image')
        {
            steps {
                script {
                    dockerImage = docker.build registry
                        }
                  }
        }
        stage('Pushing the image to HUB')
            {
                steps {
                    script {
                    docker.withRegistry('', registryCredential) 
                            {
                            dockerImage.push()
                            }
                        }
                    }

        }
}
}

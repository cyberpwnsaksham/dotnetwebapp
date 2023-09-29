pipeline {
    agent any

    environment {
        // Define environment variables as needed
        // DOTNET_VERSION = '6.0' // Change to your desired .NET version
        dockerImage = 'my-dotnet-app'
        registry = 'bsaksham/dotnetwebapp'
        registryCredential = 'dockerhub'
    }

    stages {
        stage('Build dotnet') {
            steps {
                script {
                    // Install and restore .NET dependencies
                    bat "dotnet restore"
                    // Build the .NET project
                    bat "dotnet build"
                }
            }
        }

        stage('Test dotnet') {
            steps {
                script {
                    // Run your tests here
                    bat "dotnet test"
                }
            }
        }

        stage('Containerize') {
            steps {
script {
    // Authenticate with Docker Hub (if not already authenticated)
    //bat "echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin"

    // Build a Docker image for your .NET application
    dockerImage = docker.build registry

    // Push the Docker image to Docker Hub
    docker.withRegistry('', registryCredential) {
        dockerImage.push()
}

            }
        }

    }
}
}

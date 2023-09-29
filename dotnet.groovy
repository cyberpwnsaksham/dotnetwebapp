pipeline {
    agent any

    environment {
        // Define environment variables as needed
        // DOTNET_VERSION = '6.0' // Change to your desired .NET version
        DOCKER_IMAGE_NAME = 'my-dotnet-app'
        DOCKER_REGISTRY_URL = 'bsaksham/dotnetwebapp:tagname'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Build the .NET project
                    sh "dotnet build"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run your tests here
                    sh "dotnet test"
                }
            }
        }

        stage('Containerize') {
            steps {
                script {
                    // Build a Docker image for your .NET application
                    sh "docker build -t bsaksham/dotnetwebapp:tagname/dotnetwebapp:1.0 ."
                    // Push the Docker image to a Docker registry
                    sh "docker push bsaksham/dotnetwebapp:tagname/dotnetwebapp:1.0"
                }
            }
        }

    }
}

# shared_lib_build_deploy_flask


# Jenkins Shared Library: push_deploy

This Jenkins shared library provides reusable functions for common tasks in Jenkins pipelines.

## Code Explanation

The provided code defines a Jenkins shared library with two functions:

### `buildAndPushDockerImage(credentialsId)`

This function logs in to Docker Hub using the specified credentials ID, builds a Docker image from the Dockerfile in the repository, and pushes the image to Docker Hub.

### `applyKubernetesResources(credentialsId)`

This function applies Kubernetes resources (e.g., deployments, services) using the specified `kubeconfig` file as credentials.

## Usage

To use this shared library in your Jenkins pipeline, follow these steps:

1. **Configure Jenkins**: Ensure that your Jenkins instance is configured to use shared libraries. You can configure this in the Jenkins global configuration.

2. **Import the Library**: Import the shared library into your Jenkins pipeline by adding the `@Library` annotation at the top of your pipeline script:

   ```groovy
   @Library('push_deploy') _




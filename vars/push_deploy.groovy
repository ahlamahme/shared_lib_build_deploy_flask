// // your-shared-library-name.groovy

def call(body) {
    // Define a closure to delegate to the body block
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    // Define the functions to be used in the pipeline
    def buildAndPushDockerImage(credentialsId) {
        // Use withCredentials block to access Docker Hub credentials
        withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
            // Execute Docker commands here
            sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
            sh "docker build -t ahlamahmed/flask:latest ."
            sh "docker push ahlamahmed/flask:latest"
        }
    }

    def applyKubernetesResources(credentialsId) {
        // Use withCredentials block to access Kubernetes credentials
        withCredentials([file(credentialsId: credentialsId, variable: 'KUBECONFIG_FILE')]) {
            // Apply Kubernetes resources
            sh "export KUBECONFIG=${KUBECONFIG_FILE} && kubectl apply -f ."
        }
    }

    // Return the defined functions
    return [buildAndPushDockerImage: buildAndPushDockerImage, applyKubernetesResources: applyKubernetesResources]
}

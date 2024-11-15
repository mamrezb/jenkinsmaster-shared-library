def call(Map config) {
    stage("Checkout ${config.projectName}") {
        checkout scm: [$class: 'GitSCM', branches: [[name: config.branch]], userRemoteConfigs: [[url: config.gitRepo]]]
    }
    
    stage("Build Docker Image") {
        docker.build("${config.projectName}:latest", "-f ${config.dockerFileName} .").push()
        config.IMAGE_NAME = config.projectName
        config.IMAGE_TAG = 'latest'
    }
}

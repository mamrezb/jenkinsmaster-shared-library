def call(Map config) {
    stage("Deploy ${config.projectName}") {
        def workspace = env.WORKSPACE
        def composeFilePath = "${workspace}/docker-compose.yml"
        libraryResource('docker-compose.yml').writeTo(composeFilePath)

        sh """
        IMAGE_NAME=${config.IMAGE_NAME} \
        IMAGE_TAG=${config.IMAGE_TAG} \
        CONTAINER_NAME=${config.projectName}_container \
        HOST_PORT=${config.hostPort} \
        CONTAINER_PORT=${config.containerPort} \
        docker-compose -f /path/to/deploy/directory/docker-compose.yml up -d
        """
    }
}


# Jenkins Shared Library: Docker Build & Deployment Utilities

This repository provides shared utilities for Jenkins to streamline Docker-based build and deployment workflows.
It includes modular functions to build Docker images and deploy containers using Docker Compose on the Jenkins host.

## Repository Structure

- **vars/**: Contains reusable Groovy scripts for Jenkins pipelines, including Docker build and deployment functions.
- **resources/**: Contains supplementary configuration files (e.g., Docker Compose templates).

## Prerequisites

- **Jenkins** with the **Pipeline Shared Groovy Libraries** plugin installed.
- Ensure Docker and Docker Compose are installed on the Jenkins server.

## Installation

1. **Clone or Fork the Repository**: Add this repository to your Jenkins shared library path.
2. **Configure Jenkins**: Go to `Manage Jenkins > Configure System > Global Pipeline Libraries`, and add this repository as a global library.

## Usage

To use this shared library in a Jenkinsfile, call the library with `@Library('library-name')` and pass configuration parameters as shown below.

### Example Jenkinsfile for Backend Deployment

```groovy
@Library('custom-shared-lib') _

def config = [
    gitRepo: 'https://github.com/your-organization/backend-helloworld.git',
    branch: 'main',
    projectName: 'backend-helloworld',
    dockerFileName: 'Dockerfile',
    containerPort: 8080,
    hostPort: 8000
]

buildDockerImage(config)
deployWithComposeOnHost(config)
```

### Parameters

| Parameter       | Description                               |
|-----------------|-------------------------------------------|
| `gitRepo`       | The Git repository URL for the application |
| `branch`        | The Git branch to use                     |
| `projectName`   | Name of the project and Docker container  |
| `dockerFileName`| Path to the Dockerfile within the repository |
| `containerPort` | Port exposed by the application in the container |
| `hostPort`      | Port on the host machine for the container |

## Function Reference

### `buildDockerImage(config)`

Builds a Docker image based on the provided Git repository and Dockerfile.

- **Inputs**: `config` map with parameters (`gitRepo`, `branch`, `dockerFileName`, `projectName`).
- **Output**: Built Docker image available on Jenkins host.

### `deployWithComposeOnHost(config)`

Deploys the Docker container using Docker Compose. Uses a pre-defined template in `resources/docker-compose.yml`.

- **Inputs**: `config` map with parameters (`projectName`, `hostPort`, `containerPort`).
- **Output**: Running container instance accessible on `hostPort`.

## Docker Compose Template

The Docker Compose template is located in `resources/docker-compose.yml` and uses placeholders that are dynamically replaced by the library functions based on the `config`.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

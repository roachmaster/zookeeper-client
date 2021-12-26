node {
    boolean runBuildAndTest = env.REPO_BUILD_TEST.toBoolean()
    boolean runDockerBuild = env.DOCKER_BUILD.toBoolean()
    boolean k3sBuild = env.K3S_BUILD.toBoolean()

    stage("Clone"){
        git 'git@github.com:roachmaster/zookeeper-client.git'
    }

    stage("Build"){
        if(runBuildAndTest){
            sh "./gradlew clean build --info"
        }
    }

    stage("Docker Build"){
        if(runBuildAndTest && runDockerBuild){
            withCredentials([usernamePassword(credentialsId: '87e61f11-079d-4052-b083-ea5859f0f85b', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                def dockerVersion = sh(returnStdout: true, script: "./gradlew properties -q --no-daemon --console=plain -q | grep '^version:' | awk '{print \$2}'").trim()
                dockerBuild(dockerName:"${DOCKER_USERNAME}/zookeeper-client:${dockerVersion}",
                            dockerOpt:"--build-arg JAR_FILE=build/libs/zookeeper-client-${dockerVersion}.jar",
                            DOCKER_PASSWORD: "${DOCKER_PASSWORD}",
                            DOCKER_USERNAME:"${DOCKER_USERNAME}")
            }
        }
    }

    stage("K3S Deployment"){
        if(k3sBuild){
            k3sDeployment name: "zookeeper-client"
            k3sService name: "zookeeper-client"
            waitForPodToBeReady name:"zookeeper-client", maxNumOfAttempts: 30
        }
    }
}
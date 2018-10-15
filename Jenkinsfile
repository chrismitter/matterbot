node {
    checkout scm

    properties([
            parameters([
                    string(defaultValue: 'sc-int', description: 'k8s stage', name: 'stage'),
                    string(defaultValue: 'default', description: 'k8s namespace', name: 'namespace')
            ]),
            pipelineTriggers([])
    ])

    def version = sh(returnStdout: true, script: 'grep -E "^version=(.*)$" gradle.properties | cut -d= -f2 | sed "s/-SNAPSHOT//g"').trim()
    def build = "${env.BRANCH_NAME.replace('/', '.')}-${version}-${env.BUILD_NUMBER}"
    def projectName = "matterbot"

    sh "gradle ${JAVA_PROXY} clean build -x test"
    sh "docker build -f gradle/Dockerfile build/libs -t ${projectName}:${build}"

    //sh "docker run -d --rm -p 1337:1337 --name=matterbot -e "http_proxy_host=INSERT_PROXY" -e "MATTERMOST_HOOK=INSERT_MATTERMOST_HOOK" -e "GIPHY_API_KEY=INSERT_GIPHY_API_KEY" -e "MATTERMOST_HOST=INSERT_MATTERMOST_HOST" ${projectName}:${build}"
}
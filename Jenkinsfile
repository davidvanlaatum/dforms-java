pipeline {
    agent any
    stages {
        stage('test') {
            steps {
                withEnv(["JAVA_HOME=${tool 'jdk-1.8'}", "PATH+MAVEN=${tool 'M3'}/bin:${env.JAVA_HOME}/bin"]) {
                    sh "mvn --batch-mode -V -U -e clean verify -Dsurefire.useFile=false"
                }
                step([$class: 'WsCleanup'])
            }
        }
    }
}

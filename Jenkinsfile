pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage('Clean') {
            steps {
                sh './gradlew clean'
            }
        }
        stage('Build') {
            steps {
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Analyze'){
            steps {
                step([$class: 'JacocoPublisher',
                      execPattern: '**/**.exec',
                      classPattern: '**/classes',
                      inclusionPattern: '**/com/carlmagnuson/**/*.class',
                      sourcePattern: '**/src/main/java',
                      exclusionPattern:  '**/src/test/**, **/*Tests.class, **/*Test.class, **/R.class, **/R$*.class, **/*$ViewInjector*.*, **/BuildConfig.*, **/Manifest*.*, **/**OuterClass*.*'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true

            junit 'build/test-results/**/*.xml'

            recordIssues enabledForFailure: true, tools: [java(), javaDoc()]
            recordIssues(
                    enabledForFailure: true,
                    tool: spotBugs(pattern: 'build/reports/findbugs/main.xml'),
            )
            recordIssues(
                    enabledForFailure: true,
                    tool: pmdParser(pattern: 'build/reports/pmd/main.xml'),
            )
            recordIssues(tools: [errorProne()])
            recordIssues(tool: taskScanner(highTags:'FIXME', normalTags:'TODO', includePattern: '**/*.java', excludePattern: 'target/**/*'))
        }
    }
}

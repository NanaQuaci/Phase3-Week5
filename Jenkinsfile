pipeline {
    agent any

    tools {
        allure 'Allure_Report'
    }

    environment {
        IMAGE_NAME = 'appium_swaglabs_automation'
        APPIUM_SERVER = 'http://appium:4723/wd/hub'
    }

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Test Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Start Emulator + Appium + Tests') {
            steps {
                sh '''
                    mkdir -p allure-results target/surefire-reports
                    chmod 777 allure-results target/surefire-reports

                    echo ">>> Starting emulator, Appium, and test containers..."
                    docker compose up --abort-on-container-exit --build
                '''
            }
        }

        stage('Publish Allure Report') {
            when {
                expression { fileExists('allure-results') }
            }
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'allure-results']]
                ])
            }
        }
    }

    post {
        always {
            echo 'Cleaning up containers and archiving reports...'

            sh 'docker compose down || true'

            archiveArtifacts artifacts: 'allure-results/**', allowEmptyArchive: true
            junit '**/target/surefire-reports/*.xml'

            script {
                try {
                    def statusEmoji = currentBuild.result == 'SUCCESS' ? ":white_check_mark:" :
                                      currentBuild.result == 'UNSTABLE' ? ":warning:" : ":x:"
                    def statusText = currentBuild.result?.toLowerCase() ?: "unknown"
                    def commitHash = env.GIT_COMMIT ? env.GIT_COMMIT.take(8) : "unknown"

                    def message = "${statusEmoji} Appium SwagLabs Tests finished with status: ${statusText}\n" +
                                  ":package: Repo: ${env.JOB_NAME}\n" +
                                  ":link: Commit: ${commitHash}\n" +
                                  ":bar_chart: Allure Report: ${env.BUILD_URL}allure/"

                    slackSend(
                        channel: '#ci-alerts',
                        color: currentBuild.result == 'SUCCESS' ? 'good' : 'danger',
                        message: message
                    )
                } catch (err) {
                    echo "Slack notification failed: ${err}"
                }
            }
        }
    }
}
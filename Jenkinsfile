/*pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                echo "Webhook Trigger Working"
            }
        }
    }
}*/



/*
parameters {
    choice(name: 'TEST_TYPE', choices: ['smoke', 'regression'], description: 'Select test type')
}

stages {
    stage('Test') {
        steps {
            sh "mvn test -Dgroups=${params.TEST_TYPE}"
        }
    }
}
*/






pipeline {
    agent any

    parameters {
        choice(name: 'TEST_TYPE', choices: ['smoke', 'regression'], description: 'Select test type')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/Madhushree23/RestAssured_Framework_CRUD.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat "mvn test -Dgroups=${params.TEST_TYPE}"
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }

        success {
            echo "Tests passed: ${params.TEST_TYPE}"
        }

        failure {
            echo "Tests failed: ${params.TEST_TYPE}"
        }
    }
}

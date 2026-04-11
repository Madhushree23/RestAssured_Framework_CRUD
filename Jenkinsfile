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

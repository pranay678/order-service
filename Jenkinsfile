pipeline {
  agent any

  options {
    timeout(time: 15, unit: 'MINUTES')
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build') {
      steps {
        sh 'mvn -B clean compile -f sample-projects/order-service/pom.xml'
      }
    }

    stage('Test') {
      steps {
        // Intentional test failure: applyDiscount_withNullDiscount_shouldThrowMeaningfulError
        sh 'mvn -B test -f sample-projects/order-service/pom.xml'
      }
      post {
        always {
          junit allowEmptyResults: true,
                testResults: 'sample-projects/order-service/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Package') {
      steps {
        sh 'mvn -B package -DskipTests -f sample-projects/order-service/pom.xml'
      }
    }
  }

  post {
    failure {
      echo 'Build FAILED — trigger diagnosis copilot'
    }
  }
}

pipeline {
  agent any

  tools {
    maven 'Maven 3'
    jdk   'JDK 21'
  }

  options {
    timestamps()
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
        sh 'mvn -B clean compile -pl sample-projects/order-service -am'
      }
    }

    stage('Test') {
      steps {
        // Intentional test failure: applyDiscount_withNullDiscount_shouldThrowMeaningfulError
        sh 'mvn -B test -pl sample-projects/order-service -am'
      }
      post {
        always {
          junit 'sample-projects/order-service/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Package') {
      steps {
        sh 'mvn -B package -DskipTests -pl sample-projects/order-service -am'
      }
    }
  }

  post {
    failure {
      echo 'Build FAILED — trigger diagnosis copilot'
    }
  }
}

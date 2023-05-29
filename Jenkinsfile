pipeline {
    parameters {
        string(name: 'BRANCH', defaultValue: 'main', description: 'Branch do repositório')
        string(name: 'AMBIENTE', defaultValue: 'dev', description: 'Ambiente de deploy')
    }

    agent any

    stages {
        stage('Clonar repositório') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/sauloddiniz/distance.checker.git'
            }
        }

        stage('Compilar código') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Criar pacote') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy no EC2') {
            steps {
                withCredentials([
                    awsAccessKey(credentialsId: 'aws-access-key', variable: 'AWS_ACCESS_KEY_ID'),
                    awsSecretKey(credentialsId: 'aws-secret-access-key', variable: 'AWS_SECRET_ACCESS_KEY')
                ]) {
                    script {
                        def ec2Instance = 'seu-id-da-instancia-ec2'
                        def region = 'sua-regiao-da-aws'
                        def artifactPath = 'caminho/do/seu/pacote.jar'

                        sh "aws ec2 run-command --instance-id ${ec2Instance} --region ${region} --command 'sudo service minha-aplicacao stop'"
                        sh "aws s3 cp ${artifactPath} s3://seu-bucket"
                        sh "aws ec2 run-command --instance-id ${ec2Instance} --region ${region} --command 'sudo aws s3 cp s3://seu-bucket/pacote.jar /caminho/da/aplicacao.jar'"
                        sh "aws ec2 run-command --instance-id ${ec2Instance} --region ${region} --command 'sudo service minha-aplicacao start'"
                    }
                }
            }
        }
    }
}
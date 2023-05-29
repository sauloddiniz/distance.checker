pipeline {
    agent any

    stages {
        stage('Clonar repositório') {
            steps {
                git 'https://github.com/sauloddiniz/distance.checker'
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
                script {
                    def awsAccessKeyId = credentials('aws-access-key')
                    def awsSecretAccessKey = credentials('aws-secret-access-key')
                    def ec2Instance = 'seu-id-da-instancia-ec2'
                    def region = 'sua-regiao-da-aws'
                    def artifactPath = 'caminho/do/seu/pacote.jar'

                    withAWS(region: region, credentials: awsAccessKeyId) {
                        sh "aws configure set aws_access_key_id ${awsAccessKeyId}"
                        sh "aws configure set aws_secret_access_key ${awsSecretAccessKey}"

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


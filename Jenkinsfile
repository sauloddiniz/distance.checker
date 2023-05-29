node {
    // Defina os parâmetros de entrada
    def fullBranchName = params.BRANCH_NAME
    def environment = params.ENVIRONMENT
    def branchName = fullBranchName.replaceFirst('origin/', '')

    stage('Clonar Projeto') {
        git branch: branchName, url: 'https://github.com/sauloddiniz/distance.checker.git'
    }

    stage('Outras Etapas') {
        echo "Branch: ${branchName}"
        echo "Ambiente: ${environment}"
    }

    stage('Build da Aplicação') {
        sh 'mvn package'
    }

    stage('Executar Testes') {
        echo "Testes iniciados"
        sh 'mvn test'
    }
}

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

    stage('Executar Testes') {
        echo "Testes iniciados"
        sh 'mvn test'
    }
}

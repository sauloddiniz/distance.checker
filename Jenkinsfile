node {
    // Defina os parâmetros de entrada
    def branchName = params.BRANCH_NAME
    def environment = params.ENVIRONMENT

    stage('Clonar Projeto') {
        git branch: branchName, url: 'https://github.com/sauloddiniz/distance.checker.git'
    }

    stage('Outras Etapas') {
        // Adicione as outras etapas do seu pipeline
        // Usando os valores dos parâmetros
        echo "Branch: ${branchName}"
        echo "Ambiente: ${environment}"
    }
}

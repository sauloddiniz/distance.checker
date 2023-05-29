node {
    // Defina os parâmetros de entrada
    def branchName = params.BRANCH_NAME
    def environment = params.ENVIRONMENT

    stage('Clonar Projeto') {
        echo "Clonando o projeto"
        echo "Branch: ${branchName}"
        echo "Ambiente: ${environment}"
        git branch: 'master', url: 'https://github.com/sauloddiniz/distance.checker.git'
    }

    stage('Outras Etapas') {
        // Adicione as outras etapas do seu pipeline
        // Usando os valores dos parâmetros
        echo "Branch: ${branchName}"
        echo "Ambiente: ${environment}"
    }
}

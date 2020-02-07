angular.module('app').controller('aluno', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();

    $scope.resetar = function () {
        $scope.aluno = null;
        document.getElementById('formAluno').reset();
        $scope.submitted = false;
    };
    
    $scope.alunoEdit = function (obj) {
        $scope.aluno = obj;
        $scope.mostrarCadastrar = false;
        $scope.mostrarAtualizar = true;
    }

    $scope.cancelarAtualizar = function () {
        $scope.mostrarCadastrar = true;
        $scope.mostrarAtualizar = false;
        $scope.resetar();
    };

    $scope.atualizar = function (obj) {
        $http({
            method: 'PUT',
            url: 'http://localhost:8080/alunos/update',
            data: obj
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.listarAlunos();
            $scope.mostrarCadastrar = true;
            $scope.mostrarAtualizar = false;
            $scope.resetar();
        }, function errorCallback(response) {

            console.log(response.status);
            $scope.erroMsg = response.data.message;
        });
    };

    $scope.listarAlunos = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/alunos/get-all'
        }).then(function successCallback(response) {

            console.log(response.data);
            $scope.alunoLista = response.data;
        }, function errorCallback(response) {

            console.log(response.status)
        });
    };

    $scope.cadastrar = function (obj) {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/alunos/save',
            data: obj
        }).then(function successCallback(response) {
            console.log(response.status);

            $scope.listarAlunos();
            $scope.resetar();
        }, function errorCallback(response) {

            if (response.data.message == 'CPF já cadastrado') {
                $scope.erroCPF = response.data.message;
            }

            if (response.data.message == 'E-mail já cadastrado') {
                $scope.erroEmail = response.data.message;
            }
        });
    };

    $scope.excluir = function (id) {
        console.log(id);

        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/alunos/delete/' + id,
            data: id
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.listarAlunos();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };
}]);
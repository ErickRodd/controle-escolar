angular.module('app').controller('turma', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();

    $scope.listarTurnos = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/turnos/list'
        }).then(function successCallback(response) {
            console.log(response.status);

            $scope.turnoLista = response.data;

            console.log($scope.turnoLista);

        }, function errorCallback(response) {

            console.log(response.status);
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

    $scope.resetar = function () {
        $scope.turma = null;
        document.getElementById('formTurma').reset();
        $scope.submitted = false;
    };

    $scope.buscarTurma = function (id) {

        $http({
            method: 'GET',
            url: 'http://localhost:8080/turmas/' + id,
            data: id
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.turma = response.data;
            $scope.mostrarCadastrar = false;
            $scope.mostrarAtualizar = true;
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.cancelarAtualizar = function () {
        $scope.mostrarCadastrar = true;
        $scope.mostrarAtualizar = false;
        $scope.resetar();
    };

    $scope.atualizar = function (obj) {
        $http({
            method: 'PUT',
            url: 'http://localhost:8080/turmas/update',
            data: obj
        }).then(function successCallback(response) {

            console.log($scope.turma);
            console.log(response.status);
            $scope.listarTurmas();
            $scope.mostrarCadastrar = true;
            $scope.mostrarAtualizar = false;
            $scope.resetar();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.listarTurmas = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/turmas/list'
        }).then(function successCallback(response) {

            console.log(response.data);
            $scope.turmaLista = response.data;
        }, function errorCallback(response) {

            console.log(response.status)
        });
    };

    $scope.cadastrar = function (obj) {
        console.log($scope.turma);

        $http({
            method: 'POST',
            url: 'http://localhost:8080/turmas/save',
            data: obj
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.listarTurmas();
            $scope.resetar();
        }, function errorCallback(response) {

            console.log($scope.turma);
            console.log(response.status);
        });
    };

    $scope.excluir = function (id) {
        console.log(id);

        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/turmas/delete/' + id,
            data: id
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.listarTurmas();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };
}]);
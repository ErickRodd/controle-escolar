angular.module('app').controller('turma', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();
    $scope.turma = {};

    $scope.listarTurnos = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/turnos/list'
        }).then(function successCallback(response) {

            $scope.turnoLista = response.data;
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.listarAlunos = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/alunos/list-with-no-turma'
        }).then(function successCallback(response) {

            console.log(response.data)
            $scope.alunoLista = response.data;
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.resetar = function () {
        $scope.turma = null;
        document.getElementById('formTurma').reset();
        $scope.submitted = false;
    };

    $scope.buscarTurma = function (id) {
        $('button.acoes').attr('disabled', true);

        $http({
            method: 'GET',
            url: 'http://localhost:8080/turmas/' + id,
            data: id
        }).then(function successCallback(response) {

            for (i = 0; i < response.data.alunos.length; i++) {
                $scope.alunoLista.push(response.data.alunos[i]);
            }

            $scope.turma = response.data;

            $scope.mostrarCadastrar = false;
            $scope.mostrarAtualizar = true;

        }, function errorCallback(response) {
            console.log(response.status);
        });

        console.log($scope.alunoLista);
    };

    $scope.cancelarAtualizar = function () {
        $scope.mostrarCadastrar = true;
        $scope.mostrarAtualizar = false;
        $scope.resetar();
        $scope.listarAlunos();
        $('button.acoes').attr('disabled', false);
    };

    $scope.atualizar = function (obj) {
        $http({
            method: 'PUT',
            url: 'http://localhost:8080/turmas/update',
            data: obj
        }).then(function successCallback(response) {

            $scope.listarTurmas();
            $scope.listarAlunos();
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

            $scope.turmaLista = response.data;
        }, function errorCallback(response) {

            console.log(response.status)
        });
    };

    $scope.cadastrar = function (obj) {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/turmas/save',
            data: obj
        }).then(function successCallback(response) {

            $scope.listarAlunos();
            $scope.listarTurmas();
            $scope.resetar();
        }, function errorCallback(response) {

            console.log($scope.turma);
            console.log(response.status);
        });
    };

    $scope.excluir = function (id) {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/turmas/delete/' + id,
            data: id
        }).then(function successCallback(response) {

            $scope.listarAlunos();
            $scope.listarTurmas();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };
}]);
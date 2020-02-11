angular.module('app').controller('notas', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();
    $scope.aluno = {};

    $scope.resetar = function () {
        $scope.nota = {};
        $scope.aluno = {};
        document.getElementById('formNota').reset();
        $scope.submitted = false;
    };

    $scope.cadastrarNota = function (obj) {
        console.log(obj);

        $http({
            method: 'POST',
            url: 'http://localhost:8080/notas/save',
            data: obj
        }).then(function successCallback(response) {
            $scope.resetar();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.listarBimestres = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/bimestres/list'
        }).then(function successCallback(response) {

            $scope.bimestres = response.data;
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.listarDisciplinas = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/disciplinas/list'
        }).then(function successCallback(response) {

            $scope.disciplinas = response.data;
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.listarTurmas = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/turmas/list'
        }).then(function successCallback(response) {

            $scope.turmas = response.data;
            $scope.turmas2 = response.data;
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.getAlunos = function (obj) {
        $scope.alunos = obj;
    }
    $scope.getAlunos2 = function (obj) {
        $scope.alunos2 = obj;
    }

    

    $scope.preencherCampoAluno = function (obj) {
        $scope.aluno.nome = obj.nome + ' ' + obj.sobrenome;
    }
}]);
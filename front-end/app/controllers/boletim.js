angular.module('app').controller('boletim', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();
    $scope.boletim = {};
    $scope.alunos = {};

    $scope.resetar = function (){
        $scope.turma = '';
        $scope.aluno = '';
        $scope.bimestre = '';
    }

    $scope.gerar = function (idA, idB) {

        $http({
            method: 'GET',
            url: 'http://localhost:8080/notas/export/' + idA + '/bimestre/' + idB,
        }).then(function successCallback(response) {

            window.location.href = 'http://localhost:8080/notas/export/' + idA + '/bimestre/' + idB;
            $scope.submitted = false;
            $scope.resetar();
        }, function errorCallback(response) {

            if(response.data.message.startsWith('Ne')){
                $scope.erroBoletim  = response.data.message;
            }
            if(response.data.message.startsWith('No')){
                $scope.erroBoletim  = response.data.message;
            }

            console.log(response.status);
        });
    };

    $scope.listarTurmas = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/turmas/list'
        }).then(function successCallback(response) {

            $scope.turmas = response.data;
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

    $scope.listarAlunos = function (obj) {

        $scope.alunos = obj;
    }
}]);
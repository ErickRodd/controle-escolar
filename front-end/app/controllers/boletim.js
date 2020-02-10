angular.module('app').controller('boletim', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();
    $scope.boletim = {};
    $scope.alunos = {};

    $scope.gerar = function (idA, idB) {

        $http({
            method: 'GET',
            url: 'http://localhost:8080/notas/export/' + idA + '/bimestre/' + idB
        }).then(function successCallback(response) {
            console.log(response.status);

            $scope.mostrarCadastrar=false; 
            $scope.mostrarAtualizar=true;

        }, function errorCallback(response) {

            console.log(response.status);
            console.log(response.data);
        });
    };

    $scope.listarTurmas = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/turmas/list'
        }).then(function successCallback(response) {

            $scope.turmas = response.data;
            console.log(response.status);
        }, function errorCallback(response) {

            console.log(response.status);
            console.log(response.data);
        });
    };

    $scope.listarBimestres = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/bimestres/list'
        }).then(function successCallback(response) {
            $scope.bimestres = response.data;

            console.log(response);
        }, function errorCallback(response) {
            console.log(response.status);
            console.log(response.data);
        });
    };

    $scope.listarAlunos = function (obj) {

        $scope.alunos = obj;
        console.log($scope.alunos);
    }
}]);
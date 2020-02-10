angular.module('app').controller('boletim', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();

    $scope.gerar = function (obj){
        console.log(obj);

        $http({
            method: 'POST',
            url: 'http://localhost:8080/boletins/save',
            data: obj
        }).then(function successCallback(response){
            console.log(response.status);

            $scope.resetar();
        }, function errorCallback(response){
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

    $scope.listarTurmasConsulta = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/turmas/list'
        }).then(function successCallback(response) {
            $scope.turmas2 = response.data;
        }, function errorCallback(response) {
            console.log(response.status);
            console.log(response.data);
        });
    };


    $scope.listarBimestres = function (){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/bimestres/list'
        }).then(function successCallback(response){
            $scope.bimestres = response.data;

            console.log(response);
        }, function errorCallback(response){
            console.log(response.status);
            console.log(response.data);
        });
    };

    $scope.getAlunos = function (obj){
        $scope.alunos = obj;
    }
    $scope.getAlunos2 = function (obj){
        $scope.alunos2 = obj;
    }
}]);
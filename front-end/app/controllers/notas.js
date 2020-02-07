angular.module('app').controller('notas', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();

    $scope.cadastrarNota = function (obj){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/notas/save',
            data: obj
        }).then(function successCallback(response){
            console.log(response.status);
        }, function errorCallback(response){
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
        }, function errorCallback(response){
            console.log(response.status);
            console.log(response.data);
        });
    };

    $scope.listarDisciplinas = function () {
        $http({
            method: 'GET',
            url : 'http://localhost:8080/disciplinas/list'
        }).then(function successCallback(response){
            $scope.disciplinas = response.data;
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
        }, function errorCallback(response) {
            console.log(response.status);
            console.log(response.data);
        });
    };

    $scope.getAlunos = function (obj){
        $scope.alunos = obj;
    }

    $scope.preencherCampoAluno = function(obj){
        $scope.turma.aluno = obj;

        console.log($scope.turma.alunoId);
    }
}]);
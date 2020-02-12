angular.module('app').controller('professor', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();

    $scope.resetar = function () {
        $scope.professor = null;
        document.getElementById('formProfessor').reset();
        $scope.submitted = false;
    };

    $scope.buscarProfessor = function (id) {

        $http({
            method: 'GET',
            url: 'http://localhost:8080/professor/get/' + id,
            data: id
        }).then(function successCallback(response) {

            $scope.professor = response.data;
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
            url: 'http://localhost:8080/professor/update',
            data: obj
        }).then(function successCallback(response) {

            $scope.listarProfessores();
            $scope.mostrarCadastrar = true;
            $scope.mostrarAtualizar = false;
            $scope.resetar();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.listarProfessores = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/professor/get-all'
        }).then(function successCallback(response) {

            $scope.professorLista = response.data;
        }, function errorCallback(response) {

            console.log(response.status)
        });
    };

    $scope.cadastrar = function (obj) {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/professor/save',
            data: obj
        }).then(function successCallback(response) {

            $scope.listarProfessores();
            $scope.resetar();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.excluir = function (id) {
        console.log(id);

        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/professor/delete/' + id,
            data: id
        }).then(function successCallback(response) {

            $scope.listarProfessores();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };
}]);
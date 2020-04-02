angular.module('app').controller('notas', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();
    $scope.turma = {};
    $scope.nota = {};
    $scope.notas = {};
    $scope.aluno = {};

    $scope.resetar = function () {
        $scope.nota = {};
        $scope.submitted = false;
    };

    $scope.cancelarAtualizar = function () {
        $scope.mostrarCadastrar = true;
        $scope.mostrarAtualizar = false;
        document.getElementById('turma').disabled = false;
        document.getElementById('aluno').disabled = false;
        $scope.resetar();
    };

    $scope.cadastrarNota = function (obj) {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/notas/save',
            data: obj
        }).then(function successCallback(response) {

            if ($scope.notas.length > -1) {
                $scope.listNotas(obj.alunoId);
            }

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

    $scope.findNota = function (id) {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/notas/' + id
        }).then(function successCallback(response) {
            $scope.nota = response.data;
            $scope.turma = $scope.turma2.id;
            document.getElementById('turma').disabled = true;
            document.getElementById('aluno').disabled = true;
            $scope.mostrarCadastrar = false;
            $scope.mostrarAtualizar = true;
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

    $scope.listNotas = function (id) {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/notas/list/' + id
        }).then(function successCallback(response) {
            $scope.notas = response.data;
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.editar = function (aluno) {
        $http({
            method: 'PUT',
            url: 'http://localhost:8080/notas/update',
            data: aluno
        }).then(function successCallback(response) {

            $scope.listNotas(aluno.alunoId);
            $scope.resetar();
            $scope.cancelarAtualizar();
        }, function errorCallback(response) {
            console.log(response.status);
        })
    }

    $scope.excluir = function (obj) {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/notas/delete/' + obj.id
        }).then(function successCallback(response) {
            $scope.listNotas(obj.aluno.id);
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
}]);
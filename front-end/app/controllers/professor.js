angular.module('app').controller('professor', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();

    $scope.resetar = function () {
        $scope.professor = null;
        document.getElementById('formProfessor').reset();
        $scope.submitted = false;
        $scope.erroCPF = null;
        $scope.erroEmail = null;
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
        info = {
            id: obj.id,
            nome: obj.nome,
            sobrenome: obj.sobrenome,
            cpf: obj.cpf.replace(/\.|\-/g, ''),
            telefone: obj.telefone,
            email: obj.email
        };

        $http({
            method: 'PUT',
            url: 'http://localhost:8080/professor/update',
            data: info
        }).then(function successCallback(response) {

            $scope.listarProfessores();
            $scope.mostrarCadastrar = true;
            $scope.mostrarAtualizar = false;
            $scope.resetar();

        }, function errorCallback(response) {
            console.log(response.status);

            if (response.data.message.startsWith('C')) {
                $scope.erroCPF = response.data.message;
            }

            if (response.data.message.startsWith('E')) {
                $scope.erroEmail = response.data.message;
            }
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
        info = {
            nome: obj.nome,
            sobrenome: obj.sobrenome,
            cpf: obj.cpf.replace(/\.|\-/g, ''),
            telefone: obj.telefone,
            email: obj.email
        };

        $http({
            method: 'POST',
            url: 'http://localhost:8080/professor/save',
            data: info
        }).then(function successCallback(response) {

            $scope.listarProfessores();
            $scope.resetar();

        }).catch(function errorCallback(response) {
            console.log(response.status);

            if (response.data.message.startsWith('C')) {
                $scope.erroCPF = response.data.message;
            }

            if (response.data.message.startsWith('E')) {
                $scope.erroEmail = response.data.message;
            }
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

    $scope.formatarCpf = function (str) {
        return str.substring(0, 3) + '.' + str.substring(3, 6) + '.' + str.substring(6, 9) + '-' + str.substring(9);
    }

    $scope.formatarTel = function (str) {
        if (str.length == 11) {
            return '(' + str.substring(0, 2) + ') ' + str.substring(2, 3) + ' ' + str.substring(3, 7) + '-' + str.substring(7);
        }

        return '(' + str.substring(0, 2) + ') ' + str.substring(2, 6) + '-' + str.substring(6);
    }
}]);
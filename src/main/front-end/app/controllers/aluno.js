angular.module('app').controller('aluno', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();

    $scope.resetar = function () {
        $scope.aluno = [];
        document.getElementById('formAluno').reset();
        $scope.submitted = false;
    };

    $scope.buscarAluno = function (id) {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/alunos/get/' + id,
            data: id
        }).then(function successCallback(response) {

            $scope.aluno = response.data;
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
        $scope.submitted = false;

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
            url: 'http://localhost:8080/alunos/update',
            data: info
        }).then(function successCallback(response) {

            $scope.listarAlunos();
            $scope.mostrarCadastrar = true;
            $scope.mostrarAtualizar = false;
            $scope.resetar();
        }, function errorCallback(response) {

            if (response.data.message.startsWith('C')) {
                $scope.erroCPF = response.data.message;
            }

            if (response.data.message.startsWith('E')) {
                $scope.erroEmail = response.data.message;
            }
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

    $scope.listarAlunos = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/alunos/get-all'
        }).then(function successCallback(response) {

            $scope.alunoLista = response.data;
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
            url: 'http://localhost:8080/alunos/save',
            data: info
        }).then(function successCallback(response) {

            $scope.listarAlunos();
            $scope.resetar();
        }, function errorCallback(response) {

            console.log(response.data.message);

            if (response.data.message.startsWith('C')) {
                $scope.erroCPF = response.data.message;
            }

            if (response.data.message.startsWith('E')) {
                $scope.erroEmail = response.data.message;
            }
        });
    };

    $scope.excluir = function (id) {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/alunos/delete/' + id,
            data: id
        }).then(function successCallback(response) {

            $scope.listarAlunos();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };
}]);
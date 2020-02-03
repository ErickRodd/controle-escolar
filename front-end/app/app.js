var app = angular.module('app', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/html/inicio.html',
            controller: 'inicio'
        })

        .when('/turma', {
            templateUrl: 'app/html/turma.html',
            controller: 'turma'
        })

        .when('/aluno', {
            templateUrl: 'app/html/aluno.html',
            controller: 'aluno'
        })

        .when('/professor', {
            templateUrl: 'app/html/professor.html',
            controller: 'professor'
        })

        // .when('/disciplinas', {
        //     templateUrl: 'app/html/disciplinas.html',
        //     controller: 'disciplina'
        // })

        .when('/notas', {
            templateUrl: 'app/html/notas.html',
            controller: 'notas'
        })

        .when('/boletim', {
            templateUrl: 'app/html/boletim.html',
            controller: 'boletim'
        })

        .otherwise(
            { redirectTo: '/' }
        );
});
app.controller('inicio', function ($rootScope, $location) { $rootScope.activetab = $location.path(); });
app.controller('turma', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) { 
    $rootScope.activetab = $location.path(); 

    $scope.listarTurnos = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/turnos/list'
        }).then(function successCallback(response){
            console.log(response.status);

            $scope.turnoLista = response.data;

            console.log($scope.turnoLista);

        }, function errorCallback(response){

            console.log(response.status);
        });
    };

    $scope.listarAlunos = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/alunos/get-all'
        }).then(function successCallback(response) {

            console.log(response.data);
            $scope.alunoLista = response.data;
        }, function errorCallback(response) {

            console.log(response.status)
        });
    };

    $scope.resetar = function () {
        $scope.turma = null;
        document.getElementById('formTurma').reset();
        $scope.submitted = false;
    };

    $scope.buscarTurma = function (id) {

        $http({
            method: 'GET',
            url: 'http://localhost:8080/turmas/' + id,
            data: id
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.turma = response.data;
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
            url: 'http://localhost:8080/turmas/update',
            data: obj
        }).then(function successCallback(response) {

            console.log($scope.turma);  
            console.log(response.status);
            $scope.listarTurmas();
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

            console.log(response.data);
            $scope.turmaLista = response.data;
        }, function errorCallback(response) {

            console.log(response.status)
        });
    };

    $scope.cadastrar = function (obj) {
        console.log($scope.turma);

        $http({
            method: 'POST',
            url: 'http://localhost:8080/turmas/save',
            data: obj
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.listarTurmas();
            $scope.resetar();
        }, function errorCallback(response) {

            console.log($scope.turma);
            console.log(response.status);
        });
    };

    $scope.excluir = function (id) {
        console.log(id);

        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/turmas/delete/' + id,
            data: id
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.listarTurmas();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };
}]);
app.controller('aluno', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
    $rootScope.activetab = $location.path();

    $scope.resetar = function () {
        $scope.aluno = null;
        document.getElementById('formAluno').reset();
        $scope.submitted = false;
    };

    $scope.buscarAluno = function (id) {
        console.log(id);

        $http({
            method: 'GET',
            url: 'http://localhost:8080/alunos/get/' + id,
            data: id
        }).then(function successCallback(response) {

            console.log(response.status);
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
    };

    $scope.atualizar = function (obj) {
        $http({
            method: 'PUT',
            url: 'http://localhost:8080/alunos/update',
            data: obj
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.listarAlunos();
            $scope.mostrarCadastrar = true;
            $scope.mostrarAtualizar = false;
            $scope.resetar();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };

    $scope.listarAlunos = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/alunos/get-all'
        }).then(function successCallback(response) {

            console.log(response.data);
            $scope.alunoLista = response.data;
        }, function errorCallback(response) {

            console.log(response.status)
        });
    };

    $scope.cadastrar = function (obj) {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/alunos/save',
            data: obj
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.listarAlunos();
            $scope.resetar();
        }, function errorCallback(response) {

            console.log(respons.statuse);
        });
    };

    $scope.excluir = function (id) {
        console.log(id);

        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/alunos/delete/' + id,
            data: id
        }).then(function successCallback(response) {

            console.log(response.status);
            $scope.listarAlunos();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };
}]);
app.controller('professor', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {
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

            console.log(response.status);
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

            console.log(response.status);
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

            console.log(response.data);
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

            console.log(response.status);
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

            console.log(response.status);
            $scope.listarProfessores();
        }, function errorCallback(response) {

            console.log(response.status);
        });
    };
}]);

app.controller('notas', function ($rootScope, $location) { $rootScope.activetab = $location.path(); });
app.controller('boletim', function ($rootScope, $location) { $rootScope.activetab = $location.path(); });

function campoNumerico(event) {
    var charCode = (event.which) ? event.which : event.keyCode;

    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    else {
        return true;
    }
};


// app.controller('disciplina', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) { 
//     $rootScope.activetab = $location.path(); 

//     $scope.resetar = function () {
//         $scope.disciplina = null;
//         document.getElementById('formDisciplina').reset();
//         $scope.submitted = false;
//     };

//     $scope.buscarProfessor = function (id) {

//         $http({
//             method: 'GET',
//             url: 'http://localhost:8080/disciplinas/' + id,
//             data: id
//         }).then(function successCallback(response) {

//             console.log(response.status);
//             $scope.disciplina = response.data;
//             $scope.mostrarCadastrar = false;
//             $scope.mostrarAtualizar = true;
//         }, function errorCallback(response) {

//             console.log(response.status);
//         });
//     };

//     $scope.cancelarAtualizar = function () {
//         $scope.mostrarCadastrar = true;
//         $scope.mostrarAtualizar = false;
//         $scope.resetar();
//     };

//     $scope.atualizar = function (obj) {
//         $http({
//             method: 'PUT',
//             url: 'http://localhost:8080/disciplinas/update',
//             data: obj
//         }).then(function successCallback(response) {

//             console.log(response.status);
//             $scope.listarDisciplinas();
//             $scope.mostrarCadastrar = true;
//             $scope.mostrarAtualizar = false;
//             $scope.resetar();
//         }, function errorCallback(response) {

//             console.log(response.status);
//         });
//     };

//     $scope.listarDisciplinas = function () {
//         $http({
//             method: 'GET',
//             url: 'http://localhost:8080/disciplinas/list'
//         }).then(function successCallback(response) {

//             console.log(response.data);
//             $scope.disciplinaList = response.data;
//         }, function errorCallback(response) {

//             console.log(response.status)
//         });
//     };

//     $scope.cadastrar = function (obj) {
//         $http({
//             method: 'POST',
//             url: 'http://localhost:8080/disciplinas/save',
//             data: obj
//         }).then(function successCallback(response) {

//             console.log(response.status);
//             $scope.listarDisciplinas();
//             $scope.resetar();
//         }, function errorCallback(response) {

//             console.log(response.status);
//         });
//     };

//     $scope.excluir = function (id) {
//         console.log(id);

//         $http({
//             method: 'DELETE',
//             url: 'http://localhost:8080/disciplinas/' + id,
//             data: id
//         }).then(function successCallback(response) {

//             console.log(response.status);
//             $scope.listarDisciplinas();
//         }, function errorCallback(response) {

//             console.log(response.status);
//         });
//     };
// }]);
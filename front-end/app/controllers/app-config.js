angular.module('app').config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'app/html/inicio.html',
            controller: 'inicio'
        })

        .when('/turmas', {
            templateUrl: 'app/html/turma.html',
            controller: 'turma'
        })

        .when('/alunos', {
            templateUrl: 'app/html/aluno.html',
            controller: 'aluno'
        })

        .when('/professores', {
            templateUrl: 'app/html/professor.html',
            controller: 'professor'
        })

        .when('/notas', {
            templateUrl: 'app/html/notas.html',
            controller: 'notas'
        })

        .when('/boletins', {
            templateUrl: 'app/html/boletim.html',
            controller: 'boletim'
        })

        .otherwise(
            { redirectTo: '/' }
        );
}]);
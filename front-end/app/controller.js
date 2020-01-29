var app = angular.module('app',['ngRoute']);

app.controller('inicio', []);
app.controller('turma', []);
app.controller('aluno', []);
app.controller('professor', []);
app.controller('disciplinas', []);
app.controller('notas', []);
app.controller('boletim', []);

app.config(function ($routeProvider) {

    $routeProvider
        .when('/inicio', {
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

        .when('/disciplinas', {
            templateUrl: 'app/html/disciplinas.html',
            controller: 'disciplinas'
        })

        .when('/notas', {
            templateUrl : 'app/html/notas.html',
            controller: 'notas'
        })

        .when('/boletim', {
            templateUrl: 'app/html/boletim.html',
            controller: 'boletim'
        })

        .otherwise(
            { redirectTo: '/inicio' }
        );
});
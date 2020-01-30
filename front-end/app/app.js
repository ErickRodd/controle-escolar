var app = angular.module('app', ['ngRoute']);

app.controller('inicio', function ($rootScope, $location) { $rootScope.activetab = $location.path(); });
app.controller('turma', function ($rootScope, $location) { $rootScope.activetab = $location.path(); });
app.controller('aluno', function ($rootScope, $location) { $rootScope.activetab = $location.path(); });
app.controller('professor', function ($rootScope, $location) { $rootScope.activetab = $location.path(); });
app.controller('disciplinas', function ($rootScope, $location) { $rootScope.activetab = $location.path(); });
app.controller('notas', function ($rootScope, $location) { $rootScope.activetab = $location.path(); });
app.controller('boletim', function ($rootScope, $location) { $rootScope.activetab = $location.path(); });
app.controller('cadastroAluno', ['$scope', function($scope) {
    $scope.master = {};
  
    $scope.update = function(aluno) {
      $scope.master = angular.copy(aluno);
    };
  
    $scope.reset = function(form) {
      if (form) {
        form.$setPristine();
        form.$setUntouched();
      }
      $scope.aluno = angular.copy($scope.master);
    };
  
    $scope.reset();
  }]);

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

        .when('/disciplinas', {
            templateUrl: 'app/html/disciplinas.html',
            controller: 'disciplinas'
        })

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

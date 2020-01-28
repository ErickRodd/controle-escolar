app.config(function($routeProvider){

    $routeProvider
    .when('/inicio', {
        templateUrl : 'app/views/home.html',
        controller  : 'HomeCtrl',
    })

    .otherwise ({ redirectTo: '/inicio' });
});
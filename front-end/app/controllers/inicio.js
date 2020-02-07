angular.module('app').controller('inicio', ['$rootScope', '$location', function ($rootScope, $location) { 
    $rootScope.activetab = $location.path(); 
}]);
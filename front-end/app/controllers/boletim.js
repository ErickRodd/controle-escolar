angular.module('app').controller('boletim', ['$rootScope', '$location', function ($rootScope, $location) {
    $rootScope.activetab = $location.path();
}]);
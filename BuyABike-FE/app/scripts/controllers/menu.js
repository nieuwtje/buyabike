var menuControllers = angular.module('menuControllers', ['ngRoute']);
menuControllers.controller('MenuCtrl', function ($scope, $location) {
    $scope.menuClass = function(page) {
        var current = $location.path().substring(1);
        return page === current ? "active" : "";
      };
  });

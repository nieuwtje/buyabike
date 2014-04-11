var filterControllers = angular.module('filterControllers', []);

filterControllers.controller('FilterCtrl', ['$scope', function($scope) {
  $scope.categorys = [{name:"Mountainbikes"},{name:"Racebikes"}];
  $scope.orderProp = 'age';
}]);
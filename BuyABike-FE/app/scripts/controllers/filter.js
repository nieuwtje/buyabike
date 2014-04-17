var filterControllers = angular.module('filterControllers', []);

filterControllers.controller('FilterCtrl','Category', ['$scope', function($scope, Category) {
  $scope.categorys = Catagory.query();
  $scope.orderProp = 'age';
}]);
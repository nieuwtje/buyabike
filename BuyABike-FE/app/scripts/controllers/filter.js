var filterControllers = angular.module('filterControllers', []);

filterControllers.controller('FilterCtrl','Category', ['$scope', function($scope, Category) {
  $scope.categorys = Catagory.query();
  $scope.orderProp = 'age';
  $scope.filterBy = function(row) {
	  console.log(!!_.where(row.categories, {name:$scope.search.name}).length);
	    return !!_.where(row.categories, {name:$scope.search.name}).length;
	}
}]);
var productControllers = angular.module('productControllers', []);

productControllers.controller('ProductListCtrl', ['$scope', 'Product', function($scope, Product) {
  $scope.products = Product.query();
  $scope.orderProp = 'age';

}]);
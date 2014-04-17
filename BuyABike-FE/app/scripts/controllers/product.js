var productControllers = angular.module('productControllers', []);

productControllers.controller('ProductListCtrl', ['$scope', 'Product', 'Category', function($scope, Product,Category) {
  $scope.products = Product.query();
  $scope.categorys = Category.query();
  $scope.orderProp = 'age';

}]);
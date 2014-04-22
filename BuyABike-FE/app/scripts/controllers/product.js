var productControllers = angular.module('productControllers', []);

<<<<<<< HEAD
productControllers.controller('ProductListCtrl', ['$scope', 'Product', 'Category', function($scope, Product,Category) {
  $scope.products = Product.query();
  $scope.categorys = Category.query();
  $scope.orderProp = 'age';
  console.log($scope)
  $scope.filterBy = function(product) {
	  console.log($scope)
	  if($scope.selectedC === undefined || $scope.selectedC.length === 0 ){
		  return true;
	  }
	  var pfff = false;
	  angular.forEach(product.categories, function(category){
		  if(category.name === $scope.selectedC){
			  pfff = true;
		  }
	  });
	  return pfff;
	}
  $scope.inRange = function(product) {
	  if($scope.priceRange){
		  var min = $scope.priceRange[0];
		  var max = $scope.priceRange[1];
		 return (product.resellPrice > min && product.resellPrice < max)
	  }
	  return true;
  }
}]);
productControllers.controller('ProductListCtrl', [
		'$scope',
		'Product',
		'Category',
		function($scope, Product, Category) {
			$scope.products = Product.query();
			$scope.categorys = Category.query();
			$scope.orderProp = 'age';
			$scope.numLimit = 150;
			$scope.filterBy = function(product) {
				console.log($scope)
				if ($scope.selectedC === undefined
						|| $scope.selectedC.length === 0) {
					return true;
				}
				var pfff = false;
				angular.forEach(product.categories, function(category) {
					if (category.name === $scope.selectedC) {
						pfff = true;
					}
				});
				return pfff;
			}
		} ]);
productControllers.controller('ProductDetailCtrl', [ '$scope', '$routeParams',
		function($scope, $routeParams) {
			$scope.productId = $routeParams.productId;
		} ]);

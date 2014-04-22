var productControllers = angular.module('productControllers', []);

productControllers.controller('ProductListCtrl', ['$scope','$filter', 'Product', 'Category', function($scope,$filter, Product,Category) {
  $scope.products = Product.query();
  $scope.categorys = Category.query();
  $scope.orderProp = 'age';
	$scope.numLimit = 150;
$scope.minPrice= 0;
$scope.maxPrice=1500;
$scope.priceRange=[0,1500];
	
	$scope.filterBy = function(product) {
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
  $scope.inRange = function(product) {
	  if($scope.priceRange){
		  var min = $scope.priceRange[0];
		  var max = $scope.priceRange[1];
		 return ((product.resellPrice > min||$scope.minPrice ===min) && (product.resellPrice < max || $scope.maxPrice===max ));
	  }
	  return true;
  }
}]);

productControllers.controller('ProductDetailCtrl', [ '$scope', '$routeParams',
		function($scope, $routeParams) {
			$scope.productId = $routeParams.productId;
		} ]);

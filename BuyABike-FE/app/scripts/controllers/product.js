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
  
  $scope.testFilter = function(range){
	  var result = "van ";
	  if($scope.minPrice >0 && $scope.minPrice === range[0]){
		  result = result + " < ";
	  }
		  result = result + $filter('currency')(range[0], '\u20AC') + " tot ";
	  
		  if($scope.maxPrice === range[1]){
			  result = result + " > ";
		  }
		  result = result + $filter('currency')(range[1], '\u20AC');
	  return result;
  }
}]);

productControllers.controller('ProductDetailCtrl', [ '$scope', '$routeParams','Product',
		function($scope, $routeParams, Product) {
			//$scope.productId = $routeParams.productId;
			$scope.product = Product.get({ productId: $routeParams.productId });
		} ]);

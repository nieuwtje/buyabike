var productControllers = angular.module('productControllers', []);

productControllers.controller('ProductListCtrl', ['$scope', 'Product', 'Category', function($scope, Product,Category) {
  $scope.products = Product.query();
  $scope.categorys = Category.query();
  $scope.orderProp = 'age';
  $scope.numLimit=150;
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
}]);
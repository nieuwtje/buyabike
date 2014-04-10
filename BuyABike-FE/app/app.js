var buyABikeApp = angular.module('buyABikeApp', [ 'ngRoute',
		'productControllers' ]);
buyABikeApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/products', {
		templateUrl : '/app/views/partials/product-list.html',
		controller : 'ProductListCtrl'
	}).when('/products/:productId', {
		templateUrl : '/views/partials/product-detail.html',
		controller : 'ProductDetailCtrl'
	}).otherwise({
		redirectTo : '/products'
	});
} ]);
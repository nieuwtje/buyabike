'use strict';
var buyabikefeapp = angular.module('buyAbikeFeApp',
		[ 'ngCookies', 'ngResource', 'ngSanitize', 'ngRoute','mainControllers','menuControllers','productControllers', 'productServices' ]);

buyabikefeapp.config(
		function($routeProvider) {
			$routeProvider.when('/', {
				templateUrl : 'views/main.html',
				controller : 'MainCtrl'
			}).when('/products', {
				templateUrl : 'views/product-list.html',
				controller : 'ProductListCtrl'
			}).otherwise({
				redirectTo : '/'
			});
		});

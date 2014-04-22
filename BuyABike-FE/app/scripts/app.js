'use strict';
var buyabikefeapp = angular.module('buyAbikeFeApp',
		[ 'ngCookies', 'ngResource', 'ngSanitize', 'ngRoute','mainControllers','menuControllers','productControllers','filterControllers', 'buyabikeServices' ]);

buyabikefeapp.config(
		function($routeProvider) {
			$routeProvider.when('/', {
				templateUrl : 'views/main.html',
				controller : 'MainCtrl'
			}).when('/producten', {
				templateUrl : 'views/product-list.html',
				controller : 'ProductListCtrl'
			}).when('/producten/fietsen', {
				redirectTo : '/producten'
			}).otherwise({
				redirectTo : '/'
			});
		});




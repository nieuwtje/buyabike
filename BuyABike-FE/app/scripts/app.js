'use strict';
var buyabikefeapp = angular.module('buyAbikeFeApp',
		[ 'ngCookies', 'ngResource', 'ngSanitize', 'ngRoute','mainControllers','menuControllers','productControllers', 'buyabikeServices','sliderDirectives' ]);

buyabikefeapp.config(
		function($routeProvider) {
			$routeProvider.when('/', {
				redirectTo : '/producten'
			}).when('/producten', {
				templateUrl : 'views/product-list.html',
				controller : 'ProductListCtrl'
			}).when('/producten/fietsen', {
				redirectTo : '/producten'
			}).otherwise({
				redirectTo : '/'
			});
		});




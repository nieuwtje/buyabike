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


buyabikefeapp.filter('property', property);

function property(){
    function parseString(input){
        return input.split(".");
    }
 
    function getValue(element, propertyArray){
        var value = element;
 
        _.forEach(propertyArray, function(property){
            value = value[property];
        });
 
        return value;
    }
 
    return function (array, propertyString, target){
        var properties = parseString(propertyString);
 
        return _.filter(array, function(item){
            return getValue(item, properties) == target;
        });
    }
}

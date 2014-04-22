var buyabikeServices = angular.module('buyabikeServices', ['ngResource'])
.constant('endpoints', {
	base:'http://localhost:8080/BuyABike-BE',
	products:'/products/:productId',
	categories: '/categories'
		
});
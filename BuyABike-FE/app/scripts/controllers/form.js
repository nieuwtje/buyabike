var formControllers = angular.module('formControllers', []);

formControllers.controller('FormLoginCtrl', ['$scope', 'Client', function($scope, Client) {
	$scope.submitLogin = function(form) {
		console.log($scope);
        console.log("dfhfgkjgdfhkgjfhfdkjhkj");
        console.log(form);
        
        Client.post("email=" + form.email + "&password=" + form.password);
		//Client.put({}, body);
    };
}]);
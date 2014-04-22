buyabikeServices.factory('Client', ['$resource', 'endpoints',
  function($resource, endpoints){
    return $resource(endpoints.base + endpoints.client, {}, {
    	post : {
    		method: 'POST',
    		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    	},
	    put : {
			method: 'PUT'
		}
    });
  }]);
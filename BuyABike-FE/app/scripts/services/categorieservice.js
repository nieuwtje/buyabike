buyabikeServices.factory('Category', ['$resource', 'endpoints',
  function($resource,endpoints){
    return $resource(endpoints.base+endpoints.categories, {}, {
      query: {method:'GET', isArray:true}
    });
  }]);
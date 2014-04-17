buyabikeServices.factory('Category', ['$resource',
  function($resource){
    return $resource(Endpoints.base+Endpoints.categories, {}, {
      query: {method:'GET', isArray:true}
    });
  }]);
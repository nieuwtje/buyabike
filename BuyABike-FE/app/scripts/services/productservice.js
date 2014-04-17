buyabikeServices.factory('Product', ['$resource',
  function($resource){
    return $resource(Endpoints.base+Endpoints.products, {}, {
      query: {method:'GET', isArray:true}
    });
  }]);
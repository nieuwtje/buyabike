buyabikeServices.factory('Product', ['$resource', 'endpoints',
  function($resource, endpoints){
    return $resource(endpoints.base+endpoints.products, {productId:'@productId'});
  }]);
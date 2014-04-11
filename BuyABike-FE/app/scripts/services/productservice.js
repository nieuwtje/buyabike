var productServices = angular.module('productServices', ['ngResource']);
 
productServices.factory('Product', ['$resource',
  function($resource){
    return $resource(Endpoints.products+'/:productId.json', {}, {
      query: {method:'GET', params:{productId:'products'}, isArray:true}
    });
  }]);
/**
 * built-in AngularJS directive Shows loading directive along http request and hide when ajax call is done
 * @return  'A' - only matches attribute name
 */
'use strict';

directives.directive('loading', ['$http' , function ($http) {
    return {
        restrict: 'A',
        replace:true,
        link: function (scope, element, attrs)
        {
            scope.isLoading = function () {
                return $http.pendingRequests.length > 0;
            };

            scope.$watch(scope.isLoading, function (value)
            {
                if(value){
                    element.show();

                }else{
                    element.hide();
                }
            });
        }
    };
}]);


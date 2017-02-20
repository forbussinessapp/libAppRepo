'use strict';

var app = angular.module('ngdemo', ['ngdemo.services', 'ngdemo.controllers', 'ngdemo.directives'])
    .config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
        $httpProvider.defaults.headers.common = {'Content-Type': 'application/json;charset=UTF-8'}

        //Using ng-view in index.html and routing the same page by $routeProvider is caused infinite loop.
        //To solve this specific problem index.tpl.html page is used
        $routeProvider.when('/', {templateUrl: 'views/innerView/index.tpl.html', controller: 'bookCtrl'});
        $routeProvider.otherwise({redirectTo: '/'});
}]);



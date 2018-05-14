(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies', 'ngSanitize'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];

    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                controller: 'HomeController',
                templateUrl: 'home/home.view.html',
                controllerAs: 'vm'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'register/register.view.html',
                controllerAs: 'vm'
            })

            .when('/results', {
                controller: 'ResultController',
                templateUrl: 'result/result.view.html',
                controllerAs: 'vm'
            })

            .when('/stats', {
                controller: 'StatsController',
                templateUrl: 'stats/stats.view.html',
                controllerAs: 'vm'
            })

            .when('/users', {
                controller: 'UserController',
                templateUrl: 'user/user.view.html',
                controllerAs: 'vm'
            })
            .when('/showdifference', {
                controller: 'ShowDifferenceController',
                templateUrl: 'showdifference/showdifference.view.html',
                controllerAs: 'vm'
            })

            .otherwise({redirectTo: '/login'});
    }

    run.$inject = ['$rootScope', '$location', '$cookies', '$http'];

    function run($rootScope, $location, $cookies, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register', '/results', '/users']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }

})();
(function () {
    'use strict';

    angular
        .module('app')
        .controller('StatsController', StatsController);

    StatsController.$inject = ['UserService', '$location', '$rootScope'];
    function StatsController(UserService, $location, $rootScope) {
        var vm = this;

        vm.user = null;
        vm.folderStats = null;

        initController();

        function initController() {
            loadCurrentUser();
            initStats();
        }

        function loadCurrentUser() {
            UserService.GetByEmail($rootScope.globals.currentUser.email)
                .then(function (user) {
                    vm.user = user;
                });
        }

        function initStats() {
            vm.folderStats = $rootScope.folderStats;

            if(vm.folderStats.length === 0) {

                vm.folderStats = [{algorithmName: 'Longest Common Subsequence',count: 0},
                    {algorithmName: 'Longest Common Subsequence With AST',count: 0},
                    {algorithmName: 'Jaccard',count: 0},
                    {algorithmName: 'Edit Distance',count: 0},
                    {algorithmName: 'Weighted Distribution',count: 0},
                    {algorithmName: 'Machine Learning',count: 0}];

            }
        }

    }

})();

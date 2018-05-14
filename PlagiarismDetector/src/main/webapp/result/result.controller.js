(function () {
    'use strict';

    angular
        .module('app')
        .controller('ResultController', ResultController);

    ResultController.$inject = ['UserService', '$location', '$rootScope'];

    function ResultController(UserService, $location, $rootScope) {

        var vm = this;
        vm.user = null;
        vm.comparisionType = "JACCARD";
        vm.folderResults = null;
        vm.showDifference = showDifference;

        initController();

        function initController() {
            loadCurrentUser();
            initResults();
        }

        function loadCurrentUser() {
            UserService.GetByEmail($rootScope.globals.currentUser.email)
                .then(function (user) {
                    vm.user = user;
                });
        }

        function showDifference(report) {
            if (report != null) {
                $rootScope.report = report;
                $location.path('/showdifference');
            }
        }

        function initResults() {
            vm.folderResults = $rootScope.folderResults;
            vm.comparisionType = $rootScope.comparisionType;
        }
    }
})();

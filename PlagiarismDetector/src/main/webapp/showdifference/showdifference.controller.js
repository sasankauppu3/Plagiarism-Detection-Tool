(function () {
    'use strict';

    angular
        .module('app')
        .controller('ShowDifferenceController', ShowDifferenceController);

    ShowDifferenceController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', '$scope', '$sce'];

    function ShowDifferenceController(UserService, $location, $rootScope, FlashService, $scope, $sce) {

        var vm = this;
        vm.user = null;
        vm.report = null;
        vm.comparisionType = "JACCARD";

        initController();

        function initController() {
            loadCurrentUser();
            initReport();
        }

        function loadCurrentUser() {
            UserService.GetByEmail($rootScope.globals.currentUser.email)
                .then(function (user) {
                    vm.user = user;
                });
        }

        function initReport() {
            vm.report = $rootScope.report;
            vm.comparisionType = $rootScope.comparisionType;

        }

        $scope.convert1 = $sce.trustAsHtml(vm.report.file1Content);

        $scope.convert2 = $sce.trustAsHtml(vm.report.file2Content);

    }
})();

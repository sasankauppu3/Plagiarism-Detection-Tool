(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function UserController(UserService, $location, $rootScope, FlashService) {
        var vm = this;
        vm.user = null;
        vm.allUsers = [];

        vm.deleteUser = deleteUser;

        initController();

        function initController() {
            loadCurrentUser();
            loadAllUsers();
        }

        function loadCurrentUser() {
            UserService.GetByEmail($rootScope.globals.currentUser.email)
                .then(function (user) {
                    vm.user = user;
                });
        }

        function loadAllUsers() {
            UserService.GetAll()
                .then(function (users) {
                    vm.allUsers = users;
                });
        }

        function deleteUser(id) {
            if(id === vm.user.id){
                FlashService.Error("Cannot delete yourself!",false);
            } else {
                UserService.Delete(id)
                    .then(function () {
                        loadAllUsers();
                        FlashService.Success("User Successfully deleted",true);
                    });
            }
        }
    }

})();

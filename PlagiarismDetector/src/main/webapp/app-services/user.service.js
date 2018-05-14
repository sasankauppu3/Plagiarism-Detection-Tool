
(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByEmail = GetByEmail;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        service.findUserByCredentials = findUserByCredentials;
        service.pushFilesForUser = pushFilesForUser;
        service.GetAllFoldersById = GetAllFoldersById;
        service.DeleteFolder = DeleteFolder;
        service.CompareFolder = CompareFolder;
        service.StatsFolder = StatsFolder;

        return service;

        function GetAll() {
            return $http.get('getUsers').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByEmail(email) {
            var user = {email:email};
            return $http.post("/getUser", user).then(handleSuccess, handleError('Error login in user'));
        }

        function Create(user) {
            return $http.post("/register", user).then(handleSuccess, handleError('Error registering user, email improper or already registered'));
        }

        function Update(user) {
            return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function findUserByCredentials(user) {
            return $http.post('/login', user).then(handleSuccess, handleError('Error login in user'));
        }

        function Delete(id) {
            return $http.delete('/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        function pushFilesForUser(request) {
            return $http.post('/folderUpload', request, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(handleSuccess, handleError('Error in uploading folder'));
        }

        function GetAllFoldersById(id) {
            return $http.get('/'+id+'/getFolders').then(handleSuccess, handleError('Error in getting folder'));
        }

        function DeleteFolder(id) {
            return $http.delete('/'+id+'/getFolder/').then(handleSuccess, handleError('Error deleting user'));
        }

        function CompareFolder(comparisionType, folderId) {
            return $http.get(+folderId+'/'+comparisionType+'/compare').then(handleSuccess, handleError('Error in comparing folder'));
        }

        function StatsFolder(folderId) {
            return $http.get('/'+folderId+'/statistics').then(handleSuccess, handleError('Error in getting stats' +
                ' folder'));
        }
        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();

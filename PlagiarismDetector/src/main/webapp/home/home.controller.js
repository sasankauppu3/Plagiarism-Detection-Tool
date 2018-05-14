(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['UserService', '$rootScope', 'FlashService', '$location'];
    function HomeController(UserService, $rootScope, FlashService, $location) {
        var vm = this;

        vm.user = null;
        vm.allUsers = [];
        vm.folders = [];
        vm.statsFolder= statsFolder;
        vm.deleteFolder= deleteFolder;
        vm.compareFolder= compareFolder;
        vm.loadUserFolders = loadUserFolders;
        vm.getComparisionSelector = getComparisionSelector;

        initController();

        function initController() {
            loadCurrentUser();
            initFileSelector();
        }

        function loadUserFolders(id) {
            UserService.GetAllFoldersById(id)
                .then(function (folders) {
                    vm.folders = folders;
                });
        }

        function loadCurrentUser() {
            UserService.GetByEmail($rootScope.globals.currentUser.email)
                .then(function (user) {
                    vm.user = user;
                    loadUserFolders(vm.user.id);
                });
        }

        function deleteFolder(id) {
            UserService.DeleteFolder(id)
               .then(function (response) {
                   $("html, body").animate({ scrollTop: 0 }, "slow");
                   if(response.success !== false) {
                       loadUserFolders(vm.user.id);
                       FlashService.Success("Folder successfully deleted", false);
                       document.getElementById("folder-title").innerHTML = "Choose a folder...";
                   } else {
                       FlashService.Error(response.message, false);
                   }
                });
        }

        function statsFolder(id) {
            vm.dataLoading = true;
            UserService.StatsFolder(id)
                .then(function (response) {
                    vm.dataLoading = false;
                    if (response.success !== false) {
                      $rootScope.folderStats = response;
                      $location.path('/stats');
                    } else {
                        $("html, body").animate({ scrollTop: 0 }, "slow");
                        FlashService.Error(response.message, false);
                    }
                });
        }

        function compareFolder(id) {
            vm.dataLoading = true;
            var comparisionType = getComparisionSelector(id);
                UserService.CompareFolder(comparisionType, id)
                    .then(function (response) {
                        vm.dataLoading = false;
                        if (response.success !== false) {
                           $rootScope.comparisionType = comparisionType;
                           $rootScope.folderResults =  response.sort(compare);
                           $location.path('/results');
                        } else {
                            $("html, body").animate({ scrollTop: 0 }, "slow");
                            FlashService.Error(response.message, false);
                        }
                    });
        }

        function compare(a,b) {
            if (a.similarity > b.similarity)
                return -1;
            if (a.similarity < b.similarity)
                return 1;
            return 0;
        }

        function initFileSelector() {
            var inputs = document.querySelectorAll( '.inputfile' );
            Array.prototype.forEach.call( inputs, function( input ) {
                var label	 = input.nextElementSibling,
                    labelVal = label.innerHTML;

                input.addEventListener( 'change', function( e ) {
                    if( checkValidUpload(this.files) ) {
                        var i;
                        var fileCount = this.files.length;
                        var formData = new FormData();
                        var uploadLength = 0;
                        for(i = 0; i< fileCount ; i++){
                            var file = this.files[i];
                            var pathLen = file.webkitRelativePath.split("/").length;
                            var fileName = file.webkitRelativePath.split("/")[pathLen-1].toString();
                            if(fileName.endsWith('.py')){
                                uploadLength++;
                                formData.append('file', file);
                                formData.append('userId', vm.user.id);
                            }
                        }

                        UserService.pushFilesForUser(formData)
                            .then(function (response) {
                                if(response === "OK") {
                                    label.querySelector('span').innerHTML = uploadLength + " files uploaded";
                                    loadUserFolders(vm.user.id);
                                    FlashService.Success("Folder successfully uploaded", false);
                                } else if (response === "No Content") {
                                    FlashService.Error("Cannot upload empty folder", false);
                                    label.innerHTML = labelVal;
                                } else {
                                    FlashService.Error("Improper folder structure", false);
                                    label.innerHTML = labelVal;
                                }
                                $("html, body").animate({ scrollTop: 0 }, "slow");
                            });
                    }
                    else {
                        UserService.GetById("dummy").then(function (value) {
                            $("html, body").animate({ scrollTop: 0 }, "slow");
                            FlashService.Error("Please upload in provided template", false);
                        });
                    }
                });

                // Firefox bug fix
                input.addEventListener( 'focus', function(){ input.classList.add( 'has-focus' ); });
                input.addEventListener( 'blur', function(){ input.classList.remove( 'has-focus' ); });
            });
        }

        function getComparisionSelector(id) {
            var currentAlgorithm = "LCS";
            var allTheSelectors = document.getElementsByClassName('comparisionType');
            Array.prototype.forEach.call( allTheSelectors, function( selector ) {
                if(selector.id.toString() === id.toString()) {
                    currentAlgorithm = (selector.options[selector.selectedIndex].value);
                }
            });
            return currentAlgorithm;
        }

        function checkValidUpload(files) {
            var pathLenCounter = 0;
            var numOfFiles = files.length;
            for(var i = 0; i < numOfFiles; i++){
                var file =  files[i];
                var relPath = file.webkitRelativePath;
                var pathLen = relPath.split("/").length;
                var fileName = relPath.split("/")[pathLen-1].toString();
                if(!fileName.startsWith('.')){
                    if(pathLen < 3)
                        return false;
                }
                pathLenCounter++;
            }
            return !((!(files && numOfFiles > 1)) || pathLenCounter < 2);
        }
    }

})();

var app = angular.module('myApp', []);
app.controller('mainPageController', function($scope, $http, $log) {
    $scope.showConfig = false;
    $scope.showMenu = false;
    $scope.errorMessage = null;
    $scope.myGame = {username: null, password: null};
    $scope.showGameConfig = function() {
        if($scope.showConfig==false) {
            $scope.showConfig = true;
        }else{
            $scope.showConfig = false;
        }
    }
    $scope.showSaveConfig = function() {
        if($scope.showMenu==false) {
            $scope.showMenu = true;
        }else{
            $scope.showMenu = false;
        }
    }
    $scope.createNewGame = function($event, submit) {

        //refactor
        //$http({
        //    method: 'POST',
        //    url: "/index",
        //    data: 'submit='+submit + "&username="+$scope.myGame.username+"&password="+$scope.myGame.password,
        //    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        //}).then(function mySucces(response) {
        //    var data = response.data.errorMessage;
        //    $scope.errorMessage = angular.fromJson(data)[0];
        //}, function myError(response) {
        //    $scope.myButtlefield = response.statusText;
        //});
    }
});


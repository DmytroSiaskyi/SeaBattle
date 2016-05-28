var app = angular.module('myApp', []);
app.controller('battlefieldController', function($scope, $http, $log) {
    $scope.count = 1;
    $http({
        method : "POST",
        url : "/play"
    }).then(function mySucces(response) {
        var data = response.data.battlefield1;
        var data = angular.toJson(data);
        $scope.myBattlefield1 = angular.fromJson(data)[0];
        var data = response.data.battlefield2;
        var data = angular.toJson(data);
        $scope.myBattlefield2 = angular.fromJson(data)[0];
        $scope.myScore = response.data.myScore[0];
        $scope.enemyScore = response.data.enemyScore[0];
    }, function myError(response) {
        $scope.myBattlefield1 = response.statusText;
    });
    $scope.shoot = function(x , y){
        console.log(x , y)
        $http({
            method: 'POST',
            url: "/play",
            data: 'x='+ x +'&y=' + y,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function mySucces(response) {
            var data = response.data.battlefield1;
            var data = angular.toJson(data);
            $scope.myBattlefield1 = angular.fromJson(data)[0];
            var data = response.data.battlefield2;
            var data = angular.toJson(data);
            $scope.myBattlefield2 = angular.fromJson(data)[0];
            $scope.myScore = response.data.myScore[0];
            $scope.enemyScore = response.data.enemyScore[0];
            if($scope.myScore == 6){
                alert('You win!');
            }
        }, function myError(response) {
            $scope.myButtlefield = response.statusText;
        });
    }
});


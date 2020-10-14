var app = angular.module('get',[]);

app.controller('list',function ($scope,$http) {
    $scope.list=[];
    /*$http.get('http://localhost:8080/api/barcodelsit').success(function (data) {
        $scope.list =data;
    });*/

    var deferred, url;
    url = "http://localhost:8080/api/barcodelsit";
    $http.post(url).success(function(data, status, headers, config) {
        return deferred.resolve({
            success: true,
            data: data,
            status: status,
            headers: headers,
            config: config
        });
    });
    console.log(deferred.data);
});

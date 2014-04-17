
var app = angular.module('myApplicationModule', ['google-maps']);


function angularMapCtrl($scope, $http) {


    $http.get('http://54.193.8.183:8080/v1/geo/history/demo?starttime=1387700532&endtime=169770053200000').
        success(function (data) {

            $scope.geodata = data;

            console.log(data);

            for (var i = 0; i < data.history.length; i++){
                var d = data.history[i];

                var marker = {
                    icon: 'http://www.ilinative.org/directory/images/6/6f/Blue_marker.png',
                    latitude: d.position[0],
                    longitude: d.position[1],
                    showWindow: false,
                    title: 'User: ' + d.userName + ', Time stamp: ' + d.timestamp,
                    user: d.userName
                };

                $scope.map.markers.push(marker);

            }

        });


    $scope.map = {
        center: {
            latitude: 37.41025924682617,
            longitude: -122.08200073242188
        },
        zoom: 10,
        showTraffic: true,
        dragging: false,
        markers:[]
    };




}


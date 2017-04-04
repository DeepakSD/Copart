// app.js
var routerApp = angular.module('routerApp', ['ui.router']);

routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider
        // nested views
        .state('home', {
            url: '/home',
            templateUrl: 'home.html'
        })

        // nested list with custom controller
        .state('home.list', {
            url: '/list',
            templateUrl: 'list.html',
            controller: function($scope) {
                $scope.funnycitynames = ['Boring Oregon City', 'Emba rrass Minnesota', 'No Name South Dakota', 'Penistone South Yorkshire England', 'Intercourse Lancaster County, PA'];
            }
        })

        // nested list with just some random string data
        .state('home.anything', {
            url: '/anything',
template: 'What do you want from ui-route'
        })

        // multiviews
        .state('about', {
            url: '/about',
            views: {

                // the main template will be placed here (relatively named)
                '': { templateUrl: 'about.html' },

                //  the child views (absolutely named)

                // for column #1, defines a separate controller
                'column1@about': {
                    templateUrl: 'column1.html',
                    controller: 'column1Controller'
                },

                // the child views (absolutely named)
                'column2@about': { template: 'column #2!' },

                // for bottom row, defines a separate controller shares with column #1
                'bottom-row@about': {
                    templateUrl: 'bottom.html',
controller: 'column1Controller'
                }
            }
        });
});

// column1 controller that we call up in the about state
routerApp.controller('column1Controller', function($scope) {

    $scope.message = 'column1';

    $scope.galaxies = [
        {
            name: 'Milkyway Galaxy',
            distance: '27,000'
        },
        {
            name: 'Andromeda Galaxy',
            distance: '2,560,000'
        },
        {
            name: 'Sagittarius Dwarf',
            distance: '3.400,000'
        }
    ];
});
// let's define the column1 controller that we call up in the about state
routerApp.controller('bottom-rowController', function($scope) {

    $scope.message = 'bottom row';

    $scope.variables = [
    ];
});

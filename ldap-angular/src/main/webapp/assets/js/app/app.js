'use strict';

/* App Module */

var ldapApp = angular.module('ldapApp', [ 'ngRoute', 'ldapControllers', 'ldapFilters', 'ldapServices' ]);

ldapApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/phones', {
		templateUrl : 'partials/phone-list.html',
		controller : 'PhoneListCtrl'
	}).when('/phones/:phoneId', {
		templateUrl : 'partials/phone-detail.html',
		controller : 'PhoneDetailCtrl'
	}).otherwise({
		redirectTo : '/phones'
	});
} ]);
'use strict';

/* Controllers */

var phonecatControllers = angular.module('phonecatControllers', []);

phonecatControllers.controller('PhoneListCtrl', [ '$scope', 'Phone'/*, '$http'*/, function($scope, Phone) {
	//	$http.get('main?command=loadPhones').success(function(data) {
	//		$scope.phones = data;
	//	});
	$scope.phones = Phone.query();
	$scope.orderProp = 'age';
} ]);

phonecatControllers.controller('PhoneDetailCtrl', [ '$scope', '$routeParams', 'Phone'/*, '$http'*/, function($scope, $routeParams, Phone) {
	//	$http.get('main?command=phoneDetail&phoneId=' + $routeParams.phoneId + '.json').success(function(data) {
	//		$scope.phone = data;
	//		$scope.mainImageUrl = data.images[0];
	//	});
	$scope.phone = Phone.get({
		phoneId : $routeParams.phoneId
	}, function(phone) {
		$scope.mainImageUrl = phone.images[0];
	});
	
	$scope.setImage = function(imageUrl) {
		$scope.mainImageUrl = imageUrl;
	};
} ]);

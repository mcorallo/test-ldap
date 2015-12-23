'use strict';

/* Services */

var ldapServices = angular.module('ldapServices', [ 'ngResource' ]);

ldapServices.factory('Phone', [ '$resource', function($resource) {
	return $resource('/ldap-rest/rest/phones/:phoneId.json', {}, {
		query : {
			method : 'GET',
			params : {
				phoneId : 'phones'
			},
			isArray : true
		}
	});
} ]);

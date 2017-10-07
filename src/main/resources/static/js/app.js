//criacao do modulo principal
var appCliente = angular.module("appCliente", [ "ngRoute" ]);

appCliente.config(function($routeProvider, $locationProvider) {
	$routeProvider
	.when("/clientes", {
		templateUrl : 'views/cliente.html',
		controller : 'clienteController'
	}).when("/clientes/:clienteId", {
		templateUrl : 'views/cliente-detalhe.html',
		controller : 'clienteDetalheController'
	}).when("/cidades", {
		templateUrl : 'views/cidade.html',
		controller : 'cidadeController'
	}).when("/estados", {
		templateUrl : 'views/estado.html',
		controller : 'estadoController'
	}).when("/login", {
		templateUrl : 'views/login.html',
		controller : 'loginController'
	}).otherwise({
		rediretTo : '/'
	});

	$locationProvider.hashPrefix('').html5Mode(true);
});

appCliente.config(function($httpProvider){
	$httpProvider.interceptors.push("tokenInterceptor");
	
});

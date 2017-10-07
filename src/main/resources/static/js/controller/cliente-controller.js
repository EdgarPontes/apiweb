appCliente.controller("clienteController", function($scope, $http){
	
	$scope.nome = "jão";
	$scope.sobreNome = "da Silva";
	$scope.clientes = [];
	$scope.cliente = {};
	
	carregarClientes = function(){
		token = localStorage.getItem("userToken");
		//$http.defaults.headers.common.Authorization = 'Bearer '+ token;
		
		$http({method:'GET', url: 'http://localhost:8080/admin/clientes'})
		.then(function(response){
			$scope.clientes = response.data;
		
		}, function(response){
			console.log(response.data);
			console.log(response.status);
			
		});
	};
	
	$scope.salvarClientes = function(){
		$http({method:'PUT', url: 'http://localhost:8080/admin/clientes', data:$scope.cliente })
		.then(function(response){
			//$scope.clientes.push(response.data);
			carregarClientes();
			$scope.cancelarAlterarCliente();
			
		}, function(response){
			console.log(response.data);
			console.log(response.status);
			
		});
	};
	
	$scope.excluirCliente = function(cliente){
		$http({method:'DELETE', url: 'http://localhost:8080/admin/clientes/' + cliente.id })
		.then(function(response){
			
			pos = $scope.clientes.indexOf(cliente);
			$scope.clientes.splice(pos, 1);
			
		}, function(response){
			console.log(response.data);
			console.log(response.status);
			
		});
	};
	
	$scope.alterarCliente = function(cli){
		$scope.cliente = angular.copy(cli);

	};
	
	$scope.cancelarAlterarCliente = function(){
		$scope.cliente = {};
		
	};
	
	carregarClientes();
});
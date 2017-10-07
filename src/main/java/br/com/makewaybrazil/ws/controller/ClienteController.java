package br.com.makewaybrazil.ws.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.makewaybrazil.ws.model.Cliente;
import br.com.makewaybrazil.ws.service.ClienteService;

@RestController
@RequestMapping("/admin")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(method=RequestMethod.POST, value="/clientes", 
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
		Cliente clienteSalvo = clienteService.cadastrar(cliente);
		
		return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/clientes", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarCliente(){
		
		Collection<Cliente> clienteSalvo = clienteService.buscarTodos();
		
		return new ResponseEntity<>(clienteSalvo, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/clientes/{id}", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Integer id){
		
		Cliente cliente = clienteService.buscaPorId(id);
		
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/clientes/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id){
		
		Cliente clienteEncontrado = clienteService.buscaPorId(id);
		clienteService.excluir(clienteEncontrado);
		
		if (clienteEncontrado==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/clientes", 
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente){
		
		Cliente clienteAlterado = clienteService.alterar(cliente);
		
		return new ResponseEntity<>(clienteAlterado, HttpStatus.OK);
	}
}
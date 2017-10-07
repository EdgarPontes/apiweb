package br.com.makewaybrazil.ws.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.makewaybrazil.ws.model.Usuario;
import br.com.makewaybrazil.ws.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/autenticar", 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException {
		
		if(usuario.getNome() == null || usuario.getSenha() == null){
			throw new ServletException("Nome e senha obrigatório. ");
		}
		
		Usuario usuarioAutenticado = usuarioService.buscarPorNome(usuario.getNome());
		
		if (usuarioAutenticado == null) {
			throw new ServletException("Usuario não encontrado. ");
		}
		
		if (!usuarioAutenticado.getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Usuario ou senha inválido. ");
		}
		String token = Jwts.builder()
				.setSubject(usuario.getNome())
				.signWith(SignatureAlgorithm.HS512, "banana")
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.compact();
		
		return new LoginResponse(token);
	}
	
	private class LoginResponse{
		public String token;
		
		public LoginResponse(String token){
			this.token = token;
		}
		
	}
}

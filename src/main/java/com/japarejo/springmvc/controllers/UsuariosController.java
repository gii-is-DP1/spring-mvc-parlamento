package com.japarejo.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.japarejo.springmvc.model.entities.Usuario;
import com.japarejo.springmvc.model.repositories.UsuarioRepository;




@RestController
public class UsuariosController {

	@Autowired
	private UsuarioRepository usuariosRepository;	
	
	@GetMapping("/usuarios")
	public Iterable<Usuario> usuarios(){
		return usuariosRepository.findAll();
	}
	
	@PostMapping("/usuarios")
	 public Usuario crearUsuario(@RequestBody Usuario usuario) {
	    return usuariosRepository.save(usuario);
	  }
}

package com.japarejo.springmvc.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.japarejo.springmvc.model.entities.Usuario;
import com.japarejo.springmvc.model.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuariosRepository;	
	
	@GetMapping()
	public Iterable<Usuario> usuarios(){
		return usuariosRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Usuario usuario(@PathVariable("id") Long id) {
		return usuariosRepository.findById(id).get();
	}
	
	@PostMapping
	 public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) 
	{
	    usuario=usuariosRepository.save(usuario);
	    URI uri=null;
	    try {
			uri=new URI("/empleados/"+usuario.getId());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	    return ResponseEntity.created(uri).body(usuario);	    
	  }
	
	@PutMapping	
	public ResponseEntity modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
		return null;
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity borrarUsuario(@PathVariable("id") Long id) {
		return null;
	}
		
	
}

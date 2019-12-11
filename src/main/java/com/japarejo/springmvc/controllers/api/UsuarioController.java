package com.japarejo.springmvc.controllers.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.japarejo.springmvc.model.entities.Usuario;
import com.japarejo.springmvc.model.repositories.UsuarioRepository;

import antlr.collections.List;

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
	
	@PutMapping(path = "/{id}")	
	public ResponseEntity modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
		if(id==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You must proide a valid usuario identifier");
		Optional<Usuario> usuarioPrevio=usuariosRepository.findById(id);
		if(usuarioPrevio.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is not any user with id="+id);
		usuariosRepository.save(usuario);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity borrarUsuario(@PathVariable("id") Long id) {
		if(id==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You must proide a valid usuario identifier");
		Optional<Usuario> usuarioPrevio=usuariosRepository.findById(id);
		if(usuarioPrevio.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is not any user with id="+id);
		usuariosRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
		
	
}

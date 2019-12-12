package com.japarejo.springmvc.controllers.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.japarejo.springmvc.model.entities.Sala;
import com.japarejo.springmvc.model.entities.Usuario;
import com.japarejo.springmvc.model.repositories.SalaRepository;
import com.japarejo.springmvc.model.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/salas")
public class SalaController {
	@Autowired
	private SalaRepository salaRepository;	
	
	@GetMapping()
	public Iterable<Sala> salas(){
		return salaRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Sala sala(@PathVariable("id") Long id) {
		return salaRepository.findById(id).get();
	}
	
	@PostMapping
	 public ResponseEntity<Sala> crearSala(@RequestBody @Valid Sala sala) 
	{
		if(salaRepository.findByDescripcion(sala.getDescripcion())!=null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"There is a sala with description="+sala.getDescripcion());
	    sala=salaRepository.save(sala);
	    URI uri=null;
	    try {
			uri=new URI("/api/sala/"+sala.getId());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	    return ResponseEntity.created(uri).body(sala);	    
	  }
	
	@PutMapping(path = "/{id}")	
	public ResponseEntity modificarSala(@PathVariable("id") Long id, @RequestBody Sala sala) {
		if(id==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You must proide a valid identifier");
		Optional<Sala> salaPrevia=salaRepository.findById(id);
		if(salaPrevia.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is not any user with id="+id);
		salaRepository.save(sala);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity borrarSala(@PathVariable("id") Long id) {
		if(id==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You must proide a valid usuario identifier");
		Optional<Sala> salaPrevia=salaRepository.findById(id);
		if(salaPrevia.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is not any user with id="+id);
		salaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

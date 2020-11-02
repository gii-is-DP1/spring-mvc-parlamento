package com.japarejo.springmvc.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.japarejo.springmvc.model.entities.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Usuario findByLogin(String username);
}

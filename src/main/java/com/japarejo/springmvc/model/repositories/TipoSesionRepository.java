package com.japarejo.springmvc.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.japarejo.springmvc.model.entities.TipoSesion;

@Repository
public interface TipoSesionRepository extends CrudRepository<TipoSesion, Long>{
	
}

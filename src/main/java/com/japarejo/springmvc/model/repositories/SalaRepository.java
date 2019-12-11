package com.japarejo.springmvc.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.japarejo.springmvc.model.entities.Sala;

@Repository
public interface SalaRepository extends CrudRepository<Sala, Long> {

	public Sala findByDescripcion(String descripcion);

}

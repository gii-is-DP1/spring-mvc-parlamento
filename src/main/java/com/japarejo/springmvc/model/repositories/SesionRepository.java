package com.japarejo.springmvc.model.repositories;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.japarejo.springmvc.model.entities.Organo;
import com.japarejo.springmvc.model.entities.Sala;
import com.japarejo.springmvc.model.entities.Sesion;
import com.japarejo.springmvc.model.entities.TipoSesion;

@Repository
public interface SesionRepository extends CrudRepository<Sesion,Long> {
		
}

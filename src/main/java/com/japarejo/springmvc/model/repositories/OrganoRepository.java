package com.japarejo.springmvc.model.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.japarejo.springmvc.model.entities.Organo;

@Repository
@Cacheable
public interface OrganoRepository extends CrudRepository<Organo, Long> {

}

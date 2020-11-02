package com.japarejo.springmvc.controllers.api;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.japarejo.springmvc.model.entities.Organo;
import com.japarejo.springmvc.model.entities.OrganoProjection;
import com.japarejo.springmvc.model.services.OrganoService;

@RestController
@RequestMapping("/api/organos")
public class OrganoAPIController {
	
	@Autowired
	private OrganoService organoService;
		
	ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();;

	@GetMapping
	public CollectionModel<OrganoProjection> getOrganos(){		
		Iterable<Organo> organos=organoService.findAll();
		Collection<OrganoProjection> organitos=		
		    StreamSupport.stream(organos.spliterator(),false)
		    .map(
				(organo) -> projectionFactory.createProjection(OrganoProjection.class,organo)
				).collect(Collectors.toList());
		return new CollectionModel<>(organitos);
	}

}

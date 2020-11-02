package com.japarejo.springmvc.model.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="OrganoProjection",types= {Organo.class})
public interface OrganoProjection {
	Long getId();
	String getAbreaviatura();
	String getDescripcion();
}


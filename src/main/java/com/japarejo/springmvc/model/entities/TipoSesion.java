package com.japarejo.springmvc.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.AccessType.FIELD;
import static javax.persistence.AccessType.PROPERTY;



/**
 * The persistent class for the TIPO_SESION database table.
 * 
 */
@Entity
@Table(name="TIPOSESION")
public class TipoSesion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=10)
	private String activo;

	@Column(nullable=false, length=100)
	private String descripcion;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable=false, precision=10)
	private long id;

	public TipoSesion() {
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
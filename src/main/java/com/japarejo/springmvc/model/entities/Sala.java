package com.japarejo.springmvc.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SALA database table.
 * 
 */
@Entity
@Table(name="SALA")
@NamedQuery(name="Sala.findAll", query="SELECT s FROM Sala s")
public class Sala implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, precision=10)
	private long id;

	@Column(nullable=false, length=10)
	private String activo;

	@Column(nullable=false, length=100)
	private String descripcion;

	public Sala() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

}
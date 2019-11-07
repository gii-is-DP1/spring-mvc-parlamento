package com.japarejo.springmvc.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the ORGANO database table.
 * 
 */
@Entity
@Table(name="ORGANO")
public class Organo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, precision=10)
	private long id;

	@Column(name="abreviatura",nullable=false, length=100)
	private String abreaviatura;

	@Column(nullable=false, length=100)
	private String descripcion;

	@Column(precision=10)
	private Long orden;

	@ManyToMany(targetEntity=Parlamentario.class,fetch=FetchType.EAGER,mappedBy = "organos")
	private List<Parlamentario> miembros;
	
	public Organo() {
		miembros=new ArrayList<Parlamentario>();
	}

	public List<Parlamentario> getMiembros() {
		return miembros;
	}

	public void setMiembros(List<Parlamentario> miembros) {
		this.miembros = miembros;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAbreaviatura() {
		return this.abreaviatura;
	}

	public void setAbreaviatura(String abreaviatura) {
		this.abreaviatura = abreaviatura;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getOrden() {
		return this.orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

}

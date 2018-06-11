package com.japarejo.springmvc.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the SESION database table.
 * 
 */
@Entity
@Table(name="SESION")
public class Sesion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, precision=10)
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name="fk_organo")
	private Organo organo;

	@ManyToOne(optional=false)
	@JoinColumn(name="fk_sala")
	private Sala sala;
	

	@ManyToOne(optional=false)
	@JoinColumn(name="fk_tipo_sesion")
	private TipoSesion tipoSesion;

	@Column(name="hora_fin")
	private Timestamp horaFin;

	@Column(name="hora_inicio", nullable=false)
	private Timestamp horaInicio;

	@Column(nullable=false, precision=10)
	private Long legislatura;

	@Column(nullable=false, precision=10)
	private Long numero;

	public Sesion() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	public Timestamp getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Timestamp horaFin) {
		this.horaFin = horaFin;
	}

	public Timestamp getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Long getLegislatura() {
		return this.legislatura;
	}

	public Organo getOrgano() {
		return organo;
	}

	public void setOrgano(Organo organo) {
		this.organo = organo;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public TipoSesion getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(TipoSesion tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	public void setLegislatura(Long legislatura) {
		this.legislatura = legislatura;
	}

	public Long getNumero() {
		return this.numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

}
package com.japarejo.springmvc.security.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, precision=10)
	private long id;

	@Column(nullable=false, length=100)
	private String login;

	@Column(nullable=false, length=100)
	private String password;

	@Column(nullable=false, length=100)
	private String permiso;

	public Usuario() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermiso() {
		return this.permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}

}
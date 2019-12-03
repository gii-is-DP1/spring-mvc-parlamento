package com.japarejo.springmvc.async;

import java.util.Date;

public class Comentario {
	Date fechaComentario;
	MensajeAsincrono mensaje;
	
	public Comentario(MensajeAsincrono mensaje) {
		this(new Date(),mensaje);
	}
	
	public Comentario(Date fechaComentario, MensajeAsincrono mensaje) {
		super();
		this.fechaComentario = fechaComentario;
		this.mensaje = mensaje;
	}
	
	public Date getFechaComentario() {
		return fechaComentario;
	}
	public void setFechaComentario(Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}
	public MensajeAsincrono getMensaje() {
		return mensaje;
	}
	public void setMensaje(MensajeAsincrono mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}

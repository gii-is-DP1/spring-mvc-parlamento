package com.japarejo.springmvc.async;

public class MensajeAsincrono {
	private String remitente;
	private String titulo;
	private String cuerpo;
	
	
	
	public MensajeAsincrono(String remitente, String titulo, String cuerpo) {
		super();
		this.remitente = remitente;
		this.titulo = titulo;
		this.cuerpo = cuerpo;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
}

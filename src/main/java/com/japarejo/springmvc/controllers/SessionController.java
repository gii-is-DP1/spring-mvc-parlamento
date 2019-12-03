package com.japarejo.springmvc.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import com.japarejo.springmvc.async.Comentario;
import com.japarejo.springmvc.async.MensajeAsincrono;

public class SessionController {

  @MessageMapping("/grupos")
  @SendTo("/topic/comentarios")	
  public Comentario MandarComentarios(MensajeAsincrono message) throws Exception {    
    return new Comentario(message);
  }

}


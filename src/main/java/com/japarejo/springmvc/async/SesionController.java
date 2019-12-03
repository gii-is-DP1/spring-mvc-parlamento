package com.japarejo.springmvc.async;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class SesionController {


	@MessageMapping("/comentarios")
	@SendTo("/topic/sesiones")
	public Comentario MandarComentarios(MensajeAsincrono message) throws Exception {
	    Thread.sleep(1000); // simulated delay
	    return new Comentario(message);
	  }

}

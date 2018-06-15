package com.japarejo.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.japarejo.springmvc.model.entities.Parlamentario;
import com.japarejo.springmvc.model.services.*;

@Controller
@RequestMapping("/parlamentarios")
public class ParlamentarioController<ParalamentarioSevice> {
	
	@Autowired
	ParlamentarioService parlamentarioService;
	
	@GetMapping
	public ModelAndView listadoParlamentarios(){
		Iterable<Parlamentario> parlamentarios=parlamentarioService.findAll();
		ModelAndView result=new ModelAndView("ListadoParalamentarios");
		result.addObject("parlamentarios", parlamentarios);
		return result;
		
	}
}

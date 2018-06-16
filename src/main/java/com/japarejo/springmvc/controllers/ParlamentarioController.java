package com.japarejo.springmvc.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.japarejo.springmvc.model.entities.Organo;
import com.japarejo.springmvc.model.entities.Parlamentario;
import com.japarejo.springmvc.model.services.*;

@Controller
@RequestMapping("/parlamentarios")
public class ParlamentarioController<ParalamentarioSevice> {
	
	@Autowired
	ParlamentarioService parlamentarioService;
	
	@Autowired
	OrganoService organoService;
	
	@GetMapping
	public ModelAndView listadoParlamentarios(){
		Iterable<Parlamentario> parlamentarios=parlamentarioService.findAll();
		ModelAndView result=new ModelAndView("ListadoParlamentarios");
		result.addObject("parlamentarios", parlamentarios);
		return result;
		
	}
	
	@GetMapping(path="/create")
	public ModelAndView crearParlamentario(){		
		ModelAndView result=new ModelAndView("EditarParlamentario");	
		result.addObject("parlamentario", new Organo());
		result.addObject("organos", organoService.findAll());
		return result;
	}
	
	@GetMapping(path="/edit/{id}")
	public ModelAndView editarParlamentario(@PathVariable("id") long id){		
		ModelAndView result=new ModelAndView("EditarParlamentario");	
		result.addObject("parlamentario", parlamentarioService.findById(id));
		result.addObject("organos", organoService.findAll());
		return result;
	}
	
	@PostMapping(path="/save")
	public ModelAndView grabarParlamentario(@ModelAttribute("parlamentario")  Parlamentario parlamentario) {
		parlamentarioService.save(parlamentario);
		ModelAndView result=listadoParlamentarios();	
		result.addObject("mensaje", "Parlamentario grabado con éxito");
		result.addObject("tipomensaje", "sucess");
		return result;
	}
	
	@GetMapping(path="/delete/{id}")
	public ModelAndView borrarParlamentario(@PathVariable("id") long id){
		parlamentarioService.deleteById(id);
		ModelAndView result=listadoParlamentarios();	
		result.addObject("mensaje", "Parlamentario borrado con éxito");
		result.addObject("tipomensaje", "sucess");
		return result;
	}
}

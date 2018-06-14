package com.japarejo.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.japarejo.springmvc.model.entities.Organo;
import com.japarejo.springmvc.model.services.OrganoService;

@Controller
@RequestMapping("/organos")
public class OrganoController {
	
	@Autowired
	private OrganoService organosService;
	
	@GetMapping
	public ModelAndView listadoOrganos(){		
		ModelAndView result=new ModelAndView("ListadoOrganos");
		try {
			organosService.initializeOrganos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.addObject("organos", organosService.findAll());
		return result;
	}
	
	@GetMapping(path="/create")
	public ModelAndView crearOrgano(){		
		ModelAndView result=new ModelAndView("EditarOrgano");	
		result.addObject("organo", new Organo());		
		return result;
	}
	
	@GetMapping(path="/edit/{id}")
	public ModelAndView editarOrgano(@PathVariable("id") long id){		
		ModelAndView result=new ModelAndView("EditarOrgano");	
		result.addObject("organo", organosService.findById(id));		
		return result;
	}
	
	
	@PostMapping(path="/save")
	public ModelAndView salvarOrgano(   @ModelAttribute("organo")  Organo organo){		
		organosService.save(organo);
		ModelAndView result=listadoOrganos();		
		result.addObject("msg", "Organo salvado con éxito!");		
		return result;
	}
	
	@GetMapping(path="/delete/{id}")
	public ModelAndView delteOrgano(@PathVariable("id") long id){		
		organosService.delete(id);
		ModelAndView result=listadoOrganos();		
		result.addObject("msg", "Organo borrado con éxito!");		
		return result;
	}
	

}

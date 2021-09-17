package com.japarejo.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.japarejo.springmvc.model.entities.Organo;
import com.japarejo.springmvc.model.services.AlternanciaService;
import com.japarejo.springmvc.model.services.AlternanciaService.CasoplonException;
import com.japarejo.springmvc.model.services.AlternanciaService.CutreMasterException;
import com.japarejo.springmvc.model.services.OrganoService;

@Controller
public class AlternanciaController {
	@Autowired
	OrganoService orgServ;
	@Autowired
	AlternanciaService altServ;
	
	@GetMapping("/alternancia")
	public @ResponseBody String alternancia() {
		String result="Ok!";
		try {
			altServ.alternancia();
		} catch (CutreMasterException | CasoplonException e) {
			// TODO Auto-generated catch block
			result=e.toString();
			
		}
		
		return result;
	}
	
	@GetMapping("/gobierno")
	public @ResponseBody String gobierno() {
		Organo gobierno=orgServ.findByAbreviatura("GOBIERNO");
		return gobierno==null?"":gobierno.getMiembros().toString();
	}
	
}

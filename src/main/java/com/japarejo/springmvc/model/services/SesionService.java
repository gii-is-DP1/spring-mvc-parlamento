package com.japarejo.springmvc.model.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japarejo.springmvc.model.entities.Organo;
import com.japarejo.springmvc.model.entities.Sala;
import com.japarejo.springmvc.model.entities.Sesion;
import com.japarejo.springmvc.model.entities.TipoSesion;
import com.japarejo.springmvc.model.repositories.SesionRepository;

@Service
public class SesionService {
	@Autowired
	private SesionRepository sesionRepo;
	
	@Autowired
	private OrganoService organoService;
	
	@Autowired
	private TipoSesionService tipoSesionService;

	@Autowired
	private SalaService salaService;
	
	public void borrarSesion(BufferedReader in) throws NumberFormatException, IOException {
		Long id=Long.parseLong(in.readLine());
		sesionRepo.deleteById(id);		
	}


	public void printSesiones() {		
		printSesiones(sesionRepo.findAll());
	}
	
	
	public void printSesiones(Iterable<Sesion> sesiones) {
		System.out.println("Sesiones:");
		for (Sesion s : sesiones) {
			System.out.print(s.getId() + " - " +s.getFecha()+ " ("+s.getNumero()+" de la "+s.getLegislatura()+" legislatura)");
			System.out.println(" desde las "+s.getHoraInicio() + " a las "+s.getHoraFin()+" en la sala "+s.getSala().getDescripcion() );
			System.out.println("    - "+s.getOrgano().getDescripcion() +" tipo de sesión: "+	s.getTipoSesion().getDescripcion());

		}
	}

	public void insertarSesion(BufferedReader in) throws IOException {
		Sesion s=new Sesion();
		leerSesion(s,in);
		sesionRepo.save(s);
	}

	public void printSesiones(BufferedReader in) throws IOException {
		printSesiones();
		System.out.println("Indique el id de la sesión que quiere modificar o [ENTER] var volver:");
		String sid=in.readLine();
		if(!"".equals(sid)) {
			Long id=Long.parseLong(sid);
			if(id!=null)
				modificarSesion(id,in);
		}				
		
	}

	public void modificarSesion(Long id, BufferedReader in) throws IOException {		
		Optional<Sesion> sesionOpt=sesionRepo.findById(id);
		if(sesionOpt.isPresent()) {
			Sesion sesion=sesionOpt.get();
			leerSesion(sesion,in);
			sesionRepo.save(sesion);
			System.out.println("Sesion actualizado con éxito!");
			organoService.printOrganos();
		}else
			System.out.println("Sesion con Id '"+id+" no encontrada.");
	}

	
	
	public void leerSesion(Sesion s, BufferedReader in) throws IOException {
		System.out.print("Indique la fecha de la sesión ([ENTER] para la fecha de hoy):");
		if(s.getFecha()!=null)
			System.out.println("    -Valor actual: "+s.getFecha());		
		String sfecha=in.readLine();
		Date date=null;
		if("".equals(sfecha)) {
			date=new Date(System.currentTimeMillis());
		} else {
			long ldate=Date.parse(sfecha);
			date=new Date(ldate);
		}
		s.setFecha(date);
		
		System.out.println("Indique el número de la sesión:");
		if(s.getNumero()!=null)
			System.out.println("   - Valor actual: "+s.getNumero() );
		String snumero=in.readLine();
		Long numero=Long.parseLong(snumero);
		s.setNumero(numero);
		
		System.out.println("Indique la legislatura de la sesión:");
		if(s.getLegislatura()!=null)
			System.out.println("   - Valor actual: "+s.getLegislatura());
		String slegislatura=in.readLine();
		Long legislatura=Long.parseLong(slegislatura);
		s.setLegislatura(legislatura);
		
		System.out.println("Indique la hora de inicio de la sesión (formato yyyy-mm-dd hh:mm:ss):");
		if(s.getHoraInicio()!=null)
			System.out.println("    - Valor actual: "+s.getHoraInicio());
		String shoraInicio=in.readLine();
		Timestamp horaInicio=Timestamp.valueOf(shoraInicio);
		s.setHoraInicio(horaInicio);
		
		System.out.println("Indique la hora de fin de la sesión (formato yyyy-mm-dd hh:mm:ss):");
		if(s.getHoraFin()!=null)
			System.out.println("    - Valor actual: "+s.getHoraFin());
		String shoraFin=in.readLine();
		Timestamp horaFin=Timestamp.valueOf(shoraFin);
		s.setHoraFin(horaFin);

		System.out.println("Indique el Id de la sala donde se realiza la  sesión:");
		if(s.getSala()!=null)
			System.out.println("    - Valor actual: "+s.getSala().getId()+" ("+s.getSala().getDescripcion()+")");
		salaService.printSalas();
		String salaId=in.readLine();
		Long idSala=Long.parseLong(salaId);
		Optional<Sala> sala=salaService.findById(idSala);
		if(sala.isPresent())
			s.setSala(sala.get());
		
		System.out.println("Indique el Id del órgano que realiza la  sesión:");
		if(s.getOrgano()!=null)
			System.out.println("    - Valor actual: "+s.getOrgano().getId()+" ("+s.getOrgano().getDescripcion()+")");
		organoService.printOrganos();
		String organoId=in.readLine();
		Long idOrgano=Long.parseLong(organoId);
		Optional<Organo> organo=organoService.findById(idOrgano);
		if(organo.isPresent())
			s.setOrgano(organo.get());

		
		System.out.println("Indique el Id del tipo de sesion de la  sesión:");
		if(s.getTipoSesion()!=null)
			System.out.println("    - Valor actual: "+s.getTipoSesion().getId()+" ("+s.getTipoSesion().getDescripcion()+")");
		tipoSesionService.printTiposSesion();
		String tiposesionId=in.readLine();
		Long idTipoSesion=Long.parseLong(tiposesionId);
		Optional<TipoSesion> ts=tipoSesionService.findById(idTipoSesion);
		if(ts.isPresent())
			s.setTipoSesion(ts.get());
		
	}

}

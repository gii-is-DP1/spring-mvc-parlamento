package com.japarejo.springmvc.model.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japarejo.springmvc.model.entities.Organo;
import com.japarejo.springmvc.model.entities.Parlamentario;
import com.japarejo.springmvc.model.repositories.OrganoRepository;

@Service
public class OrganoService {

	@Autowired
	private OrganoRepository organoRepo;
	
	public void insertarOrgano(BufferedReader in) throws IOException {		
		Organo newOrgano=new Organo();
		leerOrgano(newOrgano, in);
		organoRepo.save(newOrgano);		
	}

	public void printOrganos(BufferedReader in) throws IOException{
		printOrganos();
		System.out.println("Indique el id del organo a modificar o ENTER para volver.");
		String sid;		
			sid = in.readLine();
			if(!"".equals(sid)) {
				Long id=Long.parseLong(sid);
				if(id!=null)
					modificarOrgano(id,in);
			}							
		
	}

	public void modificarOrgano(Long id, BufferedReader in) throws IOException {
		Optional<Organo> organoOpt=organoRepo.findById(id);
		if(organoOpt.isPresent()) {
			Organo organo=organoOpt.get();
			leerOrgano(organo,in);
			organoRepo.save(organo);
			System.out.println("Organo actualizado con éxito!");
			printOrganos();
		}else		
			System.out.println("Organo con Id '"+id+" no encontrado.");
		
	}
	
	public void leerOrgano(Organo organo, BufferedReader in) throws IOException {
		System.out.println("Indique la nueva descripción:");
		if(organo.getDescripcion()!=null)
			System.out.print(organo.getDescripcion());
		String descripcion=in.readLine();
		organo.setDescripcion(descripcion);
		System.out.println("Indique la nueva abreviatura");
		if(organo.getAbreaviatura()!=null)
			System.out.print(organo.getAbreaviatura());
		String abreviatura=in.readLine();
		organo.setAbreaviatura(abreviatura);
		System.out.println("Indique el nuevo orden:");
		if(organo.getOrden()!=null)
			System.out.print(organo.getOrden());
		String orden=in.readLine();
		organo.setOrden(Long.parseLong(orden));
		
	}

	public void borrarOrgano(BufferedReader in) {
		Long id;
		try {
			id = Long.parseLong(in.readLine());
			organoRepo.deleteById(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

	

	public void initializeOrganos() throws Exception {
		Iterable<Organo> organoIter = organoRepo.findAll();
		Iterator<Organo> iterador = organoIter.iterator();
		if (!iterador.hasNext()) {
			// No existen organos, vamos a crearlos:
			System.out.println("No hay organos!, vamos a incializarlos...");
			String[][] organos = { 
					{ "MESA", "Mesa del Parlamento", "1" }, { "JP", "Junta de Portavoces", "2" },
					{ "PPA", "Pleno del Parlamento", "3" }, { "DIPPER", "Diputación Permamente", "4" },
					{ "GPS", "G.P. Socialista", "1" },
					{ "IULV-CA", "G.P. Izquierda Unida Los Verdes-Convocatoria por Andalucía", "5" },
					{ "GPP", "G.P. Popular Andaluz", "2" }, { "GPPD", "G.P. Podemos Andalucía", "3" },
					{ "GPC", "G.P. Ciudadanos", "4" } };
			Organo organo = null;
			for (int i = 0; i < organos.length; i++) {
				organo = new Organo();
				organo.setAbreaviatura(organos[i][0]);
				organo.setDescripcion(organos[i][1]);
				organo.setOrden(Long.valueOf(organos[i][2]));
				organoRepo.save(organo);
			}
			System.out.println("Done!");
			
		}
	}

	public void printOrganos() {
		printOrganos(organoRepo.findAll());
	}

	public void printOrganos(Iterable<Organo> organoIter) {
		System.out.println("Organos en la BD:");
		for (Organo ts : organoIter) {
			System.out.println(ts.getId() + " - " + ts.getDescripcion() + ", orden:" + ts.getOrden());
			if(!ts.getMiembros().isEmpty()) {
				System.out.println("  \\-> Miembros: ");
				for(Parlamentario miembro:ts.getMiembros()) {
					System.out.println("    - "+miembro.getNombre());
				}
			}
		}
	}

	public Optional<Organo> findById(Long idOrgano) {
		return organoRepo.findById(idOrgano);
	}

	public Iterable<Organo> findAll() {
		return organoRepo.findAll();
	}

	public void save(Organo organo) {		
		organoRepo.save(organo);
	}
	
	public void delete(long id){
		organoRepo.deleteById(id);
	}

}

package com.japarejo.springmvc.model.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japarejo.springmvc.model.entities.TipoSesion;
import com.japarejo.springmvc.model.repositories.TipoSesionRepository;

@Service
public class TipoSesionService {

	@Autowired
	private TipoSesionRepository tipoSesionRepo;
	
	public void borrarTipoSesion(BufferedReader in) throws NumberFormatException, IOException {
		Long id=Long.parseLong(in.readLine());
		tipoSesionRepo.deleteById(id);				
	}

	public void insertarTipoSesion(BufferedReader in) throws IOException {
		TipoSesion tipoSesion=new TipoSesion();
		leerTipoSesion(tipoSesion, in);
		tipoSesionRepo.save(tipoSesion);		
	}

	public void printTipoSesion(BufferedReader in) throws IOException {
		printTiposSesion();
		System.out.println("Indique el id del tipo de sesion a modificar o ENTER para volver.");
		String sid;		
			sid = in.readLine();
			if(!"".equals(sid)) {
				Long id=Long.parseLong(sid);
				if(id!=null)
					modificarTipoSesion(id,in);
			}									
	}

	public void modificarTipoSesion(Long id, BufferedReader in) throws IOException {
		Optional<TipoSesion> tipoSesionOpt=tipoSesionRepo.findById(id);
		if(tipoSesionOpt.isPresent()) {
			TipoSesion tipoSesion=tipoSesionOpt.get();
			leerTipoSesion(tipoSesion,in);
			tipoSesionRepo.save(tipoSesion);
		}else
			System.out.println("Unable to load tipo de sesion con id "+id);
	}

	public void leerTipoSesion(TipoSesion tipoSesion2, BufferedReader in) throws IOException {
		System.out.println("Proporcione la descripción del tipo de sesión");
		if(tipoSesion2.getDescripcion()!=null)
			System.out.print(tipoSesion2.getDescripcion());
		tipoSesion2.setDescripcion(in.readLine());

		System.out.println("Indique el si el tipo de sesión está activo (1) o no (0):");
		if(tipoSesion2.getActivo()!=null)
			System.out.print(tipoSesion2.getActivo());
		String orden=in.readLine();
		tipoSesion2.setActivo(orden);
		
	}
	
	
	public void initializeTipoSesion() throws Exception {
		Iterable<TipoSesion> tiposDeSesion = tipoSesionRepo.findAll();
		Iterator<TipoSesion> iterador = tiposDeSesion.iterator();
		if (!iterador.hasNext()) {
			// No existen tipos de sesión, vamos a crearlos:
			System.out.println("No hay tipos de sesión!, vamos a incializarlos...");
			String[] tipos = { "Legislativa", "Informativa, de control y de adopción de acuerdos", "Mesa de Comisión",
					"Ponencia Legislativa", "Institucional", "Visita" };
			TipoSesion ts = null;
			for (String tipo : tipos) {
				ts = new TipoSesion();
				ts.setDescripcion(tipo);
				ts.setActivo("1");
				tipoSesionRepo.save(ts);
			}
			System.out.println("Done!");

		}
	}

	public void printTiposSesion() {
		printTiposSesion(tipoSesionRepo.findAll());
	}

	public void printTiposSesion(Iterable<TipoSesion> tiposDeSesion) {
		System.out.println("Tipos de sesión en la BD:");
		for (TipoSesion ts : tiposDeSesion) {
			System.out.println(ts.getId() + " - " + ts.getDescripcion() + ", activo:" + ts.getActivo());
		}
	}

	public Optional<TipoSesion> findById(Long idTipoSesion) {
		return tipoSesionRepo.findById(idTipoSesion);
	}
}

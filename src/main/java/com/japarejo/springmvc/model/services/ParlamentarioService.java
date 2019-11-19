package com.japarejo.springmvc.model.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japarejo.springmvc.model.entities.Organo;
import com.japarejo.springmvc.model.entities.Parlamentario;
import com.japarejo.springmvc.model.repositories.ParlamentarioRepository;

@Service
public class ParlamentarioService {
	@Autowired
	private ParlamentarioRepository parlamentarioRepo;
	
	@Autowired
	private OrganoService organoService;
	
	public void insertarParlamentario(BufferedReader in) throws IOException {
		Parlamentario parlamentario=new Parlamentario();
		leerParlamentario(parlamentario, in);
		parlamentarioRepo.save(parlamentario);
		
	}

	public void borrarInsertarParlamentario(BufferedReader in) throws NumberFormatException, IOException {		
		Long id=Long.parseLong(in.readLine());
		parlamentarioRepo.deleteById(id);
		
	}

	public void printParlamentarios() {
		printParlamentarios(parlamentarioRepo.findAll());
		
	}

	public void printParlamentarios(Iterable<Parlamentario> findAll) {
		for(Parlamentario parlamentario:findAll) {
			System.out.print(parlamentario.getNombre());
			System.out.print("   -> Organos: ");
			for(Organo organo:parlamentario.getOrganos())
				System.out.println(organo.getId()+" ("+organo.getDescripcion()+ "),");
		}
		
	}

	public void printParlamentario(BufferedReader in) throws IOException {
		printParlamentarios();
		System.out.println("Indique el id del parlamentario a modificar o ENTER para volver.");		
		String sid;		
		sid = in.readLine();
		if(!"".equals(sid)) {
			Long id=Long.parseLong(sid);
			if(id!=null)
				modificarParlamentario(id,in);
		}							

		
	}

	private void modificarParlamentario(Long id, BufferedReader in) throws IOException {
		Optional<Parlamentario> parlamentarioOpt=parlamentarioRepo.findById(id);
		if(parlamentarioOpt.isPresent()) {
			Parlamentario parlamentario=parlamentarioOpt.get();
			leerParlamentario(parlamentario,in);
			parlamentarioRepo.save(parlamentario);
		}else
			System.out.println("Unable to load tipo de sesion con id "+id);				
	}

	private void leerParlamentario(Parlamentario parlamentario, BufferedReader in) throws IOException {
		System.out.println("Indique el nuevo nombre:");
		if(parlamentario.getNombre()!=null)
			System.out.println("(valor actual "+parlamentario.getNombre()+")");
		String nombre=in.readLine();
		parlamentario.setNombre(nombre);
		System.out.println("Indique los órganos a los que pertenece el parlamentario (por Id separados por comas):");
		if(!parlamentario.getOrganos().isEmpty()) {
			System.out.print("   -> Organos actuales: ");
			for(Organo organo:parlamentario.getOrganos())
				System.out.println(organo.getId()+" ("+organo.getDescripcion()+ "),");
		}
		organoService.printOrganos();
		String organos=in.readLine();
		String[] arrayOrganos=organos.split(",");
		parlamentario.getOrganos().clear();
		for(String organoId:arrayOrganos) {
			if(!"".equals(organoId)) {				
				Organo organo=organoService.findById(Long.parseLong(organoId));
				if(organo!=null)
					parlamentario.getOrganos().add(organo);
			}
		}
		
	}

	public Iterable<Parlamentario> findAll() {
		return parlamentarioRepo.findAll();
	}
	
	public void deleteById(long id) {
		parlamentarioRepo.deleteById(id);
	}

	public void save(Parlamentario parlamentario) {
		parlamentarioRepo.save(parlamentario);
	}

	public Optional<Parlamentario> findById(long id) {
		return parlamentarioRepo.findById(id);
	}

}

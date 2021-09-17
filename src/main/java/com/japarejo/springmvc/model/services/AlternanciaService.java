package com.japarejo.springmvc.model.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.japarejo.springmvc.model.entities.Organo;
import com.japarejo.springmvc.model.entities.Parlamentario;

@Service
public class AlternanciaService {

	double PROBEXCEPTION = 0.1;
	int NPARLAMENTARIOS = 5;
	String[] sustantivos = { "El milagro", "Trueno", "Vigilante", "Capitán", "Chico", "Aguijón", "Maestro", "Halcón",
			"Tornado", "Pecador", "Fistro" };
	String[] adjetivos = { "elástico", "espacial", "mágico", "biónico", "radiactivo", "maravilla", "letal", "mutante",
			"amoroso", "carmesí", "diodenarr", "de la pradera" };

	@Autowired
	ParlamentarioService parlamentarioService;

	@Autowired
	OrganoService organoService;
	private TransactionTemplate transactionTemplate;

	public AlternanciaService(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);   
	} 

	
	public void alternancia() throws CutreMasterException, CasoplonException {		
		/*transactionTemplate.execute(
				  new TransactionCallbackWithoutResult() { 
				   protected void doInTransactionWithoutResult(TransactionStatus status) {
				    try {*/
				    	
				    	parlamentarioService.resetOrganos();
						List<Parlamentario> parlamentarios = crearUObtenerParlamentarios();
						modificarOrganos(parlamentarios); 
				    
				    /*
				    } catch (CutreMasterException | CasoplonException ex) {
				       status.setRollbackOnly(); 
				    } 
				   } 
				  }); */

		
	}
 
	private void modificarOrganos(List<Parlamentario> parlamentarios) throws CutreMasterException, CasoplonException {

		Organo mesa = organoService.findByAbreviatura("MESA");
		Organo juntaPortavoces = organoService.findByAbreviatura("JP");
		Organo gobierno = organoService.findByAbreviatura("GOBIERNO");
		int i = 0;
		for (Parlamentario parlamentario : parlamentarios) {
			double random = Math.random();
			if (i > 0) {
				if (random < PROBEXCEPTION) {
					throw new CutreMasterException();
				} else if (random < 2 * PROBEXCEPTION) {
					throw new CasoplonException();
				}
			}
			parlamentario.getOrganos().add(mesa);
			parlamentario.getOrganos().add(juntaPortavoces);
			parlamentario.getOrganos().add(gobierno);
			gobierno.getMiembros().add(parlamentario);
			mesa.getMiembros().add(parlamentario);
			juntaPortavoces.getMiembros().add(parlamentario);
			parlamentarioService.save(parlamentario);			
			i++;
		}
	}

	@Transactional
	private List<Parlamentario> crearUObtenerParlamentarios() {
		List<Parlamentario> result = new ArrayList<>();
		Parlamentario parlamentario = null;
		String nombre = null;
		for (int i = 0; i < NPARLAMENTARIOS; i++) {
			nombre = generarNombreAleatorio();
			parlamentario = parlamentarioService.findByNombre(nombre);
			if (parlamentario == null) {
				parlamentario = new Parlamentario();
				parlamentario.setNombre(nombre);
				parlamentarioService.save(parlamentario);
			}
			result.add(parlamentario);
		}
		return result;
	}

	private String generarNombreAleatorio() {
		String sustantivo = sustantivos[(int) (Math.random() * sustantivos.length)];
		String adjetivo = adjetivos[(int) (Math.random() * adjetivos.length)];
		return sustantivo + " " + adjetivo;
	}

	public class CutreMasterException extends Exception {
	}

	public class CasoplonException extends Exception {
	}

}

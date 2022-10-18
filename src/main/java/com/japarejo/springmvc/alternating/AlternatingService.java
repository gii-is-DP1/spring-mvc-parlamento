package com.japarejo.springmvc.alternating;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.japarejo.springmvc.alternating.exception.LuxuryMansionException;
import com.japarejo.springmvc.alternating.exception.ShittyMasterException;
import com.japarejo.springmvc.board.Board;
import com.japarejo.springmvc.board.BoardService;
import com.japarejo.springmvc.member.Member;
import com.japarejo.springmvc.member.MemberService;

@Service
public class AlternatingService {

	double PROBEXCEPTION = 0.1;
	int NPARLAMENTARIOS = 5;
	String[] sustantives = { "El milagro", "Trueno", "Vigilante", "Capitán", "Chico", "Aguijón", "Maestro", "Halcón",
			"Tornado", "Pecador", "Fistro" };
	String[] adjective = { "elástico", "espacial", "mágico", "biónico", "radiactivo", "maravilla", "letal", "mutante",
			"amoroso", "carmesí", "diodenarr", "de la pradera" };

	@Autowired
	MemberService parlamentarioService;

	@Autowired
	BoardService organoService;
	private TransactionTemplate transactionTemplate;

	public AlternatingService(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);   
	} 

	
	public void alternanting() throws ShittyMasterException, LuxuryMansionException {		
		/*transactionTemplate.execute(
				  new TransactionCallbackWithoutResult() { 
				   protected void doInTransactionWithoutResult(TransactionStatus status) {
				    try {*/
				    	
				    	parlamentarioService.resetBoards();
						List<Member> parlamentarios = crearUObtenerParlamentarios();
						modificarOrganos(parlamentarios); 
				    
				    /*
				    } catch (CutreMasterException | CasoplonException ex) {
				       status.setRollbackOnly(); 
				    } 
				   } 
				  }); */

		
	}
 
	
	private void modificarOrganos(List<Member> parlamentarios) throws ShittyMasterException, LuxuryMansionException {

		Board mesa = organoService.findByShortname("MESA");
		Board juntaPortavoces = organoService.findByShortname("JP");
		Board gobierno = organoService.findByShortname("GOBIERNO");
		int i = 0;
		for (Member parlamentario : parlamentarios) {
			double random = Math.random();
			if (i > 0) {
				if (random < PROBEXCEPTION) {
					throw new ShittyMasterException();
				} else if (random < 2 * PROBEXCEPTION) {
					throw new LuxuryMansionException();
				}
			}
			parlamentario.getBoards().add(mesa);
			parlamentario.getBoards().add(juntaPortavoces);
			parlamentario.getBoards().add(gobierno);
			gobierno.getMembers().add(parlamentario);
			mesa.getMembers().add(parlamentario);
			juntaPortavoces.getMembers().add(parlamentario);
			parlamentarioService.save(parlamentario);			
			i++;
		}
	}

	@Transactional
	public List<Member> crearUObtenerParlamentarios() {
		List<Member> result = new ArrayList<>();
		Member parlamentario = null;
		String name = null;
		for (int i = 0; i < NPARLAMENTARIOS; i++) {
			name = generateRandomName();
			parlamentario = parlamentarioService.findByNombre(name);
			if (parlamentario == null) {
				parlamentario = new Member();
				parlamentario.setName(name);
				parlamentarioService.save(parlamentario);
			}
			result.add(parlamentario);
		}
		return result;
	}

	private String generateRandomName() {
		String sustantive = sustantives[(int) (Math.random() * sustantives.length)];
		String adjetive = adjective[(int) (Math.random() * adjective.length)];
		return sustantive + " " + adjetive;
	}

	

}

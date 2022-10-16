package com.japarejo.springmvc.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomService {	
	
	
	
	private RoomRepository salaRepo;
/* 
	public void initializeRooms() throws Exception {
		Iterable<Room> salaIter = salaRepo.findAll();
		Iterator<Room> iterador = salaIter.iterator();
		if (!iterador.hasNext()) {
			// No existen organos, vamos a crearlos:
			System.out.println("No hay salas!, vamos a incializarlas...");
			String[][] salas = { {"Salón de Plenos","1"},
								 {"Sala de Junta de Portavoces","1"},
								 {"Sala 1ª de Comisiones","1"},
								 {"Sala 2ª de Comisiones","1"},
								 {"Sala 3ª de Comisiones","1"},
								 {"Sala Mesa de Comisiones A","1"},
								 {"Sala 4ª de Comisiones","1"},
								 {"Sala Mesa de Comisiones B","1"},
								 {"Sala Mesa de Comisiones C","1"},
								 {"Sala Mesa de Comisiones D","1"},
								 {"Sala de reuniones Mesa de la Cámara","1"},
								 {"Sede Parlamentaria","1"},
								 {"Sala contigua Salón de Plenos-Antigua Sacristía","1"}
								};
			Room sala = null;
			for (int i = 0; i < salas.length; i++) {
				sala = new Room();				
				sala.setDescripcion(salas[i][0]);
				sala.setActive(Boolean.valueOf(salas[i][1]));			
				salaRepo.save(sala);
			}
			System.out.println("Done!");
			
		}
	}

	
	public void borrarSala(BufferedReader in) throws NumberFormatException, IOException {
		Long id=Long.parseLong(in.readLine());
		salaRepo.deleteById(id);
		
	}


	

	public Optional<Room> findById(Long idSala) {
		return salaRepo.findById(idSala);
	}

*/	
	
}

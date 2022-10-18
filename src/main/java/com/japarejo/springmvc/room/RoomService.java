package com.japarejo.springmvc.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoomService {	
	
	
	@Autowired
	private RoomRepository salaRepo;
	
	
	@Transactional(readOnly = true)
	List<Room> getAllRooms(){
	    return salaRepo.findAll();
	}
	

/* 
			System.out.println("No hay salas!, vamos a incializarlas...");
			String[] salas = { "Sala de Plenos","Sala de Junta de Portavoces" };
			Room sala = null;
			for (int i = 0; i < salas.length; i++) {
				sala = new Room();				
				sala.setDescripcion(salas[i]);
				sala.setActive(true);
				System.out.println("Cargando sala: "+salas[i]);			
				salaRepo.save(sala);
			}
			System.out.println("Done!");			
*/	
	
}

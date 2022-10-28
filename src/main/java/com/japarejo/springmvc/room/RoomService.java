package com.japarejo.springmvc.room;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoomService {		
	
	private RoomRepository roomRepository;

	@Autowired
	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	@Transactional(readOnly=true)
	public List<Room> getAllRooms() {
		return this.roomRepository.findAll();
	}

	@Transactional
	public void removeRoom(long id) {
		this.roomRepository.deleteById(id);
	}

	@Transactional(readOnly=true)
	public Room getRoom(Long id) {
		Optional<Room> room = this.roomRepository.findById(id);
		return room.isPresent() ? room.get() : null;
	}

	@Transactional
	public Room save(Room room) {
		return this.roomRepository.save(room);
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

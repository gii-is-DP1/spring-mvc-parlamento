package com.japarejo.springmvc.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japarejo.springmvc.board.BoardService;
import com.japarejo.springmvc.room.RoomService;

@Service
public class SessionService {
	
	@Autowired		
	private SessionRepository sessionRepo;
		
	


	

}

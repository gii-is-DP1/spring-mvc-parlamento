package com.japarejo.springmvc.session;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	
	
	private SessionRepository sessionRepo;
	
	@Autowired		
	public SessionService(SessionRepository sessionRepo){
		this.sessionRepo=sessionRepo;
	}


	@Transactional
	public Session save(Session s) throws ConcurrentSessionException{
		List<Session> sessions=sessionRepo.findByDate(s.getDate());
		for(Session prevSession:sessions){
			if(prevSession.isConcurrentWith(s))
				throw new ConcurrentSessionException();
		}
		sessionRepo.save(s);
		return s;
	}

	

}

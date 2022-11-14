package com.japarejo.springmvc.session;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {
	
	private SessionRepository sessionRepo;
	
	@Autowired
	public SessionService(SessionRepository sessionRepository) {
		this.sessionRepo = sessionRepository;
	}

	@Transactional
	public Session save(Session s) throws ConcurrentSessionException {
		List<Session> sessions = sessionRepo.findByDate(s.getDate());
		for (Session prevSession: sessions) {
			if (prevSession.isConcurrentWith(s)) {
				throw new ConcurrentSessionException();
			}
		}
		Session saved = sessionRepo.save(s);		
		return saved;			
	}
	


}

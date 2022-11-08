package com.japarejo.springmvc.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SessionRepositoryTest {
    @Autowired
    SessionRepository sr;

    @Test
    public void testInitialData(){
        List<Session> sesioneSalaPlenos=sr.findByRoomDescription("Sala de Plenos");
        assertNotNull(sesioneSalaPlenos);
        assertFalse(sesioneSalaPlenos.isEmpty());
        List<Session> sesioneSalaPortavoces=sr.findByRoomDescription("Sala de Junta de Portavoces");
        assertNotNull(sesioneSalaPortavoces);
        assertTrue(sesioneSalaPortavoces.isEmpty());
    }
}
    

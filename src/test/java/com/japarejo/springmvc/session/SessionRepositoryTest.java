package com.japarejo.springmvc.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SessionRepositoryTest {
    @Autowired
    SessionRepository sr;

    @Test
    public void testFindByRoomDescription(){
        List<Session> sesioneSalaPlenos=sr.findByRoomDescription("Sala de Plenos");
        assertNotNull(sesioneSalaPlenos);
        assertFalse(sesioneSalaPlenos.isEmpty());
        List<Session> sesioneSalaPortavoces=sr.findByRoomDescription("Sala de Junta de Portavoces");
        assertNotNull(sesioneSalaPortavoces);
        assertTrue(sesioneSalaPortavoces.isEmpty());
    }

    @Test
    public void testFindByDateSuccess(){    
        LocalDate date=LocalDate.of(2022,11,1);
        List<Session> sesioneSalaPlenos=sr.findByDate(date);
        assertNotNull(sesioneSalaPlenos);
        assertFalse(sesioneSalaPlenos.isEmpty());
        
    }

    @Test
    public void testFindByDateNull(){    
        List<Session> sesioneSalaPlenos=sr.findByDate(null);
        assertNotNull(sesioneSalaPlenos);
        assertTrue(sesioneSalaPlenos.isEmpty());
    }
}
    

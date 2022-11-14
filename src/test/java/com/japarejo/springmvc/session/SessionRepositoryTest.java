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
    SessionRepository sessionRepository;

    @Test
    public void testFindByRoomDescriptionWithSessions() {
        List<Session> sesionSalaPlenos = sessionRepository.findByRoomDescription("Sala de Plenos");
        assertNotNull(sesionSalaPlenos);
        assertFalse(sesionSalaPlenos.isEmpty());
    }

    @Test
    public void testFindByRoomDescriptionWithoutSessions() {
        List<Session> sesionSalaPortavoces = sessionRepository.findByRoomDescription("Sala de Junta de Portavoces");
        assertNotNull(sesionSalaPortavoces);
        assertTrue(sesionSalaPortavoces.isEmpty());
    }

    @Test
    public void testFindByRoomDescriptionWithMissingRoom() {
        List<Session> sala = sessionRepository.findByRoomDescription("Sala de Baile");
        assertNotNull(sala);
        assertTrue(sala.isEmpty());
    }

    @Test
    public void testFindByDateSuccess() {
        LocalDate date = LocalDate.of(2022, 11, 1);
        List<Session> sesiones = sessionRepository.findByDate(date);
        assertNotNull(sesiones);
        assertFalse(sesiones.isEmpty());
    }

    @Test
    public void testFindByDateNotFound() {
        LocalDate date = LocalDate.of(2022, 12, 1);
        List<Session> sesiones = sessionRepository.findByDate(date);
        assertNotNull(sesiones);
        assertTrue(sesiones.isEmpty());
    }

}

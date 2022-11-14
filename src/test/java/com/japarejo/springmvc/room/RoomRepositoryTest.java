package com.japarejo.springmvc.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;

    @Test    
    public void testFindAll() {
        List<Room> rooms = roomRepository.findAll();
        assertNotNull(rooms, "El repositorio ha devuelto una lista nula");
        assertEquals(2, rooms.size(), "Faltan datos de inicialización");
    }
}

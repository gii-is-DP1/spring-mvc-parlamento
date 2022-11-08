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
    RoomRepository repo;
    
    @Test
    public void roomDataInitializationTest(){        
        List<Room> rooms=repo.findAll();
        assertNotNull(rooms,"El repositorio ha devuelto una lista nula!");
        assertEquals(2,rooms.size(),"Faltan datos de inicialización en la BD");
    }

}

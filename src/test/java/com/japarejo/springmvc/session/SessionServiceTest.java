package com.japarejo.springmvc.session;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.japarejo.springmvc.room.Room;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
        
    @Mock
    SessionRepository srepo;

    Room room;
    Session s;
    LocalDate date;

    @BeforeEach
    public void config(){
        room=new Room();
        
        date=LocalDate.now();
        
        s=new Session();
        s.setDate(date);
        s.setRoom(room);
        s.setStartTime(Timestamp.valueOf(date.atTime(9, 0)));
        s.setEndTime(Timestamp.valueOf(date.atTime(11, 0)));
        
        List<Session> sessions=new ArrayList();
        sessions.add(s);

        when(srepo.findByDate(any(LocalDate.class))).thenReturn(sessions);
    }
    
    @Test    
    public void saveTestUnsucessfulDueToConcurrecy(){
        Session s2=new Session();
        s2.setDate(date);
        s2.setRoom(room);
        s2.setStartTime(Timestamp.valueOf(date.atTime(10, 0)));
        s2.setEndTime(Timestamp.valueOf(date.atTime(12, 0)));
        SessionService sessionService=new SessionService(srepo);
        assertThrows(ConcurrentSessionException.class,()->sessionService.save(s2));

    }

    @Test    
    public void saveTestSucessful(){
        Session s2=new Session();
        s2.setDate(date);
        s2.setRoom(room);
        s2.setStartTime(Timestamp.valueOf(date.atTime(13, 0)));
        s2.setEndTime(Timestamp.valueOf(date.atTime(14, 0)));
        SessionService sessionService=new SessionService(srepo);
        try{
            sessionService.save(s2);
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }

    }
}

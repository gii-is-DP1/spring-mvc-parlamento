package com.japarejo.springmvc.session;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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

import com.japarejo.springmvc.room.Room;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
    @Mock
    SessionRepository repo;

    Room room;
    Session s;
    LocalDate date;

    private Session createSession(int initHour, int initMins, int endHour, int endMins) {
        LocalDate date = LocalDate.of(2022, 12, 5);
        Session s = new Session();
        s.setRoom(room);
        s.setDate(date);
        s.setStartTime(
            Timestamp.valueOf(date.atTime(initHour, initMins, 0))
        );
        s.setEndTime(
            Timestamp.valueOf(date.atTime(endHour, endMins, 0))
        );

        return s;
    }

    @BeforeEach
    public void config() {
        room = new Room();
        s = createSession(9, 0, 11, 0);

        List<Session> sessions = new ArrayList<Session>() ;
        sessions.add(s);
        
        when(repo.findByDate(any(LocalDate.class))).thenReturn(sessions);
    }

    @Test
    public void saveTestUnsuccessfulDueToConcurrency() {
        Session s2 = createSession(10, 0, 12, 0);
        SessionService sessionService = new SessionService(repo);
        assertThrows(ConcurrentSessionException.class, () -> sessionService.save(s2));
    }

    @Test
    public void saveTestSuccessful() {
        Session s2 = createSession(13, 0, 14, 0);
        SessionService sessionService = new SessionService(repo);
        try {
            sessionService.save(s2);
            verify(repo).save(s2);
        } catch (Exception e) {
            fail("No exception should be thrown");
        }
    }
}

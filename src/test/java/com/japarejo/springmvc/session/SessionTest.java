package com.japarejo.springmvc.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionTest {

    LocalDate date;
    Session s1;

    private Session createSession(int initHour, int initMins, int endHour, int endMins) {
        LocalDate date = LocalDate.of(2022, 12, 5);
        Session s = new Session();
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
    public void setupDates() {
        s1 = createSession(9, 0, 12, 0);
    }

    @Test
    public void testIsConcurrentWithTrue() {
        Session s2 = createSession(10, 0, 13, 0);

        assertTrue(s1.isConcurrentWith(s2));
        assertTrue(s2.isConcurrentWith(s1));
    }

    @Test
    public void testIsConcurrentWithFalse() {
        Session s2 = createSession(12, 30, 13, 0);
        
        assertFalse(s1.isConcurrentWith(s2));
        assertFalse(s2.isConcurrentWith(s1));
    }
}

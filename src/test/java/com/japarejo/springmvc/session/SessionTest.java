package com.japarejo.springmvc.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class SessionTest {
    @Test
    public void isConcurrentWithTest(){
        LocalDate date=LocalDate.now();
        Session s1=new Session();
        s1.setDate(date);        
        s1.setStartTime(
            Timestamp.valueOf(date.atTime(9, 0, 0))
        );
        s1.setEndTime(
            Timestamp.valueOf(date.atTime(12, 0, 0))
        );
        
        Session s2=new Session();
        s2.setDate(date);
        s2.setStartTime(
            Timestamp.valueOf(date.atTime(10, 0, 0))
        );
        s2.setEndTime(
            Timestamp.valueOf(date.atTime(13, 0, 0))
        );

        assertTrue(s1.isConcurrentWith(s2));
        assertTrue(s2.isConcurrentWith(s1));
        
        s2.setStartTime(
            Timestamp.valueOf(date.atTime(12, 30, 0))
        );

        assertFalse(s1.isConcurrentWith(s2));
        assertFalse(s2.isConcurrentWith(s1));

    }
    
}

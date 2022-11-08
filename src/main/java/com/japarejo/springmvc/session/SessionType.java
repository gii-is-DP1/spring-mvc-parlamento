package com.japarejo.springmvc.session;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SessionType implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;
	
	private Boolean active;

	private String description;

}
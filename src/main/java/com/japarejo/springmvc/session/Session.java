package com.japarejo.springmvc.session;

import java.io.Serializable;
import javax.persistence.*;

import com.japarejo.springmvc.board.Board;
import com.japarejo.springmvc.room.Room;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;
		
	private Date fecha;
	
	@ManyToOne()
	private Board board;
	
	@ManyToOne()
	private Room sala;
	
	@ManyToOne()
	private SessionType sessionType;

	private Timestamp endTime;

	private Timestamp startTime;

	private Long legislature;	

}
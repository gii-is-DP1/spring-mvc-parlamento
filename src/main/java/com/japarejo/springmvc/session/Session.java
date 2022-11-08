package com.japarejo.springmvc.session;

import java.io.Serializable;
import javax.persistence.*;

import com.japarejo.springmvc.board.Board;
import com.japarejo.springmvc.room.Room;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;
		
	private LocalDate date;
	
	@ManyToOne()
	private Board board;
	
	@ManyToOne()
	private Room room;
	
	@ManyToOne()
	private SessionType sessionType;

	private Timestamp endTime;

	private Timestamp startTime;

	private Long legislature;	

	// NOTA IMPORTANTE AQUÍ HAY UN BUG OCULTO SE DEJA COMO EJERCICIO A LOS ALUMNOS ENCONTRAR UN CASO
	// QUE LO REVELE, PROPONERLES USAR UN TEST PARAMETRIZADO PARA INTENTAR DESVERLARLO PONIENDO TODA
	// LA CASUÍSTICA DE SOLA POSIBLE.
	public boolean isConcurrentWith(Session s){
		boolean result=false;
		if(s!=null){
			if(date.equals(s.getDate()))	{
				if(this.startTime.after(s.startTime) && this.startTime.before(s.endTime)){
					result=true;
				}else if(this.endTime.after(s.startTime) && this.endTime.before(s.endTime)){
					result=true;
				}
			}
		}
		return result;
	}
}
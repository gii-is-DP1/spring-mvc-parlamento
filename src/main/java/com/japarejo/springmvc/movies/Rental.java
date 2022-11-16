package com.japarejo.springmvc.movies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rental {
    public double getCharge() {
        return getMovie().getCharge(daysRented);
    }
	public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;
		
    @ManyToOne
    private Movie movie; 

    private int daysRented;    
}

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
        // add bonus for a two day new release rental
        if ((getMovie().getPriceCode() == PriceCode.NEW_RELEASE) && getDaysRented() > 1) 
            return 2;
        else
            return 1;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;
		
    @ManyToOne
    private Movie movie; 

    private int daysRented;    
}

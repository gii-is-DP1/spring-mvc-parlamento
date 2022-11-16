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
        double result = 0;
        //determine amounts for each line
        if (getMovie().getPriceCode().equals(PriceCode.REGULAR)) {
            result += 2;
            if (getDaysRented() > 2)
                result += (getDaysRented() - 2) * 1.5;             
        } else if (getMovie().getPriceCode().equals(PriceCode.NEW_RELEASE)) {
            result += getDaysRented() * 3; 
        } else if (getMovie().getPriceCode().equals(PriceCode.CHILDRENS)) {
            result += 1.5;
            if (getDaysRented() > 3)
                result += (getDaysRented() - 3) * 1.5;             
        }
        return result;
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

package com.japarejo.springmvc.movies;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;
		

    private String title; 

    @Convert(converter = ConverterPrice.class)
    private Price price;

    public int getFrequentRenterPoints(int daysRented) {
        // add bonus for a two day new release rental
        if (getPriceCode() == PriceCode.NEW_RELEASE && daysRented > 1) 
            return 2;
        else
            return 1;
    }

    @Transient
    public PriceCode getPriceCode() {
        return getPrice().getPriceCode();
    }

    public double getCharge(int daysRented) {
        return getPrice().getCharged(daysRented);
    }    

}

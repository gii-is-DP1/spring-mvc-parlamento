package com.japarejo.springmvc.movies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private PriceCode priceCode;

    public int getFrequentRenterPoints(int daysRented) {
        // add bonus for a two day new release rental
        if (priceCode == PriceCode.NEW_RELEASE && daysRented > 1) 
            return 2;
        else
            return 1;
    }

    public double getCharge(int daysRented) {
        double result = 0;
        //determine amounts for each line
        if (getPriceCode().equals(PriceCode.REGULAR)) {
            result += 2;
            if (daysRented > 2)
                result += (daysRented - 2) * 1.5;             
        } else if (getPriceCode().equals(PriceCode.NEW_RELEASE)) {
            result += daysRented * 3; 
        } else if (getPriceCode().equals(PriceCode.CHILDRENS)) {
            result += 1.5;
            if (daysRented > 3)
                result += (daysRented - 3) * 1.5;             
        }
        return result;
    }    

}

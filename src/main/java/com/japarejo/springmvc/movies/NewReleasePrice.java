package com.japarejo.springmvc.movies;

public class NewReleasePrice extends Price {

    @Override
    public PriceCode getPriceCode() {
        return PriceCode.NEW_RELEASE;
    }

    @Override
    public double getCharged(int daysRented) {
        return daysRented * 3;
    }   

    
}
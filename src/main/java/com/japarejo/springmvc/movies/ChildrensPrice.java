package com.japarejo.springmvc.movies;

public class ChildrensPrice extends Price {

    @Override
    public PriceCode getPriceCode() {
        return PriceCode.CHILDRENS;
    }

    @Override
    public double getCharged(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;             
        return result;
    }    
    
}
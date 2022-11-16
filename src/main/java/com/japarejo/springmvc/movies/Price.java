package com.japarejo.springmvc.movies;

public abstract class Price {
    public abstract PriceCode getPriceCode();
    public abstract double getCharged(int daysRented);
}

package com.japarejo.springmvc.movies;

import java.time.Period;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ConverterPrice implements AttributeConverter<Price, String> {

    @Override
    public String convertToDatabaseColumn(Price attribute) {
        return attribute.getPriceCode().toString();
    }

    @Override
    public Price convertToEntityAttribute(String dbData) {
        if (dbData.equals("CHILDRENS")) {
            return new ChildrensPrice();
        } else if (dbData.equals("NEW_RELEASE")) {
            return new NewReleasePrice();
        } else {
            return new RegularPrice();
        }
    }
    
}

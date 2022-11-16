package com.japarejo.springmvc.movies;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @OneToMany
    private List<Rental> rentals;

    private String name;
}


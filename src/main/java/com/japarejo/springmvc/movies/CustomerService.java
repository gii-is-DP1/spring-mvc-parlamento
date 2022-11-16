package com.japarejo.springmvc.movies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repo) {
        this.repository = repo;
    }

    public Customer findById(long id) {
        Optional<Customer> found =  this.repository.findById(id);
        return found.isPresent() ? found.get() : null;
    }
}

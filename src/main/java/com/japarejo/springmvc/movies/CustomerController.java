package com.japarejo.springmvc.movies;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerController {

    private static final String STATEMENT_VIEW = "StatementView";

    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping(path="/customer/{id}/statement")
    public String showStatement(@PathVariable("id") Long id, ModelMap modelMap) {
        Customer c = this.service.findById(id);
        Map<Rental, Double> rentalInfo = new HashMap<Rental, Double>();

        modelMap.put("customer", c);

        for (Rental rental : c.getRentals()) {
            rentalInfo.put(rental, rental.getCharge());
        }
        
        modelMap.put("rentalInfo", rentalInfo);
        modelMap.put("totalAmount", c.getTotalCharge());
        modelMap.put("earnedPoints", c.getTotalFrequentRenterPoints());

        return STATEMENT_VIEW;
    }    
}

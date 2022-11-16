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
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Map<Rental, Double> rentalInfo = new HashMap<Rental, Double>();

        modelMap.put("customer", c);

        for (Rental rental : c.getRentals()) {
            double thisAmount = 0;
            thisAmount = amountFor(rental);

            // add frequent renter points
            frequentRenterPoints ++;

            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE) && rental.getDaysRented() > 1) 
                frequentRenterPoints ++;
            //show figures
            rentalInfo.put(rental, thisAmount);
            totalAmount += thisAmount;
        }
        
        modelMap.put("rentalInfo", rentalInfo);
        modelMap.put("totalAmount", totalAmount);
        modelMap.put("earnedPoints", frequentRenterPoints);

        return STATEMENT_VIEW;
    }

    private double amountFor(Rental rental) {
        double thisAmount = 0;
        //determine amounts for each line
        if (rental.getMovie().getPriceCode().equals(PriceCode.REGULAR)) {
            thisAmount += 2;
            if (rental.getDaysRented() > 2)
                thisAmount += (rental.getDaysRented() - 2) * 1.5;             
        } else if (rental.getMovie().getPriceCode().equals(PriceCode.NEW_RELEASE)) {
            thisAmount += rental.getDaysRented() * 3; 
        } else if (rental.getMovie().getPriceCode().equals(PriceCode.CHILDRENS)) {
            thisAmount += 1.5;
            if (rental.getDaysRented() > 3)
                thisAmount += (rental.getDaysRented() - 3) * 1.5;             
        }
        return thisAmount;
    }    
}

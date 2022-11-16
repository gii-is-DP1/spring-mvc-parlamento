package com.japarejo.springmvc.movies;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.japarejo.springmvc.configuration.SecurityConfiguration;

@WebMvcTest(controllers=CustomerController.class,
    excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration = SecurityConfiguration.class)
public class CustomerControllerTest {

    private static final String ID_CUSTOMER = "1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @WithMockUser
    @Test
    public void testShowStatement() throws Exception {
        Movie m1 = new Movie();
        Movie m2 = new Movie();
        Movie m3 = new Movie();
        m1.setTitle("M1");
        m1.setPriceCode(PriceCode.CHILDRENS);
        m2.setTitle("M2");
        m2.setPriceCode(PriceCode.NEW_RELEASE);
        m3.setTitle("M3");
        m3.setPriceCode(PriceCode.REGULAR);
        Customer c = new Customer();
        Rental r1 = new Rental();        
        Rental r2 = new Rental();
        Rental r3 = new Rental();
        c.setRentals(Arrays.asList(r1,r2,r3));
        r1.setMovie(m1);
        r1.setDaysRented(3);
        r2.setMovie(m2);
        r2.setDaysRented(2);
        r3.setMovie(m3);
        r3.setDaysRented(5);

        when(customerService.findById(1)).thenReturn(c);        

        mockMvc.perform(get("/customer/{id}/statement", ID_CUSTOMER))
            .andExpect(status().isOk())
            .andExpect(view().name("StatementView"))
            .andExpect(model().attribute("customer", c))
            .andExpect(model().attribute("totalAmount", 14.0))
            .andExpect(model().attribute("earnedPoints", 4));    
    }    
}

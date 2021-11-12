package com.test.springoauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class TestRestController {

    /**
     * Test REST Controller to test OAuth server
     *
     * @param userId
     * @return
     */

    @GetMapping("customer/{userId}")
    public CustomerData getCustomerProfile(@PathVariable("userId") String userId) {
        return getCustomer(userId);
    }

    private CustomerData getCustomer(final String userId) {
        CustomerData customer = new CustomerData();
        customer.setEmail("naphade21@gmail.com");
        customer.setAge(21);
        customer.setFirstName("Atharva");
        customer.setLastName("Naphade");
        customer.setId(userId);

        return customer;
    }
}

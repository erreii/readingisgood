package com.example.demo;

import com.example.demo.model.Customer;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

public class CustomerControllerTests {
    public RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Omer");
        customer.setLastName("UNAL");

        URI location = restTemplate.postForLocation("http://localhost:8080/api/v1/customer/saveCustomer", customer);

        Customer customerTest = restTemplate.getForObject(Objects.requireNonNull(location), Customer.class);
        MatcherAssert.assertThat(Objects.requireNonNull(customerTest).getFirstName(), Matchers.equalTo(customer.getFirstName()));
        MatcherAssert.assertThat(customerTest.getLastName(), Matchers.equalTo(customer.getLastName()));
    }

    @Test
    public void testAllOrders() {

    }

}

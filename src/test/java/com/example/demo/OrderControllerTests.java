package com.example.demo;

import com.example.demo.model.Order;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

public class OrderControllerTests {
    public RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order();
        order.setBookId(1L);
        order.setCustomerId(1L);
        URI location = restTemplate.postForLocation("http://localhost:8080/api/v1/order/saveOrder", order);
        Order orderTest = restTemplate.getForObject(Objects.requireNonNull(location), Order.class);
        MatcherAssert.assertThat(Objects.requireNonNull(orderTest).getBookId(), Matchers.equalTo(order.getBookId()));
        MatcherAssert.assertThat(orderTest.getCustomerId(), Matchers.equalTo(order.getCustomerId()));
    }

    @Test
    public void testFindOrder() {
        ResponseEntity<Order> response = restTemplate.getForEntity("http://localhost:8080/api/v1/order/findOrder/1", Order.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        MatcherAssert.assertThat(Objects.requireNonNull(response.getBody()).getOrderCount(), Matchers.equalTo(3));
    }

}

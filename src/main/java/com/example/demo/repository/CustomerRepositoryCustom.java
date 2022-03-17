package com.example.demo.repository;

import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<Customer> findCustomerByEmail(String email);
}

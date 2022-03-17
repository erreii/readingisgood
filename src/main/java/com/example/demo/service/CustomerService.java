package com.example.demo.service;

import com.example.demo.dto.StatisticsDto;
import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);

    List<StatisticsDto> findCustomerStatistics(String customerId);

    List<Customer> findCustomerByEmail(String email);
}

package com.example.demo.service;

import com.example.demo.dto.StatisticsDto;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;


    public void saveCustomer(Customer customer) {
        log.info("CustomerService -- saveCustomer() -- started : " + customer);
        customerRepository.insert(customer);
        log.info("CustomerService -- saveCustomer() -- finish : " + customer);
    }


    public List<StatisticsDto> findCustomerStatistics(String customerId) {
        log.info("CustomerService -- findCustomerStatistics() -- started : CustomerId : " + customerId);
        return null;
    }

    public List<Customer> findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}

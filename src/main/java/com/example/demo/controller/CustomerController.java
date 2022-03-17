package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveCustomer")
    public ResponseEntity<Object> saveCustomer(@RequestBody CustomerDto customerDto) {
        Customer customerRequest = modelMapper.map(customerDto, Customer.class);
        try {
            List<Customer> customerList = customerService.findCustomerByEmail(customerRequest.getEmail());
            if (CollectionUtils.isEmpty(customerList)) {
                customerService.saveCustomer(customerRequest);
                return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, customerRequest);
            }
            return ResponseHandler.generateResponse("Customer's already exist with this email: " + customerRequest.getEmail(), HttpStatus.MULTI_STATUS, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/allOrders")
    public ResponseEntity<Object> findCustomerAllOrders(@RequestParam("customerId") String customerId) {
        try {
            List<Order> orderList = orderService.findCustomerAllOrders(customerId);
            return ResponseHandler.generateResponse("Customer data result", HttpStatus.OK, orderList);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}

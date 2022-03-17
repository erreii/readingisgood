package com.example.demo.controller;

import com.example.demo.dto.StatisticsDto;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/statistics")
public class StatisticsController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/find")
    public ResponseEntity<Object> findCustomerStatistics(@RequestParam("customerId") String customerId) {
        try {
            List<StatisticsDto> list = customerService.findCustomerStatistics(customerId);
            return ResponseHandler.generateResponse("Customer statistics data result", HttpStatus.OK, list);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}

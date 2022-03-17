package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDto {
    private String id;
    private Long customerId;
    private Long bookId;
    private int orderCount;
    private Date orderDate;
    private BigDecimal amount;
}

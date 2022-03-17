package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticsDto {
    private String monthName;
    private int orderCount;
    private int bookCount;
    private BigDecimal totalPurchasedAmount;
}

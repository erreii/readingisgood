package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    private String id;
    private String title;
    private String author;
    private BigDecimal price;
    private int stockNumber;
}

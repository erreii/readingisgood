package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "Book")
@Data
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private BigDecimal price;
    private int stockNumber;
}

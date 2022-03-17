package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "Order")
@Data
public class Order {
    @Id
    private String id;
    private Long customerId;
    private Long bookId;
    private int orderCount;
    private Date orderDate;
    private BigDecimal amount;
}

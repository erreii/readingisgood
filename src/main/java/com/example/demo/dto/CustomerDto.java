package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthdate;
}

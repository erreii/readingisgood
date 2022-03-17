package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CountExceedException extends RuntimeException {
    public CountExceedException(String s) {
        super(s);
    }
}
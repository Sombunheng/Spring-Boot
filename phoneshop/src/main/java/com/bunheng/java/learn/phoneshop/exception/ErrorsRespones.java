package com.bunheng.java.learn.phoneshop.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorsRespones {
    private HttpStatus status;
    private String message;
}

package com.sparkequation.spring.trial.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found.")
public class ProductNotFoundException extends RuntimeException {

}

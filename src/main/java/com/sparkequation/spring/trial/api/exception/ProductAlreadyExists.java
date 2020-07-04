package com.sparkequation.spring.trial.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Product with such id already exists.")
public class ProductAlreadyExists extends RuntimeException {

}

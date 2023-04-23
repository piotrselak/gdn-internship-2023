package com.github.piotrselak.gdninternship2023.nbp.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationError extends RuntimeException {
    public ValidationError(String message) {
        super(message);
    }
}

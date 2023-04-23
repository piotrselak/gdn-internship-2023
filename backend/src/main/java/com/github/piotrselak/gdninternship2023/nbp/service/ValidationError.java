package com.github.piotrselak.gdninternship2023.nbp.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class ValidationError extends ResponseStatusException {
    public ValidationError(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

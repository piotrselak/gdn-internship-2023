package com.github.piotrselak.gdninternship2023.nbp.service;

public class ValidationError extends RuntimeException {
    public ValidationError(String message) {
        super(message);
    }
}

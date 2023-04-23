package com.github.piotrselak.gdninternship2023.nbp.controller;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}

package com.github.piotrselak.gdninternship2023.nbp.service;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RateNotFound extends ResponseStatusException {
    public RateNotFound() {
        super(HttpStatus.NOT_FOUND, "Rate not found");
    }
}

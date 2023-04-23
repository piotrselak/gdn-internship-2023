package com.github.piotrselak.gdninternship2023.nbp.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Should never happen - unless this server is bugged.
 */
public class EmptyRateArrayException extends ResponseStatusException {
    public EmptyRateArrayException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}

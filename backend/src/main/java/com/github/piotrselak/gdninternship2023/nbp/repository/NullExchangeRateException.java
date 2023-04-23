package com.github.piotrselak.gdninternship2023.nbp.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Should never happen - unless this server is bugged.
 */
public class NullExchangeRateException extends ResponseStatusException {
    public NullExchangeRateException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}

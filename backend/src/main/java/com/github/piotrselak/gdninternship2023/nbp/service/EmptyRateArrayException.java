package com.github.piotrselak.gdninternship2023.nbp.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Should never happen - unless this server is bugged.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EmptyRateArrayException extends RuntimeException {
    public EmptyRateArrayException(String message) {
        super(message);
    }
}

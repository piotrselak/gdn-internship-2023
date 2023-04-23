package com.github.piotrselak.gdninternship2023.nbp.service;

/**
 * Should never happen - unless this server is bugged.
 */
public class EmptyRateArrayException extends RuntimeException {
    public EmptyRateArrayException(String message) {
        super(message);
    }
}

package com.github.piotrselak.gdninternship2023.nbp.repository;


/**
 * Should never happen - unless this server is bugged.
 */
public class NullExchangeRateException extends RuntimeException {
    public NullExchangeRateException(String message) {
        super(message);
    }
}

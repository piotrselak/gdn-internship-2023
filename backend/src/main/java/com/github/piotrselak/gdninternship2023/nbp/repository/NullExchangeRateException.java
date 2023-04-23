package com.github.piotrselak.gdninternship2023.nbp.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: maybe find a better http status
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NullExchangeRateException extends NullPointerException{
    public NullExchangeRateException(String message) {
        super(message);
    }
}

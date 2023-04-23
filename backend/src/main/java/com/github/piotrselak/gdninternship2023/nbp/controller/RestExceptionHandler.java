package com.github.piotrselak.gdninternship2023.nbp.controller;


import com.github.piotrselak.gdninternship2023.nbp.repository.NullExchangeRateException;
import com.github.piotrselak.gdninternship2023.nbp.service.EmptyRateArrayException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { EmptyRateArrayException.class, NullExchangeRateException.class})
    protected ResponseEntity<Object> handleInternalServerError(RuntimeException ex, WebRequest request) {
        ApiError err = new ApiError(500, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
        return handleExceptionInternal(ex, err,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {ValidationException.class, WebClientResponseException.BadRequest.class})
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
        ApiError err = new ApiError(400, HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return handleExceptionInternal(ex, err,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {WebClientResponseException.NotFound.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        ApiError err = new ApiError(404, HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
        return handleExceptionInternal(ex, err,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}

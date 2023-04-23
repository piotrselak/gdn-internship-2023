package com.github.piotrselak.gdninternship2023.nbp.controller;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiError(int statusCode, HttpStatus status, String message, LocalDateTime timestamp) {
}

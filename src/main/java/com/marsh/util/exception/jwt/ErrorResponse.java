package com.marsh.util.exception.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;

    public ErrorResponse(HttpStatus httpStatus, String errorCode, String message) {
        this(
                LocalDateTime.now(),
                httpStatus.value(),
                errorCode,
                message
        );
    }
}
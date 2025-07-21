package com.marsh.util.exception.jwt;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class JwtException extends RuntimeException {
    private final HttpStatus status;
    private final String errorCode;

    public JwtException(HttpStatus status, JwtErrorCode errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = String.format("JWT-ERROR-%03d", errorCode.ordinal() + 1);
    }
}

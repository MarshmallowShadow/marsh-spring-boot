package com.marsh.exception.upper.aes;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EncException extends RuntimeException {
    private final HttpStatus status;
    private final String errorCode;

    public EncException(HttpStatus status, EncErrorCode errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = String.format("AES-ERROR-%03d", errorCode.ordinal() + 1);
    }
}

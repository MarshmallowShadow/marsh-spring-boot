package com.marsh.exception;

import com.marsh.exception.dto.res.ErrorResDto;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> "[" + error.getField() + "]은(는) 필수 항목입니다.")
                .orElse("요청 값이 유효하지 않습니다.");

        return new ErrorResDto(HttpStatus.BAD_REQUEST, "System-001", errorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResDto handleJsonParseException(HttpMessageNotReadableException exception) {
        return new ErrorResDto(HttpStatus.BAD_REQUEST, "System-002", "요청 형식이 올바르지 않습니다.");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResDto handleMissingParam(MissingServletRequestParameterException exception) {
        String parameterName = exception.getParameterName();
        String errorMessage =  "[" + parameterName + "]은(는) 필수 파라미터입니다.";
        return new ErrorResDto(HttpStatus.BAD_REQUEST, "System-003", errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResDto handleConstraintViolation(ConstraintViolationException exception) {
        String errorMessage = exception.getConstraintViolations().stream()
                .findFirst()
                .map(violation -> {
                    String fullPath = violation.getPropertyPath().toString();
                    String param = fullPath.substring(fullPath.lastIndexOf('.') + 1); // get last part
                    return "[" + param + "]은(는) 유효하지 않습니다.";
                })
                .orElse("잘못된 요청입니다.");
        return new ErrorResDto(HttpStatus.BAD_REQUEST, "System-004", errorMessage);
    }
}

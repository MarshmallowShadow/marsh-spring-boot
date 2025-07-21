package com.marsh.util.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsh.util.exception.jwt.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        var errorResponse = objectMapper.writeValueAsString(
                new ErrorResponse(
                        HttpStatus.UNAUTHORIZED,
                        "UNAUTHORIZED",
                        "로그인 후 이용 가능합니다."
                )
        );
        response.getWriter().write(errorResponse);
    }
}
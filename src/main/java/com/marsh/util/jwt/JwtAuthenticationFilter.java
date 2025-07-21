package com.marsh.util.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsh.util.exception.jwt.ErrorResponse;
import com.marsh.util.exception.jwt.JwtErrorCode;
import com.marsh.util.exception.jwt.JwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(ObjectMapper objectMapper, JwtTokenProvider jwtTokenProvider) {
        this.objectMapper = objectMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            var claims = jwtTokenProvider.resolveToken(request);
            if (claims != null) {
                SecurityContextHolder.getContext().setAuthentication(
                        jwtTokenProvider.getAuthentication(claims)
                );
            }
            filterChain.doFilter(request, response);
        } catch (SignatureException exception) {
            sendErrorMessage(response, new JwtException(
                    HttpStatus.UNAUTHORIZED,
                    JwtErrorCode.INVALID_SIGNATURE,
                    "유효하지 않은 토큰입니다."
            ));
        } catch (MalformedJwtException exception) {
            sendErrorMessage(response, new JwtException(
                    HttpStatus.UNAUTHORIZED,
                    JwtErrorCode.MALFORMED,
                    "손상된 토큰입니다."
            ));
        } catch (DecodingException exception) {
            sendErrorMessage(response, new JwtException(
                    HttpStatus.UNAUTHORIZED,
                    JwtErrorCode.CANNOT_DECODE,
                    "잘못된 인증입니다."
            ));
        } catch (ExpiredJwtException exception) {
            sendErrorMessage(response, new JwtException(
                    HttpStatus.UNAUTHORIZED,
                    JwtErrorCode.EXPIRED,
                    "만료된 토큰입니다."
            ));
        }
    }

    private void sendErrorMessage(HttpServletResponse response, JwtException jwtException)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        new ErrorResponse(
                                jwtException.getStatus(),
                                jwtException.getErrorCode(),
                                jwtException.getMessage()
                        )
                )
        );
    }
}
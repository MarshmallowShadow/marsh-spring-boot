package com.vitasoft.allconec.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsh.util.jwt.JwtAuthenticationFilter;
import com.marsh.util.jwt.JwtTokenProvider;
import com.marsh.util.security.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    private static final String GUEST = "GUEST";

    private final ObjectMapper objectMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(ObjectMapper objectMapper, JwtTokenProvider jwtTokenProvider) {
        this.objectMapper = objectMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v2/users/**").hasAnyRole(USER, GUEST)
                        .requestMatchers("/v2/organization/**").hasAnyRole(USER)
                        .requestMatchers("/v2/organization").hasAnyRole(USER)
                        .requestMatchers("/v2/shop/connection-old-users").permitAll()
                        .requestMatchers("/v2/shop/**").hasAnyRole(USER, GUEST)
                        .requestMatchers(HttpMethod.PUT, "/v2/boards/**").hasAnyRole(USER, GUEST)
                        .requestMatchers(HttpMethod.POST, "/v2/boards/**").hasAnyRole(USER, GUEST)
                        .requestMatchers(HttpMethod.PATCH, "/v2/boards/**").hasAnyRole(USER, GUEST)
                        .requestMatchers("/v2/**").permitAll()
                )
                .addFilterBefore(
                        new JwtAuthenticationFilter(objectMapper, jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class
                )
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))
                );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/v3/api-docs",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger/**"
        );
    }
}
package com.marsh.util.jwt;

import com.marsh.util.dto.UserInfoDto;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class JwtArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JwtResolver.class) != null &&
                parameter.getParameterType() == UserInfoDto.class;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        var authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        var firstAuthority = authenticationToken.getAuthorities()
                .stream()
                .findFirst()
                .map(authority -> authority.getAuthority())
                .orElseThrow(() -> new IllegalStateException("No authorities found"));

        return new UserInfoDto(
                authenticationToken.getPrincipal().toString(),
                firstAuthority
        );
    }
}
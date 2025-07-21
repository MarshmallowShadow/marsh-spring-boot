package com.marsh.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title(applicationName + " API 명세서")
                        .version("1.0")
                        .description("스프링부트 백엔드 프로젝트"))
                .addSecurityItem( // JWT 인증 기능 구현 시 활용
                        new SecurityRequirement().addList("accessTokenAuth")
                )
                .components( // JWT 인증 기능 구현 시 활용
                        new Components().addSecuritySchemes("accessTokenAuth",
                                new SecurityScheme()
                                        .name("accessToken")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                        )
                );
    }
}

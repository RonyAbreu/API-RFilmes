package com.ronyelison.filmes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI(){
        return new OpenAPI().info(info());
    }

    private Info info() {
        return new Info()
                .title("API de Filmes")
                .version("v1.0")
                .description("API desenvolvida com Java 21 e Spring Boot 3")
                .termsOfService("Terms of Service")
                .license(new License()
                        .name("License")
                        .url("license.com"));
    }
}

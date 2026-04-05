package com.backend.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ecommerceOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce API")
                        .version("v1")
                        .description("REST API documentation for the e-commerce backend.")
                        .contact(new Contact().name("E-Commerce Project Team")));
    }
}

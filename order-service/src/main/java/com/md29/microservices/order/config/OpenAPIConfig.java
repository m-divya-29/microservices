package com.md29.microservices.order.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Customizations and configurations for swagger documentation.
 */
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI orderServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Order service API")
                        .description("This is a REST API for Order Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0"))
                ).externalDocs(new ExternalDocumentation()
                        .description("You can refer to this external documentation for more information!")
                        .url("https://dummy-order-service-documentation.com/docs"));
    }
}
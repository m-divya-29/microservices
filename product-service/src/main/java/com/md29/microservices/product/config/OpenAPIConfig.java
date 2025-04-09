package com.md29.microservices.product.config;

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
    public OpenAPI productServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Product service API")
                        .description("This is a REST API for Product Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0"))
                ).externalDocs(new ExternalDocumentation()
                        .description("You can refer to this external documentation for more information!")
                        .url("https://dummyproduct-service-documentation.com/docs"));
    }
}

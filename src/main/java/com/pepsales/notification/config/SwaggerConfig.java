package com.pepsales.notification.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI notificationAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Notification Service API")
                        .description("APIs for managing notifications")
                        .version("1.0"));
    }
}


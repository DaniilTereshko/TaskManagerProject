package org.taskmanager.task_server.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.taskmanager.base_package.errors.ErrorResponse;
import org.taskmanager.base_package.errors.StructuredErrorResponse;
import org.taskmanager.task_server.json.ErrorResponseSerializer;
import org.taskmanager.task_server.json.StructuredErrorResponseSerializer;

@Configuration
public class JacksonConfig {
    @Bean
    public StructuredErrorResponseSerializer structuredErrorResponseSerializer() {
        return new StructuredErrorResponseSerializer();
    }

    @Bean
    public ErrorResponseSerializer errorResponseSerializer() {
        return new ErrorResponseSerializer();
    }
    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(StructuredErrorResponseSerializer structuredErrorResponseSerializer, ErrorResponseSerializer errorResponseSerializer) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        builder.serializerByType(StructuredErrorResponse.class, structuredErrorResponseSerializer);
        builder.serializerByType(ErrorResponse.class, errorResponseSerializer);
        return builder;
    }
}

package org.taskmanager.user_service.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.taskmanager.user_service.core.errors.ErrorResponse;

import java.io.IOException;

public class ErrorResponseSerializer extends JsonObjectSerializer<ErrorResponse> {
    @Override
    protected void serializeObject(ErrorResponse value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        
        gen.writeStringField("logref", value.getLogref().name().toLowerCase());
        gen.writeStringField("message", value.getMessage());
    }
}

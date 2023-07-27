package org.taskmanager.user_service.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.util.StringUtils;
import org.taskmanager.user_service.core.errors.StructuredErrorResponse;

import java.io.IOException;

public class StructuredErrorResponseSerializer extends JsonObjectSerializer<StructuredErrorResponse> {
    @Override
    protected void serializeObject(StructuredErrorResponse value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStringField("logref", value.getLogref().name().toLowerCase());
        gen.writeArrayFieldStart("errors");
        value.getErrors().forEach((key, value1) -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("field", StringUtils.uncapitalize(key).replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase());
                gen.writeStringField("message", value1);
                gen.writeEndObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        gen.writeEndArray();
    }
}

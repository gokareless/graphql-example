package org.gokareless.examples.graphql;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EncodeStringSerializer extends JsonSerializer<Person> {

    @Override
    public void serialize(Person value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeString(value + "serialized");
        jsonGenerator.writeEndObject();
    }
}

package fr.lernejo.travelsite;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class TemperaturesSerializer extends JsonSerializer<Temperatures> {

    @Override
    public void serialize(Temperatures temperatures, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("date", temperatures.date());
            jsonGenerator.writeNumberField("temperature", temperatures.temperature());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            System.out.println("Cannot Parse Field");
        }
    }
}

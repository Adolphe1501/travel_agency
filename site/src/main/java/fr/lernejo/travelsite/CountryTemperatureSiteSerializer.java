package fr.lernejo.travelsite;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CountryTemperatureSiteSerializer extends JsonSerializer<CountryTemperatureSite> {

    @Override
    public void serialize(CountryTemperatureSite value, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField("country", value.country());
            gen.writeObjectField("temperatures", value.temperatures());
            gen.writeEndObject();
        } catch (IOException e) {
            System.out.println("Cannot Parse Field");
        }

    }
}

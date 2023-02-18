package fr.lernejo.travelsite;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;



public class CountryTemperatureAverageSerializer extends JsonSerializer<CountryTemperatureAverage> {

    @Override
    public void serialize(CountryTemperatureAverage countryTemperatureAverage, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)  {
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("country", countryTemperatureAverage.country());
            jsonGenerator.writeNumberField("temperature", countryTemperatureAverage.averageTemperature());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            System.out.println("Cannot Parse Field");
        }

    }
}

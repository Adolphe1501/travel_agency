package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CountryTemperatureAverageSerializer.class)
public record CountryTemperatureAverage(String country, double averageTemperature) {
    public CountryTemperatureAverage(@JsonProperty("country") String country, @JsonProperty("averageTemperature") double averageTemperature) {
        this.country = country;
        this.averageTemperature = averageTemperature;
    }
}

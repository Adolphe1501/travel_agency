package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CountryTemperatureSite(String country, List<Temperatures> temperatures) {
    public CountryTemperatureSite(@JsonProperty("country") String country, @JsonProperty("temperatures") List<Temperatures> temperatures) {
        this.country = country;
        this.temperatures = temperatures;
    }

}

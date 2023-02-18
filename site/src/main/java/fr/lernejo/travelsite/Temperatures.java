package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = TemperaturesSerializer.class)
public record Temperatures(String date, double temperature) {
    public Temperatures(@JsonProperty("date") String date, @JsonProperty("temperature") double temperature) {
        this.date = date;
        this.temperature = temperature;
    }
}

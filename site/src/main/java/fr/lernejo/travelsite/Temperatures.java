package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Temperatures(String date, double temperature) {
    public Temperatures(@JsonProperty("date") String date, @JsonProperty("temperature") double temperature) {
        this.date = date;
        this.temperature = temperature;
    }
}

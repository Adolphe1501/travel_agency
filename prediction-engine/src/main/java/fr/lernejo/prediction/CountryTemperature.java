package fr.lernejo.prediction;

import java.util.List;

public record CountryTemperature(String country, List<Temperatures> temperatures) {

}

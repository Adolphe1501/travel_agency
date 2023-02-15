package fr.lernejo.prediction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateGeneration {

    private final List<String> generatedDate = new ArrayList<>() ;



    public  DateGeneration(){
        // Obtenir la date d'aujourd'hui
        LocalDate today = LocalDate.now();
        // Obtenir la date d'hier
        LocalDate yesterday = today.minusDays(1);

        // Formater les dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedToday = today.format(formatter);
        String formattedYesterday = yesterday.format(formatter);

        this.generatedDate.add(formattedYesterday);
        this.generatedDate.add(formattedToday);

    }

    public List<String> getGeneratedDate() {
        return generatedDate;
    }
}

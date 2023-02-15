package fr.lernejo.prediction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PredictionController {

    private final TemperatureService temperatureService = new TemperatureService();


    private final ObjectMapper mapper = new ObjectMapper();
    @GetMapping("/api/temperature")
    public ResponseEntity<String> getRecordsCountry(@RequestParam(name = "country") String country) throws JsonProcessingException {
    try {

        double firstTemperature = this.temperatureService.getTemperature(country);
        double secondTemperature = this.temperatureService.getTemperature(country);
        List<String> dates = new DateGeneration().getGeneratedDate();
        List<Temparatures> temparatures = new ArrayList<>();
        temparatures.add(new Temparatures(dates.get(1), firstTemperature));
        temparatures.add(new Temparatures(dates.get(0), secondTemperature));

        return ResponseEntity.ok(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new CountryTemperature(country, temparatures)));
    }catch (UnknownCountryException exception){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new CountryTemperature("",new ArrayList<Temparatures>())));
    }
    }
}

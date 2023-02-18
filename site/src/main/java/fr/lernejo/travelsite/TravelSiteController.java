package fr.lernejo.travelsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TravelSiteController {

    private final List <User> userList = new ArrayList<>();

    @Autowired
    private final PredictionEngineService predictionEngineService;

    public TravelSiteController(PredictionEngineService predictionEngineService) {
        this.predictionEngineService = predictionEngineService;
    }

    @PostMapping("/api/inscription")
    public String registerRecord(@RequestBody User user){
        this.userList.add(user);
        return "User save succesfully";
    }
    @GetMapping("/api/travels")
    public List<CountryTemperatureAverage> getRecordsUser(@RequestParam(name = "userName") String userName) {
        List <CountryTemperatureAverage> listOfSuggestions = new ArrayList<>();
        for (User user: this.userList) {
            if(userName.equals(user.userName())){
                listOfSuggestions = predictionEngineService.filterCountryWithUserExpectations(user);
            }
        }
        return listOfSuggestions;
    }



}

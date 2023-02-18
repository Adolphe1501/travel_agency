package fr.lernejo.travelsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PredictionEngineService {

    private final PredictionEngineClient predictionEngineClient;

    @Autowired
    public PredictionEngineService(PredictionEngineClient predictionEngineClient) {
        this.predictionEngineClient = predictionEngineClient;
    }

    public CountryTemperatureSite getTemperatureForCountry(String country)  {

        Call<CountryTemperatureSite> call = this.predictionEngineClient.getCountryTemperature(country);
        try {
            Response<CountryTemperatureSite>  response = call.execute();
            if (!response.isSuccessful()) {
                if (response.code() != 200)
                    throw new HttpException(response);
            }
            return response.body();
        } catch (Exception e) {
            System.out.println(" Pays inconnus ");

        }
        return null;
    }

    public List<CountryTemperatureSite> getTemperatureForAllCountry()  {

        List<CountryTemperatureSite> countryTemperatureSiteList = new ArrayList<>();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("countries.txt");
        try {
           String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            Stream<String> lines = content.lines();
            for (String country: lines.toList()) {
                CountryTemperatureSite countryTemperatureSite = getTemperatureForCountry(country);
                if (countryTemperatureSite != null)
                    countryTemperatureSiteList.add(countryTemperatureSite);
            }
        } catch (Exception e) {
            System.out.println("Mauvais chemin de fichier");
        }
        return countryTemperatureSiteList;
    }

    public List<CountryTemperatureAverage> filterCountryWithUserExpectations(User user) {

        CountryTemperatureSite userCountryTemperatureSite = this.getTemperatureForCountry(user.userCountry());
        CountryTemperatureAverage userCountryTemperatureAverage = new CountryTemperatureAverage(user.userCountry(), userCountryTemperatureSite.temperatures().get(userCountryTemperatureSite.temperatures().size()-2).temperature()/2 +  userCountryTemperatureSite.temperatures().get(userCountryTemperatureSite.temperatures().size()-1).temperature()/2);
        List<CountryTemperatureSite> countryTemperatureSiteList = this.getTemperatureForAllCountry();
        List<CountryTemperatureAverage> countryTemperatureAverageList = new ArrayList<>();
        for ( CountryTemperatureSite countryTemperature: countryTemperatureSiteList) {
            countryTemperatureAverageList.add(new CountryTemperatureAverage(countryTemperature.country(), countryTemperature.temperatures().get(countryTemperature.temperatures().size()-2).temperature()/countryTemperature.temperatures().size() + countryTemperature.temperatures().get(countryTemperature.temperatures().size()-1).temperature()/countryTemperature.temperatures().size()));
        }
    //    CountryTemperatureAverage userCountryTemperatureAverage = countryTemperatureAverageList.stream().filter(country -> country.country().equals(user.userCountry())).findAny().orElse(new CountryTemperatureAverage(user.userCountry(),1));
        if (user.weatherExpectation().equals("WARMER"))
            return countryTemperatureAverageList.stream().filter(country -> country.averageTemperature() - userCountryTemperatureAverage.averageTemperature() >= user.minimumTemperatureDistance()).collect(Collectors.toList());
        else
            return countryTemperatureAverageList.stream().filter(country -> userCountryTemperatureAverage.averageTemperature() - country.averageTemperature() >= user.minimumTemperatureDistance()).collect(Collectors.toList());

    }
}

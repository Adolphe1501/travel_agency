package fr.lernejo.travelsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Bean
    PredictionEngineClient predictionEngineClient() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:7080/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

        return retrofit.create(PredictionEngineClient.class);
    }
}

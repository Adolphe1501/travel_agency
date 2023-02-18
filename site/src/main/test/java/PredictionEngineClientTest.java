import fr.lernejo.travelsite.CountryTemperatureSite;
import fr.lernejo.travelsite.PredictionEngineClient;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static org.junit.jupiter.api.Assertions.*;

class PredictionEngineClientTest {

    private static final String BASE_URL = "http://localhost:7080/";
    private static final String COUNTRY = "Belgium";

    @Test
    void testGetCountryTemperature() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

        PredictionEngineClient client = retrofit.create(PredictionEngineClient.class);
        Response<CountryTemperatureSite> response = client.getCountryTemperature(COUNTRY).execute();

        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertEquals(COUNTRY, response.body().country());

        System.out.println(response.body());
    }
}

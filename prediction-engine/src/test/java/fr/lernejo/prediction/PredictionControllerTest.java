package fr.lernejo.prediction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PredictionControllerTest {

    @Test
    void predict_non_existing_country(@Autowired MockMvc mockMvc) throws Exception {

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/temperature?country=Belgique"))
            .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
            .andExpect(MockMvcResultMatchers.content().string("{\n" +
                "  \"country\" : \"\",\n" +
                "  \"temperatures\" : [ ]\n" +
                "}"));
    }


    @Test
    void predict_existing_country(@Autowired MockMvc mockMvc) throws Exception {

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/temperature?country=Belgium"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

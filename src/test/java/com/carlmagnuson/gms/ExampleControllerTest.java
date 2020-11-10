package com.carlmagnuson.gms;

import com.carlmagnuson.gms.model.GardenBed;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ExampleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    void index() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }


    @Test
    @WithMockUser
    void get() throws Exception {
        mvc.perform((MockMvcRequestBuilders.get("/gardenBed/1").accept(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"lat\":0.1,\"lon\":0.2,\"planting\":[]}")));
    }

    @Test
    @WithMockUser
    void create() throws Exception {
        GardenBed gb = new GardenBed(92.1, 45.1);

        mvc.perform((MockMvcRequestBuilders.post("/gardenBed").accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(gb))
                .contentType(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":3,\"lat\":92.1,\"lon\":45.1,\"planting\":[]}")));
    }

    @Test
    @WithMockUser
    void replace() throws Exception {
        GardenBed gb = new GardenBed(92.1, 45.1);

        mvc.perform((MockMvcRequestBuilders.put("/gardenBed/1").accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(gb))
                .contentType(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"lat\":92.1,\"lon\":45.1,\"planting\":[]}")));
    }

    @Test
    void notLoggedInRedirect() throws Exception {
        GardenBed gb = new GardenBed(92.1, 45.1);

        mvc.perform((MockMvcRequestBuilders.put("/gardenBed/1").accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(gb))
                .contentType(MediaType.APPLICATION_JSON)))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("location", "http://localhost/login"));
    }
}
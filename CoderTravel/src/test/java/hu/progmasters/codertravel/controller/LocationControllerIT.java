package hu.progmasters.codertravel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.progmasters.codertravel.exceptionhandling.LocationNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class LocationControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private final String URI_PATH = "/api/locations";

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllLocations() {
    }

    @Test
    void test_findAllLocationsWithEmptyList() throws Exception {
        mockMvc.perform(get(URI_PATH))
                .andExpect((status().isOk()))
                .andExpect(content().json("[]"));

    }

    @Test
    void test_findLocationById_ThrowException() throws Exception {
        mockMvc.perform(get(URI_PATH+"/10"))
                .andExpect(status().isBadRequest());
//        assertThrows(() -> LocationNotFoundException.class);
    }

    @Test
    void createLocation() {
    }

    @Test
    void modifyLocation() {
    }
}
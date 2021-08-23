package hu.progmasters.codertravel.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DestinationControllerIT {

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllDestinations() {
    }

    @Test
    void findDestinationById() {
    }

    @Test
    void createDestination() {
    }

    @Test
    void modifyDestination() {
    }

    @Test
    void deleteDestination() {
    }
}
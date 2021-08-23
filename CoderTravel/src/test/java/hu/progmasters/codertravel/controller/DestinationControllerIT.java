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
    void test_FindAllDestinations() {

    }

    @Test
    void test_FindDestinationById() {

    }

    @Test
    void test_CreateDestination() {

    }

    @Test
    void test_ModifyDestination() {

    }

    @Test
    void test_DeleteDestination() {

    }


}
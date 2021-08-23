package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.dto.LocationCreateCommand;
import hu.progmasters.codertravel.dto.LocationInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class LocationControllerIT {


    private static LocationCreateCommand createCommand;
    private static LocationCreateCommand createCommandNotValid;
    private static LocationCreateCommand updateCommand;
    private static LocationInfo locationInfo;
    private static LocationInfo locationInfoAfterUpdate;
    private final String URI_PATH = "/api/locations";

    @Autowired
    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        createData(modelMapper);
    }

    @Test
    void test_FindAllLocations() throws Exception {
        test_CreateLocation();
        webTestClient.get().uri(URI_PATH)
                .exchange().expectStatus().isOk();
        webTestClient.get().uri(URI_PATH)
                .exchange().expectBody().jsonPath(locationInfo.toString());
    }

    @Test
    void test_FindAllLocationsWithEmptyList() throws Exception {
        webTestClient.get().uri(URI_PATH)
                .exchange().expectBody().json("[]");
    }

    @Test
    void test_findLocationById_ThrowException() throws Exception {
        webTestClient.get().uri(URI_PATH + "/10")
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    void test_CreateLocation() throws Exception {
        webTestClient.post().uri(URI_PATH)
                .bodyValue(createCommand).exchange().expectStatus().isCreated();
    }

    @Test
    void test_CreateLocation_WithValidError() throws Exception {
        webTestClient.post().uri(URI_PATH)
                .bodyValue(createCommandNotValid).exchange().expectStatus().isBadRequest();

    }

    @Test
    void test_ModifyLocation() throws Exception {
        test_CreateLocation();
        webTestClient.put().uri(URI_PATH + "/1")
                .bodyValue(updateCommand).exchange().expectStatus().isOk();

        webTestClient.get().uri(URI_PATH + "/1")
                .exchange().expectBody().jsonPath(locationInfoAfterUpdate.toString());
    }

    private void createData(ModelMapper modelMapper) {
        createCommand = new LocationCreateCommand();
        createCommand.setIso("HUN");
        createCommand.setCountry("Hungary");
        createCommand.setCity("Budapest");
        createCommand.setStreetAndNumber("Lenkey u. 7.");

        createCommandNotValid = new LocationCreateCommand();
        createCommandNotValid.setIso(" ");
        createCommandNotValid.setCountry("Hungary");
        createCommandNotValid.setCity("Budapest");
        createCommandNotValid.setStreetAndNumber("Lenkey u. 7.");

        locationInfo = modelMapper.map(createCommand, LocationInfo.class);
        locationInfo.setId(1);

        updateCommand = new LocationCreateCommand();
        updateCommand.setIso("HUN");
        updateCommand.setCountry("Hungary");
        updateCommand.setCity("Debrecen");
        updateCommand.setStreetAndNumber("Móka utca 7.");

        locationInfoAfterUpdate = modelMapper.map(updateCommand, LocationInfo.class);
        locationInfoAfterUpdate.setId(1);
    }
}
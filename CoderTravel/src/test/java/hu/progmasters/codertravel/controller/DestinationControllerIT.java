package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.dto.DestinationCreateCommand;
import hu.progmasters.codertravel.dto.LocationCreateCommand;
import hu.progmasters.codertravel.dto.TravelAgencyCreateCommand;
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
class DestinationControllerIT {

    @Autowired
    WebTestClient webTestClient;

    private final String URI_PATH = "/api/destinations";
    private final String URI_LOCATION_PATH = "/api/locations";
    private final String URI_AGENCY_PATH = "/api/travelagencies";

    private static DestinationCreateCommand createCommand;
    private static DestinationCreateCommand updateCommand;
    private static DestinationCreateCommand createCommandNotValid;
    private static LocationCreateCommand createCommandLocation;
    private static TravelAgencyCreateCommand createCommandAgency;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        createData(modelMapper);
    }

    @Test
    void test_FindAllDestinations_WithEmptyList() {
        webTestClient.get().uri(URI_PATH)
                .exchange().expectBody().json("[]");
    }

    @Test
    void test_FindDestinationById() {
        test_CreateDestination();
        webTestClient.get().uri(URI_PATH+"/1")
                .exchange().expectStatus().isOk();
    }

    @Test
    void test_CreateDestination() {
        webTestClient.post().uri(URI_LOCATION_PATH)
                .bodyValue(createCommandLocation).exchange().expectStatus().isCreated();

        webTestClient.post().uri(URI_AGENCY_PATH)
                .bodyValue(createCommandAgency).exchange().expectStatus().isCreated();

        webTestClient.post().uri(URI_PATH)
                .bodyValue(createCommand).exchange().expectStatus().isCreated();

    }

    @Test
    void test_CreateDestination_NotValidData() {
        webTestClient.post().uri(URI_LOCATION_PATH)
                .bodyValue(createCommandLocation).exchange().expectStatus().isCreated();

        webTestClient.post().uri(URI_AGENCY_PATH)
                .bodyValue(createCommandAgency).exchange().expectStatus().isCreated();

        webTestClient.post().uri(URI_PATH)
                .bodyValue(createCommandNotValid).exchange().expectStatus().isBadRequest();
    }

    @Test
    void test_ModifyDestination() {
        test_CreateDestination();

        webTestClient.put().uri(URI_PATH+"/1")
                .bodyValue(updateCommand).exchange().expectStatus().isOk();
    }

    @Test
    void test_DeleteDestination() {
        test_CreateDestination();

        webTestClient.delete().uri(URI_PATH+"/1")
                .exchange().expectStatus().isOk();
    }

    private void createData(ModelMapper modelMapper) {
        createCommand = new DestinationCreateCommand();
        createCommand.setTitle("Debrecen de szép vagy!");
        createCommand.setPricePerDay(4500);
        createCommand.setDescription("Utazz Debrecenbe a virágkarneválkor!");
        createCommand.setAgencyId(1);
        createCommand.setLocationId(1);

        updateCommand = new DestinationCreateCommand();
        updateCommand.setTitle("Debrecen de szép vagy!");
        updateCommand.setPricePerDay(3500);
        updateCommand.setDescription("Utazz Debrecenbe a virágkarneválkor!");
        updateCommand.setAgencyId(1);
        updateCommand.setLocationId(1);

        createCommandNotValid = new DestinationCreateCommand();
        createCommandNotValid.setTitle(" ");
        createCommandNotValid.setPricePerDay(-4500);
        createCommandNotValid.setDescription("Utazz Debrecenbe a virágkarneválkor!");
        createCommandNotValid.setAgencyId(1);
        createCommandNotValid.setLocationId(1);

        createCommandLocation = new LocationCreateCommand();
        createCommandLocation.setIso("HUN");
        createCommandLocation.setCountry("Hungary");
        createCommandLocation.setCity("Budapest");
        createCommandLocation.setStreetAndNumber("Lenkey u. 7.");

        createCommandAgency = new TravelAgencyCreateCommand();
        createCommandAgency.setLocationId(1);
        createCommandAgency.setName("Travel Agency");

    }
}
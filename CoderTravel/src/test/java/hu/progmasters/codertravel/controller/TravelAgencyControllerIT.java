package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TravelAgencyControllerIT {

    @Autowired
    WebTestClient webTestClient;

    private final String URI_PATH = "/api/travelagencies";
    private final String URI_LOCATION_PATH = "/api/locations";

    private static TravelAgencyCreateCommand createCommand;
    private static TravelAgencyCreateCommand updateCommand;
    private static TravelAgencyInfo travelAgency;
    private static TravelAgencyInfo travelAgencyAfterUpdate;
    private static LocationInfo locationInfo;
    private static LocationCreateCommand createCommandLocation;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        createData(modelMapper);
    }

    @Test
    void test_FindTravelAgencyById() {
        test_CreateTravelAgency();

        webTestClient.get().uri(URI_PATH + "/1")
                .exchange().expectStatus().isOk();

        webTestClient.get().uri(URI_PATH + "/1")
                .exchange().expectBody().jsonPath(travelAgency.toString());
    }

    @Test
    void test_FindTravelAgencyById_BadRequest() {
        webTestClient.get().uri(URI_PATH + "/10")
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    void test_CreateTravelAgency() {
        webTestClient.post().uri(URI_LOCATION_PATH)
                .bodyValue(createCommandLocation)
                .exchange().expectStatus().isCreated();

        webTestClient.post().uri(URI_PATH)
                .bodyValue(createCommand).exchange().expectStatus().isCreated();
    }

    @Test
    void test_ModifyTravelAgency() {
        test_CreateTravelAgency();

        webTestClient.put().uri(URI_PATH + "/1")
                .bodyValue(updateCommand).exchange().expectStatus().isOk();

        webTestClient.put().uri(URI_PATH + "/1")
                .exchange().expectBody().jsonPath(travelAgencyAfterUpdate.toString());
    }

    private void createData(ModelMapper modelMapper) {
        createCommand = new TravelAgencyCreateCommand();
        createCommand.setLocationId(1);
        createCommand.setName("Travel Agency");

        updateCommand = new TravelAgencyCreateCommand();
        updateCommand.setLocationId(1);
        updateCommand.setName("Otp Travel");

        DestinationInfo destinationInfo = new DestinationInfo();
        destinationInfo.setId(1);
        destinationInfo.setTitle("Debrecen de szép vagy!");
        destinationInfo.setPricePerDay(4500);
        destinationInfo.setDescription("Utazz Debrecenbe a virágkarneválkor!");

        travelAgency = modelMapper.map(createCommand, TravelAgencyInfo.class);
        travelAgency.setId(1);
        travelAgency.setDestinationInfos(List.of(destinationInfo));

        travelAgencyAfterUpdate = modelMapper.map(updateCommand, TravelAgencyInfo.class);
        travelAgencyAfterUpdate.setId(1);
        travelAgencyAfterUpdate.setDestinationInfos(List.of(destinationInfo));


        createCommandLocation = new LocationCreateCommand();
        createCommandLocation.setIso("HUN");
        createCommandLocation.setCountry("Hungary");
        createCommandLocation.setCity("Budapest");
        createCommandLocation.setStreetAndNumber("Lenkey utca");

        locationInfo = new LocationInfo();
        locationInfo.setId(1);
        locationInfo.setIso("HUN");
        locationInfo.setCountry("Hungary");
        locationInfo.setCity("Budapest");
        locationInfo.setStreetAndNumber("Lenkey utca");


    }

}
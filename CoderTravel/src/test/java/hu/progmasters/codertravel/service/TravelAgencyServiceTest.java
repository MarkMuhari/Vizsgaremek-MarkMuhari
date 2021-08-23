package hu.progmasters.codertravel.service;

import hu.progmasters.codertravel.domain.Location;
import hu.progmasters.codertravel.domain.TravelAgency;
import hu.progmasters.codertravel.dto.LocationCreateCommand;
import hu.progmasters.codertravel.dto.LocationInfo;
import hu.progmasters.codertravel.dto.TravelAgencyCreateCommand;
import hu.progmasters.codertravel.dto.TravelAgencyInfo;
import hu.progmasters.codertravel.exceptionhandling.TravelAgencyNotFoundException;
import hu.progmasters.codertravel.repository.LocationRepository;
import hu.progmasters.codertravel.repository.TravelAgencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelAgencyServiceTest {

    TravelAgencyRepository agencyRepository = mock(TravelAgencyRepository.class);

    ModelMapper modelMapper = new ModelMapper();
    LocationRepository locationRepository = mock(LocationRepository.class);
    LocationService locationService = mock(LocationService.class);
    @MockBean
    TravelAgencyService agencyService = new TravelAgencyService(agencyRepository, locationRepository, modelMapper);


    private static TravelAgency agency;
    private static TravelAgency agencyAfterSave;
    private static TravelAgencyInfo travelAgencyInfo;
    private static TravelAgencyCreateCommand createCommand;
    private static LocationInfo locationInfo;
    private static Location locationSaved;
    private static Location locationAfterSaved;
    private static LocationCreateCommand locationCreateCommand;
    private static TravelAgencyCreateCommand travelAgencyCreateCommand;

    @BeforeEach
    void setUp() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        creatData(modelMapper);
    }

    @Test
    void findTravelAgencyById() {

    }

    @Test
    void test_FindTravelAgencyById_ExceptionLocationNotFound() {
        when(agencyRepository.findById(10)).thenReturn(Optional.empty());
        assertThrows(TravelAgencyNotFoundException.class, () -> agencyService.findTravelAgencyById(10));

    }

    @Test
    void saveTravelAgency() {
//        when(agencyRepository.save(agency)).thenReturn(agencyAfterSave);
        when(locationRepository.save(locationSaved)).thenReturn(locationAfterSaved);
        assertEquals(1, locationAfterSaved.getId());
        Location Location = locationRepository.save(locationSaved);
        when(locationRepository.save(locationSaved)).thenReturn(locationAfterSaved);
        LocationInfo saveLocationInfo = locationService.saveLocation(locationCreateCommand);
        TravelAgencyInfo saved = new TravelAgencyInfo();
        saved.setLocationInfo(saveLocationInfo);
        saved = agencyService.saveTravelAgency(travelAgencyCreateCommand);

    }

    @Test
    void updateTravelAgency() {
    }


    private void creatData(ModelMapper modelMapper) {

        locationCreateCommand = new LocationCreateCommand();
        locationCreateCommand.setIso("HUN");
        locationCreateCommand.setCountry("Hungary");
        locationCreateCommand.setCity("Budapest");
        locationCreateCommand.setStreetAndNumber("Lenkey utca");


        locationInfo = modelMapper.map(locationCreateCommand, LocationInfo.class);
        locationInfo.setId(1);


        createCommand = new TravelAgencyCreateCommand();
        createCommand.setName("Coder Travel");
        createCommand.setLocationId(1);

        agency = modelMapper.map(createCommand, TravelAgency.class);
        agency.setId(1);
        agency.setLocation(locationSaved);
        agency.setDestinations(List.of());

        agencyAfterSave = modelMapper.map(createCommand, TravelAgency.class);
        agencyAfterSave.setId(1);
        agencyAfterSave.setLocation(locationSaved);
        agencyAfterSave.setDestinations(List.of());

        travelAgencyInfo = modelMapper.map(createCommand, TravelAgencyInfo.class);
        travelAgencyInfo.setLocationInfo(locationInfo);
        travelAgencyInfo.setId(1);

        locationSaved = modelMapper.map(locationCreateCommand, Location.class);
        locationSaved.setId(1);

        locationAfterSaved = modelMapper.map(locationCreateCommand, Location.class);
        locationAfterSaved.setId(1);


        travelAgencyCreateCommand = new TravelAgencyCreateCommand();
        travelAgencyCreateCommand.setName("otp Travel");
        travelAgencyCreateCommand.setLocationId(1);

        locationService.saveLocation(locationCreateCommand);
        locationRepository.save(locationSaved);
    }
}
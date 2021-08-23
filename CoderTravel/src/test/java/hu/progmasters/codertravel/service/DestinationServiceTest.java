package hu.progmasters.codertravel.service;

import hu.progmasters.codertravel.domain.Destination;
import hu.progmasters.codertravel.domain.Location;
import hu.progmasters.codertravel.dto.*;
import hu.progmasters.codertravel.exceptionhandling.DestinationNotFoundException;
import hu.progmasters.codertravel.repository.DestinationRepository;
import hu.progmasters.codertravel.repository.LocationRepository;
import hu.progmasters.codertravel.repository.TravelAgencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DestinationServiceTest {

    DestinationRepository destinationRepository = mock(DestinationRepository.class);
    LocationRepository locationRepository = mock(LocationRepository.class);
    TravelAgencyRepository agencyRepository = mock(TravelAgencyRepository.class);
    @Autowired
    ModelMapper modelMapper = new ModelMapper();
    DestinationService destinationService = new DestinationService(destinationRepository, locationRepository,
            agencyRepository, modelMapper);

    private static Destination destination;
    private static Destination destinationAfterSave;
    private static DestinationInfo destinationInfo;
    private static DestinationCreateCommand destinationCreateCommand;
    private static LocationDestinationInfo locationDestinationInfo;
    private static Location location;
    private static Location locationAfterSave;
    private static LocationInfo locationInfo;
    private static LocationCreateCommand locationCreateCommand;
    private static TravelAgencyDestinationInfo agencyDestinationInfo;

    @BeforeEach
    void setUp() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        createData(modelMapper);
    }

    @Test
    void test_FindAllDestinations_WithEmptyList() {
        when(destinationRepository.findAll()).thenReturn(List.of());

        List<DestinationInfo> destinationInfos = destinationService.findAllDestinations();
        assertEquals(List.of(), destinationInfos);
        assertThat(destinationService.findAllDestinations()).hasSize(0);

        verify(destinationRepository, times(2)).findAll();
        verifyNoMoreInteractions(destinationRepository);
    }

    @Test
    void test_FindDestinationById_WithError() {
        when(destinationRepository.findById(10)).thenReturn(Optional.empty());
        assertThrows(DestinationNotFoundException.class, () -> destinationService.findDestinationById(10));

        verify(destinationRepository, times(1)).findById(10);
        verifyNoMoreInteractions(destinationRepository);
    }

    @Test
    void saveDestination() {
        when(destinationRepository.save(destination)).thenReturn(destinationAfterSave);
        when(locationRepository.save(location)).thenReturn(locationAfterSave);
        locationRepository.save(location);
        LocationInfo savedLocation = modelMapper.map(locationRepository.save(location), LocationInfo.class);
        savedLocation.setId(1);
        DestinationInfo saved = destinationService.saveDestination(destinationCreateCommand);

    }
//
//    @Test
//    void updateDestination() {
//    }
//
//    @Test
//    void removeDestination() {
//
//    }

    private void createData(ModelMapper modelMapper) {

        destinationCreateCommand = new DestinationCreateCommand();
        destinationCreateCommand.setTitle("Péccel");
        destinationCreateCommand.setPricePerDay(3999);
        destinationCreateCommand.setDescription("Péccel el éccel");
        destinationCreateCommand.setAgencyId(1);
        destinationCreateCommand.setLocationId(1);

        destination = modelMapper.map(destinationCreateCommand, Destination.class);
        destination.setLocation(location);
        destination.setId(1);

        destinationAfterSave = modelMapper.map(destinationCreateCommand, Destination.class);
        destinationAfterSave.setId(1);

        locationDestinationInfo = new LocationDestinationInfo();
        locationDestinationInfo.setCountry("Hungary");
        locationDestinationInfo.setCity("Bp");
        locationDestinationInfo.setStreetAndNumber("Lenkey 7.");


        locationCreateCommand = new LocationCreateCommand();
        locationCreateCommand.setIso("Hun");
        locationCreateCommand.setCountry("Hungary");
        locationCreateCommand.setCity("Bp");
        locationCreateCommand.setStreetAndNumber("Lenkey 7.");

        location = modelMapper.map(locationCreateCommand, Location.class);
        location.setId(1);

        locationAfterSave = modelMapper.map(locationCreateCommand, Location.class);
        locationAfterSave.setId(1);

        locationInfo = modelMapper.map(locationCreateCommand, LocationInfo.class);
        locationInfo.setId(1);

        agencyDestinationInfo = new TravelAgencyDestinationInfo();
        agencyDestinationInfo.setName("otp travel");
        agencyDestinationInfo.setId(1);

        destinationInfo = modelMapper.map(destinationCreateCommand, DestinationInfo.class);
        destinationInfo.setId(1);
        destinationInfo.setAgencyInfo(agencyDestinationInfo);
        destinationInfo.setLocationDestinationInfo(locationDestinationInfo);
    }
}
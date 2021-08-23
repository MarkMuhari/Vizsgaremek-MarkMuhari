package hu.progmasters.codertravel.service;

import hu.progmasters.codertravel.domain.Location;
import hu.progmasters.codertravel.dto.LocationCreateCommand;
import hu.progmasters.codertravel.dto.LocationInfo;
import hu.progmasters.codertravel.exceptionhandling.LocationNotFoundException;
import hu.progmasters.codertravel.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    LocationRepository locationRepository = mock(LocationRepository.class);

    ModelMapper modelMapper = new ModelMapper();
    LocationService locationService = new LocationService(locationRepository, modelMapper);

    private LocationCreateCommand createCommand;
    private LocationCreateCommand updateCommand;
    private Location location;
    private Location locationUpdate;
    private Location locationAfterSave;
    private LocationInfo locationInfo;
    private LocationInfo locationInfoUpdate;
    private LocationCreateCommand updatedLocationCreateCommand;
    private LocationInfo locationAfterUpdate;

    @BeforeEach
    void setUp() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        createData();
    }

    @Test
    void test_findAllLocationsWithEmptyList() {
        when(locationRepository.findAll()).thenReturn(List.of());
        List<LocationInfo> allLocations = locationService.findAllLocations();
        assertEquals(List.of(), allLocations);

        assertThat(locationService.findAllLocations()).hasSize(0);

        verify(locationRepository, times(2)).findAll();
        verifyNoMoreInteractions(locationRepository);
    }

    @Test
    void test_findLocationById() {
        when(locationRepository.save(location)).thenReturn(locationAfterSave);
        assertEquals(1, locationAfterSave.getId());
        when(locationRepository.findAll())
                .thenReturn(List.of(locationAfterSave));

        when(locationRepository.findById(1)).thenReturn(Optional.of(locationAfterSave));
        LocationInfo locationInfoFound = locationService.findLocationById(1);
        assertEquals(locationInfo, locationInfoFound);
        assertThat(locationService.findLocationById(1)).isEqualTo(locationInfo);

        verify(locationRepository, times(2)).findById(1);
        verifyNoMoreInteractions(locationRepository);
    }

    @Test
    void test_findLocationByIdWithException() {
        when(locationRepository.findById(4)).thenReturn(Optional.empty());
        assertThrows(LocationNotFoundException.class, () -> locationService.findLocationById(1));

        verify(locationRepository, times(1)).findById(1);
        verifyNoMoreInteractions(locationRepository);
    }

    @Test
    void test_SaveSingleLocation() {
        when(locationRepository.save(location)).thenReturn(locationAfterSave);
        assertEquals(1, locationAfterSave.getId());
        when(locationRepository.findAll())
                .thenReturn(List.of(locationAfterSave));

        LocationInfo locationSaved = locationService.saveLocation(createCommand);
        assertEquals(locationInfo, locationSaved);

        assertThat(locationService.findAllLocations())
                .hasSize(1);

        verify(locationRepository, times(1)).save(any());
        verify(locationRepository, times(1)).findAll();
        verifyNoMoreInteractions(locationRepository);

    }

    @Test
    void test_UpdateLocation() {

    }



    private void createData() {
        createCommand = createLocationCommand("HUN","Hungary","Budapest","Lenkey utca7.");
        location = modelMapper.map(createCommand, Location.class);

        updatedLocationCreateCommand = locationUpdatedCreateCommand();

        updateCommand = createLocationCommand("HUN", "Hungary", "Debrecen", "Moka utca 8.");
        locationUpdate = modelMapper.map(updateCommand, Location.class);

        locationInfoUpdate = modelMapper.map(updateCommand, LocationInfo.class);
        locationInfoUpdate.setId(1);

        locationAfterSave = modelMapper.map(createCommand, Location.class);
        locationAfterSave.setId(1);

        locationInfo = modelMapper.map(createCommand, LocationInfo.class);
        locationInfo.setId(1);



        locationAfterUpdate = locationAfterUpdate();
    }

    private LocationCreateCommand createLocationCommand(String iso, String country, String city, String street) {
        LocationCreateCommand result = new LocationCreateCommand();
        result.setIso(iso);
        result.setCountry(country);
        result.setCity(city);
        result.setStreetAndNumber(street);
        return result;
    }

    private LocationCreateCommand locationUpdatedCreateCommand() {
        updatedLocationCreateCommand = new LocationCreateCommand();
        updatedLocationCreateCommand.setIso("HUN");
        updatedLocationCreateCommand.setCity("Debrecen");
        updatedLocationCreateCommand.setCountry("Hungary");
        updatedLocationCreateCommand.setStreetAndNumber("Lenkey utca 7.");
        return updatedLocationCreateCommand;
    }

    private LocationInfo locationAfterUpdate() {
        locationAfterUpdate = modelMapper.map(createCommand, LocationInfo.class);
        locationAfterUpdate.setId(1);
        locationAfterUpdate.setIso(updatedLocationCreateCommand.getIso());
        locationAfterUpdate.setCountry(updatedLocationCreateCommand.getCountry());
        locationAfterUpdate.setCity(updatedLocationCreateCommand.getCity());
        locationAfterUpdate.setStreetAndNumber(updatedLocationCreateCommand.getStreetAndNumber());
        return locationAfterUpdate;
    }
}

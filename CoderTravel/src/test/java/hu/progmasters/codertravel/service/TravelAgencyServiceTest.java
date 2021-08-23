package hu.progmasters.codertravel.service;

import hu.progmasters.codertravel.domain.TravelAgency;
import hu.progmasters.codertravel.repository.LocationRepository;
import hu.progmasters.codertravel.repository.TravelAgencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelAgencyServiceTest {

    TravelAgencyRepository agencyRepository = mock(TravelAgencyRepository.class);

    ModelMapper modelMapper = new ModelMapper();
    LocationRepository locationRepository = mock(LocationRepository.class);
    TravelAgencyService agencyService = new TravelAgencyService(agencyRepository, locationRepository, modelMapper);

    private static TravelAgency agency;
    private static TravelAgency agencyAfterSave;

    @BeforeEach
    void setUp() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    void findTravelAgencyById() {
    }

    @Test
    void saveTravelAgency() {
        when(agencyRepository.save(agency)).thenReturn(agencyAfterSave);
    }

    @Test
    void updateTravelAgency() {
    }
}
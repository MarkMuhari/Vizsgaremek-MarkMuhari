package hu.progmasters.codertravel.service;

import hu.progmasters.codertravel.domain.Location;
import hu.progmasters.codertravel.dto.LocationCreateCommand;
import hu.progmasters.codertravel.dto.LocationInfo;
import hu.progmasters.codertravel.exceptionhandling.LocationNotFoundException;
import hu.progmasters.codertravel.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationService {

    private ModelMapper mapper;
    private LocationRepository locationRepository;

    public LocationService(ModelMapper mapper, LocationRepository locationRepository) {
        this.mapper = mapper;
        this.locationRepository = locationRepository;
    }

    public LocationInfo saveLocation(LocationCreateCommand createCommand) {
        Location toSave = mapper.map(createCommand, Location.class);
        Location saved = locationRepository.save(toSave);
        return mapper.map(saved, LocationInfo.class);
    }

    public LocationInfo updateLocation(LocationCreateCommand updateCommand) {
        Location toUpdate = mapper.map(updateCommand, Location.class);
        if (!locationRepository.existsById(toUpdate.getId())) {
            throw new LocationNotFoundException(toUpdate.getId());
        } else {
            Location updated = locationRepository.save(toUpdate);
            return mapper.map(updated, LocationInfo.class);
        }
    }

    public LocationInfo findLocationById(Integer id) {
        Optional<Location> searchedLocation = locationRepository.findById(id);
        if (searchedLocation.isEmpty()) {
            throw new LocationNotFoundException(id);
        } else {
            return mapper.map(searchedLocation.get(), LocationInfo.class);
        }
    }

    public List<LocationInfo> findAllLocations() {
        return locationRepository.findAll().stream()
                .map(location -> mapper.map(location, LocationInfo.class))
                .collect(Collectors.toList());
    }

    public void deleteLocation(Integer id) {
        if (!locationRepository.existsById(id)) {
            throw new LocationNotFoundException(id);
        } else {
            locationRepository.deleteById(id);
        }
    }
}

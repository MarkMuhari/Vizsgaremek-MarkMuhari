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

    public List<LocationInfo> findAllLocations() {
        return locationRepository.findAll().stream()
                .map(location -> mapper.map(location, LocationInfo.class))
                .collect(Collectors.toList());
    }

    public LocationInfo findLocationById(Integer id) {
        Optional<Location> searched = locationRepository.findById(id);
        if (searched.isEmpty()) {
            throw new LocationNotFoundException(id);
        } else {
            return mapper.map(searched.get(), LocationInfo.class);
        }
    }

    public LocationInfo saveLocation(LocationCreateCommand createCommand) {
        Location toSave = mapper.map(createCommand, Location.class);
        Location saved = locationRepository.save(toSave);
        return mapper.map(saved, LocationInfo.class);
    }

    public LocationInfo updateLocation(Integer id, LocationCreateCommand updateCommand) {
        Optional<Location> searched = locationRepository.findById(id);
        if (searched.isEmpty()) {
            throw new LocationNotFoundException(id);
        } else {
            Location toUpdate = mapper.map(updateCommand, Location.class);
            toUpdate.setId(id);
            Location updated = locationRepository.save(toUpdate);
            return mapper.map(updated, LocationInfo.class);
        }
    }
}

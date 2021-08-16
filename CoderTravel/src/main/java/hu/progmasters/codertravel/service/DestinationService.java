package hu.progmasters.codertravel.service;

import hu.progmasters.codertravel.domain.Destination;
import hu.progmasters.codertravel.dto.DestinationCreateCommand;
import hu.progmasters.codertravel.dto.DestinationInfo;
import hu.progmasters.codertravel.dto.LocationInfo;
import hu.progmasters.codertravel.dto.TravelAgencyInfo;
import hu.progmasters.codertravel.exceptionhandling.DestinationNotFoundException;
import hu.progmasters.codertravel.exceptionhandling.LocationNotFoundException;
import hu.progmasters.codertravel.exceptionhandling.TravelAgencyNotFoundException;
import hu.progmasters.codertravel.repository.DestinationRepository;
import hu.progmasters.codertravel.repository.LocationRepository;
import hu.progmasters.codertravel.repository.TravelAgencyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DestinationService {

    private DestinationRepository destinationRepository;
    private LocationRepository locationRepository;
    private TravelAgencyRepository agencyRepository;
    private ModelMapper mapper;

    public DestinationService(DestinationRepository destinationRepository, LocationRepository locationRepository, TravelAgencyRepository agencyRepository, ModelMapper mapper) {
        this.destinationRepository = destinationRepository;
        this.locationRepository = locationRepository;
        this.agencyRepository = agencyRepository;
        this.mapper = mapper;
    }


    public List<DestinationInfo> findAllDestinations() {
        return destinationRepository.findAll().stream()
                .map(destination -> mapper.map(destination, DestinationInfo.class))
                .collect(Collectors.toList());
    }


    public DestinationInfo findDestinationById(Integer id) {
        Optional<Destination> searched = destinationRepository.findById(id);
        if (searched.isEmpty()) {
            throw new DestinationNotFoundException(id);
        } else {
            return mapper.map(searched.get(), DestinationInfo.class);
        }
    }

    public DestinationInfo saveDestination(DestinationCreateCommand createCommand) {
        Destination toSave = new Destination();
        configurationToSave(createCommand, toSave);

        if (locationRepository.existsById(createCommand.getLocationId())) {
            toSave.setLocation(locationRepository.getById(createCommand.getLocationId()));
        } else {
            throw new LocationNotFoundException(createCommand.getLocationId());
        }

        if (agencyRepository.existsById(createCommand.getAgencyId())) {
            toSave.setTravelAgency(agencyRepository.getById(createCommand.getAgencyId()));
        } else {
            throw new TravelAgencyNotFoundException(createCommand.getAgencyId());
        }

        Destination saved = destinationRepository.save(toSave);
        saved.getTravelAgency().getDestinations().add(saved);

        DestinationInfo result = mapper.map(saved, DestinationInfo.class);
        LocationInfo resultLocation = mapper.map(toSave.getLocation(), LocationInfo.class);
        result.setLocationInfo(resultLocation);
        TravelAgencyInfo resultAgency = mapper.map(toSave.getTravelAgency(), TravelAgencyInfo.class);
        resultAgency.setLocationInfo(mapper.map(toSave.getLocation(), LocationInfo.class));
        resultAgency.setDestinationInfos(toSave.getTravelAgency().getDestinations().stream()
                .map(destination -> mapper.map(destination, DestinationInfo.class))
                .collect(Collectors.toList()));
        result.setAgencyInfo(resultAgency);

        return result;
    }


    public DestinationInfo updateDestination(Integer id, DestinationCreateCommand updateCommand) {
        Optional<Destination> searched = destinationRepository.findById(id);

        return getDestinationInfo(id, updateCommand, searched);

    }

    private DestinationInfo getDestinationInfo(Integer id, DestinationCreateCommand updateCommand, Optional<Destination> searched) {
        if (searched.isPresent()) {
            Destination toUpdate = mapper.map(updateCommand, Destination.class);
            toUpdate.setId(id);
            if (!locationRepository.existsById(updateCommand.getLocationId())) {
                throw new LocationNotFoundException(updateCommand.getLocationId());
            } else if (!agencyRepository.existsById(updateCommand.getAgencyId())) {
                throw new TravelAgencyNotFoundException(updateCommand.getAgencyId());
            } else {
                toUpdate.setLocation(locationRepository.getById(updateCommand.getLocationId()));
                toUpdate.setTravelAgency(agencyRepository.getById(updateCommand.getAgencyId()));
                Destination updated = destinationRepository.save(toUpdate);
                return mapper.map(updated, DestinationInfo.class);
            }
        } else {
            throw new DestinationNotFoundException(id);
        }
    }

    public void removeDestination(Integer id) {
        if (!destinationRepository.existsById(id)) {
            throw new DestinationNotFoundException(id);
        }
        destinationRepository.delete(destinationRepository.getById(id));
    }

    private void configurationToSave(DestinationCreateCommand createCommand, Destination toSave) {
        toSave.setTitle(createCommand.getTitle());
        toSave.setDescription(createCommand.getDescription());
        toSave.setPricePerDay(createCommand.getPricePerDay());
        toSave.setLocation(locationRepository.getById(createCommand.getLocationId()));
        toSave.setTravelAgency(agencyRepository.getById(createCommand.getAgencyId()));
    }
}

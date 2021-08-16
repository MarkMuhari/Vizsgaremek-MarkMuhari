package hu.progmasters.codertravel.service;

import hu.progmasters.codertravel.domain.Location;
import hu.progmasters.codertravel.domain.TravelAgency;
import hu.progmasters.codertravel.dto.DestinationInfo;
import hu.progmasters.codertravel.dto.LocationInfo;
import hu.progmasters.codertravel.dto.TravelAgencyCreateCommand;
import hu.progmasters.codertravel.dto.TravelAgencyInfo;
import hu.progmasters.codertravel.exceptionhandling.LocationNotFoundException;
import hu.progmasters.codertravel.exceptionhandling.TravelAgencyNotFoundException;
import hu.progmasters.codertravel.repository.LocationRepository;
import hu.progmasters.codertravel.repository.TravelAgencyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TravelAgencyService {

    private TravelAgencyRepository agencyRepository;
    private LocationRepository locationRepository;
    private ModelMapper mapper;

    @Value("${coderTravel.fixLetterCase}")
    private boolean fixLetterCase;

    public TravelAgencyService(TravelAgencyRepository agencyRepository, LocationRepository locationRepository,
                               ModelMapper mapper) {
        this.agencyRepository = agencyRepository;
        this.locationRepository = locationRepository;
        this.mapper = mapper;
    }

    public TravelAgencyInfo findTravelAgencyById(Integer id) {
        Optional<TravelAgency> searched = agencyRepository.findById(id);
        if (searched.isEmpty()) {
            throw new TravelAgencyNotFoundException(id);
        } else {
            TravelAgencyInfo result = mapper.map(searched.get(), TravelAgencyInfo.class);
            LocationInfo resultLocationInfo = mapper.map(searched.get().getLocation(), LocationInfo.class);
            result.setLocationInfo(resultLocationInfo);
            List<DestinationInfo> resultDestinations = searched.get().getDestinations().stream()
                    .map(destination -> mapper.map(destination, DestinationInfo.class))
                    .collect(Collectors.toList());
            result.setDestinationInfos(resultDestinations);

            return result;
        }
    }

    public TravelAgencyInfo saveTravelAgency(TravelAgencyCreateCommand createCommand) {

        TravelAgency toSave = mapper.map(createCommand, TravelAgency.class);
        Optional<Location> locationById = locationRepository.findById(createCommand.getLocationID());

        if (locationById.isEmpty()) {
            throw new LocationNotFoundException(createCommand.getLocationID());
        } else {
            toSave.setLocation(locationById.get());
            LocationInfo resultInfo = mapper.map(locationById.get(), LocationInfo.class);

            if (fixLetterCase) {
                String name = fixLetterCharacters(toSave.getName());
                toSave.setName(name);
            }

            TravelAgency saved = agencyRepository.save(toSave);
            TravelAgencyInfo result = mapper.map(saved, TravelAgencyInfo.class);
            if (saved.getDestinations() != null) {
                List<DestinationInfo> resultDestinations = saved.getDestinations().stream()
                        .map(destination -> mapper.map(destination, DestinationInfo.class))
                        .collect(Collectors.toList());

                result.setDestinationInfos(resultDestinations);
            } else {
                result.setDestinationInfos(List.of());
            }
            result.setLocationInfo(resultInfo);

            return result;
        }
    }


    public TravelAgencyInfo updateTravelAgency(Integer id, TravelAgencyCreateCommand updateCommand) {
        Optional<TravelAgency> searched = agencyRepository.findById(id);
        Optional<Location> locationById = locationRepository.findById(updateCommand.getLocationID());

        if (searched.isEmpty()) {
            throw new TravelAgencyNotFoundException(id);
        } else {
            if (locationById.isEmpty()) {
                throw new LocationNotFoundException(updateCommand.getLocationID());
            } else {

                TravelAgency toUpdate = mapper.map(updateCommand, TravelAgency.class);

                toUpdate.setId(id);
                toUpdate.setLocation(locationById.get());
                TravelAgency updated = agencyRepository.save(toUpdate);

                if (fixLetterCase) {
                    updated.setName(fixLetterCharacters(updated.getName()));
                }

                TravelAgencyInfo result = mapper.map(updated, TravelAgencyInfo.class);
                LocationInfo resultLocationInfo = mapper.map(toUpdate.getLocation(), LocationInfo.class);
                result.setLocationInfo(resultLocationInfo);

                if (updated.getDestinations() != null) {
                    List<DestinationInfo> resultDestinations = updated.getDestinations().stream()
                            .map(destination -> mapper.map(destination, DestinationInfo.class))
                            .collect(Collectors.toList());

                    result.setDestinationInfos(resultDestinations);
                } else {
                    result.setDestinationInfos(List.of());
                }
                return result;
            }
        }
    }

    private String fixLetterCharacters(String travelName) {
        String[] agencyNamesplit = travelName.split(" ");
        String correctedWords = "";

        for (int i = 0; i < agencyNamesplit.length; i++) {
            String newWord = agencyNamesplit[i].substring(0, 1).toUpperCase().concat(agencyNamesplit[i].substring(1).toLowerCase()) + " ";
            correctedWords = correctedWords.concat(newWord);
        }
        correctedWords = correctedWords.trim();
        return correctedWords;
    }
}

package hu.progmasters.codertravel.service;

import hu.progmasters.codertravel.domain.TravelAgency;
import hu.progmasters.codertravel.repository.TravelAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class InitDbService {

    @Autowired
    private TravelAgencyRepository agencyRepository;


    public void initDb() {
        TravelAgency agency = new TravelAgency();
        agency.setName("Coder Travel Agency");
        agencyRepository.save(agency);
    }
}

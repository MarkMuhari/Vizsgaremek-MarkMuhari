package hu.progmasters.codertravel.repository;

import hu.progmasters.codertravel.domain.TravelAgency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelAgencyRepository extends JpaRepository<TravelAgency, Integer> {
}

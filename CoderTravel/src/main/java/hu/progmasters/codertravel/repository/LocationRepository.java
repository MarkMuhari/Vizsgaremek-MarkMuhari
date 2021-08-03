package hu.progmasters.codertravel.repository;

import hu.progmasters.codertravel.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}

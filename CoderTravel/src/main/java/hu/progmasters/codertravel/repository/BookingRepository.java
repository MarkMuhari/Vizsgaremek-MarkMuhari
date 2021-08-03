package hu.progmasters.codertravel.repository;

import hu.progmasters.codertravel.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}

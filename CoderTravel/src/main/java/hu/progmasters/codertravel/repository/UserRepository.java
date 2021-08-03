package hu.progmasters.codertravel.repository;

import hu.progmasters.codertravel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

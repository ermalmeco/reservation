package bus.reservation.system.agency.repositories;

import bus.reservation.system.agency.enitities.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Integer> {
   Bus findByCode(String code);
}
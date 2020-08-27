package bus.reservation.system.agency.repositories;

import bus.reservation.system.agency.enitities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<Agency, Integer> {
}
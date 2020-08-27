package bus.reservation.system.agency.repositories;

import bus.reservation.system.agency.enitities.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Integer> {
    Station findByCode(String st3);
}
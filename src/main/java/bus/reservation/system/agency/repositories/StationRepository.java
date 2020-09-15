package bus.reservation.system.agency.repositories;

import bus.reservation.system.agency.enitities.Station;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Integer> {
    List<Station> findByCode(String st3, Pageable p);
}
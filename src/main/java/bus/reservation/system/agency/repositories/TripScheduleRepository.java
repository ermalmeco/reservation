package bus.reservation.system.agency.repositories;

import bus.reservation.system.agency.enitities.TripSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripScheduleRepository extends JpaRepository<TripSchedule, Integer> {
}
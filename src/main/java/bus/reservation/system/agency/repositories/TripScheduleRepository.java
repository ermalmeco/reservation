package bus.reservation.system.agency.repositories;

import bus.reservation.system.agency.enitities.TripSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface TripScheduleRepository extends JpaRepository<TripSchedule, Integer> {
    List<TripSchedule> findTripSchedulesByAvailableSeatsGreaterThanAndTripDateGreaterThan(int minAvSeats, Date todayDate);
}
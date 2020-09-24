package bus.reservation.system.agency.repositories;

import bus.reservation.system.agency.enitities.Trip;
import bus.reservation.system.dto.model.agency.TripDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findTripsByStartStationAndEndStation(int startStation, int endStation);
}
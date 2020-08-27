package bus.reservation.system.agency.services;

import bus.reservation.system.agency.enitities.Trip;
import bus.reservation.system.agency.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TripService {
    @Autowired
    private TripRepository repository;

    public Trip addTrip(Trip trip){
        return repository.save(trip);
    }

    public String deleteTrip(int tripId){
        repository.deleteById(tripId);
        return "Trip deleted!";
    }

    public Trip updateTrip(Trip trip){
        Trip exTrip = repository.findById(trip.getId()).orElse(null);
        if (exTrip != null){
            exTrip.setFare(trip.getFare());
            exTrip.setAgency(trip.getAgency());
            exTrip.setBus(trip.getBus());
            exTrip.setEndStation(trip.getEndStation());
            exTrip.setStartStation(trip.getStartStation());
            exTrip.setTripTime(trip.getTripTime());
            return exTrip;
        }
        return trip;
    }

    public List<Trip> searchTripBetweenStations(int startStation, int endStation){
        return repository.findAllByStartStationAndEndStation(startStation,endStation);
    }
}

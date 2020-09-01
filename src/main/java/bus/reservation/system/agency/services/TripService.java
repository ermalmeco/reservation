package bus.reservation.system.agency.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Trip;
import bus.reservation.system.agency.repositories.TripRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TripService {
    @Autowired
    private TripRepository repository;

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    public Trip addTrip(Trip trip){
        logger.debug("Service call /addTrip");
        Trip result = repository.save(trip);
        logger.debug("Service result /addTrip: "+result.toString());
        return result;
    }

    public String deleteTrip(int tripId){
        repository.deleteById(tripId);
        return "Trip deleted!";
    }

    public Trip updateTrip(Trip trip){
//        Trip exTrip = repository.findById(trip.getId()).orElse(null);
//        if (exTrip != null){
//            exTrip.setFare(trip.getFare());
//            exTrip.setAgency(trip.getAgency());
//            exTrip.setBus(trip.getBus());
//            exTrip.setEndStation(trip.getEndStation());
//            exTrip.setStartStation(trip.getStartStation());
//            exTrip.setTripTime(trip.getTripTime());
//            return exTrip;
//        }
        return trip;
    }

    public List<Trip> searchTripBetweenStations(int startStation, int endStation){
        logger.debug("Service call /findTripByStations");
        List<Trip> result =  repository.findAllByStartStationAndEndStation(startStation,endStation);
        logger.debug("Service result /findTripByStations: "+result.toString());
        return result;
    }
}

package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.enitities.Trip;
import bus.reservation.system.agency.services.AgencyService;
import bus.reservation.system.agency.services.TripService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TripController {

    @Autowired
    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private TripService service;

    @PostMapping("/addTrip")
    public Trip addTrip(@RequestBody Trip trip){
        logger.debug("Controller call /addTrip");
        Trip result = service.addTrip(trip);
        logger.debug("Controller result /addTrip: "+ result.toString());
        return result;
    }

    @DeleteMapping("/removeTrip/{id}")
    public String removeTrip(@PathVariable int id){
        return service.deleteTrip(id);
    }

    @PutMapping("/updateTrip")
    public Trip updateTrip(@RequestBody Trip trip){
        return service.updateTrip(trip);
    }

    @GetMapping("/findTripByStations/{station1}/{station2}")
    public List<Trip> searchTripBetweenStations(@PathVariable int station1, @PathVariable int station2){
        logger.debug("Controller call /findTripByStations");
        List<Trip> result =  service.searchTripBetweenStations(station1,station2);
        logger.debug("Controller result /findTripByStations: "+ result.toString());
        return result;
    }
}

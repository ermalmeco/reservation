package bus.reservation.system.agency.controllers;

import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.enitities.Trip;
import bus.reservation.system.agency.services.AgencyService;
import bus.reservation.system.agency.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TripController {

    @Autowired
    private TripService service;

    @PostMapping("/addTrip")
    public Trip addTrip(@RequestBody Trip trip){
        return service.addTrip(trip);
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
        return service.searchTripBetweenStations(station1,station2);
    }
}

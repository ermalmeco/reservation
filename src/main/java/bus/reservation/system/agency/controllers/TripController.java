package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.enitities.Trip;
import bus.reservation.system.agency.services.AgencyService;
import bus.reservation.system.agency.services.TripService;
import bus.reservation.system.dto.model.agency.TripDto;
import bus.reservation.system.dto.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TripController {

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private TripService service;

    @PostMapping("/addTrip")
    public Response addTrip(@RequestBody Trip trip){
        logger.debug("Controller call /addTrip");
        TripDto result = service.addTrip(trip);
        logger.debug("Controller result /addTrip: "+ result.toString());
        return Response.ok().setPayload(result);
    }

    @DeleteMapping("/removeTrip/{id}")
    public String removeTrip(@PathVariable int id){
        return service.deleteTrip(id);
    }

    @GetMapping("/findTripByStations/{station1}/{station2}")
    public Response searchTripBetweenStations(@PathVariable int station1, @PathVariable int station2){
        logger.debug("Controller call /findTripByStations");
        List<TripDto> result =  service.searchTripBetweenStations(station1,station2);
        logger.debug("Controller result /findTripByStations: "+ result.toString());
        return Response.ok().setPayload(result);
    }
}

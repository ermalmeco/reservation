package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.services.TripService;
import bus.reservation.system.dto.model.agency.TripDto;
import bus.reservation.system.dto.response.Response;
import bus.reservation.system.forms.TripForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class TripController {

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private TripService service;

    @PostMapping("/addTrip")
    public Response addTrip(@RequestBody @Valid TripForm trip) {
        logger.debug("Controller call /addTrip");
        TripDto result = service.addTrip(trip);
        logger.debug("Controller result /addTrip: "+ result.toString());
        return Response.ok().setPayload(result);
    }

    @GetMapping("/findTripByStations/{station1}/{station2}")
    public Response searchTripBetweenStations(@PathVariable @Valid @Min(value = 1, message = "Station Id cannot be 0") int station1, @PathVariable @Valid @Min(value = 1, message = "Station Id cannot be 0") int station2){
        logger.debug("Controller call /findTripByStations");
        List<TripDto> result =  service.searchTripBetweenStations(station1,station2);
        logger.debug("Controller result /findTripByStations: "+ result.toString());
        return Response.ok().setPayload(result);
    }
}

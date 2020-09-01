package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Station;
import bus.reservation.system.agency.services.StationService;
import bus.reservation.system.agency.services.TripService;
import bus.reservation.system.dto.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {
    @Autowired
    private StationService service;

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @GetMapping("/getStations")
    public Response getAllStops() {
        return Response
                .ok()
                .setPayload(service.listAllStations());
    }
}

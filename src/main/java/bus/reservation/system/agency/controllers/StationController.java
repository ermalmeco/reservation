package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Station;
import bus.reservation.system.agency.services.StationService;
import bus.reservation.system.agency.services.TripService;
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

    @Autowired
    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @GetMapping("/getStations")
    public List<Station> getAllStations(){
        logger.debug("Controller call /getStations");
        List<Station> result = service.listAllStations();
        logger.debug("Result Controller /getStations: "+result);
        return result;
    }
}

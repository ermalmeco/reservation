package bus.reservation.system.agency.controllers;

import bus.reservation.system.agency.enitities.Station;
import bus.reservation.system.agency.services.StationService;
import bus.reservation.system.agency.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {
    @Autowired
    private StationService service;

    @GetMapping("/getStations")
    public List<Station> getAllStations(){
        return service.listAllStations();
    }
}

package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.services.AgencyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgencyController {

    @Autowired
    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private AgencyService service;

    @PostMapping("/addAgency")
    public Agency addAgency(@RequestBody Agency agency) {
        logger.debug("Controller call /addAgency");
        Agency result =  service.addAgency(agency);
        logger.debug("Controller result /addAgency: "+ result.toString());
        return result;
    }

    @DeleteMapping("/removeAgency/{id}")
    public String deleteAgency(@PathVariable int id) {
        return service.deleteAgency(id);
    }

    @PutMapping("/addBusToAgency/{busId}/{agencyId}")
    public String addBusToAgency(@PathVariable int busId, @PathVariable int agencyId) {
        logger.debug("Controller call /addBusToAgency");
        String result =  service.addBussToAgency(busId,agencyId);
        logger.debug("Controller result /addBusToAgency: "+ result);
        return result;
    }

    @PutMapping("/updateAgency")
    public Agency updateAgency(@RequestBody Agency agency) {
        return service.updateAgency(agency);
    }
}

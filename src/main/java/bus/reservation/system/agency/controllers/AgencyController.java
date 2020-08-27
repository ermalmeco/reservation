package bus.reservation.system.agency.controllers;

import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.services.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgencyController {

    @Autowired
    private AgencyService service;

    @PostMapping("/addAgency")
    public Agency addAgency(@RequestBody Agency agency) {
        return service.addAgency(agency);
    }

    @DeleteMapping("/removeAgency/{id}")
    public String deleteAgency(@PathVariable int id) {
        return service.deleteAgency(id);
    }

    @PutMapping("/addBusToAgency/{busId}/{agencyId}")
    public String addBusToAgency(@PathVariable int busId, @PathVariable int agencyId) {
        return service.addBussToAgency(busId,agencyId);
    }

    @PutMapping("/updateAgency")
    public Agency updateAgency(@RequestBody Agency agency) {
        return service.updateAgency(agency);
    }
}

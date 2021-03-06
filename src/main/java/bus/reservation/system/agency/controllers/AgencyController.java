package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.services.AgencyService;
import bus.reservation.system.dto.model.agency.AgencyDto;
import bus.reservation.system.dto.model.agency.BusDto;
import bus.reservation.system.dto.response.Response;
import bus.reservation.system.forms.CreateAgencyForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
@RequestMapping("/")
public class AgencyController {

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private AgencyService service;

    @PostMapping("/addAgency")
    public Response addAgency(@RequestBody @Valid CreateAgencyForm agency) {
        logger.debug("Controller call /addAgency");
        AgencyDto result =  service.addAgency(agency);
        logger.info("Controller result /addAgency: "+ result.toString());
        return Response.ok().setPayload(result);
    }

    @PutMapping("/addBusToAgency/{busCode}/{agencyCode}")
    public Response addBusToAgency(@PathVariable @Valid @NotEmpty(message = "Bus code cannot be empty") String busCode, @PathVariable @Valid @NotEmpty(message = "Agency cannot be empty") String agencyCode) {
        logger.debug("Controller call /addBusToAgency");
        BusDto result =  service.addBussToAgency(busCode,agencyCode);
        logger.info("Controller result /addBusToAgency: "+ result);
        return Response.ok().setPayload(result);
    }

    @GetMapping("/agency/{code}")
    public Response getAgencyDetailsByCode(@PathVariable @Valid @NotEmpty(message = "Agency code cannot be empty") @Size(min = 3, max = 3, message = "Agency Code contains 3 chars") String code){
        logger.debug("Controller call /agency/{id}");
        AgencyDto result =  service.getAgencyDetailsByCode(code);
        logger.info("Controller result /agency/{id}: "+ result.toString());
        return Response.ok().setPayload(result);
    }

    @GetMapping("/agencies")
    public Response getAllAgencies(){
        logger.debug("Controller call /agencies");
        List<AgencyDto> result =  service.getAllAgencies();
        logger.info("Controller result /agencies: "+ result.toString());
        return Response.ok().setPayload(result);
    }
}

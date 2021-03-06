package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Station;
import bus.reservation.system.agency.services.StationService;
import bus.reservation.system.agency.services.TripService;
import bus.reservation.system.dto.response.Response;
import bus.reservation.system.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/")
public class StationController {
    @Autowired
    private StationService service;

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @GetMapping("/getStations/{pageNo}/{pageSize}")
    @Validated
    public Response getAllStops(@PathVariable @Min(value = 0, message = "Page number cannot be less than 0") int pageNo,
                                @PathVariable @Min(value = Constants.PAGINATION_DEFAULT_SIZE, message = "Page size cannot be less than "+Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
        return Response.ok().setPayload(service.listAllStations(pageNo,pageSize));
    }
}

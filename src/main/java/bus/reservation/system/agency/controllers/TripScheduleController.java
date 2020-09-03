package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.services.TripScheduleService;
import bus.reservation.system.dto.model.agency.TripScheduleDto;
import bus.reservation.system.dto.response.Response;
import bus.reservation.system.forms.TripScheduleForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripScheduleController {
    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private TripScheduleService service;

    @PostMapping("/scheduleTrip")
    public Response addSchedule(@RequestBody @Validated TripScheduleForm schedule){
        logger.debug("Controller call /scheduleTrip");
        TripScheduleDto result = service.addSchedule(schedule);
        logger.debug("Controller result /scheduleTrip: "+ result.toString());
        return Response.ok().setPayload(result);
    }
}

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/getSchedules")
    public Response getSchedules(){
        logger.debug("Controller call /getSchedules");
        List<TripScheduleDto> result = service.getAvailableSchedules();
        logger.debug("Controller result /getSchedules: "+ result.toString());
        return Response.ok().setPayload(result);
    }
}

package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.services.BookService;
import bus.reservation.system.dto.model.agency.BookDto;
import bus.reservation.system.dto.response.Response;
import bus.reservation.system.forms.BookForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private BookService service;

    @PostMapping("/bookTrip")
    public Response bookTrip(@RequestBody @Validated BookForm bookForm){
        logger.debug("Controller call /bookTrip");
        BookDto result = service.bookTrip(bookForm);
        logger.debug("Controller result /bookTrip: "+ result.toString());
        return Response.ok().setPayload(result);
    }

    @GetMapping("/getuserbookings/{userId}")
    public Response getuserbookings(@PathVariable @Validated int userId){
        logger.debug("Controller call /getuserbookings");
        List<BookDto> result = service.getuserbookings(userId);
        logger.debug("Controller result /getuserbookings: "+ result.toString());
        return Response.ok().setPayload(result);
    }
}

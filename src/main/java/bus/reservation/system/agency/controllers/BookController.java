package bus.reservation.system.agency.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.services.BookService;
import bus.reservation.system.dto.model.agency.BookDto;
import bus.reservation.system.dto.response.Response;
import bus.reservation.system.forms.BookForm;
import bus.reservation.system.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class BookController {
    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private BookService service;

    @PostMapping("/bookTrip")
    public Response bookTrip(@RequestBody @Valid BookForm bookForm){
        logger.debug("Controller call /bookTrip");
        BookDto result = service.bookTrip(bookForm);
        logger.debug("Controller result /bookTrip: "+ result.toString());
        return Response.ok().setPayload(result);
    }

    @GetMapping("/getuserbookings/{userId}/{pageNo}/{pageSize}")
    public Response getuserbookings(@PathVariable @Validated int userId,
                                    @PathVariable @Min(value = 0, message = "Page number cannot be less than 0") int pageNo,
                                    @PathVariable @Min(value = Constants.PAGINATION_DEFAULT_SIZE, message = "Page size cannot be less than "+Constants.PAGINATION_DEFAULT_SIZE) int pageSize){
        logger.debug("Controller call /getuserbookings");
        List<BookDto> result = service.getuserbookings(userId,pageNo,pageSize);
        logger.debug("Controller result /getuserbookings: "+ result.toString());
        return Response.ok().setPayload(result);
    }
}

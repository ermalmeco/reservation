package bus.reservation.system.user.controllers;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.dto.response.Response;
import bus.reservation.system.user.entities.User;
import bus.reservation.system.user.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private UserService service;

    @GetMapping("/allusers")
    public Response getUsers(){
        return Response.ok().setPayload(service.getUsers());
    }

    @PutMapping("/updateUser")
    public Response updateUser(@RequestBody UserDto user){
        logger.debug("Controller call /updateUser");
        UserDto result = service.updateUser(user);
        logger.info("Controller result /updateUser: "+ result.toString());
        return Response.ok().setPayload(result);
    }
}

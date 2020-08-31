package bus.reservation.system.user.controllers;

import bus.reservation.system.BusReservationSystemApplication;
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
    public List<User> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/user/{Id}")
    public User getUserById(@PathVariable int Id){
        return service.getUserById(Id);
    }

    @GetMapping("/user/{firstName}")
    public User getUserByFirstName(@PathVariable String firstName){
        return service.getUserByFirstName(firstName);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        logger.debug("Controller call /updateUser");
        User result = service.updateUser(user);
        logger.debug("Controller result /updateUser: "+ result.toString());
        return result;
    }

    @PutMapping("/delete/{Id}")
    public String deleteUser(@PathVariable int Id){
        return service.deleteUser(Id);
    }
}

package bus.reservation.system.user.controllers;

import bus.reservation.system.user.entities.User;
import bus.reservation.system.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

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
        return service.updateUser(user);
    }

    @PutMapping("/delete/{Id}")
    public String deleteUser(@PathVariable int Id){
        return service.deleteUser(Id);
    }
}

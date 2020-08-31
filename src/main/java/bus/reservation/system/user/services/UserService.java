package bus.reservation.system.user.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.user.entities.Role;
import bus.reservation.system.user.entities.User;
import bus.reservation.system.user.repositories.RoleRepository;
import bus.reservation.system.user.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import bus.reservation.system.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private HttpSession httpSession;

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User getUserById(int Id){
        return repository.findById(Id).orElse(null);
    }

    public User getUserByFirstName(String firsName){
        return repository.findByFirstName(firsName);
    }

    public String deleteUser(int Id){
        repository.deleteById(Id);
        return "User Deleted!";
    }

    public User updateUser(User user) {
        logger.debug("Service call /updateUser");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User existingUser = repository.findByEmail(userDetails.getUsername());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setMobileNumber(user.getMobileNumber());
            User result = repository.save(existingUser);
            logger.debug("Service result /updateUser: "+result.toString());
            return result;
        }
        logger.debug("Service result /updateUser. Something went wrong. User is not Authenticated!");
        return user;
    }
}
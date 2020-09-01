package bus.reservation.system.user.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.dto.mapper.UserMapper;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.user.entities.Role;
import bus.reservation.system.user.repositories.RoleRepository;
import bus.reservation.system.user.repositories.UserRepository;
import bus.reservation.system.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        bus.reservation.system.user.entities.User user = userRepository.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getEmail(), user.getEncryptedPassword(),
                new ArrayList<>());
    }

    public UserDto save(UserDto userDto) throws Exception {
        logger.debug("Service call /register");
        Role userRole;
        bus.reservation.system.user.entities.User exUser = userRepository.findByEmail(userDto.getEmail());
        if (exUser == null) {
            if (userDto.getRole() == 1) {
                userRole = roleRepository.findByName(Constants.ADMIN_NAME);
            } else {
                userRole = roleRepository.findByName(Constants.USER_NAME);
            }
            bus.reservation.system.user.entities.User user = new bus.reservation.system.user.entities.User();
            user.setEmail(userDto.getEmail());
            user.setEncryptedPassword(bcryptEncoder.encode(userDto.getPassword()));
            user.setRole(userRole.getId());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setMobileNumber(userDto.getMobileNumber());
            UserDto result = UserMapper.toUserDto(userRepository.save(user));
            logger.debug("Service result /register "+result.toString());
            return result;
        }else{
            logger.debug("Service call /register, something went wrong");
            throw new Exception("User exists");
        }
    }

}
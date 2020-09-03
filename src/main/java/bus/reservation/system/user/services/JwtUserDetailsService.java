package bus.reservation.system.user.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.dto.mapper.UserMapper;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.exception.BRSException;
import bus.reservation.system.exception.EntityType;
import bus.reservation.system.exception.ExceptionType;
import bus.reservation.system.user.entities.Role;
import bus.reservation.system.user.entities.UserRoles;
import bus.reservation.system.user.repositories.RoleRepository;
import bus.reservation.system.user.repositories.UserRepository;
import bus.reservation.system.user.repositories.UserRolesRepository;
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
    private UserRolesRepository userRolesRepository;

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

    public UserDto save(UserDto user) throws Exception {
        logger.debug("Service call /register");
        bus.reservation.system.user.entities.User exUser = userRepository.findByEmail(user.getEmail());
        logger.debug("User: "+user.toString());
        if (exUser == null) {
            bus.reservation.system.user.entities.User newUser = new bus.reservation.system.user.entities.User();
            newUser.setMobileNumber(user.getMobileNumber());
            newUser.setEncryptedPassword(bcryptEncoder.encode(user.getPassword()));
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            bus.reservation.system.user.entities.User savedUser = userRepository.save(newUser);

            for (String role: user.getRoleNames()) {
                Role getRole = roleRepository.findByName(role);
                UserRoles newUserRole = new UserRoles();
                newUserRole.setRoleId(getRole.getId());
                newUserRole.setRole(getRole);
                newUserRole.setUser(savedUser);
                newUserRole.setUserId(savedUser.getId());
                savedUser.getUserRoles().add(newUserRole);
                userRolesRepository.save(newUserRole);
            }

            UserDto result = UserMapper.toUserDto(userRepository.save(newUser));
            logger.debug("Service result /register "+result.toString());
            return result;
        }else{
            logger.debug("Service call /register, something went wrong");
            throw BRSException.throwException(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, user.getEmail());
        }
    }

}
package bus.reservation.system.user.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.dto.mapper.UserMapper;
import bus.reservation.system.exception.BRSException;
import bus.reservation.system.user.entities.UserRoles;
import bus.reservation.system.user.repositories.UserRolesRepository;
import org.modelmapper.ModelMapper;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.user.entities.Role;
import bus.reservation.system.user.entities.User;
import bus.reservation.system.user.repositories.RoleRepository;
import bus.reservation.system.user.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bus.reservation.system.exception.EntityType.USER;
import static bus.reservation.system.exception.ExceptionType.*;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRolesRepository userRolesRepository;

    public List<UserDto> getUsers(){
        logger.debug("Service call /getUsers");
        List<UserDto> result = repository.findAll()
                .stream()
                .map(user -> UserMapper.toUserDto(user))
                .collect(Collectors.toList());
        logger.info("Result Service /getUsers: "+result.toString());
        return result;
    }

    public UserDto updateUser(UserDto user) {
        logger.debug("Service call /updateUser");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserDto exUser = findUserByEmail(userDetails.getUsername());
            logger.debug("exUser: "+exUser.toString());
            logger.debug("user: "+user.toString());
            User saveUser = new User();
            saveUser.setId(exUser.getId());
            saveUser.setEmail(exUser.getEmail());
            saveUser.setEncryptedPassword(exUser.getEncryptedPassword());
            //new data
            saveUser.setFirstName(user.getFirstName());
            saveUser.setLastName(user.getLastName());
            saveUser.setMobileNumber(user.getMobileNumber());
            User savedUser = repository.save(saveUser);

            userRolesRepository.deleteUserRolesByUserId(savedUser.getId());
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

            UserDto result = UserMapper.toUserDto(repository.save(savedUser));
            logger.info("Service result /updateUser: "+result.toString());
            return result;
        }
        logger.info("Service result /updateUser. Something went wrong. User is not Authenticated!");
        throw BRSException.throwException(USER,AUTHENTICATE,"");
    }

    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(repository.findByEmail(email));
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw BRSException.throwException(USER, ENTITY_NOT_FOUND, email);
    }
}
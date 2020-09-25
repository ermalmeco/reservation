package bus.reservation.system.user.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.dto.mapper.UserMapper;
import bus.reservation.system.exception.BRSException;
import bus.reservation.system.exception.EntityType;
import bus.reservation.system.exception.ExceptionType;
import bus.reservation.system.forms.UserUpdateForm;
import bus.reservation.system.user.entities.UserRoles;
import bus.reservation.system.user.repositories.UserRolesRepository;
import bus.reservation.system.utils.Constants;
import bus.reservation.system.utils.Utils;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public List<UserDto> getUsers(){
        logger.debug("Service call /getUsers");
        List<UserDto> result = repository.findAll()
                .stream()
                .map(user -> UserMapper.toUserDto(user))
                .collect(Collectors.toList());
        logger.info("Result Service /getUsers: "+result.toString());
        return result;
    }

    public UserDto updateUser(UserUpdateForm user) {
        logger.debug("Service call /updateUser");

        if (user.getEmail() != null && !Utils.validateEmail(user.getEmail())){
            throw BRSException.throwException(EntityType.USER, ExceptionType.EMPTY_EMAIL, user.getEmail());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            logger.info("User authentication completed! User is good to proceed");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserDto exUser = findUserByEmail(userDetails.getUsername());
            logger.debug("Existing User: " + exUser.toString());
            logger.debug("To update user: " + user.toString());

            User saveUser = new User();

            //update the base data for the user
            saveUser.setId(exUser.getId());
            saveUser.setFirstName(user.getFirstName() == null?exUser.getFirstName():user.getFirstName());
            saveUser.setLastName(user.getLastName() == null?exUser.getLastName():user.getLastName());
            saveUser.setEmail(user.getEmail() == null?exUser.getEmail():user.getEmail());
            saveUser.setEncryptedPassword(user.getPassword() == null?exUser.getEncryptedPassword():bcryptEncoder.encode(user.getPassword()));
            saveUser.setMobileNumber(user.getMobileNumber() == null?exUser.getMobileNumber():user.getMobileNumber());
            User savedUser = repository.save(saveUser);

            //update roles for the user
            //if no role has been defined for the user then he will have by default user role
            userRolesRepository.deleteUserRolesByUserId(savedUser.getId());
            if (user.getRoleNames() != null) {
                System.out.println(user.getRoleNames().toString());
                for (String role : user.getRoleNames()) {
                    role = role.toUpperCase();
                    System.out.println(role);
                    UserRoles newUserRole = this.prepareUserRoleByRoleName(role,savedUser);
                    System.out.println(newUserRole.toString());

                    savedUser.getUserRoles().add(newUserRole);
                    userRolesRepository.save(newUserRole);
                }
            }else{
                UserRoles newUserRole = this.prepareUserRoleByRoleName(Constants.USER_NAME,savedUser);
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

    public UserRoles prepareUserRoleByRoleName(String roleName, User savedUser){
        Role getRole = roleRepository.findByName(roleName);
        UserRoles newUserRole = new UserRoles();
        newUserRole.setRoleId(getRole.getId());
        newUserRole.setRole(getRole);
        newUserRole.setUser(savedUser);
        newUserRole.setUserId(savedUser.getId());

        return newUserRole;
    }

    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(repository.findByEmail(email));
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw BRSException.throwException(USER, ENTITY_NOT_FOUND, email);
    }
}
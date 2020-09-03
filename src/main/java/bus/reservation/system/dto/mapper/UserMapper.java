package bus.reservation.system.dto.mapper;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.user.entities.User;
import bus.reservation.system.user.entities.UserRoles;
import bus.reservation.system.user.repositories.RoleRepository;
import bus.reservation.system.user.repositories.UserRolesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserMapper {
    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    public static UserDto toUserDto(User user) {
        logger.debug("user to be Mapped "+ user.toString());
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMobileNumber(user.getMobileNumber());
        return userDto;
    }

}

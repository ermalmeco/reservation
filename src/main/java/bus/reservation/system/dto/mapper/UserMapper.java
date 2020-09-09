package bus.reservation.system.dto.mapper;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.user.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

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

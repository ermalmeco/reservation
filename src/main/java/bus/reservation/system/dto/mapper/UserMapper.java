package bus.reservation.system.dto.mapper;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.user.entities.User;
import bus.reservation.system.user.entities.UserRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMobileNumber(user.getMobileNumber());

        boolean isAdmin = false;
        for (UserRoles role: user.getUserRoles()) {
            if (role.getRoleId() == 1) {
                isAdmin = true;
            }
        }
        userDto.setAdmin(isAdmin);

        return userDto;
    }

}

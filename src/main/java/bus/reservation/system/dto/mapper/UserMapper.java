package bus.reservation.system.dto.mapper;

import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.user.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMobileNumber(user.getMobileNumber());
        userDto.setRole(user.getRole());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

}

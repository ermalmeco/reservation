package bus.reservation.system.dto.model.user;

import bus.reservation.system.user.entities.Role;
import bus.reservation.system.user.entities.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String encryptedPassword;
    private String password;
    private boolean isAdmin;
    private List<UserRoles> userRoles;
    private List<String> roleNames;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", password='" + password + '\'' +
                ", userRoles=" + userRoles +
                ", roleNames=" + roleNames +
                '}';
    }
}

package bus.reservation.system.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateForm {
    @Size(min = 3)
    private String firstName;
    @Size(min = 3)
    private String lastName;
    @Email(message = "Please enter a valid e-mail address")
    private String email;
    private String mobileNumber;
    private String password;
    private List<String> roleNames;
}
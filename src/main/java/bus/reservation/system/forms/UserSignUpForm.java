package bus.reservation.system.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class UserSignUpForm {
    @NotNull
    @Size(min = 3)
    private String firstName;
    @NotNull
    @Size(min = 3)
    private String lastName;
    @NotNull
    @Email(message = "Please enter a valid e-mail address")
    private String email;
    @NotNull
    private String mobileNumber;
    @NotNull
    private String password;
    @NotNull
    private List<String> roleNames;
}
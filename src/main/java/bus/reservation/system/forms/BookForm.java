package bus.reservation.system.forms;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class BookForm {
    @NotNull(message = "The booking owner is not defined")
    private String userEmail;

    @NotNull(message = "Define the trip schedule that you are booking at")
    private int scheduleId;
}

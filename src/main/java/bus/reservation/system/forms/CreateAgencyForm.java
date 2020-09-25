package bus.reservation.system.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAgencyForm {
    @NotNull(message = "Agency name cannot be empty")
    private String name;
    private String details;
}
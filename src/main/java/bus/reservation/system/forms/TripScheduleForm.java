package bus.reservation.system.forms;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class TripScheduleForm {
    @NotNull(message = "Trip Id cannot be empty")
    @Min(value = 1, message = "Trip Id cannot be 0")
    private int tripId;

    @NotNull(message = "Trip date cannot be empty")
    private String tripDate;

    @NotNull(message = "Available seats for the trip cannot be empty")
    private int availableSeats;
}

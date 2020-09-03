package bus.reservation.system.forms;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TripScheduleForm {
    @NotNull
    private int tripId;

    @NotNull
    private String tripDate;

    @NotNull
    private int availableSeats;
}

package bus.reservation.system.forms;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TripForm {
    @NotNull
    private int tripFare;

    @NotNull
    private String tripTime;

    @NotNull
    private String startStation;

    @NotNull
    private String endStation;

    @NotNull
    private String busCode;

    @NotNull
    private String agencyCode;
}

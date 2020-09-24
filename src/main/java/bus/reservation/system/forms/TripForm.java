package bus.reservation.system.forms;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class TripForm {
    @Min(value = 1,message = "Trip price must be greater than 0")
    private int tripFare;

    @NotEmpty(message = "Please set the trip time")
    private String tripTime;

    @NotNull(message = "Please define a start station for your trip")
    private String startStation;

    @NotNull(message = "Please define an end station for your trip")
    private String endStation;

    @NotNull(message = "Please define a bus for your trip")
    private String busCode;

    @NotNull(message = "Please define the agency that is creating this trip")
    private String agencyCode;
}

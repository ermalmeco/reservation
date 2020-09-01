package bus.reservation.system.dto.model.agency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripDto {
    private int id;
    private int fare;
    private String tripTime;
    private String startStationCodeAndName;
    private String endStationCodeAndName;
    private String busCode;
    private String agencyCode;
}
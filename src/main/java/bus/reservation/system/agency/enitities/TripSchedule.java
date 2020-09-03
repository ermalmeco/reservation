package bus.reservation.system.agency.enitities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TripSchedule {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(insertable = false,updatable = false)
    private Integer tripId;

    private String tripDate;

    private int availableSeats;

    private int ticketSold;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripId")
    private Trip tripTripScheduleObj;
}

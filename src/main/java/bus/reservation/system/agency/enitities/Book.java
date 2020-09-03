package bus.reservation.system.agency.enitities;

import bus.reservation.system.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;

    private int seatNumber;

    private String journeyDate;

    @Column(insertable = false,updatable = false)
    private Integer scheduleId;

    @Column(insertable = false,updatable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduleId")
    private TripSchedule tripSchedulesObj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userBookObj;
}
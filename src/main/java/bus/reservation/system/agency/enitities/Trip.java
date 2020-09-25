package bus.reservation.system.agency.enitities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue
    private int id;
    private int fare;
    private String tripTime;
    @Column(insertable = false,updatable = false)
    private int startStation;
    @Column(insertable = false,updatable = false)
    private int endStation;
    @Column(insertable = false,updatable = false)
    private int bus;
    @Column(insertable = false,updatable = false)
    private int agency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startStation")
    private Station station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endStation")
    private Station endStationObj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus")
    private Bus busObj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency")
    private Agency agencyObj;

    @OneToMany(mappedBy = "tripTripScheduleObj", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripSchedule> tripSchedulesObj = new ArrayList<>();
}
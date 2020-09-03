package bus.reservation.system.agency.enitities;

import lombok.*;

import javax.persistence.*;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startStation")
    private Station station;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endStation")
    private Station endStationObj;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus")
    private Bus busObj;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency")
    private Agency agencyObj;
}
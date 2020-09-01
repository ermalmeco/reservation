package bus.reservation.system.agency.enitities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue
    private int id;
    private int fare;
    private String tripTime;
    private int endStation;
    private int bus;
    private int agency;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "startStation", referencedColumnName = "id")
    private Station station;
}
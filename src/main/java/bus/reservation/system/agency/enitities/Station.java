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
public class Station {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String code;
    private String name;
    private String details;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trip = new ArrayList<>();

    @OneToMany(mappedBy = "endStationObj", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trip2 = new ArrayList<>();
}
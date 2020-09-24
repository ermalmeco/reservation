package bus.reservation.system.agency.enitities;

import bus.reservation.system.user.entities.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bus {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String code;
    private int capacity;
    private String name;
    private String details;
    @Column(insertable = false,updatable = false)
    private Integer agency;

    @OneToMany(mappedBy = "busObj", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trip = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency")
    private Agency busAgencyObj;
}
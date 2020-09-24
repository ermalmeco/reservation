package bus.reservation.system.agency.enitities;


import bus.reservation.system.user.entities.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Agency {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String code;
    private String name;
    private String details;
    @Column(insertable = false,updatable = false)
    private Integer owner;

    @OneToMany(mappedBy = "agencyObj", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trip = new ArrayList<>();

    @OneToOne(mappedBy = "busAgencyObj",fetch=FetchType.EAGER)
    private Bus bus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User user;
}
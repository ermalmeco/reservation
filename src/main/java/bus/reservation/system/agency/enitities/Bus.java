package bus.reservation.system.agency.enitities;

import bus.reservation.system.user.entities.Role;
import lombok.*;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "busObj",fetch=FetchType.EAGER)
    private Trip trip;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency")
    private Agency busAgencyObj;
}
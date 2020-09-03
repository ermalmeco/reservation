package bus.reservation.system.agency.enitities;

import lombok.*;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "station",fetch=FetchType.EAGER)
    private Trip trip;

    @OneToOne(mappedBy = "endStationObj",fetch=FetchType.EAGER)
    private Trip trip2;
}
package bus.reservation.system.agency.enitities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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

    @OneToOne(mappedBy = "trip")
    private Trip trip;
}
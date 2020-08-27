package bus.reservation.system.agency.enitities;


import com.fasterxml.jackson.annotation.ObjectIdResolver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Agency {
    @Id
    @GeneratedValue
    private int id;

    private String code;
    private String name;
    private String details;
    private int owner;
}

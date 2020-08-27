package bus.reservation.system.agency.enitities;

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
public class Trip {
    @Id
    @GeneratedValue
    private int id;
    private int fare;
    private int tripTime;
    private int startStation;
    private int endStation;
    private int bus;
    private int agency;
}
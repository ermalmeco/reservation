package bus.reservation.system.user.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String email;
    @Transient
    private String password;
    private String encryptedPassword;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private int role;
}
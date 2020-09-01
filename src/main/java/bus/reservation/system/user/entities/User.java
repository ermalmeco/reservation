package bus.reservation.system.user.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String email;
    @Transient
    private String password;
    @Column(nullable = false)
    private String encryptedPassword;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String mobileNumber;
    @Column(nullable = false)
    private int role;
}
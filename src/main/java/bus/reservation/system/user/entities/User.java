package bus.reservation.system.user.entities;

import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.enitities.Book;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, unique = true)
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRoles> userRoles = new ArrayList<>();

    @OneToOne(mappedBy = "user",fetch=FetchType.EAGER)
    private Agency agency;

    @OneToMany(mappedBy = "userBookObj", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> bookObj = new ArrayList<>();

    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
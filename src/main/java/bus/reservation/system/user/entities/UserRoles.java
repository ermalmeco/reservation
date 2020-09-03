package bus.reservation.system.user.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRoles {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(insertable = false,updatable = false)
    private Integer userId;
    @Column(insertable = false,updatable = false)
    private Integer roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private Role role;

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}

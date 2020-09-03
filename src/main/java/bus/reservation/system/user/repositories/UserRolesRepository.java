package bus.reservation.system.user.repositories;

import bus.reservation.system.user.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {
    List<UserRoles> findUserRolesByUserId(int userId);
    void deleteUserRolesByUserId(int userId);
}
package bus.reservation.system.user.repositories;

import bus.reservation.system.user.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String admin);
    List<Role> findRolesByIdIn(List<Integer> ids);
}
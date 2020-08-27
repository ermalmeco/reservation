package bus.reservation.system.user.repositories;

import bus.reservation.system.user.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String admin);
}
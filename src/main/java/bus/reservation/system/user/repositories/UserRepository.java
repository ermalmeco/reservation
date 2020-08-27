package bus.reservation.system.user.repositories;

import bus.reservation.system.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByFirstName(String firsName);

    User findByEmail(String email);

    User findByEmailAndEncryptedPassword(String email, String encryptedPassword);
}
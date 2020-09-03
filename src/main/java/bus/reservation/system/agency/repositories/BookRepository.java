package bus.reservation.system.agency.repositories;

import bus.reservation.system.agency.enitities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByUserId(int userId);
}
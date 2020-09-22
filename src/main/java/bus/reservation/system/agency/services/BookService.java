package bus.reservation.system.agency.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.*;
import bus.reservation.system.agency.repositories.*;
import bus.reservation.system.dto.mapper.BookMapper;
import bus.reservation.system.dto.model.agency.BookDto;
import bus.reservation.system.exception.BRSException;
import bus.reservation.system.forms.BookForm;
import bus.reservation.system.user.entities.User;
import bus.reservation.system.user.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static bus.reservation.system.exception.EntityType.*;
import static bus.reservation.system.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripScheduleRepository tripScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    public BookDto bookTrip(BookForm bookForm) {
        logger.debug("Service call /bookTrip");
        User exUser = userRepository.findByEmail(bookForm.getUserEmail());
        if (exUser != null) {
            logger.debug("exUser: " + exUser.toString());
            //check schedule
            TripSchedule schedule = tripScheduleRepository.findById(bookForm.getScheduleId()).orElse(null);
            if (schedule != null) {
                //check trip
                Trip trip = tripRepository.findById(schedule.getTripId()).orElse(null);
                if (trip != null) {
                    Book book = new Book();
                    book.setJourneyDate(schedule.getTripDate());
                    book.setScheduleId(bookForm.getScheduleId());
                    book.setUserId(exUser.getId());
                    book.setUserBookObj(exUser);
                    book.setTripSchedulesObj(schedule);
                    int seatNr = schedule.getAvailableSeats() - schedule.getTicketSold();
                    book.setSeatNumber(seatNr);
                    BookDto result = BookMapper.toBookDto(repository.save(book));
                    logger.debug("Service result /bookTrip: " + result.toString());
                    return result;
                }
                logger.debug("Service result /bookTrip. Something went wrong. Trip not found!");
                throw BRSException.throwException(TRIP, ENTITY_NOT_FOUND, "" + schedule.getTripId());
            }
            logger.debug("Service result /bookTrip. Something went wrong. Schedule not found!");
            throw BRSException.throwException(TRIPSCHEDULE, ENTITY_NOT_FOUND, "" + bookForm.getScheduleId());
        }
        logger.debug("Service result /bookTrip. Something went wrong. User not found!");
        throw BRSException.throwException(USER, ENTITY_NOT_FOUND, "" + bookForm.getUserEmail());
    }


    public List<BookDto> getuserbookings(int userId,int pageNo, int pageSize) {
        logger.debug("Service call /getuserbookings");
        Pageable paging = PageRequest.of(pageNo,pageSize);
        List<BookDto> result = repository.findBooksByUserId(userId, paging)
                .stream()
                .map(book -> BookMapper.toBookDto(book))
                .collect(Collectors.toList());
        logger.debug("Result Service /getuserbookings: "+result.toString());
        return result;
    }
}
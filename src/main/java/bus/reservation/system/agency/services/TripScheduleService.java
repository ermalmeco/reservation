package bus.reservation.system.agency.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.*;
import bus.reservation.system.agency.repositories.*;
import bus.reservation.system.dto.mapper.StationMapper;
import bus.reservation.system.dto.mapper.TripMapper;
import bus.reservation.system.dto.mapper.TripScheduleMapper;
import bus.reservation.system.dto.model.agency.StationDto;
import bus.reservation.system.dto.model.agency.TripDto;
import bus.reservation.system.dto.model.agency.TripScheduleDto;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.exception.BRSException;
import bus.reservation.system.forms.TripForm;
import bus.reservation.system.forms.TripScheduleForm;
import bus.reservation.system.user.entities.UserRoles;
import bus.reservation.system.user.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static bus.reservation.system.exception.EntityType.*;
import static bus.reservation.system.exception.ExceptionType.ENTITY_NOT_FOUND;
import static bus.reservation.system.exception.ExceptionType.NOT_ADMIN;

@Service
public class TripScheduleService {
    @Autowired
    private TripRepository repository;

    @Autowired
    private TripScheduleRepository tripScheduleRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    public TripScheduleDto addSchedule(TripScheduleForm scheduleForm) {
        logger.debug("Service call /addSchedule");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto exUser = userService.findUserByEmail(userDetails.getUsername());
        boolean isAdmin = false;
        for (UserRoles role : exUser.getUserRoles()) {
            if (role.getRoleId() == 1) {
                isAdmin = true;
                break;
            }
        }
        if (isAdmin) {
            //check trip
            Trip trip = repository.findById(scheduleForm.getTripId()).orElse(null);
            if (trip != null) {
                TripSchedule schedule = new TripSchedule();
                schedule.setTripId(scheduleForm.getTripId());
                schedule.setTripTripScheduleObj(trip);
                schedule.setTripDate(Date.valueOf(scheduleForm.getTripDate()));
                schedule.setAvailableSeats(scheduleForm.getAvailableSeats());
                schedule.setTicketSold(0);

                TripScheduleDto result = TripScheduleMapper.toScheduleDto(tripScheduleRepository.save(schedule));
                logger.debug("Service result /addSchedule: " + result.toString());
                return result;
            }
            logger.debug("Service result /addSchedule. Something went wrong. Trip not found!");
            throw BRSException.throwException(TRIP, ENTITY_NOT_FOUND, "" + scheduleForm.getTripId());
        }
        logger.debug("Service result /addSchedule. Something went wrong. You are not admin!");
        throw BRSException.throwException(USER, NOT_ADMIN, "");
    }

    public List<TripScheduleDto> getAvailableSchedules() {
        logger.debug("Service call /getSchedules");
        List<TripScheduleDto> result = tripScheduleRepository.findTripSchedulesByAvailableSeatsGreaterThanAndTripDateGreaterThan(0,Date.valueOf(LocalDate.now()))
                .stream()
                .map(schedule -> TripScheduleMapper.toScheduleDto(schedule))
                .collect(Collectors.toList());
        logger.debug("Result Service /getSchedules: "+result.toString());
        return result;
    }

}

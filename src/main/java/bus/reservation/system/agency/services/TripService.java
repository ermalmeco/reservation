package bus.reservation.system.agency.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.enitities.Bus;
import bus.reservation.system.agency.enitities.Station;
import bus.reservation.system.agency.enitities.Trip;
import bus.reservation.system.agency.repositories.AgencyRepository;
import bus.reservation.system.agency.repositories.BusRepository;
import bus.reservation.system.agency.repositories.StationRepository;
import bus.reservation.system.agency.repositories.TripRepository;
import bus.reservation.system.dto.mapper.StationMapper;
import bus.reservation.system.dto.mapper.TripMapper;
import bus.reservation.system.dto.model.agency.StationDto;
import bus.reservation.system.dto.model.agency.TripDto;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.exception.BRSException;
import bus.reservation.system.user.entities.UserRoles;
import bus.reservation.system.user.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static bus.reservation.system.exception.EntityType.*;
import static bus.reservation.system.exception.ExceptionType.ENTITY_NOT_FOUND;
import static bus.reservation.system.exception.ExceptionType.NOT_ADMIN;

@Service
public class TripService {
    @Autowired
    private TripRepository repository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    public TripDto addTrip(Trip trip) {
        logger.debug("Service call /addTrip");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto exUser = userService.findUserByEmail(userDetails.getUsername());
        logger.debug("exUser: "+exUser.toString());
        Boolean isAdmin = false;
        for (UserRoles role: exUser.getUserRoles()) {
            if (role.getRoleId() == 1) {
                isAdmin = true;
                break;
            }
        }
        if (isAdmin) {
            //check start station
            Station startStation = stationRepository.findById(trip.getStartStation()).orElse(null);
            if (startStation != null) {
                //check end station
                Station stopStation = stationRepository.findById(trip.getEndStation()).orElse(null);
                if (stopStation != null) {
                    //check bus station
                    Bus bus = busRepository.findById(trip.getBus()).orElse(null);
                    if (bus != null) {
                        //check agency station
                        Agency agency = agencyRepository.findById(trip.getAgency()).orElse(null);
                        if (agency != null) {
                            trip.setAgencyObj(agency);
                            trip.setBusObj(bus);
                            trip.setEndStationObj(stopStation);
                            trip.setStation(startStation);
                            TripDto result = TripMapper.toTripDto(repository.save(trip));
                            logger.debug("Service result /addTrip: " + result.toString());
                            return result;
                        }
                        logger.debug("Service result /addTrip. Something went wrong. Agency not found!");
                        throw BRSException.throwException(BUS, ENTITY_NOT_FOUND, "" + trip.getAgency());
                    }
                    logger.debug("Service result /addTrip. Something went wrong. Bus not found!");
                    throw BRSException.throwException(BUS, ENTITY_NOT_FOUND, "" + trip.getBus());
                }
                logger.debug("Service result /addTrip. Something went wrong. End station not found!");
                throw BRSException.throwException(STOP, ENTITY_NOT_FOUND, "" + trip.getEndStation());
            }
            logger.debug("Service result /addTrip. Something went wrong. Start station not found!");
            throw BRSException.throwException(STOP, ENTITY_NOT_FOUND, "" + trip.getStartStation());
        }
        logger.debug("Service result /addTrip. Something went wrong. You are not admin!");
        throw BRSException.throwException(USER, NOT_ADMIN, "");
    }

    public String deleteTrip(int tripId){
        repository.deleteById(tripId);
        return "Trip deleted!";
    }

    public List<TripDto> searchTripBetweenStations(int startStation, int endStation){
        logger.debug("Service call /findTripByStations");
        List<TripDto> result = repository.findTripsByStartStationAndEndStation(startStation,endStation)
                .stream()
                .map(trip -> TripMapper.toTripDto(trip))
                .collect(Collectors.toList());
        logger.debug("stations " + result);
        logger.debug("Result Service /findTripByStations: "+result.toString());
        return result;
    }
}

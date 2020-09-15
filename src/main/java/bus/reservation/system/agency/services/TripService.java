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
import bus.reservation.system.dto.mapper.TripMapper;
import bus.reservation.system.dto.model.agency.TripDto;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.exception.BRSException;
import bus.reservation.system.forms.TripForm;
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

    public TripDto addTrip(TripForm trip) {
        logger.debug("Service call /addTrip");
        logger.debug("tripData: "+trip.toString());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto exUser = userService.findUserByEmail(userDetails.getUsername());
        logger.debug("exUser: "+exUser.toString());
        boolean isAdmin = false;
        for (UserRoles role: exUser.getUserRoles()) {
            if (role.getRoleId() == 1) {
                isAdmin = true;
                break;
            }
        }
        if (isAdmin) {
            //check start station
            Pageable paging = PageRequest.of(0,1);
            List<Station> startStation = stationRepository.findByCode(trip.getStartStation(),paging);
            if (startStation.size() != 0) {
                //check end station
                List<Station> stopStation = stationRepository.findByCode(trip.getEndStation(),paging);
                if (stopStation.size() != 0) {
                    //check bus station
                    Bus bus = busRepository.findByCode(trip.getBusCode());
                    if (bus != null) {
                        //check agency station
                        Agency agency = agencyRepository.findByCode(trip.getAgencyCode());
                        if (agency != null) {
                            Trip newTrip = new Trip();
                            newTrip.setAgency(agency.getId());
                            newTrip.setAgencyObj(agency);
                            newTrip.setFare(trip.getTripFare());
                            newTrip.setTripTime(trip.getTripTime());
                            newTrip.setStartStation(startStation.get(0).getId());
                            newTrip.setEndStation(stopStation.get(0).getId());
                            newTrip.setBus(bus.getId());
                            newTrip.setStation(startStation.get(0));
                            newTrip.setEndStationObj(stopStation.get(0));
                            newTrip.setBusObj(bus);
                            TripDto result = TripMapper.toTripDto(repository.save(newTrip));
                            logger.debug("Service result /addTrip: " + result.toString());
                            return result;
                        }
                        logger.debug("Service result /addTrip. Something went wrong. Agency not found!");
                        throw BRSException.throwException(BUS, ENTITY_NOT_FOUND, "" + trip.getAgencyCode());
                    }
                    logger.debug("Service result /addTrip. Something went wrong. Bus not found!");
                    throw BRSException.throwException(BUS, ENTITY_NOT_FOUND, "" + trip.getBusCode());
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

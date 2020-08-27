package bus.reservation.system.agency.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Station;
import bus.reservation.system.agency.repositories.StationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationRepository repository;

    @Autowired
    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    public List<Station> listAllStations() {
        logger.debug("Service call /getStations");
        List<Station> result = repository.findAll();
        logger.debug("Result Service /getStations: "+result);
        return result;
    }
}

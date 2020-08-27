package bus.reservation.system.agency.services;

import bus.reservation.system.agency.enitities.Station;
import bus.reservation.system.agency.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationRepository repository;

    public List<Station> listAllStations() {
        return repository.findAll();
    }
}

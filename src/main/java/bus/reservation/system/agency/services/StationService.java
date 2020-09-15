package bus.reservation.system.agency.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.repositories.StationRepository;
import bus.reservation.system.dto.mapper.StationMapper;
import bus.reservation.system.dto.model.agency.StationDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationService {
    @Autowired
    private StationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    public List<StationDto> listAllStations(int pageNo, int pageSize) {
        logger.debug("Service call /getStations");
        Pageable paging = PageRequest.of(pageNo,pageSize);
        List<StationDto> result = repository.findAll(paging)
                .stream()
                .map(station -> StationMapper.toStationDto(station))
                .collect(Collectors.toList());
        logger.debug("Result Service /getStations: "+result.toString());
        return result;
    }
}

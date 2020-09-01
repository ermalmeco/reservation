package bus.reservation.system.dto.mapper;

import bus.reservation.system.agency.enitities.Station;
import bus.reservation.system.dto.model.agency.StationDto;
import org.springframework.stereotype.Component;

@Component
public class StationMapper {
    public static StationDto toStationDto(Station station) {
        StationDto stationDto = new StationDto();
        stationDto.setCode(station.getCode());
        stationDto.setDetail(station.getDetails());
        stationDto.setName(station.getName());

        return stationDto;
    }
}

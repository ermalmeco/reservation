package bus.reservation.system.dto.mapper;

import bus.reservation.system.agency.enitities.Bus;
import bus.reservation.system.dto.model.agency.BusDto;

public class BusMapper {
    public static BusDto toBusDto(Bus bus) {
        BusDto busDto = new BusDto();
        busDto.setCode(bus.getCode());
        busDto.setAgencyCode(bus.getBusAgencyObj().getCode());
        busDto.setName(bus.getName());
        busDto.setCapacity(bus.getCapacity());
        busDto.setDetails(bus.getDetails());

        return busDto;
    }
}
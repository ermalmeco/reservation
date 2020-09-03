package bus.reservation.system.dto.mapper;


import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.dto.model.agency.AgencyDto;

public class AgencyMapper {
    public static AgencyDto toAgencyDto(Agency agency) {
        AgencyDto agencyDto = new AgencyDto();
        agencyDto.setCode(agency.getCode());
        agencyDto.setDetails(agency.getDetails());
        agencyDto.setName(agency.getName());
        agencyDto.setOwner(agency.getUser().getEmail());

        return agencyDto;
    }
}
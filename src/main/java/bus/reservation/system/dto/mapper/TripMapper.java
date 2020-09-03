package bus.reservation.system.dto.mapper;


import bus.reservation.system.agency.enitities.Trip;
import bus.reservation.system.dto.model.agency.TripDto;

public class TripMapper {
    public static TripDto toTripDto(Trip trip) {
        TripDto tripDto = new TripDto();
        tripDto.setId(trip.getId());
        tripDto.setFare(trip.getFare());
        tripDto.setTripTime(trip.getTripTime());
        tripDto.setEndStationCodeAndName(trip.getEndStationObj().getCode()+" - "+trip.getEndStationObj().getName());
        tripDto.setStartStationCodeAndName(trip.getStation().getCode()+" - "+trip.getStation().getName());
        tripDto.setAgencyCode(trip.getAgencyObj().getCode());
        tripDto.setBusCode(trip.getBusObj().getCode());

        return tripDto;
    }
}

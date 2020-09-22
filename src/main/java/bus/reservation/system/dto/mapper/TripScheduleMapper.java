package bus.reservation.system.dto.mapper;

import bus.reservation.system.agency.enitities.TripSchedule;
import bus.reservation.system.dto.model.agency.TripScheduleDto;

public class TripScheduleMapper {
    public static TripScheduleDto toScheduleDto(TripSchedule schedule) {
        TripScheduleDto scheduleDto = new TripScheduleDto();
        scheduleDto.setTripDate(schedule.getTripDate());
        scheduleDto.setAvailableSeats(schedule.getAvailableSeats());
        scheduleDto.setTicketSold(schedule.getTicketSold());
        scheduleDto.setTripInfo("Agency: "+schedule.getTripTripScheduleObj().getAgencyObj().getName()+
                ", Start station: "+schedule.getTripTripScheduleObj().getStation().getName()+
                ", End station: "+ schedule.getTripTripScheduleObj().getEndStationObj().getName()+
                ", Bus: "+schedule.getTripTripScheduleObj().getBusObj().getName()+
                ", Time: "+schedule.getTripTripScheduleObj().getTripTime()+
                ", Fare: "+schedule.getTripTripScheduleObj().getFare());
        return scheduleDto;
    }
}
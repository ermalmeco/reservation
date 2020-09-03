package bus.reservation.system.dto.mapper;

import bus.reservation.system.agency.enitities.TripSchedule;
import bus.reservation.system.dto.model.agency.TripScheduleDto;

public class TripScheduleMapper {
    public static TripScheduleDto toScheduleDto(TripSchedule schedule) {
        TripScheduleDto scheduleDto = new TripScheduleDto();
        scheduleDto.setTripDate(schedule.getTripDate());
        scheduleDto.setAvailableSeats(schedule.getAvailableSeats());
        scheduleDto.setTicketSold(schedule.getTicketSold());
        return scheduleDto;
    }
}
package bus.reservation.system.dto.mapper;

import bus.reservation.system.agency.enitities.Book;
import bus.reservation.system.dto.model.agency.BookDto;

public class BookMapper {
    public static BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setBookId("000"+book.getId());
        bookDto.setSeatNumber(book.getSeatNumber());
        bookDto.setTripDate(book.getJourneyDate());
        String tripInfo = "User "+ book.getUserBookObj().getEmail() + " just booked for a trip on date "+
                book.getJourneyDate() + " and time "+ book.getTripSchedulesObj().getTripTripScheduleObj().getTripTime()+
                ". Trip starts from station "+ book.getTripSchedulesObj().getTripTripScheduleObj().getStation().getName()+
                " to station "+book.getTripSchedulesObj().getTripTripScheduleObj().getEndStationObj().getName()+
                ". The bus that will be used for transportation will be "+book.getTripSchedulesObj().getTripTripScheduleObj().getBusObj().getName()+
                ". The agency will be "+book.getTripSchedulesObj().getTripTripScheduleObj().getAgencyObj().getName()+".";
        bookDto.setTripInfo(tripInfo);
        return bookDto;
    }
}
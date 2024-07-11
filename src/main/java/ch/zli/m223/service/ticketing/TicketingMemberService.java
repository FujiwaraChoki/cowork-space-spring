package ch.zli.m223.service.ticketing;

import ch.zli.m223.controller.ticketing.dto.RoomBookingInputDto;
import ch.zli.m223.model.Booking;
import java.util.List;

public interface TicketingMemberService {
    public Booking createBooking(RoomBookingInputDto roomBookingInputDto);
    public Booking getBooking(Long id);
    public List<Booking> getBookingsByUserId(Long userId);
    public List<Booking> getAllBookings();
}

package ch.zli.m223.service.ticketing.impl;

import ch.zli.m223.controller.ticketing.dto.RoomBookingInputDto;
import ch.zli.m223.exception.RoomAlreadyInUseException;
import ch.zli.m223.exception.RoomNotFoundException;
import ch.zli.m223.model.BookingStatus;
import ch.zli.m223.model.Room;
import ch.zli.m223.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ch.zli.m223.service.ticketing.TicketingMemberService;
import ch.zli.m223.exception.BookingNotFoundException;
import ch.zli.m223.repository.BookingRepository;
import org.springframework.stereotype.Service;
import ch.zli.m223.model.Booking;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketingMemberServiceImpl implements TicketingMemberService {

    @Autowired
    private final BookingRepository bookingRepository;

    @Autowired
    private final RoomRepository roomRepository;

    public TicketingMemberServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Booking createBooking(RoomBookingInputDto roomBookingInputDto) {
        // check if room is already in use
        return getBooking(roomBookingInputDto, roomRepository, bookingRepository);
    }

    static Booking getBooking(RoomBookingInputDto roomBookingInputDto, RoomRepository roomRepository, BookingRepository bookingRepository) {
        Room room = roomRepository.findById(roomBookingInputDto.roomId).orElseThrow(() -> new RoomNotFoundException("Specified Room was not found."));

        if(room.getInUse()) {
            throw new RoomAlreadyInUseException("Specified Room will be in use at the same you wanted to book.");
        } else {
            // Set to in use
            room.setToInUse();
        }

        return bookingRepository.addBooking(
                roomBookingInputDto.user.id,
                roomBookingInputDto.booking_date,
                roomBookingInputDto.duration,
                BookingStatus.PENDING,
                roomBookingInputDto.roomId
        );
    }

    @Override
    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("A Booking Object was not found with the specified ID."));
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.getBookingsByUserId(userId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return new ArrayList<Booking>(bookingRepository.findAll());
    }
}

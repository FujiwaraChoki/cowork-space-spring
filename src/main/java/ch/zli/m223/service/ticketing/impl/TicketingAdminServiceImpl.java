package ch.zli.m223.service.ticketing.impl;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Room;
import ch.zli.m223.model.impl.RoomImpl;
import ch.zli.m223.repository.BookingRepository;
import ch.zli.m223.repository.RoomRepository;
import ch.zli.m223.service.ticketing.TicketingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketingAdminServiceImpl implements TicketingAdminService {

    @Autowired
    private final BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;

    public TicketingAdminServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void suspendBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Room createRoom(String name, boolean inUse) {
        return roomRepository.save(new RoomImpl(name, inUse));
    }
}

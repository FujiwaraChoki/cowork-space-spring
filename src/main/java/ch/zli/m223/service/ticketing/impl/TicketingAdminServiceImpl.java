package ch.zli.m223.service.ticketing.impl;

import ch.zli.m223.model.Booking;
import ch.zli.m223.repository.BookingRepository;
import ch.zli.m223.service.ticketing.TicketingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketingAdminServiceImpl implements TicketingAdminService {

    @Autowired
    private final BookingRepository bookingRepository;

    public TicketingAdminServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void suspendBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}

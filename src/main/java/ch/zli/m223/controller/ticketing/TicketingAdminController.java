package ch.zli.m223.controller.ticketing;

import ch.zli.m223.exception.BookingNotFoundException;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.BookingStatus;
import ch.zli.m223.repository.BookingRepository;
import ch.zli.m223.service.ticketing.TicketingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/ticketing")
@Transactional
public class TicketingAdminController {
    public TicketingAdminController(TicketingAdminService ticketingAdminService, BookingRepository bookingRepository) {
        this.ticketingAdminService = ticketingAdminService;
        this.bookingRepository = bookingRepository;
    }

    @Autowired
    private final TicketingAdminService ticketingAdminService;

    @Autowired
    private final BookingRepository bookingRepository;

    @DeleteMapping("/bookings_a")
    public void deleteBooking(@RequestBody Long id) {
        ticketingAdminService.suspendBooking(id);
    }

    // Change status
    @PutMapping("/booking_status/{bookingId}")
    public void updateBooking(@RequestBody String status, @PathVariable Long bookingId) {
        if(status.equals("ACCEPTED")) {
            Booking booking = bookingRepository.findByBookingId(bookingId);

            booking.setStatus(BookingStatus.ACCEPTED);
        } else if(status.equals("REJECTED")) {
            Booking booking = bookingRepository.findByBookingId(bookingId);

            booking.setStatus(BookingStatus.REJECTED);
        }
    }

    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}

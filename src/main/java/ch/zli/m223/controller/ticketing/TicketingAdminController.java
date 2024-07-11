package ch.zli.m223.controller.ticketing;

import ch.zli.m223.exception.BookingNotFoundException;
import ch.zli.m223.model.Booking;
import ch.zli.m223.service.ticketing.TicketingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/ticketing")
public class TicketingAdminController {
    public TicketingAdminController(TicketingAdminService ticketingAdminService) {
        this.ticketingAdminService = ticketingAdminService;
    }

    @Autowired
    private final TicketingAdminService ticketingAdminService;

    @DeleteMapping("/bookings_a")
    public void deleteBooking(@RequestBody Long id) {
        ticketingAdminService.suspendBooking(id);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}

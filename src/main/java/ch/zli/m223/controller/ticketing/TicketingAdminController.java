package ch.zli.m223.controller.ticketing;

import ch.zli.m223.model.Booking;
import ch.zli.m223.service.ticketing.TicketingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
}

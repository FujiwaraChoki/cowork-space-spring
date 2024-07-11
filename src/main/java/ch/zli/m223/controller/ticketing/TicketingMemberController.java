package ch.zli.m223.controller.ticketing;

import ch.zli.m223.controller.ticketing.dto.RoomBookingInputDto;
import ch.zli.m223.service.ticketing.TicketingMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ch.zli.m223.model.Booking;
import java.util.List;

@RestController
@RequestMapping("/api/v1/member/ticketing")
public class TicketingMemberController {
    // Without autowiring, since we are using RequiredArgsConstructor
    @Autowired
    private final TicketingMemberService ticketingMemberService;

    public TicketingMemberController(TicketingMemberService ticketingMemberService) {
        this.ticketingMemberService = ticketingMemberService;
    }

    //RoomBookingInputDto
    @PostMapping("/bookings")
    public Booking bookTicketing(@RequestBody RoomBookingInputDto roomBookingInput) {
        return ticketingMemberService.createBooking(roomBookingInput);
    }

    @GetMapping("/bookings")
    public List<Booking> getBookings() {
        return ticketingMemberService.getAllBookings();
    }

    @GetMapping("/bookings/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return ticketingMemberService.getBooking(id);
    }

    @GetMapping("/bookings/user/{id}")
    public List<Booking> getBookingsByUserId(@PathVariable Long id) {
        return ticketingMemberService.getBookingsByUserId(id);
    }
}

package ch.zli.m223.controller.ticketing;

import ch.zli.m223.controller.ticketing.dto.RoomBookingInputDto;
import ch.zli.m223.exception.BookingNotFoundException;
import ch.zli.m223.exception.RoomAlreadyInUseException;
import ch.zli.m223.exception.RoomNotFoundException;
import ch.zli.m223.service.ticketing.TicketingMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ch.zli.m223.model.Booking;
import java.util.List;

@RestController
@RequestMapping("/api/v1/member/bookings")
@RequiredArgsConstructor
public class TicketingMemberController {
    @Autowired
    private final TicketingMemberService ticketingMemberService;

    //RoomBookingInputDto
    @PostMapping("")
    public Booking createBooking(@RequestBody RoomBookingInputDto roomBookingInput) {
        return ticketingMemberService.createBooking(roomBookingInput);
    }

    @GetMapping("")
    public List<Booking> getBookings() {
        return ticketingMemberService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return ticketingMemberService.getBooking(id);
    }

    @GetMapping("/user/{id}")
    public List<Booking> getBookingsByUserId(@PathVariable Long id) {
        return ticketingMemberService.getBookingsByUserId(id);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleRoomNotFoundException(RoomNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(RoomAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleRoomAlreadyInUseException(RoomAlreadyInUseException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
}

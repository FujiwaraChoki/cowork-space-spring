package ch.zli.m223.repository;

import ch.zli.m223.exception.BookingNotFoundException;
import ch.zli.m223.model.BookingStatus;
import ch.zli.m223.model.Duration;
import ch.zli.m223.model.impl.BookingImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import ch.zli.m223.model.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.MissingFormatArgumentException;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Use RoomService to find out whether room is in use as of the current moment

    default Booking findByBookingId(Long bookingId) {
        return findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking with specified ID was unfortunately not found."));
    }

    default Booking addBooking(Long userId, LocalDateTime booking_date, Duration duration, BookingStatus status, Long roomId) {
        return save(new BookingImpl(userId, booking_date, duration, status, roomId));
    }

    default void updateBooking(Booking booking) {
        // find
        if(booking.getId() == null) {
            throw new MissingFormatArgumentException("Please provide a valid booking object. The ID is null.");
        }

        if(booking.getRoomId() == null) {
            throw new MissingFormatArgumentException("Please provide a room id, otherwise, we cannot check if the room you have requested is available.");
        }

        save(booking);
    }

    default List<Booking> getBookingsByUserid(Long id) {
        List<Booking> allBookings = findAll();

        for(Booking booking : allBookings) {
            if(booking.getUserId().equals(id)) {
                return allBookings;
            }
        }

        return null;
    }
}

package ch.zli.m223.repository;

import ch.zli.m223.exception.BookingNotFoundException;
import ch.zli.m223.model.BookingStatus;
import ch.zli.m223.model.Duration;
import ch.zli.m223.model.impl.BookingImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import ch.zli.m223.model.Booking;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface BookingRepository extends JpaRepository<BookingImpl, Long> {
    // Use RoomService to find out whether room is in use as of the current moment

    default Booking findByBookingId(Long bookingId) {
        return findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking with specified ID was unfortunately not found."));
    }

    default Booking addBooking(Long userId, LocalDateTime bookingDate, Duration duration, BookingStatus status, Long roomId) {
        return save(new BookingImpl(userId, bookingDate, duration, status, roomId));
    }

    default void updateBooking(Booking booking) {
        if (booking.getId() == null) {
            throw new IllegalArgumentException("Please provide a valid booking object. The ID is null.");
        }

        if (booking.getRoomId() == null) {
            throw new IllegalArgumentException("Please provide a room ID, otherwise, we cannot check if the room you have requested is available.");
        }

        save((BookingImpl) booking);
    }

    default List<Booking> getBookingsByUserId(Long userId) {
        return findAll().stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}

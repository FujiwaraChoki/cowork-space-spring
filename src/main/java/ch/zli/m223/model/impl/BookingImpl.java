package ch.zli.m223.model.impl;

import ch.zli.m223.model.BookingStatus;
import ch.zli.m223.model.Duration;
import ch.zli.m223.model.Booking;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class BookingImpl implements Booking {
    public BookingImpl() {}

    public BookingImpl(Long userId, LocalDateTime bookingDate, Duration duration, BookingStatus status, Long roomId) {
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.duration = duration;
        this.status = status;
        this.roomId = roomId;
    }

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @JoinColumn(nullable = false)
    private Duration duration;

    @JoinColumn(nullable = false)
    private BookingStatus status;

    @JoinColumn(nullable = false)
    private Long roomId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public BookingStatus getBookingStatus() {
        return status;
    }

    @Override
    public Long getRoomId() {
        return roomId;
    }
}

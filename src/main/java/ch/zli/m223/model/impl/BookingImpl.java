package ch.zli.m223.model.impl;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.BookingStatus;
import ch.zli.m223.model.Duration;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

public class BookingImpl implements Booking {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @Column(nullable = false)
    private Duration duration;

    @Column(nullable = false)
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

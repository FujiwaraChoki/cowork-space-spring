package ch.zli.m223.model;

import java.time.LocalDateTime;

public interface Booking {
    public Long getId();
    public Long getUserId();
    public LocalDateTime getBookingDate();
    public Duration getDuration();
    public BookingStatus getBookingStatus();
    public Long getRoomId();
}

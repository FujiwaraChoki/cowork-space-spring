package ch.zli.m223.controller.ticketing.dto;

import ch.zli.m223.model.BookingStatus;
import ch.zli.m223.model.Duration;
import ch.zli.m223.security.dto.JwtTokenDto;

import java.time.LocalDateTime;

public class RoomBookingInputDto {
    public JwtTokenDto token;
    public UserInputDto user;
    public Long roomId;
    public LocalDateTime booking_date;
    public Duration duration;
    public BookingStatus status;
}

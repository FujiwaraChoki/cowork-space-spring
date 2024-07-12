package ch.zli.m223.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException() {}

    public BookingNotFoundException(String message) {
        super(message);
    }
}

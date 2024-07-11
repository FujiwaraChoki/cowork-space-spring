package ch.zli.m223.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException() {}

    public BookingNotFoundException(String message) {
        super(message);
    }
}

package ch.zli.m223.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException() {}

    public RoomNotFoundException(String message) {
        super(message);
    }
}

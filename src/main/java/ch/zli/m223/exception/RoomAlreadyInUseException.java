package ch.zli.m223.exception;

public class RoomAlreadyInUseException extends RuntimeException {
    public RoomAlreadyInUseException() {}

    public RoomAlreadyInUseException(String message) {
        super(message);
    }
}

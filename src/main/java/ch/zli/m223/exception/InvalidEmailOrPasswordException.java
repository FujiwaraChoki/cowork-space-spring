package ch.zli.m223.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No email or password")
public class InvalidEmailOrPasswordException extends RuntimeException {
}

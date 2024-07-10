package ch.zli.m223.service.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Role does not exist")
public class RoleNotFoundException extends RuntimeException {
}
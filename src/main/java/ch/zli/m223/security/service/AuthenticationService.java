package ch.zli.m223.security.service;

import ch.zli.m223.security.dto.JwtTokenDto;
import ch.zli.m223.security.dto.SignInDto;
import ch.zli.m223.security.dto.SignUpDto;

public interface AuthenticationService {
    JwtTokenDto signup(SignUpDto request);

    JwtTokenDto signin(SignInDto request);
}

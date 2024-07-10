package ch.zli.m223.security.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import ch.zli.m223.repository.UserRepository;
import ch.zli.m223.security.dto.JwtTokenDto;
import ch.zli.m223.security.dto.SignInDto;
import ch.zli.m223.security.dto.SignUpDto;
import ch.zli.m223.security.service.AuthenticationService;
import ch.zli.m223.security.service.JwtService;
import ch.zli.m223.service.user.UserService;
import ch.zli.m223.service.user.exception.InvalidEmailOrPasswordException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    @Override
    public JwtTokenDto signup(SignUpDto request) {
        var user = userService.addUser(
            request.firstName, request.lastName, request.email, request.password);

        var jwt = jwtService.generateToken(user);
        return new JwtTokenDto(jwt);
    }

    @Override
    public JwtTokenDto signin(SignInDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email, request.password));
        var user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new InvalidEmailOrPasswordException());
        var jwt = jwtService.generateToken(user);
        return new JwtTokenDto(jwt);
    }
}

package com.atmosware.busraciftlik.music.provider.service.security;

import com.atmosware.busraciftlik.music.provider.entity.User;
import com.atmosware.busraciftlik.music.provider.enums.Role;
import com.atmosware.busraciftlik.music.provider.repository.UserRepository;
import com.atmosware.busraciftlik.music.provider.dto.request.AuthenticationRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.RegisterRequest;
import com.atmosware.busraciftlik.music.provider.dto.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @CacheEvict(value = "user_list",allEntries = true)
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}

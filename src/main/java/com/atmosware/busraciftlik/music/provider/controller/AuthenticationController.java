package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.request.AuthenticationRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.RegisterRequest;
import com.atmosware.busraciftlik.music.provider.dto.AuthenticationResponse;
import com.atmosware.busraciftlik.music.provider.service.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(service.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(service.authenticate(authenticationRequest));
    }
}
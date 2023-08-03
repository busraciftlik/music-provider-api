package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.UserDto;
import com.atmosware.busraciftlik.music.provider.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserServiceImpl service;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/follow")
    @PreAuthorize("hasAuthority('USER')")
    public void addFollowings(Integer following){
        service.follow(following);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/unfollow")
    public void unfollow(Integer followed){
        service.unfollow(followed);
    }
}

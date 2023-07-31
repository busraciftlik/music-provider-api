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
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    private final UserServiceImpl service;

    @GetMapping
    public List<UserDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/follow")
    @PreAuthorize("hasAuthority('USER')")
    public void addFollowings(Integer following){
        service.follow(following);
    }

    @PutMapping("/unfollow")
    public void unfollow(Integer follower, Integer followed){
        service.unfollow(follower,followed);
    }
}

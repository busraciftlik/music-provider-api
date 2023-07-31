package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.UserDto;
import com.atmosware.busraciftlik.music.provider.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserServiceImpl service;

    @GetMapping
    public Set<UserDto> findAll(){
        return service.findAll();
    }

    @PostMapping("/follow")
    public void addFollowings(Integer following){
        service.follow(following);
    }

    @PutMapping("/unfollow")
    public void unfollow(Integer follower, Integer followed){
        service.unfollow(follower,followed);
    }
}

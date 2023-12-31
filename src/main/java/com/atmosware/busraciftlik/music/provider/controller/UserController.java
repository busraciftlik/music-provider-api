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
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class UserController {
    private final UserServiceImpl service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/follow")
    @PreAuthorize("hasRole('USER')")
    public void addFollowings(Integer following){
        service.follow(following);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/unfollow")
    public void unfollow(Integer followed){
        service.unfollow(followed);
    }

    @GetMapping("/followers")
    public List<UserDto> getFollowers(){
       return service.findFollowers();
    }

    @GetMapping("/followings")
    public List<UserDto> getFollowings(){
        return service.findFollowings();
    }
}

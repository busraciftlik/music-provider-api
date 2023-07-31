package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.UserDto;
import com.atmosware.busraciftlik.music.provider.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {
    Set<UserDto> findAll();

    User findById(Integer id);

    void follow(Integer followed);

    void unfollow(Integer follower,Integer followed);

    Set<UserDto> findFollowers(Integer userId);

    Set<UserDto> findFollowings(Integer userId);
}

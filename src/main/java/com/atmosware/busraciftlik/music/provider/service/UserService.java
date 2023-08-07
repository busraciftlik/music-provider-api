package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.UserDto;
import com.atmosware.busraciftlik.music.provider.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> findAll();

    User findById(Integer id);

    void follow(Integer followed);

    void unfollow(Integer followed);

    List<UserDto> findFollowers();

    List<UserDto> findFollowings();
}

package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.UserDto;
import com.atmosware.busraciftlik.music.provider.entity.User;
import com.atmosware.busraciftlik.music.provider.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.mapUserEntity2UserDto;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Set<UserDto> findAll(){
        return mapUserEntity2UserDto(repository.findAll());
    }

    public void follow(Integer followed, Integer follower) {
        User followedUser = repository.findById(followed).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User followerUser = repository.findById(follower).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        followerUser.addFollowing(followedUser);
        repository.save(followedUser);
    }

    public Set<UserDto> findFollowers(Integer userId) {
        User user = repository.findById(userId).orElseThrow();
        Set<User> followers = user.getFollowers();
        return mapUserEntity2UserDto(followers);
    }
    public Set<UserDto> findFollowings(Integer userId) {
        User user = repository.findById(userId).orElseThrow();
        Set<User> followings = user.getFollowings();
        return mapUserEntity2UserDto(followings);
    }

}

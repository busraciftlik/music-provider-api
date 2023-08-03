package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.UserDto;
import com.atmosware.busraciftlik.music.provider.entity.User;
import com.atmosware.busraciftlik.music.provider.exception.BusinessException;
import com.atmosware.busraciftlik.music.provider.repository.UserRepository;
import com.atmosware.busraciftlik.music.provider.service.UserService;
import com.atmosware.busraciftlik.music.provider.service.security.JwtService;
import com.atmosware.busraciftlik.music.provider.util.constant.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.mapUserEntity2UserDto;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository repository;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(Message.User.NOT_EXISTS));
    }

    @Override
    @Cacheable("user_list")
    public List<UserDto> findAll() {
        return mapUserEntity2UserDto(repository.findAll());
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
    }

    public void follow(Integer followed) {
        // TODO: 31.07.2023
        final User follower = jwtService.extractUserDetailsFromContext();
        final User user = repository.findById(follower.getId()).get();
        User followedUser = repository.findById(followed).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
        user.addFollowing(followedUser);
        repository.save(user);
    }

    public void unfollow(Integer followed) {
        final User follower = jwtService.extractUserDetailsFromContext();
        final User user = repository.findById(follower.getId()).get();
        final User followedUser = repository.findById(followed).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
        final Set<User> followings = user.getFollowings();
        if (followings.contains(followedUser)) {
            followings.remove(followedUser);
            repository.save(user);
        }
        repository.save(followedUser);
    }

    public List<UserDto> findFollowers() {
        final User authenticatedUser = jwtService.extractUserDetailsFromContext();
        User user = repository.findById(authenticatedUser.getId()).get();
        Set<User> followers = user.getFollowers();
        return mapUserEntity2UserDto(followers);
    }

    public List<UserDto> findFollowings() {
        final User authenticatedUser = jwtService.extractUserDetailsFromContext();
        final User user = repository.findById(authenticatedUser.getId()).get();
        Set<User> followings = user.getFollowings();
        return mapUserEntity2UserDto(followings);
    }
}

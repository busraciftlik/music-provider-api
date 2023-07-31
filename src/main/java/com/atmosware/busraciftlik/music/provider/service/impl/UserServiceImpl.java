package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.UserDto;
import com.atmosware.busraciftlik.music.provider.entity.User;
import com.atmosware.busraciftlik.music.provider.exception.BusinessException;
import com.atmosware.busraciftlik.music.provider.repository.UserRepository;
import com.atmosware.busraciftlik.music.provider.service.UserService;
import com.atmosware.busraciftlik.music.provider.service.security.JwtService;
import com.atmosware.busraciftlik.music.provider.util.constant.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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



    public void unfollow(Integer follower, Integer followed) {
        User followedUser = repository.findById(followed).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
        User followerUser = repository.findById(follower).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
        Set<User> followers = followedUser.getFollowers();
        Set<User> followings = followerUser.getFollowings();
        followers.remove(followerUser);
        followings.remove(followedUser);
        repository.save(followedUser);
        repository.save(followerUser);

    }

    public List<UserDto> findFollowers(Integer userId) {
        User user = repository.findById(userId).orElseThrow();
        Set<User> followers = user.getFollowers();
        return mapUserEntity2UserDto(followers);
    }

    public List<UserDto> findFollowings(Integer userId) {
        User user = repository.findById(userId).orElseThrow();
        Set<User> followings = user.getFollowings();
        return mapUserEntity2UserDto(followings);
    }

}

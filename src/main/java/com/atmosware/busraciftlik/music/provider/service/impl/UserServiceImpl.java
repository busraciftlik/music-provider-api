package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.UserDto;
import com.atmosware.busraciftlik.music.provider.entity.User;
import com.atmosware.busraciftlik.music.provider.exception.BusinessException;
import com.atmosware.busraciftlik.music.provider.repository.UserRepository;
import com.atmosware.busraciftlik.music.provider.service.UserService;
import com.atmosware.busraciftlik.music.provider.util.constant.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.mapUserEntity2UserDto;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(Message.User.NOT_EXISTS));
    }

    public Set<UserDto> findAll(){
        return mapUserEntity2UserDto(repository.findAll());
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
    }

    public void follow(Integer followed) {
        User followedUser = repository.findById(followed).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
        followedUser.addFollowing(followedUser);
        repository.save(followedUser);
    }

    public void unfollow(Integer follower,Integer followed){
        User followedUser = repository.findById(followed).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
        User followerUser = repository.findById(follower).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
        Set<User> followers = followedUser.getFollowers();
        Set<User> followings = followerUser.getFollowings();
        followers.remove(followerUser);
        followings.remove(followedUser);
        repository.save(followedUser);
        repository.save(followerUser);

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

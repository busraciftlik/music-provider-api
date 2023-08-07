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

    /**
     * Follows the user with the specified user ID by the current user
     * @param followed The user ID of the user to be followed
     * @throws BusinessException If the user with the given ID does not exist
     */
    public void follow(Integer followed) {
        final User currentUser = jwtService.extractUserDetailsFromContext();
        final User user = repository.findById(currentUser.getId()).orElseThrow();
        User followedUser = repository.findById(followed).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
        user.addFollowing(followedUser);
        repository.save(user);
    }

    /**
     * Unfollows the user with the specified user ID by the current user
     * @param followed - The user ID of the user to be unfollowed
     * @throws BusinessException If the current user or the user to be unfollowed does not exist,
     * or if the user is not being followed by the current user.
     */
    public void unfollow(Integer followed) {
        final User currentUser = jwtService.extractUserDetailsFromContext();
        final User user = repository.findById(currentUser.getId()).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
        final User followedUser = repository.findById(followed).orElseThrow(() -> new BusinessException(Message.User.NOT_EXISTS));
        final Set<User> followings = user.getFollowings();
        if (!followings.contains(followedUser)) {
            throw new BusinessException(Message.User.NOT_EXISTS);
        }
        followings.remove(followedUser);
        repository.save(user);
        final Set<User> followers = followedUser.getFollowers();
        followers.remove(user);
        repository.save(followedUser);
    }

    /**
     * Finds and returns the followers of the current user
     * @return A list of {@link UserDto } objects representing the followers of the current user.
     * If there are no followers, an empty list is returned.
     */
    public List<UserDto> findFollowers() {
        final User currentUser = jwtService.extractUserDetailsFromContext();
        User user = repository.findById(currentUser.getId()).orElseThrow();
        Set<User> followers = user.getFollowers();
        return mapUserEntity2UserDto(followers);
    }

    /**
     * Finds and returns the followings of the current user
     * A list of {@link UserDto } objects representing the followings of the current user.
     * If there are no followers, an empty list is returned.
     */
    public List<UserDto> findFollowings() {
        final User currentUser = jwtService.extractUserDetailsFromContext();
        final User user = repository.findById(currentUser.getId()).orElseThrow();
        Set<User> followings = user.getFollowings();
        return mapUserEntity2UserDto(followings);
    }
}

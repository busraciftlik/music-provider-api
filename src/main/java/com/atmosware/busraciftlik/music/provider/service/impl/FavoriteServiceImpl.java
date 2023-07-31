package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.FavoriteDto;
import com.atmosware.busraciftlik.music.provider.entity.Favorite;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.entity.User;
import com.atmosware.busraciftlik.music.provider.repository.FavoriteRepository;
import com.atmosware.busraciftlik.music.provider.service.FavoriteService;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import com.atmosware.busraciftlik.music.provider.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository repository;
    private final MusicService musicService;
    private final UserService userService;


    @Override
    public FavoriteDto likeMusic(Integer userId, Integer musicId) {
        Music music = musicService.findById(musicId);
        User user = userService.findById(userId);
        Set<Favorite> favorites = user.getFavorites();



        return null;
    }

    @Override
    public FavoriteDto unlikeMusic(Integer userId, Integer musicId) {
        return null;
    }

    @Override
    public int getLikeCount(Integer musicId) {
        return 0;
    }

    @Override
    public boolean hasUserLikedMusic(Integer userId, Integer musicId) {
        return false;
    }
}

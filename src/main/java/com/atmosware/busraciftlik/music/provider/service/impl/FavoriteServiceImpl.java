package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.UserMusicLikeDto;
import com.atmosware.busraciftlik.music.provider.repository.FavoriteRepository;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import com.atmosware.busraciftlik.music.provider.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository repository;
    private final MusicService musicService;


    @Override
    public UserMusicLikeDto likeMusic(Integer userId, Integer musicId) {
        return null;
    }

    @Override
    public UserMusicLikeDto unlikeMusic(Integer userId, Integer musicId) {
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

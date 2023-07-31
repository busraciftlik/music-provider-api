package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.FavoriteDto;


public interface FavoriteService {
    FavoriteDto likeMusic(Integer userId, Integer musicId);

    FavoriteDto unlikeMusic(Integer userId, Integer musicId);

    int getLikeCount(Integer musicId);

    boolean hasUserLikedMusic(Integer userId, Integer musicId);
}

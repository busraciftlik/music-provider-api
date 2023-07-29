package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.UserMusicLikeDto;


public interface FavoriteService {
    UserMusicLikeDto likeMusic(Integer userId,Integer musicId);

    UserMusicLikeDto unlikeMusic(Integer userId, Integer musicId);

    int getLikeCount(Integer musicId);

    boolean hasUserLikedMusic(Integer userId, Integer musicId);
}

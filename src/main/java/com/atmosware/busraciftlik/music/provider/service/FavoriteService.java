package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.FavoriteDto;


public interface FavoriteService {
    FavoriteDto likeMusic(Integer musicId);

    FavoriteDto unlikeMusic(Integer musicId);

    int getLikeCount(Integer musicId);

}

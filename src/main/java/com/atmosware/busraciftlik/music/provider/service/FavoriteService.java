package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.FavoriteDto;

import java.util.List;


public interface FavoriteService {
    List<FavoriteDto> findAll();
    FavoriteDto likeMusic(Integer musicId);

    FavoriteDto unlikeMusic(Integer musicId);

    int getLikeCount(Integer musicId);

}

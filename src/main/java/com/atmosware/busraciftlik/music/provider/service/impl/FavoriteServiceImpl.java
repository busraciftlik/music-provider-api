package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.FavoriteDto;
import com.atmosware.busraciftlik.music.provider.entity.Favorite;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.entity.User;
import com.atmosware.busraciftlik.music.provider.repository.FavoriteRepository;
import com.atmosware.busraciftlik.music.provider.service.FavoriteService;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import com.atmosware.busraciftlik.music.provider.service.UserService;
import com.atmosware.busraciftlik.music.provider.service.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.*;


@Service
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository repository;
    private final MusicService musicService;
    private final JwtService jwtService;
    private final UserService userService;


    @Override
    public FavoriteDto likeMusic(Integer musicId) {
        final User user = jwtService.extractUserDetailsFromContext();
        Music music = musicService.findById(musicId);
        final Favorite favorite = Favorite.builder()
                .user(user)
                .music(music)
                .build();
        repository.save(favorite);
        return mapFavoriteEntity2FavoriteDto(favorite);
    }

    @Override
    public FavoriteDto unlikeMusic(Integer musicId) {
        final User user = jwtService.extractUserDetailsFromContext();
        final Favorite favorite = repository.findByUserIdAndMusicId(user.getId(), musicId).orElseThrow();
        repository.delete(favorite);
        return mapFavoriteEntity2FavoriteDto(favorite);
    }

    @Override
    public int getLikeCount(Integer musicId) {
        return repository.countFavoriteByMusicId(musicId);
    }
}
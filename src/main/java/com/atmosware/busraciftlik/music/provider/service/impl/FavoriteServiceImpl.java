package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.FavoriteDto;
import com.atmosware.busraciftlik.music.provider.entity.Favorite;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.entity.User;
import com.atmosware.busraciftlik.music.provider.repository.FavoriteRepository;
import com.atmosware.busraciftlik.music.provider.service.FavoriteService;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
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


    /**
     * This method saves the user who liked the music with the given music ID
     * and adds this like information to the database
     * @param musicId The ID of the liked music.
     * @return The {@link FavoriteDto} object containing the like information for the liked music.
     */
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

    /**
     * Unlikes the music with the specified musicId for the currently authenticated user.
     * @param musicId The ID of the music to be unliked.
     * @return The {@link FavoriteDto} object representing the unliked music's like information.
     */
    @Override
    public FavoriteDto unlikeMusic(Integer musicId) {
        final User user = jwtService.extractUserDetailsFromContext();
        final Favorite favorite = repository.findByUserIdAndMusicId(user.getId(), musicId).orElseThrow();
        repository.delete(favorite);
        return mapFavoriteEntity2FavoriteDto(favorite);
    }

    /**
     Returns the number of likes for the music with the specified musicId.
     @param musicId The ID of the music to get the like count for.
     @return The number of likes for the music with the given musicId
     */
    @Override
    public int getLikeCount(Integer musicId) {
        return repository.countFavoriteByMusicId(musicId);
    }
}
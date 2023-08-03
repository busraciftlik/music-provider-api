package com.atmosware.busraciftlik.music.provider.repository;

import com.atmosware.busraciftlik.music.provider.entity.Favorite;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Music> findByUserId(Integer userId);

    Integer countFavoriteByMusicId(Integer musicId);

    Optional<Favorite> findByUserIdAndMusicId(Integer userId, Integer musicId);

    List<User> findUsersByMusicId(Integer musicId);
}

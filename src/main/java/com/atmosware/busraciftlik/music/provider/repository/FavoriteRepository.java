package com.atmosware.busraciftlik.music.provider.repository;

import com.atmosware.busraciftlik.music.provider.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    Integer countFavoriteByMusicId(Integer musicId);

    Optional<Favorite> findByUserIdAndMusicId(Integer userId, Integer musicId);

}

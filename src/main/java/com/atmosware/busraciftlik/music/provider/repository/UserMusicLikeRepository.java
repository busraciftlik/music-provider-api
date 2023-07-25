package com.atmosware.busraciftlik.music.provider.repository;

import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.entity.UserMusicLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMusicLikeRepository extends JpaRepository<UserMusicLike,Integer> {
    List<Music> findByUser(Integer user);
    Integer countByMusic(Music music);

}

package com.atmosware.busraciftlik.music.provider.repository;

import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface MusicRepository extends BaseRepository<Music, Integer> {
    Set<Music> findAllByIdInAndStatus(List<Integer> ids,Status status);
}

package com.atmosware.busraciftlik.music.provider.repository;

import com.atmosware.busraciftlik.music.provider.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
}

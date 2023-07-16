package com.atmosware.busraciftlik.music.provider.repository;

import com.atmosware.busraciftlik.music.provider.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MusicRepository extends JpaRepository<Music, UUID> {
}
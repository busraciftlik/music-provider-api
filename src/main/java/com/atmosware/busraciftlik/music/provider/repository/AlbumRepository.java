package com.atmosware.busraciftlik.music.provider.repository;

import com.atmosware.busraciftlik.music.provider.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
}

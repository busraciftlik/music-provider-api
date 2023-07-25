package com.atmosware.busraciftlik.music.provider.service;


import com.atmosware.busraciftlik.music.provider.dto.PlaylistDto;
import com.atmosware.busraciftlik.music.provider.entity.Playlist;

import java.util.Set;

public interface PlaylistService {
    Set<PlaylistDto> findAll();
    Playlist findById(Integer id);
    Playlist add(Playlist playlist);

    Playlist update(Playlist playlist);

    Playlist delete(Integer id);
}

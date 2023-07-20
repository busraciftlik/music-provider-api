package com.atmosware.busraciftlik.music.provider.service;


import com.atmosware.busraciftlik.music.provider.entity.Playlist;

import java.util.List;

public interface PlaylistService {
    List<Playlist> findAll();

    Playlist add(Playlist playlist);

    Playlist update(Playlist playlist);

    Playlist delete(Integer id);
}

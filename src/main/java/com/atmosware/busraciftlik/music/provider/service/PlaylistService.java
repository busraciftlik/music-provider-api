package com.atmosware.busraciftlik.music.provider.service;


import com.atmosware.busraciftlik.music.provider.dto.PlaylistDto;
import com.atmosware.busraciftlik.music.provider.dto.request.PlaylistRequest;
import com.atmosware.busraciftlik.music.provider.entity.Playlist;

import java.util.Set;

public interface PlaylistService {
    Set<PlaylistDto> findAll();
    Playlist findById(Integer id);
    PlaylistDto add(PlaylistRequest request);

    PlaylistDto update(Integer id,PlaylistRequest request);

    Playlist delete(Integer id);

}

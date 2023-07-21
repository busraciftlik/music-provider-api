package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Music;

import java.util.List;

public interface MusicService {
    List<MusicDto> findAll();

    Music add(Music request);

    Music update(Music music);

    Music delete(Integer id);

    //TODO: 20.07.2023
    // AlbumDto addAlbum(Integer musicId, Album album);

}

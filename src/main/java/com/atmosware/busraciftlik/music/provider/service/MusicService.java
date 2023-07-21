package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateMusicRequest;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Music;

import java.util.Set;

public interface MusicService {
    Set<MusicDto> findAll();

    MusicDto add(CreateMusicRequest request);

    Music update(Music music);

    Music delete(Integer id);

    Set<MusicDto> addAlbum(Integer musicId, Album album);

}

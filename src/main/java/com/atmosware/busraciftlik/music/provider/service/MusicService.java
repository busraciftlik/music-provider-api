package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateMusicRequest;
import com.atmosware.busraciftlik.music.provider.entity.Music;

import java.util.List;

public interface MusicService {
    List<MusicDto> findAll();

    Music add(Music request);

    Music update(Music music);

    Music delete(Integer id);

}

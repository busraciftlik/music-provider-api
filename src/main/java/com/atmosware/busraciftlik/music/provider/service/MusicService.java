package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.entity.Music;

import java.util.List;

public interface MusicService {
    List<Music> findAll();

    Music add(Music music);

    Music update(Music music);

    Music delete(Integer id);

}

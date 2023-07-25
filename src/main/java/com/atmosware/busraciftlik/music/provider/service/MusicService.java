package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateMusicRequest;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.enums.Genre;

import java.util.List;
import java.util.Set;

public interface MusicService {
    Set<MusicDto> findAll();

    Set<Music> findAllByIds(List<Integer> ids);

    MusicDto add(CreateMusicRequest request);

    Music update(Music music); // id ekle

    Music delete(Integer id);

    Set<MusicDto> searchMusicsByAlbum(String albumName);

    Set<MusicDto> searchMusicsByArtist(String artisName);

    Set<MusicDto> searchMusicsByGenre(Genre genre);

}

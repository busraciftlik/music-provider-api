package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateMusicRequest;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.enums.Genre;

import java.util.List;
import java.util.Set;

public interface MusicService {
    Set<MusicDto> findAll();

    Set<Music> findAllByIdsAndArtistId(List<Integer> ids, Integer artistId);

    Set<Music> findAllByIds(List<Integer> ids);

    Music findById(Integer id);

    MusicDto add(CreateMusicRequest request);

    Music update(Integer id, Music music);

    Music delete(Integer id);

    Set<MusicDto> searchMusicsByAlbum(String albumName);

    Set<MusicDto> searchMusicsByArtist(String artisName);

    Set<MusicDto> searchMusicsByGenre(Genre genre);

}

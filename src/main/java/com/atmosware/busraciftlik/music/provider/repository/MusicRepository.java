package com.atmosware.busraciftlik.music.provider.repository;

import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.enums.Genre;
import com.atmosware.busraciftlik.music.provider.enums.Status;

import java.util.List;
import java.util.Set;


public interface MusicRepository extends BaseRepository<Music, Integer> {
    Set<Music> findAllByIdInAndStatus(List<Integer> ids,Status status);
    Set<Music> findAllMusicsByAlbumName(String albumName);
    Set<Music> findMusicByArtistName(String artistName);
    Set<Music> findMusicByGenre(Genre genre);
}

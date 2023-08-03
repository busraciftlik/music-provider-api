package com.atmosware.busraciftlik.music.provider.repository;

import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.enums.Genre;
import com.atmosware.busraciftlik.music.provider.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface MusicRepository extends JpaRepository<Music, Integer> {
    Set<Music> findAllByIdInAndArtistIdAndStatus(List<Integer> ids,Integer artistId,Status status);
    //select * from musics where id in (1,2) and Status = 'ACTIVE'
    Set<Music> findMusicByAlbumName(String albumName);
    Set<Music> findMusicByArtistName(String artistName);
    Set<Music> findMusicByGenre(Genre genre);

    Set<Music> findAllByIdInAndStatus(List<Integer> ids,Status status);
}

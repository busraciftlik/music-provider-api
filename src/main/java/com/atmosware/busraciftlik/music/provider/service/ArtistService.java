package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.request.UpdateArtistNameRequest;
import com.atmosware.busraciftlik.music.provider.dto.response.ArtistDto;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;

import java.util.List;
import java.util.Set;

public interface ArtistService  {
    List<Artist> findAll();

    Artist add(Artist artist);

    Artist update(Artist artist);

    Artist delete(Integer id);
    Set<Music> addMusic(Integer artistId, Music music);
    Set<Album> addAlbum(Integer artistId, Album album);

    ArtistDto updateArtistName(Integer id,UpdateArtistNameRequest request);

}

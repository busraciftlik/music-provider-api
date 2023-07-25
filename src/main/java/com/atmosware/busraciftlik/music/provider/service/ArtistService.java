package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.ArtistDto;
import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.AlbumRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateArtistRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.UpdateArtistNameRequest;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;

import java.util.Set;

public interface ArtistService  {

    long count();
    Set<ArtistDto> findAll();
    Artist getById(Integer id);
    ArtistDto add(CreateArtistRequest request);

    ArtistDto update(Integer id, Artist artist);

    ArtistDto delete(Integer id);
    Set<MusicDto> addMusic(Integer artistId, Music music);
    Set<AlbumDto> addAlbum(Integer artistId, AlbumRequest album);

    ArtistDto updateArtistName(Integer id, UpdateArtistNameRequest request);

}

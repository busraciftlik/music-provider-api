package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;

import java.util.Set;

public interface ArtistService extends CrudService<Artist> {
    Set<Music> addMusic(Integer artistId, Music music);
    Set<Album> addAlbum(Integer artistId, Album album);
}

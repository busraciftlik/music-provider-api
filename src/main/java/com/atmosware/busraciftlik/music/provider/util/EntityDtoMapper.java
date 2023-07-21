package com.atmosware.busraciftlik.music.provider.util;

import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.ArtistDto;
import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class EntityDtoMapper {

    public static MusicDto mapMusicEntity2MusicDto(Music music){
        return Optional.ofNullable(music).map(m -> MusicDto.builder()
                .id(music.getId())
                .name(music.getName())
                .genre(music.getGenre())
                .artistName(music.getArtist().getName())
                .albumName(Optional.of(music.getAlbum()).map(Album::getName).orElse(null))
                .build()).orElse(null);
    }

    public static Set<MusicDto> mapMusicEntity2MusicDto(Collection<Music> musics){
        return Optional.ofNullable(musics)
                .map(m -> m.stream().map(EntityDtoMapper::mapMusicEntity2MusicDto).collect(Collectors.toSet()))
                .orElse(null);
    }

    public static AlbumDto mapAlbumEntity2AlbumDto(final Album album){
        //Optional<Album> maybeAlbum = Optional.of(album);
        return Optional.ofNullable(album).map(a -> AlbumDto.builder()
                .id(a.getId())
                .name(a.getName())
                .artistName(a.getArtist().getName())
                .musics(mapMusicEntity2MusicDto(a.getMusics()))
                .build()).orElse(null);
    }

    public static Set<AlbumDto> mapAlbumEntity2AlbumDto(Collection<Album> albums){
        return Optional.ofNullable(albums).map(a -> a.stream().map(EntityDtoMapper::mapAlbumEntity2AlbumDto).collect(Collectors.toSet()))
                .orElse(null);
    }

    public static ArtistDto mapArtistEntity2ArtistDto(Artist artist){
        return Optional.ofNullable(artist).map(a -> ArtistDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .albums(mapAlbumEntity2AlbumDto(artist.getAlbums()))
                .musics(mapMusicEntity2MusicDto(artist.getMusics()))
                .build()).orElse(null);
    }

    public static Set<ArtistDto> mapArtistEntity2ArtistDto(Collection<Artist> artists){
        return Optional.ofNullable(artists)
                .map(a -> a.stream().map(EntityDtoMapper::mapArtistEntity2ArtistDto).collect(Collectors.toSet()))
                .orElse(null);
    }
}
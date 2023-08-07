package com.atmosware.busraciftlik.music.provider.util;

import com.atmosware.busraciftlik.music.provider.dto.*;
import com.atmosware.busraciftlik.music.provider.entity.*;

import java.util.*;
import java.util.stream.Collectors;

public final class EntityDtoMapper {
    private EntityDtoMapper() {

    }
    public static MusicDto mapMusicEntity2MusicDto(Music music) {
        return Optional.ofNullable(music).map(m -> MusicDto.builder()
                .id(music.getId())
                .name(music.getName())
                .genre(music.getGenre())
                .artistName(music.getArtist().getName())
                .albumName(Optional.ofNullable(music.getAlbum()).map(Album::getName).orElse(null))
                .build()).orElse(null);
    }

    public static Set<MusicDto> mapMusicEntity2MusicDto(Collection<Music> musics) {
        return Optional.ofNullable(musics)
                .map(m -> m.stream().map(EntityDtoMapper::mapMusicEntity2MusicDto).collect(Collectors.toSet()))
                .orElse(null);
    }

    public static AlbumDto mapAlbumEntity2AlbumDto(final Album album) {
        //Optional<Album> maybeAlbum = Optional.of(album);
        return Optional.ofNullable(album).map(a -> AlbumDto.builder()
                .id(a.getId())
                .name(a.getName())
                .releaseDate(a.getReleaseDate())
                .artistName(a.getArtist().getName())
                .musics(mapMusicEntity2MusicDto(a.getMusics()))
                .build()).orElse(null);
    }

    public static Set<AlbumDto> mapAlbumEntity2AlbumDto(Collection<Album> albums) {
        return Optional.ofNullable(albums).map(a -> a.stream().map(EntityDtoMapper::mapAlbumEntity2AlbumDto).collect(Collectors.toSet()))
                .orElse(null);
    }

    public static ArtistDto mapArtistEntity2ArtistDto(Artist artist) {
        return Optional.ofNullable(artist).map(a -> ArtistDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .albums(mapAlbumEntity2AlbumDto(artist.getAlbums()))
                .musics(mapMusicEntity2MusicDto(artist.getMusics()))
                .build()).orElse(null);
    }

    public static Set<ArtistDto> mapArtistEntity2ArtistDto(Collection<Artist> artists) {
        return Optional.ofNullable(artists)
                .map(a -> a.stream().map(EntityDtoMapper::mapArtistEntity2ArtistDto).collect(Collectors.toSet()))
                .orElse(null);
    }

    public static PlaylistDto mapPlaylistEntity2PlaylistDto(Playlist playlist) {
        return Optional.ofNullable(playlist).map(p -> PlaylistDto.builder()
                .id(playlist.getId())
                .musics(mapMusicEntity2MusicDto(playlist.getMusics()))
                .build()).orElse(null);
    }

    public static Set<PlaylistDto> mapPlaylistEntity2PlaylistDto(Collection<Playlist> playlists) {
        return Optional.ofNullable(playlists).map(p -> p.stream().map(EntityDtoMapper::mapPlaylistEntity2PlaylistDto).collect(Collectors.toSet()))
                .orElse(null);
    }

    public static UserDto mapUserEntity2UserDto(User user) {
        return Optional.ofNullable(user).map(u -> UserDto.builder()
                .id(u.getId())
                .firstName(u.getFirstname())
                .lastName(u.getLastname())
                .build()).orElse(null);
    }

    public static List<UserDto> mapUserEntity2UserDto(Collection<User> users) {
        return Optional.ofNullable(users).map(userCollection -> userCollection.stream().map(EntityDtoMapper::mapUserEntity2UserDto).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public static FavoriteDto mapFavoriteEntity2FavoriteDto(Favorite favorite) {
        return Optional.ofNullable(favorite).map(f -> FavoriteDto.builder()
                .id(f.getId())
                .userId(f.getUser().getId())
                .musicId(f.getMusic().getId()).build()).orElse(null);
    }

    public static List<FavoriteDto> mapFavoriteEntity2FavoriteDto(Collection<Favorite> favorites){
        return Optional.ofNullable(favorites).map(f -> f.stream().map(EntityDtoMapper::mapFavoriteEntity2FavoriteDto).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
package com.atmosware.busraciftlik.music.provider.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.Set;

@Builder
public record ArtistDto (Integer id, String name, Set<MusicDto> musics, Set<AlbumDto> albums) implements Serializable {
}

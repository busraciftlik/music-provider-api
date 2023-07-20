package com.atmosware.busraciftlik.music.provider.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Set;

@Builder
public record ArtistDto (Integer id, String name, Set<String>musics, Set<AlbumDto> albums) {
}

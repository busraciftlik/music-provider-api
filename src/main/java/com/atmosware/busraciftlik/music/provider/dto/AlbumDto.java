package com.atmosware.busraciftlik.music.provider.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;
@Builder
public record AlbumDto (Integer id, String name, String artistName, Set<MusicDto> musics , LocalDate releaseDate){
}

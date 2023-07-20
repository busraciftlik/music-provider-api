package com.atmosware.busraciftlik.music.provider.dto;

import lombok.Builder;

import java.util.Set;
@Builder
public record AlbumDto (Integer id, String name, String artistName, Set<String> musics){
}

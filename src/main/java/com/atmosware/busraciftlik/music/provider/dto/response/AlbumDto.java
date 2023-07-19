package com.atmosware.busraciftlik.music.provider.dto.response;

import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
public record AlbumDto(Integer id, String name, String artistName, Set<String> musicSet) {

}

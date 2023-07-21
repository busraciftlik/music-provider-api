package com.atmosware.busraciftlik.music.provider.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record PlaylistDto(Integer id, String name, Set<MusicDto> musics) {

}

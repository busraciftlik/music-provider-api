package com.atmosware.busraciftlik.music.provider.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.Set;

@Builder
public record PlaylistDto(Integer id, Set<MusicDto> musics) implements Serializable {

}
